package com.vortexbird.sapiens.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Contexto;
import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.domain.Respuesta;
import com.vortexbird.sapiens.domain.TipoPregunta;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.CargueMasivoDTO;
import com.vortexbird.sapiens.dto.GuardarPreguntaDTO;
import com.vortexbird.sapiens.dto.PreguntaDTO;
import com.vortexbird.sapiens.dto.RespuestaDTO;
import com.vortexbird.sapiens.dto.UsuarioDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.mapper.PreguntaMapper;
import com.vortexbird.sapiens.repository.PreguntaRepository;
import com.vortexbird.sapiens.utility.Constantes;
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
public class PreguntaServiceImpl implements PreguntaService {

	private static final Logger log = LoggerFactory.getLogger(PreguntaServiceImpl.class);

	@Autowired
	private PreguntaRepository preguntaRepository;

	@Autowired
	private Validator validator;

	@Autowired
	private ModuloService moduloService;

	@Autowired
	private TipoPreguntaService tipoPreguntaService;

	@Autowired
	private RespuestaService respuestaService;

	@Autowired
	private DetallePruebaUsuarioService detallePruebaUsuarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PreguntaMapper preguntaMapper;

	@Autowired
	private ContextoService contextoService;

	@Override
	public void validate(Pregunta pregunta) throws Exception {
		try {
			Set<ConstraintViolation<Pregunta>> constraintViolations = validator.validate(pregunta);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Pregunta> constraintViolation : constraintViolations) {
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
		return preguntaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> findAll() {
		log.debug("finding all Pregunta instances");
		return preguntaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Pregunta save(Pregunta entity) throws Exception {
		log.debug("saving Pregunta instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Pregunta");
			}

			validate(entity);

//			if (preguntaRepository.findById(entity.getPregId()).isPresent()) {
//				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//			}

			return preguntaRepository.save(entity);

		} catch (Exception e) {
			log.error("save Pregunta failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Pregunta entity) throws Exception {
		log.debug("deleting Pregunta instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Pregunta");
		}

		if (entity.getPregId() == null) {
			throw new ZMessManager().new EmptyFieldException("pregId");
		}

		findById(entity.getPregId()).ifPresent(entidad -> {
			List<DetallePruebaUsuario> detallePruebaUsuarios = entidad.getDetallePruebaUsuarios();

			if (Utilities.validationsList(detallePruebaUsuarios) == true) {
				throw new ZMessManager().new DeletingException("detallePruebaUsuarios");
			}
			List<Respuesta> respuestas = entidad.getRespuestas();

			if (Utilities.validationsList(respuestas) == true) {
				throw new ZMessManager().new DeletingException("respuestas");
			}
		});

		try {

			preguntaRepository.deleteById(entity.getPregId());
			log.debug("delete Pregunta successful");

		} catch (Exception e) {
			log.error("delete Pregunta failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Pregunta instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("pregId");
		}
		if (preguntaRepository.findById(id).isPresent()) {
			delete(preguntaRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Pregunta update(Pregunta entity) throws Exception {

		log.debug("updating Pregunta instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Pregunta");
			}

			validate(entity);

			return preguntaRepository.save(entity);

		} catch (Exception e) {
			log.error("update Pregunta failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pregunta> findById(Integer pregId) throws Exception {
		log.debug("getting Pregunta instance");
		return preguntaRepository.findById(pregId);
	}

	@Override
	@Transactional(readOnly = true)
	public PreguntaDTO getPregunta(Integer pregId) throws Exception {
		log.debug("getPregunta");

		Optional<Pregunta> pregunta = findById(pregId);
		if (!pregunta.isPresent()) {
			return null;
		}

		PreguntaDTO preguntaDTO = preguntaMapper.preguntaToPreguntaDTO(pregunta.get());

		// Se consulta el modulo de la pregunta
		preguntaDTO.setNombreModulo(pregunta.get().getModulo().getNombre());

		return preguntaDTO;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pregunta guardarPregunta(GuardarPreguntaDTO guardarPreguntaDTO) throws Exception {
		try {

			log.info("Guardando la pregunta");

			if (guardarPreguntaDTO.getModuId_Modulo() == null) {
				throw new Exception("El Id de módulo es obligatorio");
			}

			if (guardarPreguntaDTO.getTprgId_TipoPregunta() == null) {
				throw new Exception("El Id de tipo pregunta es obligatorio");
			}

			// Se consulta el modulo
			Optional<Modulo> modulo = moduloService.findById(guardarPreguntaDTO.getModuId_Modulo());
			if (!modulo.isPresent()) {
				throw new Exception("No exite el módulo " + guardarPreguntaDTO.getModuId_Modulo());
			}

			// Se consulta el tipo pregunta
			Optional<TipoPregunta> tipoPregunta = tipoPreguntaService
					.findById(guardarPreguntaDTO.getTprgId_TipoPregunta());
			if (!tipoPregunta.isPresent()) {
				throw new Exception("No existe el tipo pregunta " + guardarPreguntaDTO.getTprgId_TipoPregunta());
			}

			// Se consulta el usuario
			Optional<Usuario> usuario = usuarioService.findById(guardarPreguntaDTO.getUsuCreador());
			if (!tipoPregunta.isPresent()) {
				throw new Exception("No existe el tipo pregunta " + guardarPreguntaDTO.getTprgId_TipoPregunta());
			}

			// Se consulta el contexto si tiene
			Contexto contexto = null;
			if (guardarPreguntaDTO.getContId() != null) {
				Optional<Contexto> contextoOpt = contextoService.findById(guardarPreguntaDTO.getContId());
				if (!contextoOpt.isPresent()) {
					throw new Exception("No existe el contexto " + guardarPreguntaDTO.getContId());
				}
				contexto = contextoOpt.get();
			}

			// Se crea la pregunta
			Pregunta pregunta = new Pregunta();

			pregunta.setDescripcion(guardarPreguntaDTO.getDescripcion());
			pregunta.setEstadoRegistro(guardarPreguntaDTO.getEstadoRegistro());
			pregunta.setFechaCreacion(new Date());
			pregunta.setModulo(modulo.get());
			pregunta.setRetroalimentacion(guardarPreguntaDTO.getRetroalimentacion());
			pregunta.setTipoPregunta(tipoPregunta.get());
			pregunta.setUsuario(usuario.get());
			pregunta.setComplejidad(guardarPreguntaDTO.getComplejidad());
			pregunta.setValorPregunta(guardarPreguntaDTO.getValorPregunta());
			pregunta.setContexto(contexto);

			save(pregunta);

			if (pregunta.getTipoPregunta().getTprgId() == 1L) {
				// Se guardan las respuestas
				List<RespuestaDTO> respuestas = guardarPreguntaDTO.getRespuestasDTO();
				if (respuestas == null || respuestas.isEmpty()) {
					throw new Exception("Las respuestas no pueden estar vacías");
				}

				for (RespuestaDTO respuestaDTO : respuestas) {

					// Se crea la respuesta
					Respuesta respuesta = new Respuesta();

					respuesta.setCorrecta(respuestaDTO.getCorrecta());
					respuesta.setDescripcion(respuestaDTO.getDescripcion());
					respuesta.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					respuesta.setFechaCreacion(new Date());
					respuesta.setPregunta(pregunta);
					respuesta.setRetroalimentacion(
							respuesta.getRetroalimentacion() == null ? "-" : respuesta.getRetroalimentacion());
					respuesta.setUsuCreador(guardarPreguntaDTO.getUsuCreador().longValue());

					respuestaService.save(respuesta);

				}
			}
			log.info("Pregunta guardada");
			return pregunta;
		} catch (Exception e) {
			log.error("Error guardando pregunta", e);

			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Pregunta actualizarPregunta(GuardarPreguntaDTO guardarPreguntaDTO) throws Exception {
		try {

			// Se consulta la pregunta
			Optional<Pregunta> optPregunta = findById(guardarPreguntaDTO.getPregId());

			if (!optPregunta.isPresent()) {
				throw new Exception("No existe la pregunta " + guardarPreguntaDTO.getPregId());
			}

			if (guardarPreguntaDTO.getModuId_Modulo() == null) {
				throw new Exception("El Id de módulo es obligatorio");
			}

			if (guardarPreguntaDTO.getTprgId_TipoPregunta() == null) {
				throw new Exception("El Id de tipo pregunta es obligatorio");
			}

			// Se consulta el modulo
			Optional<Modulo> modulo = moduloService.findById(guardarPreguntaDTO.getModuId_Modulo());
			if (!modulo.isPresent()) {
				throw new Exception("No exite el módulo " + guardarPreguntaDTO.getModuId_Modulo());
			}

			// Se consulta el tipo pregunta
			Optional<TipoPregunta> tipoPregunta = tipoPreguntaService
					.findById(guardarPreguntaDTO.getTprgId_TipoPregunta());
			if (!tipoPregunta.isPresent()) {
				throw new Exception("No existe el tipo pregunta " + guardarPreguntaDTO.getTprgId_TipoPregunta());
			}

			// Se consulta el usuario
			Optional<Usuario> usuario = usuarioService.findById(guardarPreguntaDTO.getUsuCreador());
			if (!usuario.isPresent()) {
				throw new Exception("No existe el usuario " + guardarPreguntaDTO.getTprgId_TipoPregunta());
			}

			// Se consulta el contexto si tiene
			Contexto contexto = null;
			if (guardarPreguntaDTO.getContId() != null) {
				Optional<Contexto> contextoOpt = contextoService.findById(guardarPreguntaDTO.getContId());
				if (!contextoOpt.isPresent()) {
					throw new Exception("No existe el contexto " + guardarPreguntaDTO.getContId());
				}
				contexto = contextoOpt.get();
			}

			Pregunta pregunta = optPregunta.get();
			
			if(!pregunta.getUsuario().getUsuaId().equals(guardarPreguntaDTO.getUsuCreador())) {
				throw new Exception("El usuario que modifica no es el dueño de la pregunta");
			}

			// Se valida que la pregunta NO haya tenido ya ejecución (Respuestas realizadas
			// por estudiantes)
			List<DetallePruebaUsuario> detallesPruebaUsuario = detallePruebaUsuarioService.findByPregunta(pregunta);
			if (detallesPruebaUsuario != null && detallesPruebaUsuario.size() > 0) {
				throw new Exception(
						"La pregunta no se puede modificar. Ya se encuentra con algunas ejecuciones (Respuestas) en simulaciones o talleres");
			}

			// Se modifican los datos de la pregunta
			pregunta.setDescripcion(guardarPreguntaDTO.getDescripcion());
			pregunta.setEstadoRegistro(guardarPreguntaDTO.getEstadoRegistro());
			pregunta.setFechaModificacion(new Date());
			pregunta.setModulo(modulo.get());
			pregunta.setRetroalimentacion(guardarPreguntaDTO.getRetroalimentacion());
			pregunta.setTipoPregunta(tipoPregunta.get());
			pregunta.setComplejidad(guardarPreguntaDTO.getComplejidad());
			pregunta.setValorPregunta(guardarPreguntaDTO.getValorPregunta());
			pregunta.setContexto(contexto);

			update(pregunta);

			// Se borran las respuestas originales de la pregunta
			List<Respuesta> respuestasOriginales = pregunta.getRespuestas();
			for (Respuesta respuesta : respuestasOriginales) {
				respuestaService.delete(respuesta);
			}

			if (pregunta.getTipoPregunta().getTprgId() == 1L) {

				// Se crean las nuevas respuestas
				List<RespuestaDTO> respuestas = guardarPreguntaDTO.getRespuestasDTO();
				if (respuestas == null || respuestas.isEmpty()) {
					throw new Exception("Las respuestas no pueden estar vacías");
				}

				for (RespuestaDTO respuestaDTO : respuestas) {

					// Se crea la respuesta
					Respuesta respuesta = new Respuesta();

					respuesta.setCorrecta(respuestaDTO.getCorrecta());
					respuesta.setDescripcion(respuestaDTO.getDescripcion());
					respuesta.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					respuesta.setFechaCreacion(new Date());
					respuesta.setPregunta(pregunta);
					respuesta.setRetroalimentacion(respuestaDTO.getRetroalimentacion());
					respuesta.setRetroalimentacion(
							respuesta.getRetroalimentacion() == null ? "-" : respuesta.getRetroalimentacion());
					respuesta.setUsuCreador(guardarPreguntaDTO.getUsuCreador().longValue());

					respuestaService.save(respuesta);

				}
			}

			log.info("Pregunta modificada");

			return pregunta;

		} catch (Exception e) {
			log.error("Error actualizarPregunta", e);

			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<PreguntaDTO> getPreguntasPorUsuario(Integer usuaId) throws Exception {
		try {

			// Se consulta el usuario
			Optional<Usuario> optUsuario = usuarioService.findById(usuaId);

			if (!optUsuario.isPresent() || !optUsuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				throw new Exception("No se encontró el usuario, o no se encuentra activo: " + usuaId);
			}

			Usuario usuario = optUsuario.get();

			// Se obtiene el programa académico del usuario
			Programa programa = usuario.getPrograma();

			// Se valida si el programa no está activo
			if (programa == null || !programa.getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				throw new Exception("No existe el programa o se encuentra inactivo: " + programa.getProgId());
			}

			// Se obtienen todos los módulos que el programa tiene asignados
			List<ProgramaModulo> programasModulo = programa.getProgramaModulos();
			if (programasModulo == null || programasModulo.size() == 0) {
				throw new Exception("El programa (" + programa.getProgId() + ") " + programa.getNombre()
						+ ", no tiene módulos asignados");
			}

			List<Pregunta> preguntas = new ArrayList<>();
			for (ProgramaModulo programaModulo : programasModulo) {

				// Se obtienen todas las preguntas activas del módulo
				List<Pregunta> preguntasDelModulo = preguntaRepository.findByModulo_moduIdAndEstadoRegistro(
						programaModulo.getModulo().getModuId(), Constantes.ESTADO_ACTIVO);

				preguntas.addAll(preguntasDelModulo);
			}

			// Se obtienen todas las preguntas inactivas que sean creadas por el usuario
			List<Pregunta> preguntasDelUsuario = preguntaRepository
					.findByEstadoRegistroAndUsuario_usuaId(Constantes.ESTADO_INACTIVO, usuaId);
			preguntas.addAll(preguntasDelUsuario);

			List<PreguntaDTO> preguntasDTO = preguntaMapper.listPreguntaToListPreguntaDTO(preguntas);

			// A cada pregunta se le calcula su modulo
			for (int i = 0; i < preguntas.size(); i++) {

				Pregunta pregunta = preguntas.get(i);
				Modulo modulo = pregunta.getModulo();

				preguntasDTO.get(i).setNombreModulo(modulo.getNombre());

				List<Respuesta> respuestas = pregunta.getRespuestas();
				List<RespuestaDTO> respuestasDTO = new ArrayList<RespuestaDTO>();
				for (int j = 0; j < respuestas.size(); j++) {
					RespuestaDTO respuesta = new RespuestaDTO();
					respuesta.setDescripcion(respuestas.get(j).getDescripcion());
					respuestasDTO.add(respuesta);
				}
				preguntasDTO.get(i).setRespuestasDTO(respuestasDTO);
			}

			preguntasDTO.sort(new Comparator<PreguntaDTO>() {
				@Override
				public int compare(PreguntaDTO p1, PreguntaDTO p2) {
					return p2.getPregId().compareTo(p1.getPregId());
				}
			});

			return preguntasDTO;

		} catch (Exception e) {
			log.error("Error getPreguntasPorUsuario", e);

			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pregunta> getPreguntasPorModulo(Integer moduId) throws Exception {
		return preguntaRepository.findByModulo_moduIdAndEstadoRegistro(moduId, Constantes.ESTADO_ACTIVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cargar(CargueMasivoDTO request) throws Exception {
		try {
			// Se valida el request
			if (request == null) {
				throw new Exception("El request se encuentra vacío");
			}
			if (request.getPreguntas() == null || request.getPreguntas().isEmpty()) {
				throw new Exception("La lista se encuentra vacía");
			}
			if (request.getUsuarioCreador() == null) {
				throw new Exception("El usuario se encuentra vacío");
			}
			// Obtengo todos los programas
			List<Modulo> modulos = moduloService.findAll();
			Map<String, Modulo> modulosMap = modulos.stream()
					.collect(Collectors.toMap(Modulo::getNombre, modulo -> modulo));
			for (PreguntaDTO pregunta : request.getPreguntas()) {
				if (!modulosMap.containsKey(pregunta.getNombreModulo().trim().toUpperCase())) {
					throw new Exception("El módulo " + pregunta.getNombreModulo().trim() + " no existe en el sistema");
				}
				GuardarPreguntaDTO preguntaGuardar = new GuardarPreguntaDTO();

				preguntaGuardar
						.setModuId_Modulo(modulosMap.get(pregunta.getNombreModulo().trim().toUpperCase()).getModuId());
				preguntaGuardar.setTprgId_TipoPregunta(pregunta.getTprgId_TipoPregunta());
				preguntaGuardar.setDescripcion(pregunta.getDescripcion());
				preguntaGuardar.setRetroalimentacion(pregunta.getRetroalimentacion());
				preguntaGuardar.setComplejidad(pregunta.getComplejidad());
				preguntaGuardar.setValorPregunta(pregunta.getValorPregunta());

				preguntaGuardar.setRespuestasDTO(pregunta.getRespuestasDTO());

				preguntaGuardar.setUsuCreador(request.getUsuarioCreador().intValue());
				preguntaGuardar.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				guardarPregunta(preguntaGuardar);
			}

		} catch (Exception e) {
			throw e;
		}
	}

}
