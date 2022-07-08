package com;

import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/Admission"})
@MultipartConfig


public class Admission extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void service(HttpServletRequest req,HttpServletResponse res) 
	 {
		 
		 int f=0;
	     String email=(String)req.getParameter("s_email");
	     try{
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			  
			  Statement st=c.createStatement();
			  
			  ResultSet rs=st.executeQuery("select email from logindetails;");
				
			  while(rs.next())
			  {
				   
				 if(email.equals(rs.getString(1)))
               {
               	  System.out.println("Inside the if block");
               	 PrintWriter out=res.getWriter();
               	 res.setContentType("text/html");
               	 out.println("<center><div style=\" background-color: yellow;"
               	 		+ "opacity: 0.6\""
               	 		+ "font-family: \"Lucida Console\", \"Courier New\", monospace;><h2 style=\" color: red\">"
               	 		+ "The email adress you had entered had already took admission in the college."
               	 		+ "You can click on the forget password to reset password. <h2><div></center>");
                    RequestDispatcher rd=req.getRequestDispatcher("Addmision.html");
                   f=1;
                   rd.include(req, res);
                     
                     return;
               }
			  }
			  
		   if(f==0) {
			   PreparedStatement ps=c.prepareStatement("insert into studentdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			     ps.setString(1,(String)req.getParameter("fname"));
			     ps.setString(2,(String)req.getParameter("mname"));
			     ps.setString(3,(String)req.getParameter("lname"));
			     ps.setString(5,(String)req.getParameter("mother_name"));
			     ps.setString(4,(String)req.getParameter("s_email"));
			     ps.setString(6,(String)req.getParameter("f_email"));
			     ps.setString(7,(String)req.getParameter("s_mno"));
			     ps.setString(8,(String)req.getParameter("gender"));
			     ps.setString(21,(String)req.getParameter("date"));
			     
			     Part p1=req.getPart("passport_img");
			     String photo=upload(p1,email,"C:\\Users\\Akash\\eclipse-workspace\\JProject\\src\\main\\webapp\\img\\profile");
			     ps.setString(9,photo);
			     
			     Part p2=req.getPart("sign_img");
			     String sign=upload(p2,email,"C:\\Users\\Akash\\eclipse-workspace\\JProject\\src\\main\\webapp\\img\\sign");
			     ps.setString(10,sign);
			     
			     ps.setString(11,(String)req.getParameter("village"));
			     ps.setString(12,(String)req.getParameter("taluka"));
			     ps.setString(13,(String)req.getParameter("district"));
			     ps.setString(14,(String)req.getParameter("pincode"));
			   
			     ps.setString(15,(String)req.getParameter("10percentage"));
			     ps.setString(16,(String)req.getParameter("10passyear"));
			    
			     Part p3=req.getPart("10_img");
			     String tenmarksheet=upload(p3,email,"C:\\Users\\Akash\\eclipse-workspace\\JProject\\src\\main\\webapp\\img\\10_certificate");
			     ps.setString(17,tenmarksheet);
			     
			     ps.setString(18,(String)req.getParameter("12percentage"));
			     ps.setString(19,(String)req.getParameter("12passyear"));
			    
			     Part p4=req.getPart("12_img");
			     String twelvemarksheet=upload(p4,email,"C:\\Users\\Akash\\eclipse-workspace\\JProject\\src\\main\\webapp\\img\\12_certificate");
			     ps.setString(20,twelvemarksheet);
			    
			     ps.executeUpdate();
			     
			     //coding for insert values into logindetails table
			     
			     ps=c.prepareStatement("insert into logindetails values(?,?,?,?,?)");
			     ps.setString(1,(String)req.getParameter("s_email") );
			     ps.setString(2,"123123");
			     ps.setString(3,(String)req.getParameter("fname") );
			     ps.setString(4,(String)req.getParameter("lname") );
			     ps.setString(5,(String)req.getParameter("class"));
			     ps.executeUpdate();
			     System.out.println("Data inserted successfully");
                HttpSession s=req.getSession();
                s.setAttribute("email", email);
   			 s.setAttribute("fname", (String)req.getParameter("fname"));
   			 s.setAttribute("lname", (String)req.getParameter("lname"));
   			 s.setAttribute("action", "completed process of admission");
   			
   			 //Coding for enter fee into fee_structure table
   			
   			 rs=st.executeQuery("select value from status where event=\"admission fee\";");
		       rs.next();
		       int TUTION_FEE=rs.getInt(1);
		       ps=c.prepareStatement("insert into fee_structure values(?,?,?,?,?,?,?,?);");
		       ps.setString(1,email);  
		       ps.setInt(2,TUTION_FEE);
		       ps.setInt(3,0);
		       ps.setInt(4,0);
		       ps.setInt(5,0);
		       ps.setInt(6,TUTION_FEE);
		       ps.setInt(7,0);
		       ps.setInt(8,TUTION_FEE);
		       ps.executeUpdate();
		       
		      
		       //code for updating the value of status table of student count 
		       rs=st.executeQuery("select value from status where event=\"student count\";");
		       rs.next();
		       int STUDENT_COUNT=rs.getInt(1);
		       STUDENT_COUNT+=1;
		      st.executeUpdate("update status set value="+ STUDENT_COUNT+" where event=\"student count\";");
		     c.close();
		     RequestDispatcher rd=req.getRequestDispatcher("Admission_password.html");
		      rd.forward(req,res);
		  } 
		   }catch(Exception e) {System.out.println(e);
		   System.out.println("Exception is occuring in Admission.java");}   
	      
	     
		 
	 }
	
	 
	 
	 
	 
	
		protected String upload(Part filepart, String name,String path)throws IOException, ServletException {
		
			
			
		    final String fileName = name+getExtension(filepart);
    		
		    
		    OutputStream out = null;
		    InputStream filecontent = null;  
		    
		    
		    

		    try {
		        out = new FileOutputStream(new File(path + File.separator
		                + fileName));
		        filecontent = filepart.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        
		         }
		        
		        
		      
		    } catch (FileNotFoundException fne) {
		             System.out.println(fne);}
		    
		    finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		           
		    }
		    return fileName;
		}

		    

	


      private String getExtension(final Part part) {

     for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
             return content.substring(
                    content.indexOf('.')).trim().replace("\"", "");
        }
    }
    return null;
  }
      
      
}
