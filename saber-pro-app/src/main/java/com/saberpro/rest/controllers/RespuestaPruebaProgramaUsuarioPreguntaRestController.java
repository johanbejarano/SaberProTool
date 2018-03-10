package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IRespuestaPruebaProgramaUsuarioPreguntaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.RespuestaPruebaProgramaUsuarioPreguntaDTO;

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
@RequestMapping("/respuestaPruebaProgramaUsuarioPregunta")
public class RespuestaPruebaProgramaUsuarioPreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(RespuestaPruebaProgramaUsuarioPreguntaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IRespuestaPruebaProgramaUsuarioPreguntaMapper respuestaPruebaProgramaUsuarioPreguntaMapper;

    @PostMapping(value = "/saveRespuestaPruebaProgramaUsuarioPregunta")
    public void saveRespuestaPruebaProgramaUsuarioPregunta(
        @RequestBody
    RespuestaPruebaProgramaUsuarioPreguntaDTO respuestaPruebaProgramaUsuarioPreguntaDTO)
        throws Exception {
        try {
            RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta =
                respuestaPruebaProgramaUsuarioPreguntaMapper.respuestaPruebaProgramaUsuarioPreguntaDTOToRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPreguntaDTO);

            businessDelegatorView.saveRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteRespuestaPruebaProgramaUsuarioPregunta/{idRespuestaPruebaProgramaUsuarioPregunta}")
    public void deleteRespuestaPruebaProgramaUsuarioPregunta(
        @PathVariable("idRespuestaPruebaProgramaUsuarioPregunta")
    Long idRespuestaPruebaProgramaUsuarioPregunta) throws Exception {
        try {
            RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta =
                businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);

            businessDelegatorView.deleteRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateRespuestaPruebaProgramaUsuarioPregunta/")
    public void updateRespuestaPruebaProgramaUsuarioPregunta(
        @RequestBody
    RespuestaPruebaProgramaUsuarioPreguntaDTO respuestaPruebaProgramaUsuarioPreguntaDTO)
        throws Exception {
        try {
            RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta =
                respuestaPruebaProgramaUsuarioPreguntaMapper.respuestaPruebaProgramaUsuarioPreguntaDTOToRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPreguntaDTO);

            businessDelegatorView.updateRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataRespuestaPruebaProgramaUsuarioPregunta")
    public List<RespuestaPruebaProgramaUsuarioPreguntaDTO> getDataRespuestaPruebaProgramaUsuarioPregunta()
        throws Exception {
        try {
            return businessDelegatorView.getDataRespuestaPruebaProgramaUsuarioPregunta();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getRespuestaPruebaProgramaUsuarioPregunta/{idRespuestaPruebaProgramaUsuarioPregunta}")
    public RespuestaPruebaProgramaUsuarioPreguntaDTO getRespuestaPruebaProgramaUsuarioPregunta(
        @PathVariable("idRespuestaPruebaProgramaUsuarioPregunta")
    Long idRespuestaPruebaProgramaUsuarioPregunta) throws Exception {
        try {
            RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta =
                businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);

            return respuestaPruebaProgramaUsuarioPreguntaMapper.respuestaPruebaProgramaUsuarioPreguntaToRespuestaPruebaProgramaUsuarioPreguntaDTO(respuestaPruebaProgramaUsuarioPregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
