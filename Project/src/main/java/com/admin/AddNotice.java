package com.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
/**
 * Servlet implementation class AddNotice
 */
public class AddNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddNotice() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		 try {
			 	Class.forName("com.mysql.jdbc.Driver");
			 	Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			 	Statement st=c.createStatement();
			 	
			 	String notice=request.getParameter("notice");
			 	String target=request.getParameter("target");
		  
			 	st.executeUpdate("update  notice set notice=\""+notice+"\" where target=\""+target+"\";");
			 	c.close(); 
			 	RequestDispatcher rd=request.getRequestDispatcher("Admin_home.jsp");
			 	rd.forward(request, response);
		  
		 }catch(Exception e) {System.out.println(e);}
	}

}
