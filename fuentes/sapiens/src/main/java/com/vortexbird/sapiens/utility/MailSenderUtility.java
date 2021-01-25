package com.vortexbird.sapiens.utility;

/**
 *
 * @author Freddy Sebastián García Victoria
 * @version Apr 18, 2018
 * @since 1.8
 *
 */
		
import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Freddy Sebastián García Victoria
 * @version Apr 18, 2018
 * @since 1.8
 *
 */
@Service
public class MailSenderUtility {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String recipients[], String subject, String message, File attachment, boolean isHTML)
			throws Exception {

		try {
			// -----Contruir el cuerpo del correo ---///			

			String body = message;

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			mimeMessage.setRecipients(Message.RecipientType.BCC, addressTo);

			// Setting the Subject and Content Type
			mimeMessage.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			if (!isHTML) {
				messageBodyPart.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
				messageBodyPart.setContent(body, "text/plain; charset=utf-8");
			} else {
				messageBodyPart.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
				messageBodyPart.setContent(body, "text/html; charset=utf-8");
			}

			messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			if (attachment != null) {
				if (attachment.isFile() && attachment.exists()) {
					messageBodyPart = new MimeBodyPart();
					String filename = attachment.getName();
					DataSource source = new FileDataSource(attachment);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);
				}
			}
			
			
			
			// Send the complete message parts
//			mimeMessage.setContent(body, "text/html; charset=utf-8");
			
			mimeMessage.setContent(multipart);
			mimeMessage.setFrom("sabertool@usbcali.edu.co");

			// Send message
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * GETTERS && SETTERS
	 */

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

}
