package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.ProgramaModuloDTO;
import com.vortexbird.sapiens.mapper.ProgramaModuloMapper;
import com.vortexbird.sapiens.service.ProgramaModuloService;

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
@RequestMapping("/api/programaModulo")
@CrossOrigin(origins = "*")
public class ProgramaModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(ProgramaModuloRestController.class);
    @Autowired
    private ProgramaModuloService programaModuloService;
    @Autowired
    private ProgramaModuloMapper programaModuloMapper;

    @GetMapping(value = "/findById/{prmoId}")
    public ResponseEntity<?> findById(@PathVariable("prmoId")
    Integer prmoId) {
        log.debug("Request to findById() ProgramaModulo");

        try {
            ProgramaModulo programaModulo = programaModuloService.findById(prmoId)
                                                                 .get();

            return ResponseEntity.ok()
                                 .body(programaModuloMapper.programaModuloToProgramaModuloDTO(
                    programaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() ProgramaModulo");

        try {
            return ResponseEntity.ok()
                                 .body(programaModuloMapper.listProgramaModuloToListProgramaModuloDTO(
                    programaModuloService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(
        @RequestBody
    ProgramaModuloDTO programaModuloDTO) {
        log.debug("Request to save ProgramaModulo: {}", programaModuloDTO);

        try {
            ProgramaModulo programaModulo = programaModuloMapper.programaModuloDTOToProgramaModulo(programaModuloDTO);
            programaModulo = programaModuloService.save(programaModulo);

            return ResponseEntity.ok()
                                 .body(programaModuloMapper.programaModuloToProgramaModuloDTO(
                    programaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(
        @RequestBody
    ProgramaModuloDTO programaModuloDTO) {
        log.debug("Request to update ProgramaModulo: {}", programaModuloDTO);

        try {
            ProgramaModulo programaModulo = programaModuloMapper.programaModuloDTOToProgramaModulo(programaModuloDTO);
            programaModulo = programaModuloService.update(programaModulo);

            return ResponseEntity.ok()
                                 .body(programaModuloMapper.programaModuloToProgramaModuloDTO(
                    programaModulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prmoId}")
    public ResponseEntity<?> delete(@PathVariable("prmoId")
    Integer prmoId) throws Exception {
        log.debug("Request to delete ProgramaModulo");

        try {
            ProgramaModulo programaModulo = programaModuloService.findById(prmoId)
                                                                 .get();

            programaModuloService.delete(programaModulo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(programaModuloService.count());
    }
}
