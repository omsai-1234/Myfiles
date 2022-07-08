package com.admin;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
/**
 * Servlet implementation class DeleteStudent
 */
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public void service(HttpServletRequest request,HttpServletResponse response)
    {
    	
    	try {
    	  String id=request.getParameter("id");
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
		  Statement st=c.createStatement();
		  st.executeUpdate("delete from studentdetails where smailid=\""+id+"\";");
		
		  st.executeUpdate("delete from logindetails where email=\""+id+"\";");
		 
		  //updating values inside the status table
		   ResultSet rs=st.executeQuery("select value from status where event=\"student count\";");
	       rs.next();
	       int STUDENT_COUNT=rs.getInt(1);
	       STUDENT_COUNT-=1;
	      st.executeUpdate("update status set value="+ STUDENT_COUNT+" where event=\"student count\";");
	     c.close();
	     
		  PrintWriter out=response.getWriter();	     
		  out.println("<center><h1>Operation done successfully</h1></center>");
		  out.println("<br><center><a href=\"studentdelete.jsp\"><button style=\" width: 200px;height: 50px; background-color: cyan;\">OK</button></a></center>");
	    }catch(Exception e) {System.out.println(e);}
	
    }
}
