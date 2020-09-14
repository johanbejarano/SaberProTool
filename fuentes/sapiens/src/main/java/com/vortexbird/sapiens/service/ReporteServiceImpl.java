package com.vortexbird.sapiens.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Contexto;
import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.ContextoDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.mapper.ContextoMapper;
import com.vortexbird.sapiens.repository.ContextoRepository;
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
public class ReporteServiceImpl implements ContextoService {

	private static final Logger log = LoggerFactory.getLogger(ReporteServiceImpl.class);

	@Autowired
	private ContextoRepository contextoRepository;
	
	@Autowired
	private ContextoMapper contextoMapper;

	@Autowired
	private Validator validator;
	
	@Autowired
	private ModuloService moduloService;

	@Override
	public void validate(Contexto contexto) throws Exception {
		try {
			Set<ConstraintViolation<Contexto>> constraintViolations = validator.validate(contexto);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Contexto> constraintViolation : constraintViolations) {
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
		return contextoRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contexto> findAll() {
		log.debug("finding all Contexto instances");
		return contextoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Contexto save(Contexto entity) throws Exception {
		log.debug("saving Contexto instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Contexto");
			}

			validate(entity);

			return contextoRepository.save(entity);

		} catch (Exception e) {
			log.error("save Contexto failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Contexto entity) throws Exception {
		log.debug("deleting Contexto instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Contexto");
		}

		if (entity.getContId() == null) {
			throw new ZMessManager().new EmptyFieldException("tiusId");
		}

		findById(entity.getContId()).ifPresent(entidad -> {
			List<Usuario> usuarios = entidad.getUsuarios();

			if (Utilities.validationsList(usuarios) == true) {
				throw new ZMessManager().new DeletingException("usuarios");
			}
		});

		try {

			contextoRepository.deleteById(entity.getContId());
			log.debug("delete Contexto successful");

		} catch (Exception e) {
			log.error("delete Contexto failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Contexto instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("tiusId");
		}
		if (contextoRepository.findById(id).isPresent()) {
			delete(contextoRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Contexto update(Contexto entity) throws Exception {

		log.debug("updating Contexto instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Contexto");
			}

			validate(entity);

			return contextoRepository.save(entity);

		} catch (Exception e) {
			log.error("update Contexto failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Contexto> findById(Integer tiusId) throws Exception {
		log.debug("getting Contexto instance");
		return contextoRepository.findById(tiusId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContextoDTO> getByModulo(Integer moduId) throws Exception {
		try {
			List<Contexto> contextos = contextoRepository.findByModulo_moduIdAndEstadoRegistro(moduId, Constantes.ESTADO_ACTIVO);
			return contextoMapper.listContextoToListContextoDTO(contextos);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guardar(ContextoDTO contextoDTO) throws Exception {
		try {
			//Valido que llegue la info
			if(contextoDTO == null) {
				throw new Exception("El contexto es obligatorio");
			}
			//Si no tiene contId se crea
			Contexto contexto;
			if(contextoDTO.getContId() == null) {
				contexto = new Contexto();
			}else {
				//Valido que exista
				contexto = findById(contextoDTO.getContId()).get();
			}
			contexto.setNombre(contextoDTO.getNombre().trim());
			contexto.setDescripcion(contextoDTO.getDescripcion());
			
			if(contextoDTO.getContId() == null) {
				Modulo modulo = moduloService.findById(contextoDTO.getModuId()).get();
				contexto.setModulo(modulo);
				contexto.setUsuCreador(contextoDTO.getUsuCreador());
				contexto.setFechaCreacion(new Date());
				contexto.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				save(contexto);
			}else {
				contexto.setUsuModificador(contextoDTO.getUsuCreador());
				contexto.setFechaModificacion(new Date());
				update(contexto);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
