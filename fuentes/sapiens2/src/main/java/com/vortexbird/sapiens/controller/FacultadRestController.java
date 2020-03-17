package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.FacultadDTO;
import com.vortexbird.sapiens.mapper.FacultadMapper;
import com.vortexbird.sapiens.service.FacultadService;

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
@RequestMapping("/api/facultad")
@CrossOrigin(origins = "*")
public class FacultadRestController {
    private static final Logger log = LoggerFactory.getLogger(FacultadRestController.class);
    @Autowired
    private FacultadService facultadService;
    @Autowired
    private FacultadMapper facultadMapper;

    @GetMapping(value = "/findById/{facuId}")
    public ResponseEntity<?> findById(@PathVariable("facuId")
    Integer facuId) {
        log.debug("Request to findById() Facultad");

        try {
            Facultad facultad = facultadService.findById(facuId).get();

            return ResponseEntity.ok()
                                 .body(facultadMapper.facultadToFacultadDTO(
                    facultad));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Facultad");

        try {
            return ResponseEntity.ok()
                                 .body(facultadMapper.listFacultadToListFacultadDTO(
                    facultadService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    FacultadDTO facultadDTO) {
        log.debug("Request to save Facultad: {}", facultadDTO);

        try {
            Facultad facultad = facultadMapper.facultadDTOToFacultad(facultadDTO);
            facultad = facultadService.save(facultad);

            return ResponseEntity.ok()
                                 .body(facultadMapper.facultadToFacultadDTO(
                    facultad));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    FacultadDTO facultadDTO) {
        log.debug("Request to update Facultad: {}", facultadDTO);

        try {
            Facultad facultad = facultadMapper.facultadDTOToFacultad(facultadDTO);
            facultad = facultadService.update(facultad);

            return ResponseEntity.ok()
                                 .body(facultadMapper.facultadToFacultadDTO(
                    facultad));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{facuId}")
    public ResponseEntity<?> delete(@PathVariable("facuId")
    Integer facuId) throws Exception {
        log.debug("Request to delete Facultad");

        try {
            Facultad facultad = facultadService.findById(facuId).get();

            facultadService.delete(facultad);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(facultadService.count());
    }
}
