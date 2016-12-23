package com.konstrukcija.service;
/*
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

@Service
public class MailPotvrde {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public void mail(String kome, String ko, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try{
			helper = new MimeMessageHelper(message, true);
			helper.setSubject(ko);
			helper.setTo(kome);
			helper.setText(text, true);
		} catch(MessagingException ex) {
			ex.printStackTrace();
		}
		
		javaMailSender.send(message);
	}
}*/
