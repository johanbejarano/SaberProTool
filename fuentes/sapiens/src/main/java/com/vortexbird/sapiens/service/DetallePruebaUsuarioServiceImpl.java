package com.vortexbird.sapiens.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.domain.Respuesta;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioDTO;
import com.vortexbird.sapiens.dto.RespuestaDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.DetallePruebaUsuarioRepository;
import com.vortexbird.sapiens.utility.Constantes;

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
public class DetallePruebaUsuarioServiceImpl implements DetallePruebaUsuarioService {
	private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioServiceImpl.class);
	@Autowired
	private DetallePruebaUsuarioRepository detallePruebaUsuarioRepository;
	@Autowired
	private Validator validator;
	@Autowired
	private RespuestaService respuestaService;
	@Autowired
	private PruebaUsuarioService pruebaUsuarioService;

	@Override
	public void validate(DetallePruebaUsuario detallePruebaUsuario) throws Exception {
		try {
			Set<ConstraintViolation<DetallePruebaUsuario>> constraintViolations = validator
					.validate(detallePruebaUsuario);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<DetallePruebaUsuario> constraintViolation : constraintViolations) {
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
	public DetallePruebaUsuario save(DetallePruebaUsuario entity) throws Exception {
		log.debug("saving DetallePruebaUsuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("DetallePruebaUsuario");
			}

			validate(entity);

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
			throw new ZMessManager().new NullEntityExcepcion("DetallePruebaUsuario");
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
	public DetallePruebaUsuario update(DetallePruebaUsuario entity) throws Exception {
		log.debug("updating DetallePruebaUsuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("DetallePruebaUsuario");
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
	public Optional<DetallePruebaUsuario> findById(Integer dpruId) throws Exception {
		log.debug("getting DetallePruebaUsuario instance");

		return detallePruebaUsuarioRepository.findById(dpruId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetallePruebaUsuario> findByPregunta(Pregunta pregunta) {
		return detallePruebaUsuarioRepository.findByPregunta(pregunta);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCantidadDeEjecucionesPorModulo(Integer moduId) throws Exception {
		try {

			return detallePruebaUsuarioRepository.cantidadEjecucionesPorModulo(moduId);

		} catch (Exception e) {
			log.error("Error en getCantidadDeEjecucionesPorModulo", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCantidadDeEjecucionesPorUsuario(Integer usuaId) throws Exception {
		try {

			return detallePruebaUsuarioRepository.cantidadEjecucionesPorUsuario(usuaId);

		} catch (Exception e) {
			log.error("Error en getCantidadDeEjecucionesPorUsuario", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void responder(Integer dpruId, Integer respId, String respuestaAbierta, Long usuario) throws Exception {
		try {
			// Valido que lleguen los datos
			if (dpruId == null) {
				throw new Exception("No se ingresó la pregunta");
			}
			if (respId == null && (respuestaAbierta == null || respuestaAbierta.trim().isEmpty())) {
				throw new Exception("No se ingresó la respuesta");
			}
			if (usuario == null) {
				throw new Exception("No se ingresó el usuario");
			}
			// Valido que la información exista
			Optional<DetallePruebaUsuario> detallePruebaUsuarioOpt = findById(dpruId);
			if (!detallePruebaUsuarioOpt.isPresent()
					|| detallePruebaUsuarioOpt.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("No se encontró el detalle de la prueba o no se encuentra activo");
			}

			DetallePruebaUsuario detallePruebaUsuario = detallePruebaUsuarioOpt.get();

			if (respId != null) {
				Optional<Respuesta> respuestaOpt = respuestaService.findById(respId);
				if (!respuestaOpt.isPresent()
						|| respuestaOpt.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
					throw new Exception("No se encontró la respuesta o no se encuentra activa");
				}
				Respuesta respuesta = respuestaOpt.get();
				// Valido que la respuesta sea de la pregunta dada
				if (!respuesta.getPregunta().getPregId().equals(detallePruebaUsuario.getPregunta().getPregId())) {
					throw new Exception("La respuesta no es de la pregunta dada");
				}
				detallePruebaUsuario.setRespuesta(respuesta);
			} else {
				detallePruebaUsuario.setRespuestaAbierta(respuestaAbierta);
			}

			PruebaUsuario pruebaUsuario = detallePruebaUsuario.getPruebaUsuario();
			if (pruebaUsuario.getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)
					|| !pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_INICIADA)) {
				throw new Exception("La prueba no existe o no ha sido iniciada");
			}

			Date fecha = new Date();
			Long tiempoDisponible = pruebaUsuario.getTiempoDisponible();
			Long milliseconds = fecha.getTime() - pruebaUsuario.getFechaInicio().getTime();
			int minutes = (int) ((milliseconds / (1000 * 60)) % 60) + 1;
			tiempoDisponible -= minutes;
			if (tiempoDisponible < 0L) {
				throw new Exception("Se terminó el tiempo de la prueba");
			}
			if (pruebaUsuario.getPrueba().getFechaFinal().before(fecha)) {
				throw new Exception("Se terminó el tiempo de la prueba");
			}

			detallePruebaUsuario.setFechaModificacion(fecha);
			detallePruebaUsuario.setUsuModificador(usuario);
			update(detallePruebaUsuario);

		} catch (Exception e) {
			log.error("Error en responder", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetallePruebaUsuario> getPreguntasByPruebaUsuario(Integer prusId) throws Exception {
		try {
			return detallePruebaUsuarioRepository.findByPruebaUsuario_prusIdAndEstadoRegistro(prusId,
					Constantes.ESTADO_ACTIVO);
		} catch (Exception e) {
			log.error("Error en getRespuestasCorrectas", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetallePruebaUsuario> getRespuestasCorrectas(Integer prusId) throws Exception {
		try {
			List<DetallePruebaUsuario> respuestasCorrectas = new ArrayList<DetallePruebaUsuario>();
			List<DetallePruebaUsuario> preguntas = getPreguntasByPruebaUsuario(prusId);
			for (DetallePruebaUsuario detallePruebaUsuario : preguntas) {
				if (detallePruebaUsuario.getRespuesta() != null
						&& detallePruebaUsuario.getRespuesta().getCorrecta().equals(Constantes.RESPUESTA_CORRECTA)) {
					respuestasCorrectas.add(detallePruebaUsuario);
				}
			}
			return respuestasCorrectas;
		} catch (Exception e) {
			log.error("Error en getRespuestasCorrectas", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetallePruebaUsuarioDTO> getPreguntasByPruebaUsuarioDTO(Integer prusId) throws Exception {
		try {
			Optional<PruebaUsuario> pruebaUsuario = pruebaUsuarioService.findById(prusId);
			if (!pruebaUsuario.isPresent()
					|| pruebaUsuario.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("No se encontró la prueba");
			}

			boolean mostrarCorrecto = false;
			boolean mostrarRetroalimentacion = false;
			// Valido si la prueba ya finalizó para mostrar las respuestas
			if (pruebaUsuario.get().getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_TERMINADA)) {
				mostrarCorrecto = true;
				mostrarRetroalimentacion = true;
			}
			Date fecha = new Date();
			// Valido si la prueba ya terminó el periodo para mostrar la retroalimentación

			List<DetallePruebaUsuarioDTO> preguntasDTO = new ArrayList<DetallePruebaUsuarioDTO>();
			List<DetallePruebaUsuario> preguntas = getPreguntasByPruebaUsuario(prusId);
			for (DetallePruebaUsuario detallePruebaUsuario : preguntas) {
				DetallePruebaUsuarioDTO detallePruebaUsuarioDTO = new DetallePruebaUsuarioDTO();
				detallePruebaUsuarioDTO.setDpruId(detallePruebaUsuario.getDpruId());
				detallePruebaUsuarioDTO.setPregId(detallePruebaUsuario.getPregunta().getPregId());
				detallePruebaUsuarioDTO.setNombreModulo(detallePruebaUsuario.getPregunta().getModulo().getNombre());
				detallePruebaUsuarioDTO
						.setPrioridadModulo(detallePruebaUsuario.getPregunta().getModulo().getPrioridad());
				detallePruebaUsuarioDTO.setDescripcionPregunta(detallePruebaUsuario.getPregunta().getDescripcion());
				detallePruebaUsuarioDTO.setOrdenPregunta(detallePruebaUsuario.getPregunta().getOrden());

				if (detallePruebaUsuario.getPregunta().getContexto() != null) {
					detallePruebaUsuarioDTO
							.setDescripcionContexto(detallePruebaUsuario.getPregunta().getContexto().getDescripcion());
				}
				if (mostrarRetroalimentacion) {
					detallePruebaUsuarioDTO
							.setRetroalimentacionPregunta(detallePruebaUsuario.getPregunta().getRetroalimentacion());
				}
				detallePruebaUsuarioDTO.setRespuestaAbierta(detallePruebaUsuario.getRespuestaAbierta());
				detallePruebaUsuarioDTO.setRespId(
						detallePruebaUsuario.getRespuesta() != null ? detallePruebaUsuario.getRespuesta().getRespId()
								: null);
				List<RespuestaDTO> respuestas = getRespuestas(detallePruebaUsuario, mostrarCorrecto,
						mostrarRetroalimentacion);

				detallePruebaUsuarioDTO.setRespuestas(respuestas);
				preguntasDTO.add(detallePruebaUsuarioDTO);
			}

			preguntasDTO.sort(new Comparator<DetallePruebaUsuarioDTO>() {
				@Override
				public int compare(DetallePruebaUsuarioDTO p1, DetallePruebaUsuarioDTO p2) {
					int prioridadModulo = p1.getPrioridadModulo().compareTo(p2.getPrioridadModulo());

					if (prioridadModulo != 0) {
						return prioridadModulo;
					}

					int nombreModulo = p2.getNombreModulo().compareTo(p1.getNombreModulo());

					if (nombreModulo != 0) {
						return nombreModulo;
					}

					String contexto1 = p1.getDescripcionContexto() == null ? "" : p1.getDescripcionContexto();
					String contexto2 = p2.getDescripcionContexto() == null ? "" : p2.getDescripcionContexto();

					int contexto = contexto1.compareTo(contexto2);

					if (contexto != 0) {
						return contexto;
					}

					Long orden1 = p1.getOrdenPregunta() == null ? 999L : p1.getOrdenPregunta();
					Long orden2 = p2.getOrdenPregunta() == null ? 999L : p2.getOrdenPregunta();
					
					int orden = orden1.compareTo(orden2); 
					
					if (orden != 0) {
						return orden;
					}

					return p1.getDpruId().compareTo(p2.getDpruId());
				}
			});

			return preguntasDTO;
		} catch (Exception e) {
			log.error("Error en getRespuestasCorrectas", e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	private List<RespuestaDTO> getRespuestas(DetallePruebaUsuario detallePruebaUsuario, boolean mostrarCorrecto,
			boolean mostrarRetroalimentacion) {
		List<RespuestaDTO> respuestasDTO = new ArrayList<RespuestaDTO>();
		List<Respuesta> respuestas = detallePruebaUsuario.getPregunta().getRespuestas();
		for (Respuesta respuesta : respuestas) {
			if (!respuesta.getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				continue;
			}
			RespuestaDTO respuestaDTO = new RespuestaDTO();
			respuestaDTO.setRespId(respuesta.getRespId());
			respuestaDTO.setDescripcion(respuesta.getDescripcion());
			respuestaDTO.setRetroalimentacion(respuesta.getRetroalimentacion());
			respuestaDTO.setEsCorrecta(null);
			if (mostrarCorrecto) {
				if (detallePruebaUsuario.getRespuesta() != null
						&& detallePruebaUsuario.getRespuesta().getRespId().equals(respuesta.getRespId())) {
					respuestaDTO.setEsCorrecta(respuesta.getCorrecta().equals(Constantes.RESPUESTA_CORRECTA));
				}
			}
			if (mostrarRetroalimentacion) {
				respuestaDTO.setCorrecta(respuesta.getCorrecta());
			}
			respuestasDTO.add(respuestaDTO);
		}
		Collections.shuffle(respuestasDTO);
		return respuestasDTO;
	}
}
