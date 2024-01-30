package com.dpk.EmployeeManagementSystem.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String toEmail, String subject, String message) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toEmail);

		msg.setSubject(subject);
		msg.setText(message);

		javaMailSender.send(msg);

	}

	public	void sendEmailWithAttachment(String toEmail, String subject, String message, MultipartFile attachFile) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(toEmail);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(message, true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

       // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        	
        		//Determine(Check) if there is file upload. If Yes then Attach it to the Client email
        		if((attachFile != null) && (attachFile.getSize() > 0) && (!attachFile.equals(""))) {
        			
        			helper.addAttachment(attachFile.getOriginalFilename(), new InputStreamSource() {
						
						@Override
						public InputStream getInputStream() throws IOException {
							
							return attachFile.getInputStream();
						}
					});
        		}

        javaMailSender.send(msg);

    }

}
