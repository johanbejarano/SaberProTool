package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IModuloMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ModuloDTO;

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
@RequestMapping("/modulo")
public class ModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(ModuloRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IModuloMapper moduloMapper;

    @PostMapping(value = "/saveModulo")
    public void saveModulo(@RequestBody
    ModuloDTO moduloDTO) throws Exception {
        try {
            Modulo modulo = moduloMapper.moduloDTOToModulo(moduloDTO);

            businessDelegatorView.saveModulo(modulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteModulo/{idModulo}")
    public void deleteModulo(@PathVariable("idModulo")
    Long idModulo) throws Exception {
        try {
            Modulo modulo = businessDelegatorView.getModulo(idModulo);

            businessDelegatorView.deleteModulo(modulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateModulo/")
    public void updateModulo(@RequestBody
    ModuloDTO moduloDTO) throws Exception {
        try {
            Modulo modulo = moduloMapper.moduloDTOToModulo(moduloDTO);

            businessDelegatorView.updateModulo(modulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataModulo")
    public List<ModuloDTO> getDataModulo() throws Exception {
        try {
            return businessDelegatorView.getDataModulo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getModulo/{idModulo}")
    public ModuloDTO getModulo(@PathVariable("idModulo")
    Long idModulo) throws Exception {
        try {
            Modulo modulo = businessDelegatorView.getModulo(idModulo);

            return moduloMapper.moduloToModuloDTO(modulo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
