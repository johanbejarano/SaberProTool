package com.vortexbird.sapiens.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.sapiens.service.ReporteService;


@RestController
@RequestMapping("/api/contexto")
@CrossOrigin(origins = "*")
public class ReporteRestController {
    private static final Logger log = LoggerFactory.getLogger(ReporteRestController.class);
    @Autowired
    private ReporteService reporteService;

}
