package com;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
 
	public void sendmail(String recepient) throws Exception {
		System.out.println("Preparing to send email");
		Properties props=new Properties();
		props.put("mail.smtp/auth","true");
		props.put("mail.smtp.starttls.enable", "Yes");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable","true");
		
		String from="samarthcollege2@gmail.com";
		String password="Samarth@123";
		
		
		Session s=Session.getInstance(props,new Authenticator(){
		    protected PasswordAuthentication getPasswordAuthentication(){
		    	return new PasswordAuthentication(from,password);
		    }	
		});
		
		Message msg=prepareMessage(s,from,recepient);
	    Transport.send(msg);
	    System.out.println("Message is sent");
	}
	
	private static Message prepareMessage(Session s,String from,String recepient) {
		// TODO Auto-generated method stub
	  Message message=new MimeMessage(s);
	  try {
		  
		  
		message.setFrom(new InternetAddress("from"));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
		message.setSubject("My first Email from java api");
		message.setText("Hii, dear\n look my email");
		return message;
	} catch (Exception e) {e.printStackTrace();}
	
	  return null;
	
	}

	
	
	
}
