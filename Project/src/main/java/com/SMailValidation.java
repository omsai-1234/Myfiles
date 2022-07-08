package com;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

/**
 * This servlet is checking that entered mail is already registered with us or not and if not registered then registration is done otherwise forward's to studentregistration.html page 
 * Servlet implementation class SMailValidation
 */
public class SMailValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int f=0;
		   String email=request.getParameter("email");
		try{
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			  
			  Statement st=c.createStatement();
			  
			  ResultSet rs=st.executeQuery("select * from studentregistration");
					  while(rs.next())
			  {
                if(email.equals(rs.getString(4)))
                {
                	 RequestDispatcher rd=request.getRequestDispatcher("studentregistration.html"); 
                	 PrintWriter out=response.getWriter();
                	 response.setContentType("text/html");
                	 out.println("<center><div style=\" background-color: yellow;"
                	 		+ "opacity: 0.6\""
                	 		+ "font-family: \"Lucida Console\", \"Courier New\", monospace;><h2 style=\" color: red\">Entered email address is already registered try another one <h2><div></center>");
                      rd.include(request, response);
                      f=1;
                      break;
                }
			  }
		   if(f==0) {
		     PreparedStatement ps=c.prepareStatement("insert into studentregistration values(?,?,?,?,?,?)");
		     ps.setString(1,(String)request.getParameter("fname"));
		     ps.setString(2,(String)request.getParameter("mname"));
		     ps.setString(3,(String)request.getParameter("lname"));
		     ps.setString(4,(String)request.getParameter("email"));
		     ps.setString(5,(String)request.getParameter("pass1"));
		     ps.setString(6,(String)request.getParameter("mno"));
			 ps.executeUpdate();
			 HttpSession s=request.getSession();
			 s.setAttribute("fname", (String)request.getParameter("fname"));
			 s.setAttribute("lname", (String)request.getParameter("lname"));
			 s.setAttribute("email", (String)request.getParameter("email"));
			 s.setAttribute("action", "registered");
			 RequestDispatcher rd=request.getRequestDispatcher("c_registration.jsp");
			  rd.forward(request,response);
		    }
		   c.close();
		   }catch(Exception e) {System.out.println(e);}
		
       
	}

}
