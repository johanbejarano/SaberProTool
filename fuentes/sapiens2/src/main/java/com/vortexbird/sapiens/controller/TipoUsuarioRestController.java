package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.TipoUsuarioDTO;
import com.vortexbird.sapiens.mapper.TipoUsuarioMapper;
import com.vortexbird.sapiens.service.TipoUsuarioService;

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
@RequestMapping("/api/tipoUsuario")
@CrossOrigin(origins = "*")
public class TipoUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoUsuarioRestController.class);
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private TipoUsuarioMapper tipoUsuarioMapper;

    @GetMapping(value = "/findById/{tiusId}")
    public ResponseEntity<?> findById(@PathVariable("tiusId")
    Integer tiusId) {
        log.debug("Request to findById() TipoUsuario");

        try {
            TipoUsuario tipoUsuario = tipoUsuarioService.findById(tiusId).get();

            return ResponseEntity.ok()
                                 .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                    tipoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() TipoUsuario");

        try {
            return ResponseEntity.ok()
                                 .body(tipoUsuarioMapper.listTipoUsuarioToListTipoUsuarioDTO(
                    tipoUsuarioService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) {
        log.debug("Request to save TipoUsuario: {}", tipoUsuarioDTO);

        try {
            TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
            tipoUsuario = tipoUsuarioService.save(tipoUsuario);

            return ResponseEntity.ok()
                                 .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                    tipoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) {
        log.debug("Request to update TipoUsuario: {}", tipoUsuarioDTO);

        try {
            TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);
            tipoUsuario = tipoUsuarioService.update(tipoUsuario);

            return ResponseEntity.ok()
                                 .body(tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(
                    tipoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{tiusId}")
    public ResponseEntity<?> delete(@PathVariable("tiusId")
    Integer tiusId) throws Exception {
        log.debug("Request to delete TipoUsuario");

        try {
            TipoUsuario tipoUsuario = tipoUsuarioService.findById(tiusId).get();

            tipoUsuarioService.delete(tipoUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoUsuarioService.count());
    }
}
