package com.vortexbird.sapiens.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
	
	@Autowired
    private EmailService emailService;

    @Test
    public void sendCrearUsuario() throws Exception{
    	//emailService.sendCrearUsuario("cdelgado@vortexbird.com", "cdelgado", "123456");
    }
}
