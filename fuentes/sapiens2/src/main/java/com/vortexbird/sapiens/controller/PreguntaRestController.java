package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.PreguntaDTO;
import com.vortexbird.sapiens.mapper.PreguntaMapper;
import com.vortexbird.sapiens.service.PreguntaService;

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
@RequestMapping("/api/pregunta")
@CrossOrigin(origins = "*")
public class PreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(PreguntaRestController.class);
    @Autowired
    private PreguntaService preguntaService;
    @Autowired
    private PreguntaMapper preguntaMapper;

    @GetMapping(value = "/findById/{pregId}")
    public ResponseEntity<?> findById(@PathVariable("pregId")
    Integer pregId) {
        log.debug("Request to findById() Pregunta");

        try {
            Pregunta pregunta = preguntaService.findById(pregId).get();

            return ResponseEntity.ok()
                                 .body(preguntaMapper.preguntaToPreguntaDTO(
                    pregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Pregunta");

        try {
            return ResponseEntity.ok()
                                 .body(preguntaMapper.listPreguntaToListPreguntaDTO(
                    preguntaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    PreguntaDTO preguntaDTO) {
        log.debug("Request to save Pregunta: {}", preguntaDTO);

        try {
            Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);
            pregunta = preguntaService.save(pregunta);

            return ResponseEntity.ok()
                                 .body(preguntaMapper.preguntaToPreguntaDTO(
                    pregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    PreguntaDTO preguntaDTO) {
        log.debug("Request to update Pregunta: {}", preguntaDTO);

        try {
            Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);
            pregunta = preguntaService.update(pregunta);

            return ResponseEntity.ok()
                                 .body(preguntaMapper.preguntaToPreguntaDTO(
                    pregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{pregId}")
    public ResponseEntity<?> delete(@PathVariable("pregId")
    Integer pregId) throws Exception {
        log.debug("Request to delete Pregunta");

        try {
            Pregunta pregunta = preguntaService.findById(pregId).get();

            preguntaService.delete(pregunta);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(preguntaService.count());
    }
}
