package com.agami.leavemanagement.helper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailHelper
{
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String to[], String from, String subject,
			String mailBody, String attachmentFilePath)
	{
		try
		{
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(mailBody, "UTF-8", "html");
			Multipart multipart = new MimeMultipart();
			if(attachmentFilePath !=null)
			{
				DataSource source = new FileDataSource(attachmentFilePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attachmentFilePath);
			}
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			javaMailSender.send(message);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
