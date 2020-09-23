package com.vortexbird.sapiens.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.domain.PruebaModulo;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.ModuloDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.ModuloRepository;
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
public class ModuloServiceImpl implements ModuloService {

	private static final Logger log = LoggerFactory.getLogger(ModuloServiceImpl.class);

	@Autowired
	private ModuloRepository moduloRepository;

	@Autowired
	private Validator validator;

	@Autowired
	private TipoModuloService tipoModuloService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProgramaModuloService programaModuloService;

	@Override
	public void validate(Modulo modulo) throws Exception {
		try {
			Set<ConstraintViolation<Modulo>> constraintViolations = validator.validate(modulo);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Modulo> constraintViolation : constraintViolations) {
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
		return moduloRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Modulo> findAll() {
		log.debug("finding all Modulo instances");
		return moduloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Modulo save(Modulo entity) throws Exception {
		log.debug("saving Modulo instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Modulo");
			}

			validate(entity);

			return moduloRepository.save(entity);

		} catch (Exception e) {
			log.error("save Modulo failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Modulo entity) throws Exception {
		log.debug("deleting Modulo instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Modulo");
		}

		if (entity.getModuId() == null) {
			throw new ZMessManager().new EmptyFieldException("moduId");
		}

		findById(entity.getModuId()).ifPresent(entidad -> {
			List<Pregunta> preguntas = entidad.getPreguntas();

			if (Utilities.validationsList(preguntas) == true) {
				throw new ZMessManager().new DeletingException("preguntas");
			}
			List<ProgramaModulo> programaModulos = entidad.getProgramaModulos();

			if (Utilities.validationsList(programaModulos) == true) {
				throw new ZMessManager().new DeletingException("programaModulos");
			}
			List<PruebaModulo> pruebaModulos = entidad.getPruebaModulos();

			if (Utilities.validationsList(pruebaModulos) == true) {
				throw new ZMessManager().new DeletingException("pruebaModulos");
			}
		});

		try {

			moduloRepository.deleteById(entity.getModuId());
			log.debug("delete Modulo successful");

		} catch (Exception e) {
			log.error("delete Modulo failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Modulo instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("moduId");
		}
		if (moduloRepository.findById(id).isPresent()) {
			delete(moduloRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Modulo update(Modulo entity) throws Exception {

		log.debug("updating Modulo instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Modulo");
			}

			validate(entity);

			return moduloRepository.save(entity);

		} catch (Exception e) {
			log.error("update Modulo failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Modulo> findById(Integer moduId) throws Exception {
		log.debug("getting Modulo instance");
		return moduloRepository.findById(moduId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Modulo> findByTipoModulo(Integer timoId) throws Exception {
		try {
			return moduloRepository.findByTipoModulo_timoId(timoId);
		} catch (Exception e) {
			log.error("Error findByTipoModulo", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Modulo> findByPrograma(Integer progId) throws Exception {
		try {
			List<Modulo> modulos = new ArrayList<Modulo>();
			List<ProgramaModulo> programaModulos = programaModuloService.findByPrograma(progId);
			for (ProgramaModulo programaModulo : programaModulos) {
				modulos.add(programaModulo.getModulo());
			}
			return modulos;
		} catch (Exception e) {
			log.error("Error findByTipoModulo", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guardar(ModuloDTO moduloDTO) throws Exception{
		try {
			Modulo modulo; 
			//Si no trae ID lo creo
			if(moduloDTO.getModuId() == null){
				modulo = new Modulo();
			}else{
				modulo = findById(moduloDTO.getModuId()).get();
			}

			modulo.setNombre(moduloDTO.getNombre().toUpperCase());
			modulo.setDescripcion(moduloDTO.getDescripcion());
			modulo.setCantidadPreguntas1(moduloDTO.getCantidadPreguntas1());
			modulo.setCantidadPreguntas2(moduloDTO.getCantidadPreguntas2());
			modulo.setCantidadPreguntas3(moduloDTO.getCantidadPreguntas3());
			modulo.setCantidadPreguntas4(moduloDTO.getCantidadPreguntas4());
			modulo.setPrioridad(moduloDTO.getPrioridad());
			modulo.setIgualValor(moduloDTO.getIgualValor());
			modulo.setTipoModulo(tipoModuloService.findById(moduloDTO.getTimoId_TipoModulo()).get());
			modulo.setEstadoRegistro(moduloDTO.getEstadoRegistro());

			if(moduloDTO.getModuId() == null){
				modulo.setUsuCreador(moduloDTO.getUsuCreador());
				modulo.setFechaCreacion(new Date());
				save(modulo);

				//Cuando se crea un módulo se le agrega al programa del usuario
				Usuario usuario = usuarioService.findById(moduloDTO.getUsuCreador().intValue()).get();
				ProgramaModulo programaModulo = new ProgramaModulo();
				programaModulo.setModulo(modulo);
				programaModulo.setPrograma(usuario.getPrograma());
				programaModulo.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				programaModulo.setUsuCreador(moduloDTO.getUsuCreador());
				programaModulo.setFechaCreacion(new Date());
				programaModuloService.save(programaModulo);
			}else{
				modulo.setUsuModificador(moduloDTO.getUsuCreador());
				modulo.setFechaModificacion(new Date());
				update(modulo);
			}
			
		} catch (Exception e) {
			log.error("Error en guardar", e);
			throw e;
		}
	}

}
