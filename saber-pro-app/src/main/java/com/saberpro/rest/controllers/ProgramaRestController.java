package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IProgramaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaDTO;

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
@RequestMapping("/programa")
public class ProgramaRestController {
    private static final Logger log = LoggerFactory.getLogger(ProgramaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IProgramaMapper programaMapper;

    @PostMapping(value = "/savePrograma")
    public void savePrograma(@RequestBody
    ProgramaDTO programaDTO) throws Exception {
        try {
            Programa programa = programaMapper.programaDTOToPrograma(programaDTO);

            businessDelegatorView.savePrograma(programa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePrograma/{idPrograma}")
    public void deletePrograma(@PathVariable("idPrograma")
    Long idPrograma) throws Exception {
        try {
            Programa programa = businessDelegatorView.getPrograma(idPrograma);

            businessDelegatorView.deletePrograma(programa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePrograma/")
    public void updatePrograma(@RequestBody
    ProgramaDTO programaDTO) throws Exception {
        try {
            Programa programa = programaMapper.programaDTOToPrograma(programaDTO);

            businessDelegatorView.updatePrograma(programa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPrograma")
    public List<ProgramaDTO> getDataPrograma() throws Exception {
        try {
            return businessDelegatorView.getDataPrograma();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPrograma/{idPrograma}")
    public ProgramaDTO getPrograma(@PathVariable("idPrograma")
    Long idPrograma) throws Exception {
        try {
            Programa programa = businessDelegatorView.getPrograma(idPrograma);

            return programaMapper.programaToProgramaDTO(programa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
