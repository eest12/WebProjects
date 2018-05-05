package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReturnLibraryItem")
public class ReturnLibraryItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReturnLibraryItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			String checkoutID = request.getParameter("checkoutID");
			String itemID = request.getParameter("itemID");
			String itemName = new String();
			boolean available = true;
			
			Connection c = null;
			
			try {
				// url, username, and password declarations removed for security
				
				c = DriverManager.getConnection(url, username, password);
				
				String sql1 = "select name, available from library where id = ?;";
				PreparedStatement pstmt = c.prepareStatement(sql1);
				pstmt.setString(1, itemID);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					itemName = rs.getString("name");
					available = rs.getBoolean("available");
				}
				
				if (!available) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String sql2 = "update lib_checkout set returned = ? where id = ?;";
					pstmt = c.prepareStatement(sql2);
					pstmt.setString(1, dateFormat.format(new Date()));
					pstmt.setString(2, checkoutID);
					pstmt.executeUpdate();
					
					String sql3 = "update library set available = true where id = ?;";
					pstmt = c.prepareStatement(sql3);
					pstmt.setString(1, itemID);
					pstmt.executeUpdate();
					available = true;
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
		} else {
			response.sendRedirect("LibraryLogin");
		}
	}

}
