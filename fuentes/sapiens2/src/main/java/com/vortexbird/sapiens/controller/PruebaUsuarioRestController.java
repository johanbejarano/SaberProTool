package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;
import com.vortexbird.sapiens.mapper.PruebaUsuarioMapper;
import com.vortexbird.sapiens.service.PruebaUsuarioService;

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
@RequestMapping("/api/pruebaUsuario")
@CrossOrigin(origins = "*")
public class PruebaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaUsuarioRestController.class);
    @Autowired
    private PruebaUsuarioService pruebaUsuarioService;
    @Autowired
    private PruebaUsuarioMapper pruebaUsuarioMapper;

    @GetMapping(value = "/findById/{prusId}")
    public ResponseEntity<?> findById(@PathVariable("prusId")
    Integer prusId) {
        log.debug("Request to findById() PruebaUsuario");

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioService.findById(prusId)
                                                              .get();

            return ResponseEntity.ok()
                                 .body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(
                    pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() PruebaUsuario");

        try {
            return ResponseEntity.ok()
                                 .body(pruebaUsuarioMapper.listPruebaUsuarioToListPruebaUsuarioDTO(
                    pruebaUsuarioService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    PruebaUsuarioDTO pruebaUsuarioDTO) {
        log.debug("Request to save PruebaUsuario: {}", pruebaUsuarioDTO);

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioMapper.pruebaUsuarioDTOToPruebaUsuario(pruebaUsuarioDTO);
            pruebaUsuario = pruebaUsuarioService.save(pruebaUsuario);

            return ResponseEntity.ok()
                                 .body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(
                    pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(
        @RequestBody
    PruebaUsuarioDTO pruebaUsuarioDTO) {
        log.debug("Request to update PruebaUsuario: {}", pruebaUsuarioDTO);

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioMapper.pruebaUsuarioDTOToPruebaUsuario(pruebaUsuarioDTO);
            pruebaUsuario = pruebaUsuarioService.update(pruebaUsuario);

            return ResponseEntity.ok()
                                 .body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(
                    pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prusId}")
    public ResponseEntity<?> delete(@PathVariable("prusId")
    Integer prusId) throws Exception {
        log.debug("Request to delete PruebaUsuario");

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioService.findById(prusId)
                                                              .get();

            pruebaUsuarioService.delete(pruebaUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(pruebaUsuarioService.count());
    }
}
