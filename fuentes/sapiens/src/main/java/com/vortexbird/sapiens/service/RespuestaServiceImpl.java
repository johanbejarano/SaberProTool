package com.vortexbird.sapiens.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Respuesta;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.RespuestaRepository;
import com.vortexbird.sapiens.utility.Utilities;

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
public class RespuestaServiceImpl implements RespuestaService {

	private static final Logger log = LoggerFactory.getLogger(RespuestaServiceImpl.class);

	@Autowired
	private RespuestaRepository respuestaRepository;

	@Autowired
	private Validator validator;
	
	@Autowired
	private PreguntaService preguntaService;

	@Override
	public void validate(Respuesta respuesta) throws Exception {
		try {
			Set<ConstraintViolation<Respuesta>> constraintViolations = validator.validate(respuesta);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Respuesta> constraintViolation : constraintViolations) {
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
		return respuestaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Respuesta> findAll() {
		log.debug("finding all Respuesta instances");
		return respuestaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Respuesta save(Respuesta entity) throws Exception {
		log.debug("saving Respuesta instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Respuesta");
			}

			validate(entity);

//		if(respuestaRepository.findById(entity.getRespId()).isPresent()){
//           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//        }    

			return respuestaRepository.save(entity);

		} catch (Exception e) {
			log.error("save Respuesta failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Respuesta entity) throws Exception {
		log.debug("deleting Respuesta instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Respuesta");
		}

		if (entity.getRespId() == null) {
			throw new ZMessManager().new EmptyFieldException("respId");
		}

		findById(entity.getRespId()).ifPresent(entidad -> {
			List<DetallePruebaUsuario> detallePruebaUsuarios = entidad.getDetallePruebaUsuarios();

			if (Utilities.validationsList(detallePruebaUsuarios) == true) {
				throw new ZMessManager().new DeletingException("detallePruebaUsuarios");
			}
		});

		try {

			respuestaRepository.deleteById(entity.getRespId());
			log.debug("delete Respuesta successful");

		} catch (Exception e) {
			log.error("delete Respuesta failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Respuesta instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("respId");
		}
		if (respuestaRepository.findById(id).isPresent()) {
			delete(respuestaRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Respuesta update(Respuesta entity) throws Exception {

		log.debug("updating Respuesta instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Respuesta");
			}

			validate(entity);

			return respuestaRepository.save(entity);

		} catch (Exception e) {
			log.error("update Respuesta failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Respuesta> findById(Integer respId) throws Exception {
		log.debug("getting Respuesta instance");
		return respuestaRepository.findById(respId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Respuesta> findRespuestasDePregunta(Integer pregId) throws Exception {
		try {
			
			//Se consulta la pregunta
			Optional<Pregunta> pregunta = preguntaService.findById(pregId);
			
			if (!pregunta.isPresent()) {
				throw new Exception("No se encontró la pregunta " + pregId);
			}
			
			//Se consultan las respuestas de la pregunta
			List<Respuesta> respuestas = respuestaRepository.findByPregunta(pregunta.get());
			
			return respuestas;
			
		} catch (Exception e) {
			log.error("Error en findRespuestasDePregunta", e);
			throw e;
		}
	}

}
