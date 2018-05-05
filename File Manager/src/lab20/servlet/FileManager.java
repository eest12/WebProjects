package lab20.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import lab20.model.File;

@WebServlet("/FileManager")
public class FileManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileManager() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<File> files = new ArrayList<File>();
		int parentID = 0;
		String parentName = new String();
		Connection c = null;
		
		try {
			// url, username, and password declarations removed for security
            
            // RETRIEVE PARENT ID AND NAME
			
			c = DriverManager.getConnection(url, username, password);
	    		String sql1 = "select f1.id, f1.name from files f1 inner join files f2 on f1.id = f2.parent_id where f2.id = ?";
	    		PreparedStatement pstmt = c.prepareStatement(sql1);
	        pstmt.setString(1, request.getParameter("id"));
	        ResultSet rs1 = pstmt.executeQuery();
	        
	        if (rs1.next()) {
                parentID = rs1.getInt("id");
                parentName = rs1.getString("name");
            }
	        
			c.close();
            
            // RETRIEVE FOLDER CONTENTS
            
            c = DriverManager.getConnection(url, username, password);
            Statement stmt = c.createStatement();
            String sql = new String();
            
            if (request.getParameter("id") == null || request.getParameter("id").equals("0")) {
            		sql = "select f.* from files f inner join users u on f.owner_id = u.id "
                    		+ "where u.name = 'cysun' and parent_id is null "
                    		+ "order by f.is_folder desc;";
            } else {
            		sql = "select * from files where parent_id = " + request.getParameter("id") + ";";
            }
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                files.add(new File(rs.getInt("id"), rs.getString("name"), rs.getBoolean("is_folder"),
                		rs.getInt("parent_id"), rs.getInt("owner_id")));
            }
            
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
		
		request.setAttribute("files", files);
		request.setAttribute("filesSize", files.size());
		request.setAttribute("parentID", parentID);
		request.setAttribute("parentName", parentName);
        request.getRequestDispatcher("/WEB-INF/FileManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
