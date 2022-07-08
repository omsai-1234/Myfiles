package com.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.*;
/**
 * Servlet implementation class SSAdmission
 */
public class SSAdmission extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		try{
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
              Statement st=c.createStatement();
              ResultSet rs=st.executeQuery("select value from status where event=\"admission process\";");
			  rs.next();
			  int status=rs.getInt(1);
			  if(status==1)
				  status=0;
			  else
				  status=1;
			  
			  
			  st.executeUpdate("update status set value="+status+" where event=\"admission process\";");
			  c.close();
	           
	           RequestDispatcher rd=request.getRequestDispatcher("ssadmission.jsp");
	           rd.forward(request,response);
		}catch(Exception e){System.out.println(e);}
	}

}
