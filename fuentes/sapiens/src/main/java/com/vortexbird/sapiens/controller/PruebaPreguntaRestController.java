package com.vortexbird.sapiens.controller;

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

import com.vortexbird.sapiens.domain.PruebaPregunta;
import com.vortexbird.sapiens.dto.PruebaPreguntaDTO;
import com.vortexbird.sapiens.mapper.PruebaPreguntaMapper;
import com.vortexbird.sapiens.service.PruebaPreguntaService;


@RestController
@RequestMapping("/api/pruebaPregunta")
@CrossOrigin(origins = "*")
public class PruebaPreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaPreguntaRestController.class);
    @Autowired
    private PruebaPreguntaService pruebaPreguntaService;
    @Autowired
    private PruebaPreguntaMapper pruebaPreguntaMapper;

    @GetMapping(value = "/findById/{prprId}")
    public ResponseEntity<?> findById(@PathVariable("prprId")
    Long prprId) {
        log.debug("Request to findById() PruebaPregunta");

        try {
            PruebaPregunta pruebaPregunta = pruebaPreguntaService.findById(prprId)
                                                                 .get();

            return ResponseEntity.ok()
                                 .body(pruebaPreguntaMapper.pruebaPreguntaToPruebaPreguntaDTO(
                    pruebaPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() PruebaPregunta");

        try {
            return ResponseEntity.ok()
                                 .body(pruebaPreguntaMapper.listPruebaPreguntaToListPruebaPreguntaDTO(
                    pruebaPreguntaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(
        @RequestBody
    PruebaPreguntaDTO pruebaPreguntaDTO) {
        log.debug("Request to save PruebaPregunta: {}", pruebaPreguntaDTO);

        try {
            PruebaPregunta pruebaPregunta = pruebaPreguntaMapper.pruebaPreguntaDTOToPruebaPregunta(pruebaPreguntaDTO);
            pruebaPregunta = pruebaPreguntaService.save(pruebaPregunta);

            return ResponseEntity.ok()
                                 .body(pruebaPreguntaMapper.pruebaPreguntaToPruebaPreguntaDTO(
                    pruebaPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(
        @RequestBody
    PruebaPreguntaDTO pruebaPreguntaDTO) {
        log.debug("Request to update PruebaPregunta: {}", pruebaPreguntaDTO);

        try {
            PruebaPregunta pruebaPregunta = pruebaPreguntaMapper.pruebaPreguntaDTOToPruebaPregunta(pruebaPreguntaDTO);
            pruebaPregunta = pruebaPreguntaService.update(pruebaPregunta);

            return ResponseEntity.ok()
                                 .body(pruebaPreguntaMapper.pruebaPreguntaToPruebaPreguntaDTO(
                    pruebaPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prprId}")
    public ResponseEntity<?> delete(@PathVariable("prprId")
    Long prprId) throws Exception {
        log.debug("Request to delete PruebaPregunta");

        try {
            PruebaPregunta pruebaPregunta = pruebaPreguntaService.findById(prprId)
                                                                 .get();

            pruebaPreguntaService.delete(pruebaPregunta);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(pruebaPreguntaService.count());
    }
}
