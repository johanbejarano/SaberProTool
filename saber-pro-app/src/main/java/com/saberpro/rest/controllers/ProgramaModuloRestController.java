package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IProgramaModuloMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaModuloDTO;

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
@RequestMapping("/programaModulo")
public class ProgramaModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(ProgramaModuloRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IProgramaModuloMapper programaModuloMapper;

    @PostMapping(value = "/saveProgramaModulo")
    public void saveProgramaModulo(
        @RequestBody
    ProgramaModuloDTO programaModuloDTO) throws Exception {
        try {
            ProgramaModulo programaModulo = programaModuloMapper.programaModuloDTOToProgramaModulo(programaModuloDTO);

            businessDelegatorView.saveProgramaModulo(programaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteProgramaModulo/{idProgramaModulo}")
    public void deleteProgramaModulo(
        @PathVariable("idProgramaModulo")
    Long idProgramaModulo) throws Exception {
        try {
            ProgramaModulo programaModulo = businessDelegatorView.getProgramaModulo(idProgramaModulo);

            businessDelegatorView.deleteProgramaModulo(programaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateProgramaModulo/")
    public void updateProgramaModulo(
        @RequestBody
    ProgramaModuloDTO programaModuloDTO) throws Exception {
        try {
            ProgramaModulo programaModulo = programaModuloMapper.programaModuloDTOToProgramaModulo(programaModuloDTO);

            businessDelegatorView.updateProgramaModulo(programaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataProgramaModulo")
    public List<ProgramaModuloDTO> getDataProgramaModulo()
        throws Exception {
        try {
            return businessDelegatorView.getDataProgramaModulo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getProgramaModulo/{idProgramaModulo}")
    public ProgramaModuloDTO getProgramaModulo(
        @PathVariable("idProgramaModulo")
    Long idProgramaModulo) throws Exception {
        try {
            ProgramaModulo programaModulo = businessDelegatorView.getProgramaModulo(idProgramaModulo);

            return programaModuloMapper.programaModuloToProgramaModuloDTO(programaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
