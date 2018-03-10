package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPruebaProgramaUsuarioPreguntaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioPreguntaDTO;

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
@RequestMapping("/pruebaProgramaUsuarioPregunta")
public class PruebaProgramaUsuarioPreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaProgramaUsuarioPreguntaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPruebaProgramaUsuarioPreguntaMapper pruebaProgramaUsuarioPreguntaMapper;

    @PostMapping(value = "/savePruebaProgramaUsuarioPregunta")
    public void savePruebaProgramaUsuarioPregunta(
        @RequestBody
    PruebaProgramaUsuarioPreguntaDTO pruebaProgramaUsuarioPreguntaDTO)
        throws Exception {
        try {
            PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = pruebaProgramaUsuarioPreguntaMapper.pruebaProgramaUsuarioPreguntaDTOToPruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPreguntaDTO);

            businessDelegatorView.savePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePruebaProgramaUsuarioPregunta/{idPruebaProgramaUsuarioPregunta}")
    public void deletePruebaProgramaUsuarioPregunta(
        @PathVariable("idPruebaProgramaUsuarioPregunta")
    Long idPruebaProgramaUsuarioPregunta) throws Exception {
        try {
            PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);

            businessDelegatorView.deletePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePruebaProgramaUsuarioPregunta/")
    public void updatePruebaProgramaUsuarioPregunta(
        @RequestBody
    PruebaProgramaUsuarioPreguntaDTO pruebaProgramaUsuarioPreguntaDTO)
        throws Exception {
        try {
            PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = pruebaProgramaUsuarioPreguntaMapper.pruebaProgramaUsuarioPreguntaDTOToPruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPreguntaDTO);

            businessDelegatorView.updatePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPruebaProgramaUsuarioPregunta")
    public List<PruebaProgramaUsuarioPreguntaDTO> getDataPruebaProgramaUsuarioPregunta()
        throws Exception {
        try {
            return businessDelegatorView.getDataPruebaProgramaUsuarioPregunta();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPruebaProgramaUsuarioPregunta/{idPruebaProgramaUsuarioPregunta}")
    public PruebaProgramaUsuarioPreguntaDTO getPruebaProgramaUsuarioPregunta(
        @PathVariable("idPruebaProgramaUsuarioPregunta")
    Long idPruebaProgramaUsuarioPregunta) throws Exception {
        try {
            PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);

            return pruebaProgramaUsuarioPreguntaMapper.pruebaProgramaUsuarioPreguntaToPruebaProgramaUsuarioPreguntaDTO(pruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
