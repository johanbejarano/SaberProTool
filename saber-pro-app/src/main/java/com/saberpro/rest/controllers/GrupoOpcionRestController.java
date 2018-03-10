package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IGrupoOpcionMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.GrupoOpcionDTO;

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
@RequestMapping("/grupoOpcion")
public class GrupoOpcionRestController {
    private static final Logger log = LoggerFactory.getLogger(GrupoOpcionRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IGrupoOpcionMapper grupoOpcionMapper;

    @PostMapping(value = "/saveGrupoOpcion")
    public void saveGrupoOpcion(@RequestBody
    GrupoOpcionDTO grupoOpcionDTO) throws Exception {
        try {
            GrupoOpcion grupoOpcion = grupoOpcionMapper.grupoOpcionDTOToGrupoOpcion(grupoOpcionDTO);

            businessDelegatorView.saveGrupoOpcion(grupoOpcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteGrupoOpcion/{idGrupoOpcion}")
    public void deleteGrupoOpcion(
        @PathVariable("idGrupoOpcion")
    Long idGrupoOpcion) throws Exception {
        try {
            GrupoOpcion grupoOpcion = businessDelegatorView.getGrupoOpcion(idGrupoOpcion);

            businessDelegatorView.deleteGrupoOpcion(grupoOpcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateGrupoOpcion/")
    public void updateGrupoOpcion(@RequestBody
    GrupoOpcionDTO grupoOpcionDTO) throws Exception {
        try {
            GrupoOpcion grupoOpcion = grupoOpcionMapper.grupoOpcionDTOToGrupoOpcion(grupoOpcionDTO);

            businessDelegatorView.updateGrupoOpcion(grupoOpcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataGrupoOpcion")
    public List<GrupoOpcionDTO> getDataGrupoOpcion() throws Exception {
        try {
            return businessDelegatorView.getDataGrupoOpcion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getGrupoOpcion/{idGrupoOpcion}")
    public GrupoOpcionDTO getGrupoOpcion(
        @PathVariable("idGrupoOpcion")
    Long idGrupoOpcion) throws Exception {
        try {
            GrupoOpcion grupoOpcion = businessDelegatorView.getGrupoOpcion(idGrupoOpcion);

            return grupoOpcionMapper.grupoOpcionToGrupoOpcionDTO(grupoOpcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
