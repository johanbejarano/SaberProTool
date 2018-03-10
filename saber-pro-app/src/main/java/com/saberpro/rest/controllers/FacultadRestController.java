package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IFacultadMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.FacultadDTO;

import com.saberpro.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/facultad")
public class FacultadRestController {
    private static final Logger log = LoggerFactory.getLogger(FacultadRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IFacultadMapper facultadMapper;

    @PostMapping(value = "/saveFacultad")
    public void saveFacultad(@RequestBody
    FacultadDTO facultadDTO) throws Exception {
        try {
            Facultad facultad = facultadMapper.facultadDTOToFacultad(facultadDTO);

            businessDelegatorView.saveFacultad(facultad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteFacultad/{idFacultad}")
    public void deleteFacultad(@PathVariable("idFacultad")
    Long idFacultad) throws Exception {
        try {
            Facultad facultad = businessDelegatorView.getFacultad(idFacultad);

            businessDelegatorView.deleteFacultad(facultad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateFacultad/")
    public void updateFacultad(@RequestBody
    FacultadDTO facultadDTO) throws Exception {
        try {
            Facultad facultad = facultadMapper.facultadDTOToFacultad(facultadDTO);

            businessDelegatorView.updateFacultad(facultad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataFacultad")
    public List<FacultadDTO> getDataFacultad() throws Exception {
        try {
            return businessDelegatorView.getDataFacultad();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getFacultad/{idFacultad}")
    public FacultadDTO getFacultad(@PathVariable("idFacultad")
    Long idFacultad) throws Exception {
        try {
            Facultad facultad = businessDelegatorView.getFacultad(idFacultad);

            return facultadMapper.facultadToFacultadDTO(facultad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
