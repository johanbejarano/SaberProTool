package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.TipoPruebaDTO;
import com.vortexbird.sapiens.mapper.TipoPruebaMapper;
import com.vortexbird.sapiens.service.TipoPruebaService;

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
@RequestMapping("/api/tipoPrueba")
@CrossOrigin(origins = "*")
public class TipoPruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoPruebaRestController.class);
    @Autowired
    private TipoPruebaService tipoPruebaService;
    @Autowired
    private TipoPruebaMapper tipoPruebaMapper;

    @GetMapping(value = "/findById/{tiprId}")
    public ResponseEntity<?> findById(@PathVariable("tiprId")
    Integer tiprId) {
        log.debug("Request to findById() TipoPrueba");

        try {
            TipoPrueba tipoPrueba = tipoPruebaService.findById(tiprId).get();

            return ResponseEntity.ok()
                                 .body(tipoPruebaMapper.tipoPruebaToTipoPruebaDTO(
                    tipoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() TipoPrueba");

        try {
            return ResponseEntity.ok()
                                 .body(tipoPruebaMapper.listTipoPruebaToListTipoPruebaDTO(
                    tipoPruebaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    TipoPruebaDTO tipoPruebaDTO) {
        log.debug("Request to save TipoPrueba: {}", tipoPruebaDTO);

        try {
            TipoPrueba tipoPrueba = tipoPruebaMapper.tipoPruebaDTOToTipoPrueba(tipoPruebaDTO);
            tipoPrueba = tipoPruebaService.save(tipoPrueba);

            return ResponseEntity.ok()
                                 .body(tipoPruebaMapper.tipoPruebaToTipoPruebaDTO(
                    tipoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    TipoPruebaDTO tipoPruebaDTO) {
        log.debug("Request to update TipoPrueba: {}", tipoPruebaDTO);

        try {
            TipoPrueba tipoPrueba = tipoPruebaMapper.tipoPruebaDTOToTipoPrueba(tipoPruebaDTO);
            tipoPrueba = tipoPruebaService.update(tipoPrueba);

            return ResponseEntity.ok()
                                 .body(tipoPruebaMapper.tipoPruebaToTipoPruebaDTO(
                    tipoPrueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{tiprId}")
    public ResponseEntity<?> delete(@PathVariable("tiprId")
    Integer tiprId) throws Exception {
        log.debug("Request to delete TipoPrueba");

        try {
            TipoPrueba tipoPrueba = tipoPruebaService.findById(tiprId).get();

            tipoPruebaService.delete(tipoPrueba);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(tipoPruebaService.count());
    }
}
