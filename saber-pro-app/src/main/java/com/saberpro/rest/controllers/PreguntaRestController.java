package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPreguntaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PreguntaDTO;

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
@RequestMapping("/pregunta")
public class PreguntaRestController {
    private static final Logger log = LoggerFactory.getLogger(PreguntaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPreguntaMapper preguntaMapper;

    @PostMapping(value = "/savePregunta")
    public void savePregunta(@RequestBody
    PreguntaDTO preguntaDTO) throws Exception {
        try {
            Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);

            businessDelegatorView.savePregunta(pregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePregunta/{idPregunta}")
    public void deletePregunta(@PathVariable("idPregunta")
    Long idPregunta) throws Exception {
        try {
            Pregunta pregunta = businessDelegatorView.getPregunta(idPregunta);

            businessDelegatorView.deletePregunta(pregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePregunta/")
    public void updatePregunta(@RequestBody
    PreguntaDTO preguntaDTO) throws Exception {
        try {
            Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);

            businessDelegatorView.updatePregunta(pregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPregunta")
    public List<PreguntaDTO> getDataPregunta() throws Exception {
        try {
            return businessDelegatorView.getDataPregunta();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPregunta/{idPregunta}")
    public PreguntaDTO getPregunta(@PathVariable("idPregunta")
    Long idPregunta) throws Exception {
        try {
            Pregunta pregunta = businessDelegatorView.getPregunta(idPregunta);

            return preguntaMapper.preguntaToPreguntaDTO(pregunta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
