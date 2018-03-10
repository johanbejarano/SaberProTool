package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.ITipoUsuarioMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.TipoUsuarioDTO;

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
@RequestMapping("/tipoUsuario")
public class TipoUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(TipoUsuarioRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ITipoUsuarioMapper tipoUsuarioMapper;

    @PostMapping(value = "/saveTipoUsuario")
    public void saveTipoUsuario(@RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
        try {
            TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);

            businessDelegatorView.saveTipoUsuario(tipoUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTipoUsuario/{idTipoUsuario}")
    public void deleteTipoUsuario(
        @PathVariable("idTipoUsuario")
    Long idTipoUsuario) throws Exception {
        try {
            TipoUsuario tipoUsuario = businessDelegatorView.getTipoUsuario(idTipoUsuario);

            businessDelegatorView.deleteTipoUsuario(tipoUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTipoUsuario/")
    public void updateTipoUsuario(@RequestBody
    TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
        try {
            TipoUsuario tipoUsuario = tipoUsuarioMapper.tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);

            businessDelegatorView.updateTipoUsuario(tipoUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTipoUsuario")
    public List<TipoUsuarioDTO> getDataTipoUsuario() throws Exception {
        try {
            return businessDelegatorView.getDataTipoUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTipoUsuario/{idTipoUsuario}")
    public TipoUsuarioDTO getTipoUsuario(
        @PathVariable("idTipoUsuario")
    Long idTipoUsuario) throws Exception {
        try {
            TipoUsuario tipoUsuario = businessDelegatorView.getTipoUsuario(idTipoUsuario);

            return tipoUsuarioMapper.tipoUsuarioToTipoUsuarioDTO(tipoUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
