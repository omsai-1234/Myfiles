package com;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
/**
 * Servlet implementation class UC
 */
public class UC extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// TODO Auto-generated method stub
		
		try{
			  HttpSession s=req.getSession();
			  String uid=(String)s.getAttribute("uid");
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			  Statement st=c.createStatement();
			  String email=(String)req.getParameter("email");
			  String mno=(String)req.getParameter("mno");
			  int f=0;
			  int j=0;
			  if(mno!=null||!(mno.equals(" ")))
			  {  
				 st.executeUpdate("update studentregistration set mobile=\""+mno+"\" where emailid=\""+uid+"\";");
			     j=1;
			  }
			  if(email!=null||!(email.equals(" ")))
			  {  
				 st.executeUpdate("update studentregistration set emailid=\""+email+"\" where emailid=\""+uid+"\";");
			     f=1;
			  }
			  
			  c.close();
			  if(j==1)
			  {
				  res.setContentType("text/html");
				  PrintWriter out=res.getWriter();
                 				
				  out.println("<html><body><center><h1>Records updated succefully</h1><center>"
				  		+ " <br><br> "
						  +" <a href=\"sregistrationhome.jsp\">  Click here to goto student home page </a>"
						 + "</body></html>");
				  
			  }
			  
			  if(f==1)
			  {
				  res.setContentType("text/html");
				  PrintWriter out=res.getWriter();
                  s.setAttribute("uid",email);				
				  out.println("<html><body><center><h1>Records updated succefully</h1><center>"
				  		+ " <br><br> "
						  +" <a href=\"sregistrationhome.jsp\">  Click here to goto student home page </a>"
						 + "</body></html>");
				  
			  }
			 
		}catch(Exception e) {System.out.println(e);}
			  
			
	}

}
