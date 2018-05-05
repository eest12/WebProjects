package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LibraryLogin")
public class LibraryLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibraryLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("/WEB-INF/LibraryLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			String sql = "select id from lib_users where username = ? and password = ?;";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("user"));
			pstmt.setString(2, request.getParameter("password"));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", request.getParameter("user"));
				response.sendRedirect("Library");
			} else {
				response.sendRedirect("LibraryLogin");
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
	}

}
