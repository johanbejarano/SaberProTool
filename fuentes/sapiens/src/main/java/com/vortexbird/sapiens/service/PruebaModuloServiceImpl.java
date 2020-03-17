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
public class PruebaModuloServiceImpl implements PruebaModuloService {
    private static final Logger log = LoggerFactory.getLogger(PruebaModuloServiceImpl.class);
    @Autowired
    private PruebaModuloRepository pruebaModuloRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(PruebaModulo pruebaModulo) throws Exception {
        try {
            Set<ConstraintViolation<PruebaModulo>> constraintViolations = validator.validate(pruebaModulo);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<PruebaModulo> constraintViolation : constraintViolations) {
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
        return pruebaModuloRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PruebaModulo> findAll() {
        log.debug("finding all PruebaModulo instances");

        return pruebaModuloRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PruebaModulo save(PruebaModulo entity) throws Exception {
        log.debug("saving PruebaModulo instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PruebaModulo");
            }

            validate(entity);

            if (pruebaModuloRepository.findById(entity.getPrmoId()).isPresent()) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            return pruebaModuloRepository.save(entity);
        } catch (Exception e) {
            log.error("save PruebaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(PruebaModulo entity) throws Exception {
        log.debug("deleting PruebaModulo instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PruebaModulo");
        }

        if (entity.getPrmoId() == null) {
            throw new ZMessManager().new EmptyFieldException("prmoId");
        }

        try {
            pruebaModuloRepository.deleteById(entity.getPrmoId());
            log.debug("delete PruebaModulo successful");
        } catch (Exception e) {
            log.error("delete PruebaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting PruebaModulo instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("prmoId");
        }

        if (pruebaModuloRepository.findById(id).isPresent()) {
            delete(pruebaModuloRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PruebaModulo update(PruebaModulo entity) throws Exception {
        log.debug("updating PruebaModulo instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("PruebaModulo");
            }

            validate(entity);

            return pruebaModuloRepository.save(entity);
        } catch (Exception e) {
            log.error("update PruebaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PruebaModulo> findById(Integer prmoId)
        throws Exception {
        log.debug("getting PruebaModulo instance");

        return pruebaModuloRepository.findById(prmoId);
    }
}
