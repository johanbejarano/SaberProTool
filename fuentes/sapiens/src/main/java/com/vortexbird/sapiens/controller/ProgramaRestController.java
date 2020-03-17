package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.ProgramaDTO;
import com.vortexbird.sapiens.mapper.ProgramaMapper;
import com.vortexbird.sapiens.service.ProgramaService;

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
@RequestMapping("/api/programa")
@CrossOrigin(origins = "*")
public class ProgramaRestController {
    private static final Logger log = LoggerFactory.getLogger(ProgramaRestController.class);
    @Autowired
    private ProgramaService programaService;
    @Autowired
    private ProgramaMapper programaMapper;

    @GetMapping(value = "/findById/{progId}")
    public ResponseEntity<?> findById(@PathVariable("progId")
    Integer progId) {
        log.debug("Request to findById() Programa");

        try {
            Programa programa = programaService.findById(progId).get();

            return ResponseEntity.ok()
                                 .body(programaMapper.programaToProgramaDTO(
                    programa));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Programa");

        try {
            return ResponseEntity.ok()
                                 .body(programaMapper.listProgramaToListProgramaDTO(
                    programaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    ProgramaDTO programaDTO) {
        log.debug("Request to save Programa: {}", programaDTO);

        try {
            Programa programa = programaMapper.programaDTOToPrograma(programaDTO);
            programa = programaService.save(programa);

            return ResponseEntity.ok()
                                 .body(programaMapper.programaToProgramaDTO(
                    programa));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    ProgramaDTO programaDTO) {
        log.debug("Request to update Programa: {}", programaDTO);

        try {
            Programa programa = programaMapper.programaDTOToPrograma(programaDTO);
            programa = programaService.update(programa);

            return ResponseEntity.ok()
                                 .body(programaMapper.programaToProgramaDTO(
                    programa));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{progId}")
    public ResponseEntity<?> delete(@PathVariable("progId")
    Integer progId) throws Exception {
        log.debug("Request to delete Programa");

        try {
            Programa programa = programaService.findById(progId).get();

            programaService.delete(programa);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(programaService.count());
    }
}
