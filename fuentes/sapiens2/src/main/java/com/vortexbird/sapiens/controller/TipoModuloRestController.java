package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.TipoModuloDTO;
import com.vortexbird.sapiens.mapper.TipoModuloMapper;
import com.vortexbird.sapiens.service.TipoModuloService;

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
@RequestMapping("/api/tipoModulo")
@CrossOrigin(origins = "*")
public class TipoModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoModuloRestController.class);
    @Autowired
    private TipoModuloService tipoModuloService;
    @Autowired
    private TipoModuloMapper tipoModuloMapper;

    @GetMapping(value = "/findById/{timoId}")
    public ResponseEntity<?> findById(@PathVariable("timoId")
    Integer timoId) {
        log.debug("Request to findById() TipoModulo");

        try {
            TipoModulo tipoModulo = tipoModuloService.findById(timoId).get();

            return ResponseEntity.ok()
                                 .body(tipoModuloMapper.tipoModuloToTipoModuloDTO(
                    tipoModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() TipoModulo");

        try {
            return ResponseEntity.ok()
                                 .body(tipoModuloMapper.listTipoModuloToListTipoModuloDTO(
                    tipoModuloService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    TipoModuloDTO tipoModuloDTO) {
        log.debug("Request to save TipoModulo: {}", tipoModuloDTO);

        try {
            TipoModulo tipoModulo = tipoModuloMapper.tipoModuloDTOToTipoModulo(tipoModuloDTO);
            tipoModulo = tipoModuloService.save(tipoModulo);

            return ResponseEntity.ok()
                                 .body(tipoModuloMapper.tipoModuloToTipoModuloDTO(
                    tipoModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    TipoModuloDTO tipoModuloDTO) {
        log.debug("Request to update TipoModulo: {}", tipoModuloDTO);

        try {
            TipoModulo tipoModulo = tipoModuloMapper.tipoModuloDTOToTipoModulo(tipoModuloDTO);
            tipoModulo = tipoModuloService.update(tipoModulo);

            return ResponseEntity.ok()
                                 .body(tipoModuloMapper.tipoModuloToTipoModuloDTO(
                    tipoModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{timoId}")
    public ResponseEntity<?> delete(@PathVariable("timoId")
    Integer timoId) throws Exception {
        log.debug("Request to delete TipoModulo");

        try {
            TipoModulo tipoModulo = tipoModuloService.findById(timoId).get();

            tipoModuloService.delete(tipoModulo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoModuloService.count());
    }
}
