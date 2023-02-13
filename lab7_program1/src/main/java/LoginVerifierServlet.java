

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginVerifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/lab06","root","");
		    String n = request.getParameter("name");
		    String p = request.getParameter("pw");
		    
		    PreparedStatement ps = con.prepareStatement("select password from login where uname=?");
		    ps.setString(1, n);
		   
		    boolean login =  true;
		    RequestDispatcher rd;
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	if((rs.getString("password")).equals(p)) {
		    		
		    		HttpSession session = request.getSession();
		    		session.setAttribute("username", n);
		    		out.println("<font color=green size=5>Welcome "+n+" !");
		    		
		    		rd = request.getRequestDispatcher("first.jsp");
		    		out.println("<br><a href=first.jsp>First.jsp</a>");
		    		out.println("<br><a href=second.jsp>Second.jsp</a>");
		    		login = false;
		    	}
		    }
		    if (login){
		       out.println("ERROR!!");
		       out.println("Try to login again");
		       rd = request.getRequestDispatcher("login.html");
		       rd.include(request, response);
		    }  
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
