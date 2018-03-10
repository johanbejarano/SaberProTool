package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IMatriculaMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.MatriculaDTO;

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
@RequestMapping("/matricula")
public class MatriculaRestController {
    private static final Logger log = LoggerFactory.getLogger(MatriculaRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IMatriculaMapper matriculaMapper;

    @PostMapping(value = "/saveMatricula")
    public void saveMatricula(@RequestBody
    MatriculaDTO matriculaDTO) throws Exception {
        try {
            Matricula matricula = matriculaMapper.matriculaDTOToMatricula(matriculaDTO);

            businessDelegatorView.saveMatricula(matricula);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteMatricula/{idMatricula}")
    public void deleteMatricula(@PathVariable("idMatricula")
    Long idMatricula) throws Exception {
        try {
            Matricula matricula = businessDelegatorView.getMatricula(idMatricula);

            businessDelegatorView.deleteMatricula(matricula);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateMatricula/")
    public void updateMatricula(@RequestBody
    MatriculaDTO matriculaDTO) throws Exception {
        try {
            Matricula matricula = matriculaMapper.matriculaDTOToMatricula(matriculaDTO);

            businessDelegatorView.updateMatricula(matricula);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataMatricula")
    public List<MatriculaDTO> getDataMatricula() throws Exception {
        try {
            return businessDelegatorView.getDataMatricula();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getMatricula/{idMatricula}")
    public MatriculaDTO getMatricula(
        @PathVariable("idMatricula")
    Long idMatricula) throws Exception {
        try {
            Matricula matricula = businessDelegatorView.getMatricula(idMatricula);

            return matriculaMapper.matriculaToMatriculaDTO(matricula);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
