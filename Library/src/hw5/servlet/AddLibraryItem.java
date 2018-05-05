package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddLibraryItem")
public class AddLibraryItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddLibraryItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.setContentType("text/html");
			request.getRequestDispatcher("/WEB-INF/AddLibraryItem.jsp").forward(request, response);
		} else {
			response.sendRedirect("LibraryLogin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			
			int copies = Integer.parseInt(request.getParameter("copies"));
			for (int i = 0; i < copies; i++) {
				String sql = "insert into library (type, name, info) values (?, ?, ?);";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("type"));
				pstmt.setString(2, request.getParameter("name"));
				pstmt.setString(3,  request.getParameter("info"));
				pstmt.executeUpdate();
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
		
		response.sendRedirect("Library");
	}

}
