package com;

import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;


/**
 * Servlet implementation class LoginValidation
 */
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
    public void service(HttpServletRequest req,HttpServletResponse res) 
    {
       String ltype=(String)req.getParameter("login_type");
       String uname=(String)req.getParameter("username");
       String password=(String)req.getParameter("password");
   try {
	   Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8&useSSL=false&useUnicode=true","root","root");
		Statement st=c.createStatement();

	    System.out.println("Inside the loginvalidation");
	    
	    
	    	if(ltype.equals("student"))
	    	{
	    	    
	
    	         int f=0;
	    		ResultSet rs=st.executeQuery("select emailid,password from studentregistration");
	    	    
	    		while(rs.next())
    	         {
    	        	 String n=(String)rs.getString(1);
    	        
    	        	 String p=(String)rs.getString(2);
    	        
    	        	if(uname.equals(n)&&password.equals(p))
    	        	{
    	        		f=1;
    	        		HttpSession s=req.getSession();
    	        		s.setAttribute("uid", uname);
    	        	    
    	        	     RequestDispatcher rd1=req.getRequestDispatcher("sregistrationhome.jsp");
    	        		rd1.forward(req,res);
    	        	     return;
    	        	}
    	         }
    	        if(f==0) {
    	          rs=st.executeQuery("select email,password from logindetails");
     	        while(rs.next())
     	        {
     	        	String n=(String)rs.getString(1);
   	        	    String p=(String)rs.getString(2);
     	        	if(uname.equals(n)&&password.equals(p))
     	        	{
     	        		HttpSession s=req.getSession();
     	        		s.setAttribute("uid", n);
     	        		RequestDispatcher rds=req.getRequestDispatcher("sadmissionhome.jsp");
     	        		rds.forward(req,res);
     	        		return;
     	        	}
     	        }
    	        
    	        RequestDispatcher rd=req.getRequestDispatcher("wronglogindetails.html");
    	        rd.forward(req, res);
    	      }
	    	}
       
       
       
	//  parent login code block starts here.    	
	    	if(ltype.equals("parent"))
	    	{
    	      
	    	}
       
       
	    	
    //staff login code block starts here.   
	    	if(ltype.equals("staff"))
	    	{
    	   
    	   
	    		if(uname.equals("Admin")&&password.equals("Admin"))
	    		{
    		      RequestDispatcher r=req.getRequestDispatcher("Admin_home.jsp");
    		      r.forward(req, res);
	    		}
	    		else 
	    		{
    		      ResultSet rs=st.executeQuery("select email,password from staffrecord;");
    		      
    		      while(rs.next())
    		      {
                    String uid=rs.getString(1);
                    String pass=rs.getString(2);
    		    	  if((uname.equals(uid))&&(password.equals(pass))) 
    		    	  {
    		    	    HttpSession s=req.getSession();
    		    	    s.setAttribute("uid",uid);
    		    	    RequestDispatcher r=req.getRequestDispatcher("staffhome.jsp");
    		    	    r.forward(req,res);
    		    	    return;
    		    	  }
    		    	  
    		      }
    		      RequestDispatcher rb=req.getRequestDispatcher("wronglogindetails.html");
      	        rb.forward(req, res);
    		      
	    		}
    	   
    	   
	    	}
       
      }catch(Exception e) {System.out.println(e);}
    
    }
}
