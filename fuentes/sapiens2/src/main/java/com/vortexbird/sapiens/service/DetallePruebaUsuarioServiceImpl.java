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
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class DetallePruebaUsuarioServiceImpl
    implements DetallePruebaUsuarioService {
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioServiceImpl.class);
    @Autowired
    private DetallePruebaUsuarioRepository detallePruebaUsuarioRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(DetallePruebaUsuario detallePruebaUsuario)
        throws Exception {
        try {
            Set<ConstraintViolation<DetallePruebaUsuario>> constraintViolations = validator.validate(detallePruebaUsuario);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<DetallePruebaUsuario> constraintViolation : constraintViolations) {
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
        return detallePruebaUsuarioRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePruebaUsuario> findAll() {
        log.debug("finding all DetallePruebaUsuario instances");

        return detallePruebaUsuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public DetallePruebaUsuario save(DetallePruebaUsuario entity)
        throws Exception {
        log.debug("saving DetallePruebaUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "DetallePruebaUsuario");
            }

            validate(entity);

            if (detallePruebaUsuarioRepository.findById(entity.getDpruId())
                                                  .isPresent()) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            return detallePruebaUsuarioRepository.save(entity);
        } catch (Exception e) {
            log.error("save DetallePruebaUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(DetallePruebaUsuario entity) throws Exception {
        log.debug("deleting DetallePruebaUsuario instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "DetallePruebaUsuario");
        }

        if (entity.getDpruId() == null) {
            throw new ZMessManager().new EmptyFieldException("dpruId");
        }

        try {
            detallePruebaUsuarioRepository.deleteById(entity.getDpruId());
            log.debug("delete DetallePruebaUsuario successful");
        } catch (Exception e) {
            log.error("delete DetallePruebaUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting DetallePruebaUsuario instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("dpruId");
        }

        if (detallePruebaUsuarioRepository.findById(id).isPresent()) {
            delete(detallePruebaUsuarioRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public DetallePruebaUsuario update(DetallePruebaUsuario entity)
        throws Exception {
        log.debug("updating DetallePruebaUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "DetallePruebaUsuario");
            }

            validate(entity);

            return detallePruebaUsuarioRepository.save(entity);
        } catch (Exception e) {
            log.error("update DetallePruebaUsuario failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetallePruebaUsuario> findById(Integer dpruId)
        throws Exception {
        log.debug("getting DetallePruebaUsuario instance");

        return detallePruebaUsuarioRepository.findById(dpruId);
    }
}
