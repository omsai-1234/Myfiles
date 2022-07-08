package com;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/setPassword"})

public class AdmisionPasswordSetter extends HttpServlet  {
   public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException
   {
	   try{
			  Class.forName("com.mysql.jdbc.Driver");
			  System.out.println("Inside the password setter programme");
			  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			  
			  Statement st=c.createStatement();
			  HttpSession s=req.getSession();
			  String email=(String)s.getAttribute("email");
			  String pass=(String)req.getParameter("pass1");
		      st.executeUpdate("update logindetails set password=\""+pass+"\" where email=\""+email+"\";");
	          st.executeUpdate("delete from studentregistration where emailid=\""+email+"\";");				 RequestDispatcher rd=req.getRequestDispatcher("c_registration.jsp");
				  rd.forward(req,res);
	   }catch(Exception e) {System.out.println(e);
	   System.out.println("Inside the passwordSetter's Exception");
	   }
   }
	
}
