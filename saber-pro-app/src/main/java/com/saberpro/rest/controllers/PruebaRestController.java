package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPruebaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaDTO;

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
@RequestMapping("/prueba")
public class PruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPruebaMapper pruebaMapper;

    @PostMapping(value = "/savePrueba")
    public void savePrueba(@RequestBody
    PruebaDTO pruebaDTO) throws Exception {
        try {
            Prueba prueba = pruebaMapper.pruebaDTOToPrueba(pruebaDTO);

            businessDelegatorView.savePrueba(prueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePrueba/{idPrueba}")
    public void deletePrueba(@PathVariable("idPrueba")
    Long idPrueba) throws Exception {
        try {
            Prueba prueba = businessDelegatorView.getPrueba(idPrueba);

            businessDelegatorView.deletePrueba(prueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePrueba/")
    public void updatePrueba(@RequestBody
    PruebaDTO pruebaDTO) throws Exception {
        try {
            Prueba prueba = pruebaMapper.pruebaDTOToPrueba(pruebaDTO);

            businessDelegatorView.updatePrueba(prueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPrueba")
    public List<PruebaDTO> getDataPrueba() throws Exception {
        try {
            return businessDelegatorView.getDataPrueba();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPrueba/{idPrueba}")
    public PruebaDTO getPrueba(@PathVariable("idPrueba")
    Long idPrueba) throws Exception {
        try {
            Prueba prueba = businessDelegatorView.getPrueba(idPrueba);

            return pruebaMapper.pruebaToPruebaDTO(prueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
