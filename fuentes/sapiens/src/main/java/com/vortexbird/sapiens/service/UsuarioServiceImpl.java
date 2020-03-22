package com.vortexbird.sapiens.service;

import java.math.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vortexbird.sapiens.exception.*;
import com.vortexbird.sapiens.mapper.UsuarioMapper;
import com.vortexbird.sapiens.repository.*;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.PasswordGenerator;
import com.vortexbird.sapiens.utility.Utilities;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Validator validator;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Override
	public void validate(Usuario usuario) throws Exception {
		try {
			Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
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
		return usuarioRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		log.debug("finding all Usuario instances");
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Usuario save(Usuario entity) throws Exception {
		log.debug("saving Usuario instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Usuario");
			}

			validate(entity);

//			if (usuarioRepository.findById(entity.getUsuaId()).isPresent()) {
//				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//			}

			return usuarioRepository.save(entity);

		} catch (Exception e) {
			log.error("save Usuario failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Usuario entity) throws Exception {
		log.debug("deleting Usuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}

		if (entity.getUsuaId() == null) {
			throw new ZMessManager().new EmptyFieldException("usuaId");
		}

		findById(entity.getUsuaId()).ifPresent(entidad -> {
			List<PruebaUsuario> pruebaUsuarios = entidad.getPruebaUsuarios();

			if (Utilities.validationsList(pruebaUsuarios) == true) {
				throw new ZMessManager().new DeletingException("pruebaUsuarios");
			}
		});

		try {

			usuarioRepository.deleteById(entity.getUsuaId());
			log.debug("delete Usuario successful");

		} catch (Exception e) {
			log.error("delete Usuario failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Usuario instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("usuaId");
		}
		if (usuarioRepository.findById(id).isPresent()) {
			delete(usuarioRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Usuario update(Usuario entity) throws Exception {

		log.debug("updating Usuario instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Usuario");
			}

			validate(entity);

			return usuarioRepository.save(entity);

		} catch (Exception e) {
			log.error("update Usuario failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Integer usuaId) throws Exception {
		log.debug("getting Usuario instance");
		return usuarioRepository.findById(usuaId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO login(String codigo, String password) throws Exception {
		try {
			
			List<Usuario> usuariosEncontrados = usuarioRepository.findByCodigo(codigo);
			if (usuariosEncontrados==null || usuariosEncontrados.isEmpty()) {
				throw new Exception("No se encontró el usuario");
			}
			
			Usuario usuario = usuariosEncontrados.get(0);
			
			if (usuario.getPassword().equals( PasswordGenerator.hashPassword(password).get() )) {
				
				UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
				
				//Se consulta la información de la facultad y el programa al que pertenece el usuario
				Programa programa = usuario.getPrograma();
				usuarioDTO.setProgId(programa.getProgId());
				usuarioDTO.setNombrePrograma(programa.getNombre());
				
				usuarioDTO.setFacuId(programa.getFacultad().getFacuId());
				usuarioDTO.setNombreFacultad(programa.getFacultad().getNombre());
				
				return usuarioDTO;
			}else {
				throw new Exception("No se encontró el usuario");
			}
			
		} catch (Exception e) {
			log.error("Error en login", e);
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> getUsuariosPorTipo(Integer tiusId, String filtro, int pageNumber, int pageSize) throws Exception {
		try {
			
			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			if (filtro!=null && filtro.equals("*")) {
				filtro = "";
			}
			filtro = "%" + filtro + "%";
			
			Page<UsuarioDTO> usuarios = usuarioRepository.getUsuariosPorTipo(tiusId, filtro, pageable);
			
			return usuarios;
			
		} catch (Exception e) {
			log.error("Error en getUsuariosPorTipo", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(readOnly = true)
	public String getNombreUsuario(Integer usuaId) throws Exception{
		try {
			
			//Se consulta el usuario
			Optional<Usuario> usuario = findById(usuaId);
			
			if (!usuario.isPresent()) {
				return null;
			}else {
				return usuario.get().getApellido().toUpperCase() + ", " + Utilities.capitalize(usuario.get().getNombre().toLowerCase());
			}
			
		} catch (Exception e) {
			log.error("Error en getNombreUsuario", e);
			throw e;
		}
	}
}
