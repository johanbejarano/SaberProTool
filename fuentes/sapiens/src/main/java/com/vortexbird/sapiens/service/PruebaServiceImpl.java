package com.vortexbird.sapiens.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vortexbird.sapiens.exception.*;
import com.vortexbird.sapiens.mapper.PruebaMapper;
import com.vortexbird.sapiens.repository.*;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.Utilities;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.PruebaDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
public class PruebaServiceImpl implements PruebaService {

	private static final Logger log = LoggerFactory.getLogger(PruebaServiceImpl.class);

	@Autowired
	private PruebaRepository pruebaRepository;

	@Autowired
	private Validator validator;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PruebaMapper pruebaMapper;
	
	@Autowired
	private TipoPruebaService tipoPruebaService;
	
	@Autowired
	private PruebaModuloService pruebaModuloService;
	
	@Autowired
	private ModuloService moduloService;
	
	@Autowired
	private PruebaUsuarioService pruebaUsuarioService;

	@Autowired
	private EstadoPruebaService estadoPruebaService;
	
	@Override
	public void validate(Prueba prueba) throws Exception {
		try {
			Set<ConstraintViolation<Prueba>> constraintViolations = validator.validate(prueba);
			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();
				for (ConstraintViolation<Prueba> constraintViolation : constraintViolations) {
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
		return pruebaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> findAll() {
		log.debug("finding all Prueba instances");
		return pruebaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Prueba save(Prueba entity) throws Exception {
		log.debug("saving Prueba instance");
		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Prueba");
			}

			validate(entity);

//			if (pruebaRepository.findById(entity.getPrueId()).isPresent()) {
//				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//			}

			return pruebaRepository.save(entity);

		} catch (Exception e) {
			log.error("save Prueba failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Prueba entity) throws Exception {
		log.debug("deleting Prueba instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Prueba");
		}

		if (entity.getPrueId() == null) {
			throw new ZMessManager().new EmptyFieldException("prueId");
		}

		findById(entity.getPrueId()).ifPresent(entidad -> {
			List<PruebaModulo> pruebaModulos = entidad.getPruebaModulos();

			if (Utilities.validationsList(pruebaModulos) == true) {
				throw new ZMessManager().new DeletingException("pruebaModulos");
			}
			List<PruebaUsuario> pruebaUsuarios = entidad.getPruebaUsuarios();

			if (Utilities.validationsList(pruebaUsuarios) == true) {
				throw new ZMessManager().new DeletingException("pruebaUsuarios");
			}
		});

		try {

			pruebaRepository.deleteById(entity.getPrueId());
			log.debug("delete Prueba successful");

		} catch (Exception e) {
			log.error("delete Prueba failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Prueba instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("prueId");
		}
		if (pruebaRepository.findById(id).isPresent()) {
			delete(pruebaRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Prueba update(Prueba entity) throws Exception {

		log.debug("updating Prueba instance");

		try {

			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Prueba");
			}

			validate(entity);

			return pruebaRepository.save(entity);

		} catch (Exception e) {
			log.error("update Prueba failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Prueba> findById(Integer prueId) throws Exception {
		log.debug("getting Prueba instance");
		return pruebaRepository.findById(prueId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PruebaDTO> getPruebasDeUsuarioCreador(Long usuCreador) throws Exception {
		try {
			
			List<PruebaDTO> pruebas = new ArrayList<PruebaDTO>();
			
			//Se consulta el usuario
			Optional<Usuario> usuario = usuarioService.findById(usuCreador.intValue());
			if(!usuario.isPresent() || !usuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				throw new Exception("No se encontró el usuario o no se encuentra activo");
			}
			
			//Se obtiene el tipo de usuario
			TipoUsuario tipoUsuario = usuario.get().getTipoUsuario();
			
			//Si es un profesor, solo se consultan las pruebas creadas por el
			if (tipoUsuario.getTiusId().equals(Constantes.TIPO_USUARIO_PROFESOR)) {
				
				List<Prueba> pruebasDeUsuarioCreador = pruebaRepository.findByUsuCreador(usuCreador);
				if (pruebasDeUsuarioCreador!=null && !pruebasDeUsuarioCreador.isEmpty()) {
					pruebas.addAll(pruebaMapper.listPruebaToListPruebaDTO(pruebasDeUsuarioCreador));
				}
				
			}else if (tipoUsuario.getTiusId().equals(Constantes.TIPO_USUARIO_DIRECTOR)) {
				//Si es un director, se consultan las pruebas creadas por todos los usuarios del programa que dirige
				Programa programa = usuario.get().getPrograma();
				
				//Se consultan todos los modulos que el programa tiene asignados
				List<ProgramaModulo> programasModulo = programa.getProgramaModulos();
				
				if (programasModulo==null || programasModulo.isEmpty()) {
					throw new Exception("El programa (" + programa.getProgId() + ") " + programa.getNombre() + ", no tiene módulos asignados");
				}
				
				Map<Integer, Modulo> modulosDePrograma = new HashMap<>();
				for (ProgramaModulo programaModulo : programasModulo) {
					modulosDePrograma.put(programaModulo.getModulo().getModuId(), programaModulo.getModulo());
				}
				
				//Se obtienes las pruebas de cada modulo
				Collection<Modulo> modulos = modulosDePrograma.values();
				Map<Integer, Prueba> mapaPruebas = new HashMap<Integer, Prueba>();
				for (Modulo modulo : modulos) {
					List<PruebaModulo> pruebasModulo = modulo.getPruebaModulos();
					
					for(PruebaModulo pruebaModulo : pruebasModulo) {
						mapaPruebas.put(pruebaModulo.getPrueba().getPrueId(), pruebaModulo.getPrueba());
					}
					
				}
				
				Collection<Prueba> lasPruebas = mapaPruebas.values();
				for (Prueba prueba : lasPruebas) {
					pruebas.add(pruebaMapper.pruebaToPruebaDTO(prueba));
				}
			}
			
			return pruebas;
			
		} catch (Exception e) {
			log.error("Error en getPruebasDeUsuarioCreador", e);
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public PruebaDTO guardarPrueba(PruebaDTO pruebaDTO) throws Exception {
		try {
			
			//Se validan los datos de la prueba
			if (pruebaDTO==null) {
				throw new Exception("La prueba es obligatoria"); 
			}
			
			if (pruebaDTO.getTiprId_TipoPrueba()==null) {
				throw new Exception("El tipo prueba es obligatorio");
			}
			
			if (pruebaDTO.getFechaInicial() == null) {
				throw new Exception("La fecha inicial es obligatoria");
			}
			
			if (pruebaDTO.getFechaFinal() == null) {
				throw new Exception("La fecha final es obligatoria");
			}
			
			if (pruebaDTO.getFechaFinal().before(pruebaDTO.getFechaInicial())) {
				throw new Exception("La fecha final debe ser posterior a la fecha inicial");
			}
			
			if (pruebaDTO.getTiempo()==null || pruebaDTO.getTiempo().longValue()==0L) {
				throw new Exception("La duarción es obligatoria");
			}
			
			if (pruebaDTO.getIdUsuarios()==null || pruebaDTO.getIdUsuarios().isEmpty()) {
				throw new Exception("Debe establecer la población de la prueba");
			}
			
			//Se consulta el tipo de prueba
			Optional<TipoPrueba> tipoPrueba = tipoPruebaService.findById(pruebaDTO.getTiprId_TipoPrueba());
			if (!tipoPrueba.isPresent()) {
				throw new Exception("No existe el tipo de prueba: " + pruebaDTO.getTiprId_TipoPrueba());
			}
			
			//Se guarda la prueba
			Prueba prueba = new Prueba();
			
			prueba.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
			prueba.setFechaCreacion(new Date());
			prueba.setFechaFinal(pruebaDTO.getFechaFinal());
			prueba.setFechaInicial(pruebaDTO.getFechaInicial());
			prueba.setTiempo(pruebaDTO.getTiempo());
			prueba.setTipoPrueba(tipoPrueba.get());
			prueba.setUsuCreador(pruebaDTO.getUsuCreador());
			
			save(prueba);
			
			//Se guardan los modulos de la prueba
			for(Integer moduId : pruebaDTO.getIdModulos()) {
				
				//Se consulta el modulo
				Optional<Modulo> modulo = moduloService.findById(moduId);
				if(!modulo.isPresent()) {
					throw new Exception("No existe el módulo: " + moduId);
				}
				
				//Se crea el modulo prueba
				PruebaModulo pruebaModulo = new PruebaModulo();
				
				pruebaModulo.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				pruebaModulo.setFechaCreacion(new Date());
				pruebaModulo.setModulo(modulo.get());
				pruebaModulo.setPrueba(prueba);
				pruebaModulo.setUsuCreador(pruebaDTO.getUsuCreador());
				
				pruebaModuloService.save(pruebaModulo);
				
			}
			
			//Se guarda la población
			for(Integer usuaId : pruebaDTO.getIdUsuarios()) {
				
				//Se consulta el usuario
				Optional<Usuario> usuario = usuarioService.findById(usuaId);
				if (!usuario.isPresent()) {
					throw new Exception("El usuario no existe: " + usuaId);
				}
				if (!usuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
					throw new Exception("El usuario no se encuentra activo. Código: " + usuario.get().getCodigo() + " "
							+ usuario.get().getApellido() + ", " + usuario.get().getNombre());
				}

				//Se consulta el estado de prueba "iniciada"
				Optional<EstadoPrueba> estadoPrueba = estadoPruebaService.findById(Constantes.ESTADO_PRUEBA_SIN_INICIAR);
				
				if(!estadoPrueba.isPresent()) {
					throw new Exception("No existe el estado prueba: " + Constantes.ESTADO_PRUEBA_SIN_INICIAR);
				}
				
				//Se crea el registro de prueba usuario
				PruebaUsuario pruebaUsuario = new PruebaUsuario();
				
				pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
				pruebaUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				pruebaUsuario.setFechaCreacion(new Date());
				pruebaUsuario.setPrueba(prueba);
				pruebaUsuario.setUsuario(usuario.get());
				pruebaUsuario.setUsuCreador(pruebaDTO.getUsuCreador());

				pruebaUsuarioService.save(pruebaUsuario);
				
			}
			
			pruebaDTO.setPrueId(prueba.getPrueId());
			
			return pruebaDTO;
		} catch (Exception e) {
			log.error("Error en getPruebasDeUsuarioCreador", e);
			throw e;
		}
	}

}
