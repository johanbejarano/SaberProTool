package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.PruebaModuloDTO;
import com.vortexbird.sapiens.mapper.PruebaModuloMapper;
import com.vortexbird.sapiens.service.PruebaModuloService;

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
@RequestMapping("/api/pruebaModulo")
@CrossOrigin(origins = "*")
public class PruebaModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaModuloRestController.class);
    @Autowired
    private PruebaModuloService pruebaModuloService;
    @Autowired
    private PruebaModuloMapper pruebaModuloMapper;

    @GetMapping(value = "/findById/{prmoId}")
    public ResponseEntity<?> findById(@PathVariable("prmoId")
    Integer prmoId) {
        log.debug("Request to findById() PruebaModulo");

        try {
            PruebaModulo pruebaModulo = pruebaModuloService.findById(prmoId)
                                                           .get();

            return ResponseEntity.ok()
                                 .body(pruebaModuloMapper.pruebaModuloToPruebaModuloDTO(
                    pruebaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() PruebaModulo");

        try {
            return ResponseEntity.ok()
                                 .body(pruebaModuloMapper.listPruebaModuloToListPruebaModuloDTO(
                    pruebaModuloService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    PruebaModuloDTO pruebaModuloDTO) {
        log.debug("Request to save PruebaModulo: {}", pruebaModuloDTO);

        try {
            PruebaModulo pruebaModulo = pruebaModuloMapper.pruebaModuloDTOToPruebaModulo(pruebaModuloDTO);
            pruebaModulo = pruebaModuloService.save(pruebaModulo);

            return ResponseEntity.ok()
                                 .body(pruebaModuloMapper.pruebaModuloToPruebaModuloDTO(
                    pruebaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    PruebaModuloDTO pruebaModuloDTO) {
        log.debug("Request to update PruebaModulo: {}", pruebaModuloDTO);

        try {
            PruebaModulo pruebaModulo = pruebaModuloMapper.pruebaModuloDTOToPruebaModulo(pruebaModuloDTO);
            pruebaModulo = pruebaModuloService.update(pruebaModulo);

            return ResponseEntity.ok()
                                 .body(pruebaModuloMapper.pruebaModuloToPruebaModuloDTO(
                    pruebaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prmoId}")
    public ResponseEntity<?> delete(@PathVariable("prmoId")
    Integer prmoId) throws Exception {
        log.debug("Request to delete PruebaModulo");

        try {
            PruebaModulo pruebaModulo = pruebaModuloService.findById(prmoId)
                                                           .get();

            pruebaModuloService.delete(pruebaModulo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(pruebaModuloService.count());
    }
}
