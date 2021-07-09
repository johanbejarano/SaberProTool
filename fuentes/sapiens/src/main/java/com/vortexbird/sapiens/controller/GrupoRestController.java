package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.GrupoDTO;
import com.vortexbird.sapiens.mapper.GrupoMapper;
import com.vortexbird.sapiens.service.GrupoService;

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
@RequestMapping("/api/grupo")
@CrossOrigin(origins = "*")
public class GrupoRestController {
    private static final Logger log = LoggerFactory.getLogger(GrupoRestController.class);
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private GrupoMapper grupoMapper;

    @GetMapping(value = "/findById/{grupId}")
    public ResponseEntity<?> findById(@PathVariable("grupId")
    Long grupId) {
        log.debug("Request to findById() Grupo");

        try {
            Grupo grupo = grupoService.findById(grupId).get();

            return ResponseEntity.ok().body(grupoMapper.grupoToGrupoDTO(grupo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Grupo");

        try {
            return ResponseEntity.ok()
                                 .body(grupoMapper.listGrupoToListGrupoDTO(
                    grupoService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    GrupoDTO grupoDTO) {
        log.debug("Request to save Grupo: {}", grupoDTO);

        try {
            Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
            grupo = grupoService.save(grupo);

            return ResponseEntity.ok().body(grupoMapper.grupoToGrupoDTO(grupo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    GrupoDTO grupoDTO) {
        log.debug("Request to update Grupo: {}", grupoDTO);

        try {
            Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
            grupo = grupoService.update(grupo);

            return ResponseEntity.ok().body(grupoMapper.grupoToGrupoDTO(grupo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{grupId}")
    public ResponseEntity<?> delete(@PathVariable("grupId")
    Long grupId) throws Exception {
        log.debug("Request to delete Grupo");

        try {
            Grupo grupo = grupoService.findById(grupId).get();

            grupoService.delete(grupo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(grupoService.count());
    }
    
    @GetMapping(value = "/consultarGrupos")
    public ResponseEntity<?> consultarGrupos() {
        log.debug("Request to consultarGrupos() Grupo");

        try {
            return ResponseEntity.ok()
                                 .body(grupoMapper.listGrupoToListGrupoDTO(
                    grupoService.consultarGrupos()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/guardarGrupo")
    public ResponseEntity<?> guardarGrupo(@RequestBody GrupoDTO grupoDTO) {
        log.debug("Request to save Grupo: {}", grupoDTO);

        try {

            return ResponseEntity.ok().body(grupoService.guardarGrupo(grupoDTO));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/actualizarGrupo")
    public ResponseEntity<?> actualizarGrupo(@RequestBody GrupoDTO grupoDTO) {
        log.debug("Request to update Grupo: {}", grupoDTO);

        try {

            return ResponseEntity.ok().body(grupoService.actualizarGrupo(grupoDTO));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
