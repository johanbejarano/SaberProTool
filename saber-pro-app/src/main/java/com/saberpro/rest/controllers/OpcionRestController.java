package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IOpcionMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.OpcionDTO;

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
@RequestMapping("/opcion")
public class OpcionRestController {
    private static final Logger log = LoggerFactory.getLogger(OpcionRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IOpcionMapper opcionMapper;

    @PostMapping(value = "/saveOpcion")
    public void saveOpcion(@RequestBody
    OpcionDTO opcionDTO) throws Exception {
        try {
            Opcion opcion = opcionMapper.opcionDTOToOpcion(opcionDTO);

            businessDelegatorView.saveOpcion(opcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteOpcion/{idOpcion}")
    public void deleteOpcion(@PathVariable("idOpcion")
    Long idOpcion) throws Exception {
        try {
            Opcion opcion = businessDelegatorView.getOpcion(idOpcion);

            businessDelegatorView.deleteOpcion(opcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateOpcion/")
    public void updateOpcion(@RequestBody
    OpcionDTO opcionDTO) throws Exception {
        try {
            Opcion opcion = opcionMapper.opcionDTOToOpcion(opcionDTO);

            businessDelegatorView.updateOpcion(opcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataOpcion")
    public List<OpcionDTO> getDataOpcion() throws Exception {
        try {
            return businessDelegatorView.getDataOpcion();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getOpcion/{idOpcion}")
    public OpcionDTO getOpcion(@PathVariable("idOpcion")
    Long idOpcion) throws Exception {
        try {
            Opcion opcion = businessDelegatorView.getOpcion(idOpcion);

            return opcionMapper.opcionToOpcionDTO(opcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
