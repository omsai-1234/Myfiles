package com.admin;

import jakarta.servlet.http.*;
import java.io.IOException;
import jakarta.servlet.*;
import java.sql.*;


/**
 * Servlet implementation class UpdateFee
 */
public class UpdateFee extends HttpServlet implements Servlet {
    	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     try {
	    	 Class.forName("com.mysql.jdbc.Driver");
			 Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=utf8","root","root");
			 Statement st=c.createStatement();
			 String email=request.getParameter("id");
		     ResultSet rs=st.executeQuery("select * from fee_structure where student_id=\""+ email+"\"; ");
	    	 rs.next(); 
	    	 int bus_fee=rs.getInt(3);
	    	 int hostel_fee=rs.getInt(4);
	    	 int other_fee=rs.getInt(5);
	    	 int total_fee=rs.getInt(6);
	    	 int paid_fee=rs.getInt(7);
	    	 int remaining_fee=rs.getInt(8);
	    	 String type=request.getParameter("Fee_type");
	    	 
	    	 if(type.equals("bus_fee"))
	    	 {
	    		 bus_fee+=Integer.parseInt(request.getParameter("fee"));
	             total_fee+=bus_fee;
	             remaining_fee+=Integer.parseInt(request.getParameter("fee"));
	    	 }
	    	 
	    	 
	    	 if(type.equals("hostel_fee"))
	    	 {
	    		 hostel_fee+=Integer.parseInt(request.getParameter("fee"));
	             total_fee+=Integer.parseInt(request.getParameter("fee"));   	 
	             remaining_fee+=Integer.parseInt(request.getParameter("fee"));

	    	 }
	    	 
	    	 
	    	 if(type.equals("other_fee"))
	    	 {
	    		 other_fee+=Integer.parseInt(request.getParameter("fee"));
	             total_fee+=Integer.parseInt(request.getParameter("fee"));   	 
	             remaining_fee+=Integer.parseInt(request.getParameter("fee"));

	    	 }
	    	 
	    	 
	    	 if(type.equals("pay_fee"))
	    	 {
	    		 paid_fee+=Integer.parseInt(request.getParameter("fee"));
	             remaining_fee-=Integer.parseInt(request.getParameter("fee"));   	 
	    	 }
	    	 st.executeUpdate("update fee_structure set bus_fee="+ bus_fee+",hostel_fee="+ hostel_fee+",other_fee="+other_fee+",total_fee="+total_fee+",paid_fee="+ paid_fee+",remaining_fee="+remaining_fee+" where student_id=\""+email+"\";");
	    	 RequestDispatcher rd=request.getRequestDispatcher("Showfeedetails.jsp");
	    	 rd.forward(request, response);
	    	 
	           }catch(Exception e) {System.out.print(e);}
	     }
    		
 }


