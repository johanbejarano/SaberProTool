package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IRespuestaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.RespuestaDTO;

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
@RequestMapping("/respuesta")
public class RespuestaRestController {
    private static final Logger log = LoggerFactory.getLogger(RespuestaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IRespuestaMapper respuestaMapper;

    @PostMapping(value = "/saveRespuesta")
    public void saveRespuesta(@RequestBody
    RespuestaDTO respuestaDTO) throws Exception {
        try {
            Respuesta respuesta = respuestaMapper.respuestaDTOToRespuesta(respuestaDTO);

            businessDelegatorView.saveRespuesta(respuesta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteRespuesta/{idRespuesta}")
    public void deleteRespuesta(@PathVariable("idRespuesta")
    Long idRespuesta) throws Exception {
        try {
            Respuesta respuesta = businessDelegatorView.getRespuesta(idRespuesta);

            businessDelegatorView.deleteRespuesta(respuesta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateRespuesta/")
    public void updateRespuesta(@RequestBody
    RespuestaDTO respuestaDTO) throws Exception {
        try {
            Respuesta respuesta = respuestaMapper.respuestaDTOToRespuesta(respuestaDTO);

            businessDelegatorView.updateRespuesta(respuesta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataRespuesta")
    public List<RespuestaDTO> getDataRespuesta() throws Exception {
        try {
            return businessDelegatorView.getDataRespuesta();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getRespuesta/{idRespuesta}")
    public RespuestaDTO getRespuesta(
        @PathVariable("idRespuesta")
    Long idRespuesta) throws Exception {
        try {
            Respuesta respuesta = businessDelegatorView.getRespuesta(idRespuesta);

            return respuestaMapper.respuestaToRespuestaDTO(respuesta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
