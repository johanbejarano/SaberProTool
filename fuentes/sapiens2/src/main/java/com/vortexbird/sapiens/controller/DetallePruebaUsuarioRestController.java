package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioDTO;
import com.vortexbird.sapiens.mapper.DetallePruebaUsuarioMapper;
import com.vortexbird.sapiens.service.DetallePruebaUsuarioService;

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
@RequestMapping("/api/detallePruebaUsuario")
@CrossOrigin(origins = "*")
public class DetallePruebaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioRestController.class);
    @Autowired
    private DetallePruebaUsuarioService detallePruebaUsuarioService;
    @Autowired
    private DetallePruebaUsuarioMapper detallePruebaUsuarioMapper;

    @GetMapping(value = "/findById/{dpruId}")
    public ResponseEntity<?> findById(@PathVariable("dpruId")
    Integer dpruId) {
        log.debug("Request to findById() DetallePruebaUsuario");

        try {
            DetallePruebaUsuario detallePruebaUsuario = detallePruebaUsuarioService.findById(dpruId)
                                                                                   .get();

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioMapper.detallePruebaUsuarioToDetallePruebaUsuarioDTO(
                    detallePruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() DetallePruebaUsuario");

        try {
            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioMapper.listDetallePruebaUsuarioToListDetallePruebaUsuarioDTO(
                    detallePruebaUsuarioService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(
        @RequestBody
    DetallePruebaUsuarioDTO detallePruebaUsuarioDTO) {
        log.debug("Request to save DetallePruebaUsuario: {}",
            detallePruebaUsuarioDTO);

        try {
            DetallePruebaUsuario detallePruebaUsuario = detallePruebaUsuarioMapper.detallePruebaUsuarioDTOToDetallePruebaUsuario(detallePruebaUsuarioDTO);
            detallePruebaUsuario = detallePruebaUsuarioService.save(detallePruebaUsuario);

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioMapper.detallePruebaUsuarioToDetallePruebaUsuarioDTO(
                    detallePruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(
        @RequestBody
    DetallePruebaUsuarioDTO detallePruebaUsuarioDTO) {
        log.debug("Request to update DetallePruebaUsuario: {}",
            detallePruebaUsuarioDTO);

        try {
            DetallePruebaUsuario detallePruebaUsuario = detallePruebaUsuarioMapper.detallePruebaUsuarioDTOToDetallePruebaUsuario(detallePruebaUsuarioDTO);
            detallePruebaUsuario = detallePruebaUsuarioService.update(detallePruebaUsuario);

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioMapper.detallePruebaUsuarioToDetallePruebaUsuarioDTO(
                    detallePruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{dpruId}")
    public ResponseEntity<?> delete(@PathVariable("dpruId")
    Integer dpruId) throws Exception {
        log.debug("Request to delete DetallePruebaUsuario");

        try {
            DetallePruebaUsuario detallePruebaUsuario = detallePruebaUsuarioService.findById(dpruId)
                                                                                   .get();

            detallePruebaUsuarioService.delete(detallePruebaUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(detallePruebaUsuarioService.count());
    }
}
