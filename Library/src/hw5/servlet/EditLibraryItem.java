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

import hw5.model.LibraryItem;

@WebServlet("/EditLibraryItem")
public class EditLibraryItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditLibraryItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.setContentType("text/html");
	        
	        LibraryItem selectedItem = new LibraryItem();
	        Connection c = null;
			
			try {
				// url, username, and password declarations removed for security
				
				c = DriverManager.getConnection(url, username, password);
				String sql = "select * from library where id = ?;";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("id"));
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					selectedItem = new LibraryItem(rs.getInt("id"), rs.getString("type"), rs.getString("name"),
							rs.getString("info"), rs.getBoolean("available"));
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
			
	        request.setAttribute("select", selectedItem);
			request.getRequestDispatcher("/WEB-INF/EditLibraryItem.jsp").forward(request, response);
		} else {
			response.sendRedirect("LibraryLogin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			String sql = "update library set type = ?, name = ?, info = ? where id = ?;";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("type"));
			pstmt.setString(2, request.getParameter("name"));
			pstmt.setString(3, request.getParameter("info"));
			pstmt.setString(4, request.getParameter("id"));
			pstmt.executeUpdate();
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
