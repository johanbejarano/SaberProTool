package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.EstadoPruebaDTO;
import com.vortexbird.sapiens.mapper.EstadoPruebaMapper;
import com.vortexbird.sapiens.service.EstadoPruebaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/estadoPrueba")
@CrossOrigin(origins = "*")
public class EstadoPruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(EstadoPruebaRestController.class);
    @Autowired
    private EstadoPruebaService estadoPruebaService;
    @Autowired
    private EstadoPruebaMapper estadoPruebaMapper;

    @GetMapping(value = "/findById/{esprId}")
    public ResponseEntity<?> findById(@PathVariable("esprId")
    Integer esprId) {
        log.debug("Request to findById() EstadoPrueba");

        try {
            EstadoPrueba estadoPrueba = estadoPruebaService.findById(esprId)
                                                           .get();

            return ResponseEntity.ok()
                                 .body(estadoPruebaMapper.estadoPruebaToEstadoPruebaDTO(
                    estadoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() EstadoPrueba");

        try {
            return ResponseEntity.ok()
                                 .body(estadoPruebaMapper.listEstadoPruebaToListEstadoPruebaDTO(
                    estadoPruebaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    EstadoPruebaDTO estadoPruebaDTO) {
        log.debug("Request to save EstadoPrueba: {}", estadoPruebaDTO);

        try {
            EstadoPrueba estadoPrueba = estadoPruebaMapper.estadoPruebaDTOToEstadoPrueba(estadoPruebaDTO);
            estadoPrueba = estadoPruebaService.save(estadoPrueba);

            return ResponseEntity.ok()
                                 .body(estadoPruebaMapper.estadoPruebaToEstadoPruebaDTO(
                    estadoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    EstadoPruebaDTO estadoPruebaDTO) {
        log.debug("Request to update EstadoPrueba: {}", estadoPruebaDTO);

        try {
            EstadoPrueba estadoPrueba = estadoPruebaMapper.estadoPruebaDTOToEstadoPrueba(estadoPruebaDTO);
            estadoPrueba = estadoPruebaService.update(estadoPrueba);

            return ResponseEntity.ok()
                                 .body(estadoPruebaMapper.estadoPruebaToEstadoPruebaDTO(
                    estadoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{esprId}")
    public ResponseEntity<?> delete(@PathVariable("esprId")
    Integer esprId) throws Exception {
        log.debug("Request to delete EstadoPrueba");

        try {
            EstadoPrueba estadoPrueba = estadoPruebaService.findById(esprId)
                                                           .get();

            estadoPruebaService.delete(estadoPrueba);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(estadoPruebaService.count());
    }
}
