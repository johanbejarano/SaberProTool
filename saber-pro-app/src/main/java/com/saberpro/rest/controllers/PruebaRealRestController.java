package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPruebaRealMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaRealDTO;

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
@RequestMapping("/pruebaReal")
public class PruebaRealRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaRealRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPruebaRealMapper pruebaRealMapper;

    @PostMapping(value = "/savePruebaReal")
    public void savePruebaReal(@RequestBody
    PruebaRealDTO pruebaRealDTO) throws Exception {
        try {
            PruebaReal pruebaReal = pruebaRealMapper.pruebaRealDTOToPruebaReal(pruebaRealDTO);

            businessDelegatorView.savePruebaReal(pruebaReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePruebaReal/{idPruebaReal}")
    public void deletePruebaReal(@PathVariable("idPruebaReal")
    Long idPruebaReal) throws Exception {
        try {
            PruebaReal pruebaReal = businessDelegatorView.getPruebaReal(idPruebaReal);

            businessDelegatorView.deletePruebaReal(pruebaReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePruebaReal/")
    public void updatePruebaReal(@RequestBody
    PruebaRealDTO pruebaRealDTO) throws Exception {
        try {
            PruebaReal pruebaReal = pruebaRealMapper.pruebaRealDTOToPruebaReal(pruebaRealDTO);

            businessDelegatorView.updatePruebaReal(pruebaReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPruebaReal")
    public List<PruebaRealDTO> getDataPruebaReal() throws Exception {
        try {
            return businessDelegatorView.getDataPruebaReal();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPruebaReal/{idPruebaReal}")
    public PruebaRealDTO getPruebaReal(
        @PathVariable("idPruebaReal")
    Long idPruebaReal) throws Exception {
        try {
            PruebaReal pruebaReal = businessDelegatorView.getPruebaReal(idPruebaReal);

            return pruebaRealMapper.pruebaRealToPruebaRealDTO(pruebaReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
