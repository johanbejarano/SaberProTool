package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IProgramaUsuarioMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaUsuarioDTO;

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
@RequestMapping("/programaUsuario")
public class ProgramaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(ProgramaUsuarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IProgramaUsuarioMapper programaUsuarioMapper;

    @PostMapping(value = "/saveProgramaUsuario")
    public void saveProgramaUsuario(
        @RequestBody
    ProgramaUsuarioDTO programaUsuarioDTO) throws Exception {
        try {
            ProgramaUsuario programaUsuario = programaUsuarioMapper.programaUsuarioDTOToProgramaUsuario(programaUsuarioDTO);

            businessDelegatorView.saveProgramaUsuario(programaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteProgramaUsuario/{idProgramaUsuario}")
    public void deleteProgramaUsuario(
        @PathVariable("idProgramaUsuario")
    Long idProgramaUsuario) throws Exception {
        try {
            ProgramaUsuario programaUsuario = businessDelegatorView.getProgramaUsuario(idProgramaUsuario);

            businessDelegatorView.deleteProgramaUsuario(programaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateProgramaUsuario/")
    public void updateProgramaUsuario(
        @RequestBody
    ProgramaUsuarioDTO programaUsuarioDTO) throws Exception {
        try {
            ProgramaUsuario programaUsuario = programaUsuarioMapper.programaUsuarioDTOToProgramaUsuario(programaUsuarioDTO);

            businessDelegatorView.updateProgramaUsuario(programaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataProgramaUsuario")
    public List<ProgramaUsuarioDTO> getDataProgramaUsuario()
        throws Exception {
        try {
            return businessDelegatorView.getDataProgramaUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getProgramaUsuario/{idProgramaUsuario}")
    public ProgramaUsuarioDTO getProgramaUsuario(
        @PathVariable("idProgramaUsuario")
    Long idProgramaUsuario) throws Exception {
        try {
            ProgramaUsuario programaUsuario = businessDelegatorView.getProgramaUsuario(idProgramaUsuario);

            return programaUsuarioMapper.programaUsuarioToProgramaUsuarioDTO(programaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
