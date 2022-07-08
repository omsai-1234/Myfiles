package com.admin;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.sql.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "AddStaff", urlPatterns = {"/addstaff"})
@MultipartConfig

public class AddStaff extends HttpServlet {
          
	   public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException 
	   {
		   try {
			    Class.forName("com.mysql.jdbc.Driver");
				  Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			     PreparedStatement ps=c.prepareStatement("insert into staffrecord values(?,?,?,?,?,?,?,?)");	   
		         ps.setString(1, req.getParameter("firstname"));
		         ps.setString(2, req.getParameter("lastname"));
		         ps.setString(3, req.getParameter("email"));
		         ps.setString(5, req.getParameter("mobile"));
		         ps.setString(6, req.getParameter("salary"));
		         ps.setString(7, req.getParameter("address"));
		         ps.setString(8, req.getParameter("qualification"));
		         ps.setString(4, req.getParameter("password"));
		         ps.executeUpdate();
		         
		         Statement st=c.createStatement();
		         ResultSet rs=st.executeQuery("select value from status where event=\"staff count\";");
			       rs.next();
			       int STAFF_COUNT=rs.getInt(1);
			       STAFF_COUNT+=1;
			      st.executeUpdate("update status set value="+ STAFF_COUNT+" where event=\"staff count\";");
			     c.close();

		         PrintWriter out=res.getWriter();	     
				  out.println("<center><h1>Operation done successfully</h1></center>");
				  out.println("<br><center><a href=\"Stafflist.jsp\"><button style=\" width: 200px;height: 50px; background-color: cyan; border: 5px solid green;\">OK</button></a></center>");
                       		   
		   
		   }catch(Exception e){System.out.println(e);}
		   
		   
	   }
}
