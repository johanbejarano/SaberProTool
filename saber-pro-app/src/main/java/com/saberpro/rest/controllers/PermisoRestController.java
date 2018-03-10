package com.saberpro.rest.controllers;

import com.saberpro.dto.mapper.IPermisoMapper;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PermisoDTO;

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
@RequestMapping("/permiso")
public class PermisoRestController {
    private static final Logger log = LoggerFactory.getLogger(PermisoRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPermisoMapper permisoMapper;

    @PostMapping(value = "/savePermiso")
    public void savePermiso(@RequestBody
    PermisoDTO permisoDTO) throws Exception {
        try {
            Permiso permiso = permisoMapper.permisoDTOToPermiso(permisoDTO);

            businessDelegatorView.savePermiso(permiso);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePermiso/{idPermiso}")
    public void deletePermiso(@PathVariable("idPermiso")
    Long idPermiso) throws Exception {
        try {
            Permiso permiso = businessDelegatorView.getPermiso(idPermiso);

            businessDelegatorView.deletePermiso(permiso);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePermiso/")
    public void updatePermiso(@RequestBody
    PermisoDTO permisoDTO) throws Exception {
        try {
            Permiso permiso = permisoMapper.permisoDTOToPermiso(permisoDTO);

            businessDelegatorView.updatePermiso(permiso);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPermiso")
    public List<PermisoDTO> getDataPermiso() throws Exception {
        try {
            return businessDelegatorView.getDataPermiso();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPermiso/{idPermiso}")
    public PermisoDTO getPermiso(@PathVariable("idPermiso")
    Long idPermiso) throws Exception {
        try {
            Permiso permiso = businessDelegatorView.getPermiso(idPermiso);

            return permisoMapper.permisoToPermisoDTO(permiso);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
