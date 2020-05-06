package com.vortexbird.sapiens.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.ProgramaModuloRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class ProgramaModuloServiceImpl implements ProgramaModuloService {
    private static final Logger log = LoggerFactory.getLogger(ProgramaModuloServiceImpl.class);
    @Autowired
    private ProgramaModuloRepository programaModuloRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(ProgramaModulo programaModulo) throws Exception {
        try {
            Set<ConstraintViolation<ProgramaModulo>> constraintViolations = validator.validate(programaModulo);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<ProgramaModulo> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath().toString());
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
        return programaModuloRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramaModulo> findAll() {
        log.debug("finding all ProgramaModulo instances");

        return programaModuloRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProgramaModulo save(ProgramaModulo entity) throws Exception {
        log.debug("saving ProgramaModulo instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("ProgramaModulo");
            }

            validate(entity);

            return programaModuloRepository.save(entity);
        } catch (Exception e) {
            log.error("save ProgramaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(ProgramaModulo entity) throws Exception {
        log.debug("deleting ProgramaModulo instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("ProgramaModulo");
        }

        if (entity.getPrmoId() == null) {
            throw new ZMessManager().new EmptyFieldException("prmoId");
        }

        try {
            programaModuloRepository.deleteById(entity.getPrmoId());
            log.debug("delete ProgramaModulo successful");
        } catch (Exception e) {
            log.error("delete ProgramaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting ProgramaModulo instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("prmoId");
        }

        if (programaModuloRepository.findById(id).isPresent()) {
            delete(programaModuloRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProgramaModulo update(ProgramaModulo entity) throws Exception {
        log.debug("updating ProgramaModulo instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("ProgramaModulo");
            }

            validate(entity);

            return programaModuloRepository.save(entity);
        } catch (Exception e) {
            log.error("update ProgramaModulo failed", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgramaModulo> findById(Integer prmoId) throws Exception {
        log.debug("getting ProgramaModulo instance");

        return programaModuloRepository.findById(prmoId);
    }
}
