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

@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security

            c = DriverManager.getConnection(url, username, password);
        		String sql = "delete from files where id = ?;";
        		PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("id"));
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
