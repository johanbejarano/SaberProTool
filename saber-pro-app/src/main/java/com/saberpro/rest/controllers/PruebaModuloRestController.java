package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPruebaModuloMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaModuloDTO;

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
@RequestMapping("/pruebaModulo")
public class PruebaModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaModuloRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPruebaModuloMapper pruebaModuloMapper;

    @PostMapping(value = "/savePruebaModulo")
    public void savePruebaModulo(@RequestBody
    PruebaModuloDTO pruebaModuloDTO) throws Exception {
        try {
            PruebaModulo pruebaModulo = pruebaModuloMapper.pruebaModuloDTOToPruebaModulo(pruebaModuloDTO);

            businessDelegatorView.savePruebaModulo(pruebaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePruebaModulo/{idPruebaModulo}")
    public void deletePruebaModulo(
        @PathVariable("idPruebaModulo")
    Long idPruebaModulo) throws Exception {
        try {
            PruebaModulo pruebaModulo = businessDelegatorView.getPruebaModulo(idPruebaModulo);

            businessDelegatorView.deletePruebaModulo(pruebaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePruebaModulo/")
    public void updatePruebaModulo(@RequestBody
    PruebaModuloDTO pruebaModuloDTO) throws Exception {
        try {
            PruebaModulo pruebaModulo = pruebaModuloMapper.pruebaModuloDTOToPruebaModulo(pruebaModuloDTO);

            businessDelegatorView.updatePruebaModulo(pruebaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPruebaModulo")
    public List<PruebaModuloDTO> getDataPruebaModulo()
        throws Exception {
        try {
            return businessDelegatorView.getDataPruebaModulo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPruebaModulo/{idPruebaModulo}")
    public PruebaModuloDTO getPruebaModulo(
        @PathVariable("idPruebaModulo")
    Long idPruebaModulo) throws Exception {
        try {
            PruebaModulo pruebaModulo = businessDelegatorView.getPruebaModulo(idPruebaModulo);

            return pruebaModuloMapper.pruebaModuloToPruebaModuloDTO(pruebaModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
