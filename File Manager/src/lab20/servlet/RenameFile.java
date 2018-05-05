package lab20.servlet;

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

@WebServlet("/RenameFile")
public class RenameFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RenameFile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/RenameFile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security

            c = DriverManager.getConnection(url, username, password);
        		String sql = "update files set name = ? where id = ?";
        		PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("name"));
            pstmt.setString(2,  request.getParameter("id"));
            pstmt.executeUpdate();
			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
                if (c != null) {
                		c.close();
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
		}
		
		response.sendRedirect("FileManager?id=" + request.getParameter("parentID") + "&name=" + request.getParameter("parentName"));
	}

}
