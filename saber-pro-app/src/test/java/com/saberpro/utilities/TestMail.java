package com.saberpro.utilities;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class TestMail {

	private final static Logger log = LoggerFactory.getLogger(TestMail.class);

	@Autowired
	private Email email;

	@Test
	public void testSend(){
		
		List<Documento> list = new ArrayList<>();
		list.add(new Documento("monodevelop.jpg","/home/jhony/Imágenes/icon/monodevelop.jpg"));
		list.add(new Documento("monodevelop.jpg","/home/jhony/Imágenes/icon/monodevelop.jpg"));
		
		try {
			email.sendSimpleMessage("jhonypk18@gmail.com", "prueba", "prueba");
			email.sendSimpleHtml("jhonypk18@gmail.com", "prueba", "<h1>prueba</h1>");
			//email.sendMessageWithAttachmentHtml("jhonypk18@gmail.com", "prueba", "<h1>prueba</h1>",list);
			//email.sendMessageWithAttachment("jhonypk18@gmail.com", "prueba", "<h1>prueba</h1>",list);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		

	}

}
