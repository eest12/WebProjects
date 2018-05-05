package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw5.model.CheckoutEntry;

@WebServlet("/CheckoutLog")
public class CheckoutLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckoutLog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ArrayList<CheckoutEntry> checkoutLog = new ArrayList<CheckoutEntry>();
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			String sql = "select id, cin, name, date_format(borrowed, '%c/%e/%Y') as borrowed,"
					+ " date_format(due, '%c/%e/%Y') as due, date_format(returned, '%c/%e/%Y') as returned"
					+ " from lib_checkout where item_id = ? order by id desc;";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("id"));
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				checkoutLog.add(new CheckoutEntry(rs.getInt("id"), rs.getInt("cin"), rs.getString("name"),
						rs.getString("borrowed"), rs.getString("due"), rs.getString("returned")));
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
		
		request.setAttribute("checkoutLog", checkoutLog);
		request.getRequestDispatcher("/WEB-INF/CheckoutLog.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
