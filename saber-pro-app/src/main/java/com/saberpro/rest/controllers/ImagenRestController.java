package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IImagenMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ImagenDTO;

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
@RequestMapping("/imagen")
public class ImagenRestController {
    private static final Logger log = LoggerFactory.getLogger(ImagenRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IImagenMapper imagenMapper;

    @PostMapping(value = "/saveImagen")
    public void saveImagen(@RequestBody
    ImagenDTO imagenDTO) throws Exception {
        try {
            Imagen imagen = imagenMapper.imagenDTOToImagen(imagenDTO);

            businessDelegatorView.saveImagen(imagen);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteImagen/{idImagen}")
    public void deleteImagen(@PathVariable("idImagen")
    Long idImagen) throws Exception {
        try {
            Imagen imagen = businessDelegatorView.getImagen(idImagen);

            businessDelegatorView.deleteImagen(imagen);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateImagen/")
    public void updateImagen(@RequestBody
    ImagenDTO imagenDTO) throws Exception {
        try {
            Imagen imagen = imagenMapper.imagenDTOToImagen(imagenDTO);

            businessDelegatorView.updateImagen(imagen);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataImagen")
    public List<ImagenDTO> getDataImagen() throws Exception {
        try {
            return businessDelegatorView.getDataImagen();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getImagen/{idImagen}")
    public ImagenDTO getImagen(@PathVariable("idImagen")
    Long idImagen) throws Exception {
        try {
            Imagen imagen = businessDelegatorView.getImagen(idImagen);

            return imagenMapper.imagenToImagenDTO(imagen);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
