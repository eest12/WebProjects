package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckOutLibraryItem")
public class CheckOutLibraryItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckOutLibraryItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null && request.getParameter("available").equals("true")) {
			response.setContentType("text/html");
			SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
			request.setAttribute("date", dateFormat.format(new Date()));
			request.getRequestDispatcher("/WEB-INF/CheckOutLibraryItem.jsp").forward(request, response);
		} else {
			response.sendRedirect("LibraryLogin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemID = request.getParameter("id");
		String itemName = new String();
		boolean available = false;
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			
			String sql1 = "select name, available from library where id = ?;";
			PreparedStatement pstmt = c.prepareStatement(sql1);
			pstmt.setString(1, request.getParameter("id"));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				itemName = rs.getString("name");
				available = rs.getBoolean("available");
			}
			
			if (available) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String borrowed = dateFormat.format(new Date());
					String dueParam = request.getParameter("due");
					String due = null;
					if (dueParam != null && dueParam.trim().length() != 0) {
						SimpleDateFormat dueInputFormat = new SimpleDateFormat("M/d/yyyy");
						Date dueDate = dueInputFormat.parse(request.getParameter("due"));
						due = dateFormat.format(dueDate);
					}
					
					String sql2 = "insert into lib_checkout (cin, name, borrowed, due, item_id) values (?, ?, ?, ?, ?);";
					pstmt = c.prepareStatement(sql2);
					pstmt.setString(1, request.getParameter("cin"));
					pstmt.setString(2, request.getParameter("name"));
					pstmt.setString(3, borrowed);
					pstmt.setString(4, due);
					pstmt.setString(5, itemID);
					pstmt.executeUpdate();
					
					String sql3 = "update library set available = false where id = ?;";
					pstmt = c.prepareStatement(sql3);
					pstmt.setString(1, itemID);
					pstmt.executeUpdate();
					available = false;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			c.close();
		}
		catch (SQLException e) {
			throw new ServletException(e);
		}
		finally {
			try {
				if (c != null) {
					c.close();
				}
			}
			catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		response.sendRedirect("CheckoutLog?id=" + itemID + "&name=" + itemName + "&available=" + available);
	}

}
