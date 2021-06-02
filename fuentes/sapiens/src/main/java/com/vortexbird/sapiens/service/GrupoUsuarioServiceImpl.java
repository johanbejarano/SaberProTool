package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.exception.*;
import com.vortexbird.sapiens.repository.*;
import com.vortexbird.sapiens.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class GrupoUsuarioServiceImpl implements GrupoUsuarioService {
    private static final Logger log = LoggerFactory.getLogger(GrupoUsuarioServiceImpl.class);
    @Autowired
    private GrupoUsuarioRepository grupoUsuarioRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(GrupoUsuario grupoUsuario) throws Exception {
        try {
            Set<ConstraintViolation<GrupoUsuario>> constraintViolations = validator.validate(grupoUsuario);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<GrupoUsuario> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return grupoUsuarioRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoUsuario> findAll() {
        log.debug("finding all GrupoUsuario instances");

        return grupoUsuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public GrupoUsuario save(GrupoUsuario entity) throws Exception {
        log.debug("saving GrupoUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("GrupoUsuario");
            }

            validate(entity);

            if (grupoUsuarioRepository.findById(entity.getGrusId()).isPresent()) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            return grupoUsuarioRepository.save(entity);
        } catch (Exception e) {
            log.error("save GrupoUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(GrupoUsuario entity) throws Exception {
        log.debug("deleting GrupoUsuario instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("GrupoUsuario");
        }

        if (entity.getGrusId() == null) {
            throw new ZMessManager().new EmptyFieldException("grusId");
        }

        try {
            grupoUsuarioRepository.deleteById(entity.getGrusId());
            log.debug("delete GrupoUsuario successful");
        } catch (Exception e) {
            log.error("delete GrupoUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws Exception {
        log.debug("deleting GrupoUsuario instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("grusId");
        }

        if (grupoUsuarioRepository.findById(id).isPresent()) {
            delete(grupoUsuarioRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public GrupoUsuario update(GrupoUsuario entity) throws Exception {
        log.debug("updating GrupoUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("GrupoUsuario");
            }

            validate(entity);

            return grupoUsuarioRepository.save(entity);
        } catch (Exception e) {
            log.error("update GrupoUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrupoUsuario> findById(Long grusId)
        throws Exception {
        log.debug("getting GrupoUsuario instance");

        return grupoUsuarioRepository.findById(grusId);
    }
}
