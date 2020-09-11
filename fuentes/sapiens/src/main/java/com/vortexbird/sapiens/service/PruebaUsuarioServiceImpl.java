package com.vortexbird.sapiens.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.domain.EstadoPrueba;
import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.domain.PruebaModulo;
import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.PruebaUsuarioRepository;
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
public class PruebaUsuarioServiceImpl implements PruebaUsuarioService {

	private static final Logger log = LoggerFactory.getLogger(PruebaUsuarioServiceImpl.class);

	@Autowired
	private PruebaUsuarioRepository pruebaUsuarioRepository;

	@Autowired
	private Validator validator;

	@Autowired
	private PreguntaService preguntaService;

	@Autowired
	private DetallePruebaUsuarioService detallePruebaUsuarioService;

	@Autowired
	private EstadoPruebaService estadoPruebaService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void validate(PruebaUsuario pruebaUsuario) throws Exception {
		try {
			Set<ConstraintViolation<PruebaUsuario>> constraintViolations = validator.validate(pruebaUsuario);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<PruebaUsuario> constraintViolation : constraintViolations) {
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
		return pruebaUsuarioRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PruebaUsuario> findAll() {
		log.debug("finding all PruebaUsuario instances");
		return pruebaUsuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PruebaUsuario save(PruebaUsuario entity) throws Exception {
		log.debug("saving PruebaUsuario instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
			}

			validate(entity);

			// if(pruebaUsuarioRepository.findById(entity.getPrusId()).isPresent()){
			// throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			// }

			return pruebaUsuarioRepository.save(entity);

		} catch (Exception e) {
			log.error("save PruebaUsuario failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(PruebaUsuario entity) throws Exception {
		log.debug("deleting PruebaUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
		}

		if (entity.getPrusId() == null) {
			throw new ZMessManager().new EmptyFieldException("prusId");
		}

		findById(entity.getPrusId()).ifPresent(entidad -> {
			List<DetallePruebaUsuario> detallePruebaUsuarios = entidad.getDetallePruebaUsuarios();

			if (Utilities.validationsList(detallePruebaUsuarios) == true) {
				throw new ZMessManager().new DeletingException("detallePruebaUsuarios");
			}
		});

		try {

			pruebaUsuarioRepository.deleteById(entity.getPrusId());
			log.debug("delete PruebaUsuario successful");

		} catch (Exception e) {
			log.error("delete PruebaUsuario failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting PruebaUsuario instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("prusId");
		}
		if (pruebaUsuarioRepository.findById(id).isPresent()) {
			delete(pruebaUsuarioRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PruebaUsuario update(PruebaUsuario entity) throws Exception {

		log.debug("updating PruebaUsuario instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
			}

			validate(entity);

			return pruebaUsuarioRepository.save(entity);

		} catch (Exception e) {
			log.error("update PruebaUsuario failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PruebaUsuario> findById(Integer prusId) throws Exception {
		log.debug("getting PruebaUsuario instance");
		return pruebaUsuarioRepository.findById(prusId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PruebaUsuarioDTO> getPruebas(Long usuaId, Long prusId) throws Exception {
		try {
			// Se consulta el usuario
			Optional<Usuario> usuario = usuarioService.findById(usuaId.intValue());
			if (!usuario.isPresent() || !usuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				throw new Exception("No se encontró el usuario o no se encuentra activo");
			}

			if(prusId == null){
				prusId = -1L;
			}

			List<PruebaUsuarioDTO> pruebasUsuario = pruebaUsuarioRepository.findByUsuario(usuario.get().getUsuaId(), prusId.intValue(), Constantes.ESTADO_ACTIVO);
			return pruebasUsuario;
		} catch (Exception e) {
			log.error("Error en getPruebas", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void iniciarPrueba(Integer prusId, Long usuario) throws Exception {
		try {
			// Valido que lleguen los datos
			if (prusId == null) {
				throw new Exception("No se ingresó la prueba a iniciar");
			}
			if (usuario == null) {
				throw new Exception("No se ingresó el usuario");
			}

			// Valido que exista la prueba y se encuentra pendiente por iniciar
			Optional<PruebaUsuario> pruebaUsuarioOpt = findById(prusId);
			if (!pruebaUsuarioOpt.isPresent()
					|| pruebaUsuarioOpt.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("No se encontró la prueba o no se encuentra activa");
			}

			PruebaUsuario pruebaUsuario = pruebaUsuarioOpt.get();
			if (!pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_SIN_INICIAR)
					&& !pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_PAUSADA)) {
				throw new Exception("La prueba ya ha sido iniciada");
			}

			Prueba prueba = pruebaUsuario.getPrueba();
			Date fecha = new Date();
			if (prueba.getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("La prueba ha sido cancelada");
			}
			if (prueba.getFechaInicial().after(fecha)){
				throw new Exception("La prueba no se encuentra disponible para comenzar");
			}
			if (prueba.getFechaFinal().before(fecha)){
				throw new Exception("Se terminó el tiempo de la prueba");
			}

			// Si la prueba apenas se va a iniciar, se construye el cuestionario
			if (pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_SIN_INICIAR)) {
				List<PruebaModulo> pruebasModulo = prueba.getPruebaModulos();
				List<Pregunta> preguntas = new ArrayList<Pregunta>();
				for (PruebaModulo pruebaModulo : pruebasModulo) {
					// Por cada modulo consulto las preguntas a realizar
					Modulo modulo = pruebaModulo.getModulo();
					int cantidadPreguntas = modulo.getCantidadPreguntas().intValue();
					List<Pregunta> preguntasModulo = preguntaService.getPreguntasPorModulo(modulo.getModuId());
					Collections.shuffle(preguntasModulo);
					preguntasModulo = preguntasModulo.subList(0,
							preguntasModulo.size() > cantidadPreguntas ? cantidadPreguntas : preguntasModulo.size());
					preguntas.addAll(preguntasModulo);
				}

				Long puntaje = 0L;
				
				for (Pregunta pregunta : preguntas) {
					DetallePruebaUsuario detallePruebaUsuario = new DetallePruebaUsuario();
					detallePruebaUsuario.setPruebaUsuario(pruebaUsuario);
					detallePruebaUsuario.setPregunta(pregunta);
					detallePruebaUsuario.setUsuCreador(usuario);
					detallePruebaUsuario.setFechaCreacion(fecha);
					detallePruebaUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					detallePruebaUsuarioService.save(detallePruebaUsuario);
					puntaje += pregunta.getValorPregunta() == null ? 50L : pregunta.getValorPregunta();
				}
				pruebaUsuario.setTotalPreguntas(puntaje);
			}

			Optional<EstadoPrueba> estadoPrueba = estadoPruebaService.findById(Constantes.ESTADO_PRUEBA_INICIADA);

			pruebaUsuario.setFechaInicio(new Date());
			pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
			pruebaUsuario.setFechaModificacion(new Date());
			pruebaUsuario.setUsuModificador(usuario);

			update(pruebaUsuario);

		} catch (Exception e) {
			log.error("Error en iniciarPrueba", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void pausarPrueba(Integer prusId, Long usuario) throws Exception {
		try {
			// Valido que lleguen los datos
			if (prusId == null) {
				throw new Exception("No se ingresó la prueba a finalizar");
			}
			if (usuario == null) {
				throw new Exception("No se ingresó el usuario");
			}
			// Valido que exista la prueba y se encuentra iniciada
			Optional<PruebaUsuario> pruebaUsuarioOpt = findById(prusId);
			if (!pruebaUsuarioOpt.isPresent()
					|| pruebaUsuarioOpt.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("No se encontró la prueba o no se encuentra activa");
			}

			PruebaUsuario pruebaUsuario = pruebaUsuarioOpt.get();
			if (!pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_INICIADA)) {
				throw new Exception("La prueba no ha sido iniciada");
			}

			Prueba prueba = pruebaUsuario.getPrueba();
			if (!prueba.getTipoPrueba().getTiprId().equals(Constantes.TIPO_PRUEBA_ENTRENAMIENTO)) {
				throw new Exception("No es posible pausar una prueba que no sea un entrenamiento");
			}

			// Calculo el tiempo disponible restando el tiempo actual menos la diferencia
			// entre la fecha de inicio y la fecha actual
			Date fecha = new Date();
			Long tiempoDisponible = pruebaUsuario.getTiempoDisponible();
			Long milliseconds = fecha.getTime() - pruebaUsuario.getFechaInicio().getTime();
			int minutes = (int) ((milliseconds / (1000 * 60)) % 60) + 1;
			tiempoDisponible -= minutes;
			if (tiempoDisponible < 0L) {
				tiempoDisponible = 0L;
			}

			Optional<EstadoPrueba> estadoPrueba = estadoPruebaService.findById(Constantes.ESTADO_PRUEBA_PAUSADA);

			pruebaUsuario.setFechaInicio(null);
			pruebaUsuario.setTiempoDisponible(tiempoDisponible);
			pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
			pruebaUsuario.setFechaModificacion(new Date());
			pruebaUsuario.setUsuModificador(usuario);

			update(pruebaUsuario);

		} catch (Exception e) {
			log.error("Error en pausarPrueba", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void finalizarPrueba(Integer prusId, Long usuario) throws Exception {
		try {
			// Valido que lleguen los datos
			if (prusId == null) {
				throw new Exception("No se ingresó la prueba a finalizar");
			}
			if (usuario == null) {
				throw new Exception("No se ingresó el usuario");
			}
			// Valido que exista la prueba y se encuentra iniciada
			Optional<PruebaUsuario> pruebaUsuarioOpt = findById(prusId);
			if (!pruebaUsuarioOpt.isPresent()
					|| pruebaUsuarioOpt.get().getEstadoRegistro().equals(Constantes.ESTADO_INACTIVO)) {
				throw new Exception("No se encontró la prueba o no se encuentra activa");
			}

			PruebaUsuario pruebaUsuario = pruebaUsuarioOpt.get();
			if (pruebaUsuario.getEstadoPrueba().getEsprId().equals(Constantes.ESTADO_PRUEBA_TERMINADA)) {
				throw new Exception("La prueba ya ha sido finalizada");
			}

			//Calculos las respuestas correctas obtenidas
			List<DetallePruebaUsuario> detallesPruebaUsuario = detallePruebaUsuarioService.getRespuestasCorrectas(prusId);
			Long puntaje = 0L;
			//Sumo el puntaje por pregunta
			for (int i = 0; i < detallesPruebaUsuario.size(); i++) {
				DetallePruebaUsuario detalle = detallesPruebaUsuario.get(i);
				puntaje += detalle.getPregunta().getValorPregunta() == null ? 50L : detalle.getPregunta().getValorPregunta();
			}

			Optional<EstadoPrueba> estadoPrueba = estadoPruebaService.findById(Constantes.ESTADO_PRUEBA_TERMINADA);
			pruebaUsuario.setFechaInicio(null);
			pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
			pruebaUsuario.setFechaModificacion(new Date());
			pruebaUsuario.setUsuModificador(usuario);
			pruebaUsuario.setTotalRespuestasCorrectas(puntaje);

			update(pruebaUsuario);
		} catch (Exception e) {
			log.error("Error en finalizarPrueba", e);
			throw e;
		}
	}
}
