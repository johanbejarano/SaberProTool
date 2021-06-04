package com.vortexbird.sapiens.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.sapiens.domain.PruebaPregunta;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.PruebaPreguntaRepository;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class PruebaPreguntaServiceImpl implements PruebaPreguntaService {
    private static final Logger log = LoggerFactory.getLogger(PruebaPreguntaServiceImpl.class);
    @Autowired
    private PruebaPreguntaRepository pruebaPreguntaRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(PruebaPregunta pruebaPregunta)
        throws Exception {
        try {
            Set<ConstraintViolation<PruebaPregunta>> constraintViolations = validator.validate(pruebaPregunta);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<PruebaPregunta> constraintViolation : constraintViolations) {
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
        return pruebaPreguntaRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PruebaPregunta> findAll() {
        log.debug("finding all PruebaPregunta instances");

        return pruebaPreguntaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PruebaPregunta save(PruebaPregunta entity) throws Exception {
        log.debug("saving PruebaPregunta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PruebaPregunta");
            }

            validate(entity);

//            if (pruebaPreguntaRepository.findById(entity.getPrprId()).isPresent()) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            return pruebaPreguntaRepository.save(entity);
        } catch (Exception e) {
            log.error("save PruebaPregunta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(PruebaPregunta entity) throws Exception {
        log.debug("deleting PruebaPregunta instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("PruebaPregunta");
        }

        if (entity.getPrprId() == null) {
            throw new ZMessManager().new EmptyFieldException("prprId");
        }

        try {
            pruebaPreguntaRepository.deleteById(entity.getPrprId());
            log.debug("delete PruebaPregunta successful");
        } catch (Exception e) {
            log.error("delete PruebaPregunta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) throws Exception {
        log.debug("deleting PruebaPregunta instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("prprId");
        }

        if (pruebaPreguntaRepository.findById(id).isPresent()) {
            delete(pruebaPreguntaRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public PruebaPregunta update(PruebaPregunta entity)
        throws Exception {
        log.debug("updating PruebaPregunta instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "PruebaPregunta");
            }

            validate(entity);

            return pruebaPreguntaRepository.save(entity);
        } catch (Exception e) {
            log.error("update PruebaPregunta failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PruebaPregunta> findById(Long prprId)
        throws Exception {
        log.debug("getting PruebaPregunta instance");

        return pruebaPreguntaRepository.findById(prprId);
    }
}
