package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioRespuestaDTO;
import com.vortexbird.sapiens.mapper.DetallePruebaUsuarioRespuestaMapper;
import com.vortexbird.sapiens.service.DetallePruebaUsuarioRespuestaService;

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
@RequestMapping("/api/detallePruebaUsuarioRespuesta")
@CrossOrigin(origins = "*")
public class DetallePruebaUsuarioRespuestaRestController {
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioRespuestaRestController.class);
    @Autowired
    private DetallePruebaUsuarioRespuestaService detallePruebaUsuarioRespuestaService;
    @Autowired
    private DetallePruebaUsuarioRespuestaMapper detallePruebaUsuarioRespuestaMapper;

    @GetMapping(value = "/findById/{dpurId}")
    public ResponseEntity<?> findById(@PathVariable("dpurId")
    Long dpurId) {
        log.debug("Request to findById() DetallePruebaUsuarioRespuesta");

        try {
            DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaService.findById(dpurId)
                                                                                                              .get();

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioRespuestaMapper.detallePruebaUsuarioRespuestaToDetallePruebaUsuarioRespuestaDTO(
                    detallePruebaUsuarioRespuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() DetallePruebaUsuarioRespuesta");

        try {
            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioRespuestaMapper.listDetallePruebaUsuarioRespuestaToListDetallePruebaUsuarioRespuestaDTO(
                    detallePruebaUsuarioRespuestaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(
        @RequestBody
    DetallePruebaUsuarioRespuestaDTO detallePruebaUsuarioRespuestaDTO) {
        log.debug("Request to save DetallePruebaUsuarioRespuesta: {}",
            detallePruebaUsuarioRespuestaDTO);

        try {
            DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaMapper.detallePruebaUsuarioRespuestaDTOToDetallePruebaUsuarioRespuesta(detallePruebaUsuarioRespuestaDTO);
            detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaService.save(detallePruebaUsuarioRespuesta);

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioRespuestaMapper.detallePruebaUsuarioRespuestaToDetallePruebaUsuarioRespuestaDTO(
                    detallePruebaUsuarioRespuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(
        @RequestBody
    DetallePruebaUsuarioRespuestaDTO detallePruebaUsuarioRespuestaDTO) {
        log.debug("Request to update DetallePruebaUsuarioRespuesta: {}",
            detallePruebaUsuarioRespuestaDTO);

        try {
            DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaMapper.detallePruebaUsuarioRespuestaDTOToDetallePruebaUsuarioRespuesta(detallePruebaUsuarioRespuestaDTO);
            detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaService.update(detallePruebaUsuarioRespuesta);

            return ResponseEntity.ok()
                                 .body(detallePruebaUsuarioRespuestaMapper.detallePruebaUsuarioRespuestaToDetallePruebaUsuarioRespuestaDTO(
                    detallePruebaUsuarioRespuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{dpurId}")
    public ResponseEntity<?> delete(@PathVariable("dpurId")
    Long dpurId) throws Exception {
        log.debug("Request to delete DetallePruebaUsuarioRespuesta");

        try {
            DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta = detallePruebaUsuarioRespuestaService.findById(dpurId)
                                                                                                              .get();

            detallePruebaUsuarioRespuestaService.delete(detallePruebaUsuarioRespuesta);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok()
                             .body(detallePruebaUsuarioRespuestaService.count());
    }
}
