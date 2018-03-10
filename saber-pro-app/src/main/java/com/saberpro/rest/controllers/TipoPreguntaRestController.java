package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.ITipoPreguntaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.TipoPreguntaDTO;

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
@RequestMapping("/tipoPregunta")
public class TipoPreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoPreguntaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoPreguntaMapper tipoPreguntaMapper;

    @PostMapping(value = "/saveTipoPregunta")
    public void saveTipoPregunta(@RequestBody
    TipoPreguntaDTO tipoPreguntaDTO) throws Exception {
        try {
            TipoPregunta tipoPregunta = tipoPreguntaMapper.tipoPreguntaDTOToTipoPregunta(tipoPreguntaDTO);

            businessDelegatorView.saveTipoPregunta(tipoPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoPregunta/{idTipoPregunta}")
    public void deleteTipoPregunta(
        @PathVariable("idTipoPregunta")
    Long idTipoPregunta) throws Exception {
        try {
            TipoPregunta tipoPregunta = businessDelegatorView.getTipoPregunta(idTipoPregunta);

            businessDelegatorView.deleteTipoPregunta(tipoPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoPregunta/")
    public void updateTipoPregunta(@RequestBody
    TipoPreguntaDTO tipoPreguntaDTO) throws Exception {
        try {
            TipoPregunta tipoPregunta = tipoPreguntaMapper.tipoPreguntaDTOToTipoPregunta(tipoPreguntaDTO);

            businessDelegatorView.updateTipoPregunta(tipoPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoPregunta")
    public List<TipoPreguntaDTO> getDataTipoPregunta()
        throws Exception {
        try {
            return businessDelegatorView.getDataTipoPregunta();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoPregunta/{idTipoPregunta}")
    public TipoPreguntaDTO getTipoPregunta(
        @PathVariable("idTipoPregunta")
    Long idTipoPregunta) throws Exception {
        try {
            TipoPregunta tipoPregunta = businessDelegatorView.getTipoPregunta(idTipoPregunta);

            return tipoPreguntaMapper.tipoPreguntaToTipoPreguntaDTO(tipoPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
