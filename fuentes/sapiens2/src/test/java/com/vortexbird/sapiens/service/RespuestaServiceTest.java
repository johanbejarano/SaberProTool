package com.vortexbird.sapiens.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@SpringBootTest
public class RespuestaServiceTest {
    private static final Logger log = LoggerFactory.getLogger(RespuestaServiceTest.class);
    @Autowired
    private RespuestaService respuestaService;

    @Test
    @DisplayName("findAll")
    public void findAll() {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("save")
    public void save() throws Exception {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("delete")
    public void delete() throws Exception {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("deleteById")
    public void deleteById() throws Exception {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("update")
    public void update() throws Exception {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("findById")
    public void findById() throws Exception {
        assertNotNull(respuestaService);
    }

    @Test
    @DisplayName("count")
    public void count() throws Exception {
        assertNotNull(respuestaService);
    }
}
