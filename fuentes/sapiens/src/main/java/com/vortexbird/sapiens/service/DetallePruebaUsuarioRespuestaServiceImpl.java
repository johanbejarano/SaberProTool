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
public class DetallePruebaUsuarioRespuestaServiceImpl
    implements DetallePruebaUsuarioRespuestaService {
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioRespuestaServiceImpl.class);
    @Autowired
    private DetallePruebaUsuarioRespuestaRepository detallePruebaUsuarioRespuestaRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(
        DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta)
        throws Exception {
        try {
            Set<ConstraintViolation<DetallePruebaUsuarioRespuesta>> constraintViolations =
                validator.validate(detallePruebaUsuarioRespuesta);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<DetallePruebaUsuarioRespuesta> constraintViolation : constraintViolations) {
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
        return detallePruebaUsuarioRespuestaRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePruebaUsuarioRespuesta> findAll() {
        log.debug("finding all DetallePruebaUsuarioRespuesta instances");

        return detallePruebaUsuarioRespuestaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public DetallePruebaUsuarioRespuesta save(
        DetallePruebaUsuarioRespuesta entity) throws Exception {
        log.debug("saving DetallePruebaUsuarioRespuesta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "DetallePruebaUsuarioRespuesta");
            }

            return detallePruebaUsuarioRespuestaRepository.save(entity);
        } catch (Exception e) {
            log.error("save DetallePruebaUsuarioRespuesta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(DetallePruebaUsuarioRespuesta entity)
        throws Exception {
        log.debug("deleting DetallePruebaUsuarioRespuesta instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "DetallePruebaUsuarioRespuesta");
        }

        if (entity.getDpurId() == null) {
            throw new ZMessManager().new EmptyFieldException("dpurId");
        }

        try {
            detallePruebaUsuarioRespuestaRepository.deleteById(entity.getDpurId());
            log.debug("delete DetallePruebaUsuarioRespuesta successful");
        } catch (Exception e) {
            log.error("delete DetallePruebaUsuarioRespuesta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws Exception {
        log.debug("deleting DetallePruebaUsuarioRespuesta instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("dpurId");
        }

        if (detallePruebaUsuarioRespuestaRepository.findById(id).isPresent()) {
            delete(detallePruebaUsuarioRespuestaRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public DetallePruebaUsuarioRespuesta update(
        DetallePruebaUsuarioRespuesta entity) throws Exception {
        log.debug("updating DetallePruebaUsuarioRespuesta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "DetallePruebaUsuarioRespuesta");
            }

            validate(entity);

            return detallePruebaUsuarioRespuestaRepository.save(entity);
        } catch (Exception e) {
            log.error("update DetallePruebaUsuarioRespuesta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetallePruebaUsuarioRespuesta> findById(Long dpurId)
        throws Exception {
        log.debug("getting DetallePruebaUsuarioRespuesta instance");

        return detallePruebaUsuarioRespuestaRepository.findById(dpurId);
    }
}
