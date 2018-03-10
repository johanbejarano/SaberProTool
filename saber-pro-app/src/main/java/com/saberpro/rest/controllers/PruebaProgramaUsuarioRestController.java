package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPruebaProgramaUsuarioMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioDTO;

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
@RequestMapping("/pruebaProgramaUsuario")
public class PruebaProgramaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaProgramaUsuarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPruebaProgramaUsuarioMapper pruebaProgramaUsuarioMapper;

    @PostMapping(value = "/savePruebaProgramaUsuario")
    public void savePruebaProgramaUsuario(
        @RequestBody
    PruebaProgramaUsuarioDTO pruebaProgramaUsuarioDTO)
        throws Exception {
        try {
            PruebaProgramaUsuario pruebaProgramaUsuario = pruebaProgramaUsuarioMapper.pruebaProgramaUsuarioDTOToPruebaProgramaUsuario(pruebaProgramaUsuarioDTO);

            businessDelegatorView.savePruebaProgramaUsuario(pruebaProgramaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePruebaProgramaUsuario/{idPruebaProgramaUsuario}")
    public void deletePruebaProgramaUsuario(
        @PathVariable("idPruebaProgramaUsuario")
    Long idPruebaProgramaUsuario) throws Exception {
        try {
            PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario);

            businessDelegatorView.deletePruebaProgramaUsuario(pruebaProgramaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePruebaProgramaUsuario/")
    public void updatePruebaProgramaUsuario(
        @RequestBody
    PruebaProgramaUsuarioDTO pruebaProgramaUsuarioDTO)
        throws Exception {
        try {
            PruebaProgramaUsuario pruebaProgramaUsuario = pruebaProgramaUsuarioMapper.pruebaProgramaUsuarioDTOToPruebaProgramaUsuario(pruebaProgramaUsuarioDTO);

            businessDelegatorView.updatePruebaProgramaUsuario(pruebaProgramaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPruebaProgramaUsuario")
    public List<PruebaProgramaUsuarioDTO> getDataPruebaProgramaUsuario()
        throws Exception {
        try {
            return businessDelegatorView.getDataPruebaProgramaUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPruebaProgramaUsuario/{idPruebaProgramaUsuario}")
    public PruebaProgramaUsuarioDTO getPruebaProgramaUsuario(
        @PathVariable("idPruebaProgramaUsuario")
    Long idPruebaProgramaUsuario) throws Exception {
        try {
            PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario);

            return pruebaProgramaUsuarioMapper.pruebaProgramaUsuarioToPruebaProgramaUsuarioDTO(pruebaProgramaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
