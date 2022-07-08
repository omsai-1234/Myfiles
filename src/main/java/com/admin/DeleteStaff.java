package com.admin;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class DeleteStaff
 */
public class DeleteStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteStaff() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	         try {
		String id=request.getParameter("id");
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
		  Statement st=c.createStatement();
		 st.executeUpdate("delete from staffrecord where email=\""+id+"\";");
		
		 ResultSet rs=st.executeQuery("select value from status where event=\"staff count\";");
	       rs.next();
	       int STAFF_COUNT=rs.getInt(1);
	       STAFF_COUNT-=1;
	      st.executeUpdate("update status set value="+ STAFF_COUNT+" where event=\"staff count\";");
	     c.close(); 
		 
		 PrintWriter out=response.getWriter();	     
		  out.println("<center><h1>Operation done successfully</h1></center>");
		  out.println("<br><center><a href=\"deletestaff.jsp\"><button style=\" width: 200px;height: 50px; background-color: cyan;\">OK</button></a></center>");
	    
	         
	         
	         }catch(Exception e) {System.out.println(e);}
	
	}

}
