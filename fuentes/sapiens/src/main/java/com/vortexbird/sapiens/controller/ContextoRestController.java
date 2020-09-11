package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.ContextoDTO;
import com.vortexbird.sapiens.dto.PruebaDTO;
import com.vortexbird.sapiens.dto.ReporteDTO;
import com.vortexbird.sapiens.mapper.ContextoMapper;
import com.vortexbird.sapiens.service.ContextoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contexto")
@CrossOrigin(origins = "*")
public class ContextoRestController {
    private static final Logger log = LoggerFactory.getLogger(ContextoRestController.class);
    @Autowired
    private ContextoService contextoService;

    @GetMapping(value = "/getByModulo/{moduId}")
    public ResponseEntity<?> getByModulo(@PathVariable("moduId") Integer moduId) {
        try {
        	List<ContextoDTO> contextos = contextoService.getByModulo(moduId);
            return ResponseEntity.ok().body(contextos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/guardar")
    public ResponseEntity<?> guardar(@RequestBody ContextoDTO contextoDTO) {
        try {
        	contextoService.guardar(contextoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
