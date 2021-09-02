package com.Emily.Model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	//A寄給B
	public void sendMailpass(String to) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "massfood2021@gmail.com";
	     final String myGmail_password = "TFA102TFA102";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject("親愛的MassFood會員，資料已通過審核");
		   //設定信中的內容 
		   message.setContent(" <html><body style=\"border-color:#DB7243;border-width:3px;border-style:dashed;padding:5px;width: 250px;\">\r\n" + 
		   		"    <h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料已通過審核</p><p>快來使用本系統功能吧</p>\r\n" + 
		   		"</body></html>", "text/html;charset=UTF-8");
//		   message.setText(" <html><h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料已通過審核</p><p>快來使用本系統功能吧</p></html>");

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	public void sendMailfail(String to) {
		
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "massfood2021@gmail.com";
		     final String myGmail_password = "TFA102TFA102";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject("親愛的MassFood會員，資料未通過審核");
			   //設定信中的內容 
			   message.setContent(" <html><body style=\"border-color:#DB7243;border-width:3px;border-style:dashed;padding:5px;width: 250px;\">\r\n" + 
			   		"    <h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料填寫不全</p><p>請登入填寫後重新審核，謝謝。</p>\r\n" + 
			   		"</body></html>", "text/html;charset=UTF-8");
//			   message.setText("<html><h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料填寫不全</p><p>請登入填寫後重新審核，謝謝。</p></html>");

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
}
