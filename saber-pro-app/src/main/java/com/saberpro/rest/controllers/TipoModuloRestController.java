package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.ITipoModuloMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.TipoModuloDTO;

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
@RequestMapping("/tipoModulo")
public class TipoModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoModuloRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoModuloMapper tipoModuloMapper;

    @PostMapping(value = "/saveTipoModulo")
    public void saveTipoModulo(@RequestBody
    TipoModuloDTO tipoModuloDTO) throws Exception {
        try {
            TipoModulo tipoModulo = tipoModuloMapper.tipoModuloDTOToTipoModulo(tipoModuloDTO);

            businessDelegatorView.saveTipoModulo(tipoModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoModulo/{idTipoModulo}")
    public void deleteTipoModulo(@PathVariable("idTipoModulo")
    Long idTipoModulo) throws Exception {
        try {
            TipoModulo tipoModulo = businessDelegatorView.getTipoModulo(idTipoModulo);

            businessDelegatorView.deleteTipoModulo(tipoModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoModulo/")
    public void updateTipoModulo(@RequestBody
    TipoModuloDTO tipoModuloDTO) throws Exception {
        try {
            TipoModulo tipoModulo = tipoModuloMapper.tipoModuloDTOToTipoModulo(tipoModuloDTO);

            businessDelegatorView.updateTipoModulo(tipoModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoModulo")
    public List<TipoModuloDTO> getDataTipoModulo() throws Exception {
        try {
            return businessDelegatorView.getDataTipoModulo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoModulo/{idTipoModulo}")
    public TipoModuloDTO getTipoModulo(
        @PathVariable("idTipoModulo")
    Long idTipoModulo) throws Exception {
        try {
            TipoModulo tipoModulo = businessDelegatorView.getTipoModulo(idTipoModulo);

            return tipoModuloMapper.tipoModuloToTipoModuloDTO(tipoModulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
