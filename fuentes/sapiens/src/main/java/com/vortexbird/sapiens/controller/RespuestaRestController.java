package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.RespuestaDTO;
import com.vortexbird.sapiens.mapper.RespuestaMapper;
import com.vortexbird.sapiens.service.RespuestaService;

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
@RequestMapping("/api/respuesta")
@CrossOrigin(origins = "*")
public class RespuestaRestController {
    private static final Logger log = LoggerFactory.getLogger(RespuestaRestController.class);
    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private RespuestaMapper respuestaMapper;

    @GetMapping(value = "/findById/{respId}")
    public ResponseEntity<?> findById(@PathVariable("respId")
    Integer respId) {
        log.debug("Request to findById() Respuesta");

        try {
            Respuesta respuesta = respuestaService.findById(respId).get();

            return ResponseEntity.ok()
                                 .body(respuestaMapper.respuestaToRespuestaDTO(
                    respuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Respuesta");

        try {
            return ResponseEntity.ok()
                                 .body(respuestaMapper.listRespuestaToListRespuestaDTO(
                    respuestaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    RespuestaDTO respuestaDTO) {
        log.debug("Request to save Respuesta: {}", respuestaDTO);

        try {
            Respuesta respuesta = respuestaMapper.respuestaDTOToRespuesta(respuestaDTO);
            respuesta = respuestaService.save(respuesta);

            return ResponseEntity.ok()
                                 .body(respuestaMapper.respuestaToRespuestaDTO(
                    respuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    RespuestaDTO respuestaDTO) {
        log.debug("Request to update Respuesta: {}", respuestaDTO);

        try {
            Respuesta respuesta = respuestaMapper.respuestaDTOToRespuesta(respuestaDTO);
            respuesta = respuestaService.update(respuesta);

            return ResponseEntity.ok()
                                 .body(respuestaMapper.respuestaToRespuestaDTO(
                    respuesta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{respId}")
    public ResponseEntity<?> delete(@PathVariable("respId")
    Integer respId) throws Exception {
        log.debug("Request to delete Respuesta");

        try {
            Respuesta respuesta = respuestaService.findById(respId).get();

            respuestaService.delete(respuesta);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(respuestaService.count());
    }
    
    @GetMapping(value = "/findRespuestasDePregunta/{pregId}")
    public ResponseEntity<?> findRespuestasDePregunta(@PathVariable("pregId") Integer pregId) {
        log.debug("Request to findRespuestasDePregunta() Respuesta");

        try {
            return ResponseEntity.ok()
                                 .body(respuestaMapper.listRespuestaToListRespuestaDTO(
                    respuestaService.findRespuestasDePregunta(pregId)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
