package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.GrupoUsuarioDTO;
import com.vortexbird.sapiens.mapper.GrupoUsuarioMapper;
import com.vortexbird.sapiens.service.GrupoUsuarioService;

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
@RequestMapping("/api/grupoUsuario")
@CrossOrigin(origins = "*")
public class GrupoUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(GrupoUsuarioRestController.class);
    @Autowired
    private GrupoUsuarioService grupoUsuarioService;
    @Autowired
    private GrupoUsuarioMapper grupoUsuarioMapper;

    @GetMapping(value = "/findById/{grusId}")
    public ResponseEntity<?> findById(@PathVariable("grusId")
    Long grusId) {
        log.debug("Request to findById() GrupoUsuario");

        try {
            GrupoUsuario grupoUsuario = grupoUsuarioService.findById(grusId)
                                                           .get();

            return ResponseEntity.ok()
                                 .body(grupoUsuarioMapper.grupoUsuarioToGrupoUsuarioDTO(
                    grupoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() GrupoUsuario");

        try {
            return ResponseEntity.ok()
                                 .body(grupoUsuarioMapper.listGrupoUsuarioToListGrupoUsuarioDTO(
                    grupoUsuarioService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    GrupoUsuarioDTO grupoUsuarioDTO) {
        log.debug("Request to save GrupoUsuario: {}", grupoUsuarioDTO);

        try {
            GrupoUsuario grupoUsuario = grupoUsuarioMapper.grupoUsuarioDTOToGrupoUsuario(grupoUsuarioDTO);
            grupoUsuario = grupoUsuarioService.save(grupoUsuario);

            return ResponseEntity.ok()
                                 .body(grupoUsuarioMapper.grupoUsuarioToGrupoUsuarioDTO(
                    grupoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    GrupoUsuarioDTO grupoUsuarioDTO) {
        log.debug("Request to update GrupoUsuario: {}", grupoUsuarioDTO);

        try {
            GrupoUsuario grupoUsuario = grupoUsuarioMapper.grupoUsuarioDTOToGrupoUsuario(grupoUsuarioDTO);
            grupoUsuario = grupoUsuarioService.update(grupoUsuario);

            return ResponseEntity.ok()
                                 .body(grupoUsuarioMapper.grupoUsuarioToGrupoUsuarioDTO(
                    grupoUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{grusId}")
    public ResponseEntity<?> delete(@PathVariable("grusId")
    Long grusId) throws Exception {
        log.debug("Request to delete GrupoUsuario");

        try {
            GrupoUsuario grupoUsuario = grupoUsuarioService.findById(grusId)
                                                           .get();

            grupoUsuarioService.delete(grupoUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(grupoUsuarioService.count());
    }
}
