package com.vortexbird.sapiens.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.domain.TipoUsuario;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.CargueMasivoDTO;
import com.vortexbird.sapiens.dto.UsuarioDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.mapper.UsuarioMapper;
import com.vortexbird.sapiens.repository.UsuarioRepository;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.PasswordGenerator;
import com.vortexbird.sapiens.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	private ProgramaService programaService;

	@Autowired
	private EmailService emailService;

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
			if (usuariosEncontrados == null || usuariosEncontrados.isEmpty()) {
				throw new Exception("No se encontró el usuario");
			}

			Usuario usuario = usuariosEncontrados.get(0);

			if (usuario.getPassword().equals(PasswordGenerator.hashPassword(password).get())) {

				UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);

				// Se consulta la información de la facultad y el programa al que pertenece el
				// usuario
				Programa programa = usuario.getPrograma();
				usuarioDTO.setProgId(programa.getProgId());
				usuarioDTO.setNombrePrograma(programa.getNombre());

				usuarioDTO.setFacuId(programa.getFacultad().getFacuId());
				usuarioDTO.setNombreFacultad(programa.getFacultad().getNombre());

				return usuarioDTO;
			} else {
				throw new Exception("No se encontró el usuario");
			}

		} catch (Exception e) {
			log.error("Error en login", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> getUsuariosPorTipo(Integer tiusId, String filtro, int pageNumber, int pageSize)
			throws Exception {
		try {

			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			if (filtro != null && filtro.equals("*")) {
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
	public String getNombreUsuario(Integer usuaId) throws Exception {
		try {

			// Se consulta el usuario
			Optional<Usuario> usuario = findById(usuaId);

			if (!usuario.isPresent()) {
				return null;
			} else {
				return usuario.get().getApellido().toUpperCase() + ", "
						+ Utilities.capitalize(usuario.get().getNombre().toLowerCase());
			}

		} catch (Exception e) {
			log.error("Error en getNombreUsuario", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guardar(UsuarioDTO usuarioDTO) throws Exception {
		try {
			Usuario usuario;
			// Si no trae ID lo creo
			if (usuarioDTO.getUsuaId() == null) {
				usuario = new Usuario();
			} else {
				usuario = findById(usuarioDTO.getUsuaId()).get();
			}

			// Valido que el código no se repita ni la identificación
			List<Usuario> usuarioTmp = usuarioRepository.findByCodigo(usuarioDTO.getCodigo());
			if (!usuarioTmp.isEmpty()) {
				if (usuarioDTO.getUsuaId() == null || !usuarioTmp.get(0).getUsuaId().equals(usuarioDTO.getUsuaId())) {
					throw new Exception("Ya se encuentra un usuario con el código ingresado");
				}
			}
			usuarioTmp = usuarioRepository.findByIdentificacion(usuarioDTO.getIdentificacion());
			if (!usuarioTmp.isEmpty()) {
				if (usuarioDTO.getUsuaId() == null || !usuarioTmp.get(0).getUsuaId().equals(usuarioDTO.getUsuaId())) {
					throw new Exception("Ya se encuentra un usuario con la identificacion ingresado");
				}
			}
			
			usuario.setNombre(usuarioDTO.getNombre());
			usuario.setApellido(usuarioDTO.getApellido());
			usuario.setCodigo(usuarioDTO.getCodigo());
			usuario.setIdentificacion(usuarioDTO.getIdentificacion());
			usuario.setCorreo(usuarioDTO.getCorreo());
			usuario.setGenero(usuarioDTO.getGenero());
			usuario.setCelular(usuarioDTO.getCelular());
			usuario.setPrograma(programaService.findById(usuarioDTO.getProgId_Programa()).get());
			usuario.setTipoUsuario(tipoUsuarioService.findById(usuarioDTO.getTiusId_TipoUsuario()).get());
			usuario.setEstadoRegistro(usuarioDTO.getEstadoRegistro());

//			if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().trim().isEmpty()) {
//				// Actualizo la contraseña
//				String newPassword = PasswordGenerator.hashPassword(usuarioDTO.getPassword().trim()).get();
//				usuario.setPassword(newPassword);
//			}

			if (usuarioDTO.getUsuaId() == null) {
				// Cuando se cree el usuario se genera una clave aleatoria y se envía por email
				final String claveNueva = PasswordGenerator.getPassword(6);
				Optional<String> claveMd5 = PasswordGenerator.hashPassword(claveNueva);
				usuario.setPassword(claveMd5.get());
				usuario.setToken(null);

				usuario.setUsuCreador(usuarioDTO.getUsuCreador());
				usuario.setFechaCreacion(new Date());
				save(usuario);

				emailService.sendCrearUsuario(usuario.getCorreo(), usuario.getCodigo(), claveNueva);
			} else {
				usuario.setUsuModificador(usuarioDTO.getUsuCreador());
				usuario.setFechaModificacion(new Date());
				update(usuario);
			}

		} catch (Exception e) {
			log.error("Error en guardar", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String solicitarClave(String codigo) throws Exception {
		try {
			// Consulto el usuario
			List<Usuario> usuariosEncontrados = usuarioRepository.findByCodigo(codigo);
			if (usuariosEncontrados == null || usuariosEncontrados.isEmpty()) {
				throw new Exception("No existe un usuario con el código ingresado");
			}

			Usuario usuario = usuariosEncontrados.get(0);

			String token = Utilities.generarToken();

			// Genero un token temporal
			usuario.setToken(token);
			update(usuario);

			// Se envía el correo
			emailService.sendRecuperarContrasena(usuario.getCorreo(), token);
			return usuariosEncontrados.get(0).getCorreo();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void recuperarClave(String token) throws Exception {
		try {
			// Consulto si existe algún usuario con el token ingresado
			Usuario usuario = usuarioRepository.findByToken(token);
			if (usuario == null) {
				throw new Exception("El token ingresado ha expirado");
			}

			final String claveNueva = PasswordGenerator.getPassword(6);
			Optional<String> claveMd5 = PasswordGenerator.hashPassword(claveNueva);
			usuario.setPassword(claveMd5.get());
			usuario.setToken(null);
			update(usuario);

			emailService.sendNuevaContrasena(usuario.getCorreo(), claveNueva);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void cambiarClave(UsuarioDTO usuarioDTO) throws Exception {
		try {
			Usuario usuario = findById(usuarioDTO.getUsuaId()).get();

			// Valido que la contraseña ingresada sea la actual

			String claveActual = PasswordGenerator.hashPassword(usuarioDTO.getPassword().trim()).get();
			String claveNueva = PasswordGenerator.hashPassword(usuarioDTO.getPasswordNueva().trim()).get();

			if (!usuario.getPassword().equals(claveActual)) {
				throw new Exception("La clave ingresada no coincide con la actual");
			}

			usuario.setPassword(claveNueva);
			usuario.setFechaModificacion(new Date());
			update(usuario);

		} catch (Exception e) {
//			log.error("Error en guardar", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cargar(CargueMasivoDTO request) throws Exception {
		try {
			// Se valida el request
			if (request == null) {
				throw new Exception("El request se encuentra vacío");
			}
			if (request.getUsuarios() == null || request.getUsuarios().isEmpty()) {
				throw new Exception("La lista se encuentra vacía");
			}
			if (request.getUsuarioCreador() == null) {
				throw new Exception("El usuario se encuentra vacío");
			}
			// Obtengo todos los programas
			List<Programa> programas = programaService.findAll();
			Map<String, Programa> programasMap = programas.stream()
					.collect(Collectors.toMap(Programa::getDescripcion, programa -> programa));
			for (UsuarioDTO usuario : request.getUsuarios()) {
				try {
					if (!programasMap.containsKey(usuario.getNombrePrograma().trim().toUpperCase())) {
						throw new Exception(
								"El programa " + usuario.getNombrePrograma().trim() + " no existe en el sistema");
					}
					usuario.setProgId_Programa(
							programasMap.get(usuario.getNombrePrograma().trim().toUpperCase()).getProgId());
					usuario.setUsuCreador(request.getUsuarioCreador());
					usuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					guardar(usuario);
				} catch (Exception e) {
					log.error("Error al crear usuario masivo:" + e.getMessage(), e);
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void recrearClaves(Long usuarioCreador) throws Exception {
		try {
			List<Usuario> usuarios = findAll();
			
			for (Usuario usuario : usuarios) {
				try {
					final String claveNueva = PasswordGenerator.getPassword(6);
					Optional<String> claveMd5 = PasswordGenerator.hashPassword(claveNueva);
					usuario.setPassword(claveMd5.get());
					usuario.setToken(null);

					usuario.setUsuModificador(usuarioCreador);
					usuario.setFechaModificacion(new Date());
					save(usuario);

					emailService.sendCrearUsuario(usuario.getCorreo(), usuario.getCodigo(), claveNueva);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
