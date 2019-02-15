package anhvanmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Mail.MyConstants;
import anhvanmobile.service.SendMail;

@Controller
public class SendMailController {

	@Autowired
	private SendMail sendmail;

	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail() {

		sendmail.SendEmail("vannaph05296@fpt.edu.vn");
		return "Email Sent!";
	}

}
