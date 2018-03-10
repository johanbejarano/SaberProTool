package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IResultadoRealMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ResultadoRealDTO;

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
@RequestMapping("/resultadoReal")
public class ResultadoRealRestController {
    private static final Logger log = LoggerFactory.getLogger(ResultadoRealRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IResultadoRealMapper resultadoRealMapper;

    @PostMapping(value = "/saveResultadoReal")
    public void saveResultadoReal(@RequestBody
    ResultadoRealDTO resultadoRealDTO) throws Exception {
        try {
            ResultadoReal resultadoReal = resultadoRealMapper.resultadoRealDTOToResultadoReal(resultadoRealDTO);

            businessDelegatorView.saveResultadoReal(resultadoReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteResultadoReal/{idResultadoReal}")
    public void deleteResultadoReal(
        @PathVariable("idResultadoReal")
    Long idResultadoReal) throws Exception {
        try {
            ResultadoReal resultadoReal = businessDelegatorView.getResultadoReal(idResultadoReal);

            businessDelegatorView.deleteResultadoReal(resultadoReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateResultadoReal/")
    public void updateResultadoReal(
        @RequestBody
    ResultadoRealDTO resultadoRealDTO) throws Exception {
        try {
            ResultadoReal resultadoReal = resultadoRealMapper.resultadoRealDTOToResultadoReal(resultadoRealDTO);

            businessDelegatorView.updateResultadoReal(resultadoReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataResultadoReal")
    public List<ResultadoRealDTO> getDataResultadoReal()
        throws Exception {
        try {
            return businessDelegatorView.getDataResultadoReal();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getResultadoReal/{idResultadoReal}")
    public ResultadoRealDTO getResultadoReal(
        @PathVariable("idResultadoReal")
    Long idResultadoReal) throws Exception {
        try {
            ResultadoReal resultadoReal = businessDelegatorView.getResultadoReal(idResultadoReal);

            return resultadoRealMapper.resultadoRealToResultadoRealDTO(resultadoReal);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
