package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.ITipoPruebaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.TipoPruebaDTO;

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
@RequestMapping("/tipoPrueba")
public class TipoPruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoPruebaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoPruebaMapper tipoPruebaMapper;

    @PostMapping(value = "/saveTipoPrueba")
    public void saveTipoPrueba(@RequestBody
    TipoPruebaDTO tipoPruebaDTO) throws Exception {
        try {
            TipoPrueba tipoPrueba = tipoPruebaMapper.tipoPruebaDTOToTipoPrueba(tipoPruebaDTO);

            businessDelegatorView.saveTipoPrueba(tipoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoPrueba/{idTipoPrueba}")
    public void deleteTipoPrueba(@PathVariable("idTipoPrueba")
    Long idTipoPrueba) throws Exception {
        try {
            TipoPrueba tipoPrueba = businessDelegatorView.getTipoPrueba(idTipoPrueba);

            businessDelegatorView.deleteTipoPrueba(tipoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoPrueba/")
    public void updateTipoPrueba(@RequestBody
    TipoPruebaDTO tipoPruebaDTO) throws Exception {
        try {
            TipoPrueba tipoPrueba = tipoPruebaMapper.tipoPruebaDTOToTipoPrueba(tipoPruebaDTO);

            businessDelegatorView.updateTipoPrueba(tipoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoPrueba")
    public List<TipoPruebaDTO> getDataTipoPrueba() throws Exception {
        try {
            return businessDelegatorView.getDataTipoPrueba();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoPrueba/{idTipoPrueba}")
    public TipoPruebaDTO getTipoPrueba(
        @PathVariable("idTipoPrueba")
    Long idTipoPrueba) throws Exception {
        try {
            TipoPrueba tipoPrueba = businessDelegatorView.getTipoPrueba(idTipoPrueba);

            return tipoPruebaMapper.tipoPruebaToTipoPruebaDTO(tipoPrueba);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
