package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IEstadoPruebaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.EstadoPruebaDTO;

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
@RequestMapping("/estadoPrueba")
public class EstadoPruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(EstadoPruebaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IEstadoPruebaMapper estadoPruebaMapper;

    @PostMapping(value = "/saveEstadoPrueba")
    public void saveEstadoPrueba(@RequestBody
    EstadoPruebaDTO estadoPruebaDTO) throws Exception {
        try {
            EstadoPrueba estadoPrueba = estadoPruebaMapper.estadoPruebaDTOToEstadoPrueba(estadoPruebaDTO);

            businessDelegatorView.saveEstadoPrueba(estadoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteEstadoPrueba/{idEstadoPrueba}")
    public void deleteEstadoPrueba(
        @PathVariable("idEstadoPrueba")
    Long idEstadoPrueba) throws Exception {
        try {
            EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(idEstadoPrueba);

            businessDelegatorView.deleteEstadoPrueba(estadoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateEstadoPrueba/")
    public void updateEstadoPrueba(@RequestBody
    EstadoPruebaDTO estadoPruebaDTO) throws Exception {
        try {
            EstadoPrueba estadoPrueba = estadoPruebaMapper.estadoPruebaDTOToEstadoPrueba(estadoPruebaDTO);

            businessDelegatorView.updateEstadoPrueba(estadoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataEstadoPrueba")
    public List<EstadoPruebaDTO> getDataEstadoPrueba()
        throws Exception {
        try {
            return businessDelegatorView.getDataEstadoPrueba();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getEstadoPrueba/{idEstadoPrueba}")
    public EstadoPruebaDTO getEstadoPrueba(
        @PathVariable("idEstadoPrueba")
    Long idEstadoPrueba) throws Exception {
        try {
            EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(idEstadoPrueba);

            return estadoPruebaMapper.estadoPruebaToEstadoPruebaDTO(estadoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
