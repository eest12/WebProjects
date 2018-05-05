package hw5.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw5.model.LibraryItem;

@WebServlet(urlPatterns = "/Library", loadOnStartup = 1)
public class Library extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Library() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		List<LibraryItem> libItems = new ArrayList<LibraryItem>();
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
			
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from library;");
			
			while (rs.next()) {
				libItems.add(new LibraryItem(rs.getInt("id"), rs.getString("type"), rs.getString("name"),
						rs.getString("info"), rs.getBoolean("available")));
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
		
		request.setAttribute("libItems", libItems);
		request.getRequestDispatcher("/WEB-INF/Library.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
