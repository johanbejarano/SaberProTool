package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.TipoPreguntaDTO;
import com.vortexbird.sapiens.mapper.TipoPreguntaMapper;
import com.vortexbird.sapiens.service.TipoPreguntaService;

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
@RequestMapping("/api/tipoPregunta")
@CrossOrigin(origins = "*")
public class TipoPreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoPreguntaRestController.class);
    @Autowired
    private TipoPreguntaService tipoPreguntaService;
    @Autowired
    private TipoPreguntaMapper tipoPreguntaMapper;

    @GetMapping(value = "/findById/{tprgId}")
    public ResponseEntity<?> findById(@PathVariable("tprgId")
    Integer tprgId) {
        log.debug("Request to findById() TipoPregunta");

        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.findById(tprgId)
                                                           .get();

            return ResponseEntity.ok()
                                 .body(tipoPreguntaMapper.tipoPreguntaToTipoPreguntaDTO(
                    tipoPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() TipoPregunta");

        try {
            return ResponseEntity.ok()
                                 .body(tipoPreguntaMapper.listTipoPreguntaToListTipoPreguntaDTO(
                    tipoPreguntaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    TipoPreguntaDTO tipoPreguntaDTO) {
        log.debug("Request to save TipoPregunta: {}", tipoPreguntaDTO);

        try {
            TipoPregunta tipoPregunta = tipoPreguntaMapper.tipoPreguntaDTOToTipoPregunta(tipoPreguntaDTO);
            tipoPregunta = tipoPreguntaService.save(tipoPregunta);

            return ResponseEntity.ok()
                                 .body(tipoPreguntaMapper.tipoPreguntaToTipoPreguntaDTO(
                    tipoPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    TipoPreguntaDTO tipoPreguntaDTO) {
        log.debug("Request to update TipoPregunta: {}", tipoPreguntaDTO);

        try {
            TipoPregunta tipoPregunta = tipoPreguntaMapper.tipoPreguntaDTOToTipoPregunta(tipoPreguntaDTO);
            tipoPregunta = tipoPreguntaService.update(tipoPregunta);

            return ResponseEntity.ok()
                                 .body(tipoPreguntaMapper.tipoPreguntaToTipoPreguntaDTO(
                    tipoPregunta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{tprgId}")
    public ResponseEntity<?> delete(@PathVariable("tprgId")
    Integer tprgId) throws Exception {
        log.debug("Request to delete TipoPregunta");

        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.findById(tprgId)
                                                           .get();

            tipoPreguntaService.delete(tipoPregunta);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoPreguntaService.count());
    }
}
