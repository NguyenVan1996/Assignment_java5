package anhvanmobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class SendMail {
	
	 @Autowired
	    public JavaMailSender emailSender;
	 
	    public void SendEmail(String email) {
	   	 
	        // Create a Simple MailMessage.
	        SimpleMailMessage message = new SimpleMailMessage();
	         
	        message.setTo(email);
	        message.setSubject("Đăng ký tài khoản thành công");
	        message.setText("Chúc bạn đăng ký tài khoản thành công!");
	 
	        // Send Message!
	        this.emailSender.send(message);
	 
	      
	    }

}
