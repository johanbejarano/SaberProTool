package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IParametroMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ParametroDTO;

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
@RequestMapping("/parametro")
public class ParametroRestController {
    private static final Logger log = LoggerFactory.getLogger(ParametroRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IParametroMapper parametroMapper;

    @PostMapping(value = "/saveParametro")
    public void saveParametro(@RequestBody
    ParametroDTO parametroDTO) throws Exception {
        try {
            Parametro parametro = parametroMapper.parametroDTOToParametro(parametroDTO);

            businessDelegatorView.saveParametro(parametro);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteParametro/{idParametro}")
    public void deleteParametro(@PathVariable("idParametro")
    Long idParametro) throws Exception {
        try {
            Parametro parametro = businessDelegatorView.getParametro(idParametro);

            businessDelegatorView.deleteParametro(parametro);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateParametro/")
    public void updateParametro(@RequestBody
    ParametroDTO parametroDTO) throws Exception {
        try {
            Parametro parametro = parametroMapper.parametroDTOToParametro(parametroDTO);

            businessDelegatorView.updateParametro(parametro);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataParametro")
    public List<ParametroDTO> getDataParametro() throws Exception {
        try {
            return businessDelegatorView.getDataParametro();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getParametro/{idParametro}")
    public ParametroDTO getParametro(
        @PathVariable("idParametro")
    Long idParametro) throws Exception {
        try {
            Parametro parametro = businessDelegatorView.getParametro(idParametro);

            return parametroMapper.parametroToParametroDTO(parametro);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
