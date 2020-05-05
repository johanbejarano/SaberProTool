package com.vortexbird.sapiens.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.DatatypeConverter;

import com.vortexbird.sapiens.domain.EstadoPrueba;
import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.domain.PruebaModulo;
import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.domain.TipoPrueba;
import com.vortexbird.sapiens.domain.TipoUsuario;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.PruebaDTO;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.mapper.PruebaMapper;
import com.vortexbird.sapiens.repository.PruebaRepository;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.GlobalProperties;
import com.vortexbird.sapiens.utility.Utilities;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

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

	@Autowired
	private DetallePruebaUsuarioService detallePruebaUsuarioService;
	
	@Autowired
	private GlobalProperties globalProperties;
	
	@Autowired
    protected DataSource dataSource;

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

			// if (pruebaRepository.findById(entity.getPrueId()).isPresent()) {
			// throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			// }

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

			// Se consulta el usuario
			Optional<Usuario> usuario = usuarioService.findById(usuCreador.intValue());
			if (!usuario.isPresent() || !usuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				throw new Exception("No se encontró el usuario o no se encuentra activo");
			}

			// Se obtiene el tipo de usuario
			TipoUsuario tipoUsuario = usuario.get().getTipoUsuario();

			// Si es un profesor, solo se consultan las pruebas creadas por el
			if (tipoUsuario.getTiusId().equals(Constantes.TIPO_USUARIO_PROFESOR)) {

				List<Prueba> pruebasDeUsuarioCreador = pruebaRepository.findByUsuCreador(usuCreador);
				if (pruebasDeUsuarioCreador != null && !pruebasDeUsuarioCreador.isEmpty()) {
					pruebas.addAll(pruebaMapper.listPruebaToListPruebaDTO(pruebasDeUsuarioCreador));
				}

			} else if (tipoUsuario.getTiusId().equals(Constantes.TIPO_USUARIO_DIRECTOR)) {
				// Si es un director, se consultan las pruebas creadas por todos los usuarios
				// del programa que dirige
				Programa programa = usuario.get().getPrograma();

				// Se consultan todos los modulos que el programa tiene asignados
				List<ProgramaModulo> programasModulo = programa.getProgramaModulos();

				if (programasModulo == null || programasModulo.isEmpty()) {
					throw new Exception("El programa (" + programa.getProgId() + ") " + programa.getNombre()
							+ ", no tiene módulos asignados");
				}

				Map<Integer, Modulo> modulosDePrograma = new HashMap<>();
				for (ProgramaModulo programaModulo : programasModulo) {
					modulosDePrograma.put(programaModulo.getModulo().getModuId(), programaModulo.getModulo());
				}

				// Se obtienes las pruebas de cada modulo
				Collection<Modulo> modulos = modulosDePrograma.values();
				Map<Integer, Prueba> mapaPruebas = new HashMap<Integer, Prueba>();
				for (Modulo modulo : modulos) {
					List<PruebaModulo> pruebasModulo = modulo.getPruebaModulos();

					for (PruebaModulo pruebaModulo : pruebasModulo) {
						mapaPruebas.put(pruebaModulo.getPrueba().getPrueId(), pruebaModulo.getPrueba());
					}

				}

				Collection<Prueba> lasPruebas = mapaPruebas.values();
				for (Prueba prueba : lasPruebas) {
					pruebas.add(pruebaMapper.pruebaToPruebaDTO(prueba));
				}
			}

			// Se calculan los nombres de los usuarios creadores de cada prueba
			for (PruebaDTO pruebaDTO : pruebas) {
				pruebaDTO.setNombrePropietario(usuarioService.getNombreUsuario(pruebaDTO.getUsuCreador().intValue()));
			}

			// Se ordenan las pruebas por fecha de inicio
			pruebas.sort(new Comparator<PruebaDTO>() {
				@Override
				public int compare(PruebaDTO prueba1, PruebaDTO prueba2) {
					if (prueba1 == null || prueba2 == null) {
						return 0;
					}
					return prueba1.getFechaInicial().compareTo(prueba2.getFechaInicial());
				}
			});

			return pruebas;

		} catch (Exception e) {
			log.error("Error en getPruebasDeUsuarioCreador", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PruebaDTO getPrueba(Integer prueId) throws Exception {
		try {

			Optional<Prueba> prueba = findById(prueId);

			if (!prueba.isPresent()) {
				throw new Exception("No existe la prueba");
			}

			PruebaDTO pruebaDTO = pruebaMapper.pruebaToPruebaDTO(prueba.get());

			// Se consultan los módulos de la prueba
			pruebaDTO.setIdModulos(new ArrayList<Integer>());

			List<PruebaModulo> pruebasModulo = prueba.get().getPruebaModulos();
			for (PruebaModulo pruebaModulo : pruebasModulo) {
				Modulo modulo = pruebaModulo.getModulo();

				pruebaDTO.getIdModulos().add(modulo.getModuId());
			}

			// Se consultan los usuarios de la prueba
			pruebaDTO.setIdUsuarios(new ArrayList<Integer>());

			List<PruebaUsuario> pruebasUsuario = prueba.get().getPruebaUsuarios();
			for (PruebaUsuario pruebaUsuario : pruebasUsuario) {
				Usuario usuario = pruebaUsuario.getUsuario();

				pruebaDTO.getIdUsuarios().add(usuario.getUsuaId());
			}

			pruebaDTO.setNombrePropietario(usuarioService.getNombreUsuario(pruebaDTO.getUsuCreador().intValue()));

			return pruebaDTO;

		} catch (Exception e) {
			log.error("Error en getPrueba", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PruebaDTO guardarPrueba(PruebaDTO pruebaDTO) throws Exception {
		try {

			// Se validan los datos de la prueba
			if (pruebaDTO == null) {
				throw new Exception("La prueba es obligatoria");
			}

			if (pruebaDTO.getTiprId_TipoPrueba() == null) {
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

			if (pruebaDTO.getTiempo() == null || pruebaDTO.getTiempo().longValue() == 0L) {
				throw new Exception("La duarción es obligatoria");
			}

			if (pruebaDTO.getIdUsuarios() == null || pruebaDTO.getIdUsuarios().isEmpty()) {
				throw new Exception("Debe establecer la población de la prueba");
			}

			// Se consulta el tipo de prueba
			Optional<TipoPrueba> tipoPrueba = tipoPruebaService.findById(pruebaDTO.getTiprId_TipoPrueba());
			if (!tipoPrueba.isPresent()) {
				throw new Exception("No existe el tipo de prueba: " + pruebaDTO.getTiprId_TipoPrueba());
			}

			// Se guarda la prueba
			Prueba prueba = new Prueba();

			prueba.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
			prueba.setFechaCreacion(new Date());
			prueba.setFechaFinal(pruebaDTO.getFechaFinal());
			prueba.setFechaInicial(pruebaDTO.getFechaInicial());
			prueba.setTiempo(pruebaDTO.getTiempo());
			prueba.setTipoPrueba(tipoPrueba.get());
			prueba.setUsuCreador(pruebaDTO.getUsuCreador());

			save(prueba);

			// Se guardan los modulos de la prueba
			for (Integer moduId : pruebaDTO.getIdModulos()) {

				// Se consulta el modulo
				Optional<Modulo> modulo = moduloService.findById(moduId);
				if (!modulo.isPresent()) {
					throw new Exception("No existe el módulo: " + moduId);
				}

				// Se crea el modulo prueba
				PruebaModulo pruebaModulo = new PruebaModulo();

				pruebaModulo.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				pruebaModulo.setFechaCreacion(new Date());
				pruebaModulo.setModulo(modulo.get());
				pruebaModulo.setPrueba(prueba);
				pruebaModulo.setUsuCreador(pruebaDTO.getUsuCreador());

				pruebaModuloService.save(pruebaModulo);

			}

			// Se guarda la población
			for (Integer usuaId : pruebaDTO.getIdUsuarios()) {

				// Se consulta el usuario
				Optional<Usuario> usuario = usuarioService.findById(usuaId);
				if (!usuario.isPresent()) {
					throw new Exception("El usuario no existe: " + usuaId);
				}
				if (!usuario.get().getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
					throw new Exception("El usuario no se encuentra activo. Código: " + usuario.get().getCodigo() + " "
							+ usuario.get().getApellido() + ", " + usuario.get().getNombre());
				}

				// Se consulta el estado de prueba "iniciada"
				Optional<EstadoPrueba> estadoPrueba = estadoPruebaService
						.findById(Constantes.ESTADO_PRUEBA_SIN_INICIAR);

				if (!estadoPrueba.isPresent()) {
					throw new Exception("No existe el estado prueba: " + Constantes.ESTADO_PRUEBA_SIN_INICIAR);
				}

				// Se crea el registro de prueba usuario
				PruebaUsuario pruebaUsuario = new PruebaUsuario();

				pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
				pruebaUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				pruebaUsuario.setFechaCreacion(new Date());
				pruebaUsuario.setPrueba(prueba);
				pruebaUsuario.setUsuario(usuario.get());
				pruebaUsuario.setUsuCreador(pruebaDTO.getUsuCreador());
				pruebaUsuario.setTiempoDisponible(prueba.getTiempo());
				pruebaUsuarioService.save(pruebaUsuario);

			}

			pruebaDTO.setPrueId(prueba.getPrueId());

			return pruebaDTO;
		} catch (Exception e) {
			log.error("Error en getPruebasDeUsuarioCreador", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PruebaDTO modificarPrueba(PruebaDTO pruebaDTO) throws Exception {
		try {

			// Se validan los datos de la prueba
			if (pruebaDTO == null) {
				throw new Exception("La prueba es obligatoria");
			}

			if (pruebaDTO.getPrueId() == null) {
				throw new Exception("El id de la prueba a modificar, es obligatorio");
			}

			if (pruebaDTO.getTiprId_TipoPrueba() == null) {
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

			if (pruebaDTO.getTiempo() == null || pruebaDTO.getTiempo().longValue() == 0L) {
				throw new Exception("La duarción es obligatoria");
			}

			if (pruebaDTO.getIdUsuarios() == null || pruebaDTO.getIdUsuarios().isEmpty()) {
				throw new Exception("Debe establecer la población de la prueba");
			}

			// Se consulta el tipo de prueba
			Optional<TipoPrueba> tipoPrueba = tipoPruebaService.findById(pruebaDTO.getTiprId_TipoPrueba());
			if (!tipoPrueba.isPresent()) {
				throw new Exception("No existe el tipo de prueba: " + pruebaDTO.getTiprId_TipoPrueba());
			}

			// Se consulta la prueba
			Optional<Prueba> optPrueba = findById(pruebaDTO.getPrueId());

			if (!optPrueba.isPresent()) {
				throw new Exception("No se encontró la prueba " + pruebaDTO.getPrueId());
			}

			// Se actualiza la prueba
			Prueba prueba = optPrueba.get();

			prueba.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
			prueba.setFechaModificacion(new Date());
			prueba.setFechaFinal(pruebaDTO.getFechaFinal());
			prueba.setFechaInicial(pruebaDTO.getFechaInicial());
			prueba.setTiempo(pruebaDTO.getTiempo());
			prueba.setTipoPrueba(tipoPrueba.get());
			prueba.setUsuModificador(pruebaDTO.getUsuCreador());

			update(prueba);

			// Se consultan los módulos que actualmente tiene la prueba
			List<PruebaModulo> pruebasModulo = prueba.getPruebaModulos();
			List<PruebaUsuario> pruebasUsuario = prueba.getPruebaUsuarios();

			List<Modulo> modulosActuales = new ArrayList<Modulo>();
			List<Usuario> usuariosActuales = new ArrayList<Usuario>();

			Map<Integer, PruebaModulo> pruebasModuloMapa = new HashMap<Integer, PruebaModulo>();
			Map<Integer, PruebaUsuario> pruebasUsuarioMapa = new HashMap<Integer, PruebaUsuario>();

			pruebasModulo.forEach(pruebaModulo -> {
				modulosActuales.add(pruebaModulo.getModulo());
				pruebasModuloMapa.put(pruebaModulo.getModulo().getModuId(), pruebaModulo);
			});

			pruebasUsuario.forEach(pruebaUsuaurio -> {
				usuariosActuales.add(pruebaUsuaurio.getUsuario());
				pruebasUsuarioMapa.put(pruebaUsuaurio.getUsuario().getUsuaId(), pruebaUsuaurio);
			});

			// Módulos

			// Se calculan los módulos a asignar
			for (Integer moduId : pruebaDTO.getIdModulos()) {
				boolean encontrado = false;
				for (Modulo moduloActual : modulosActuales) {
					if (moduloActual.getModuId().equals(moduId)) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					// Se asigna el modulo
					Optional<Modulo> modulo = moduloService.findById(moduId);
					if (!modulo.isPresent()) {
						throw new Exception("No existe el módulo: " + moduId);
					}

					// Se crea el modulo prueba
					PruebaModulo pruebaModulo = new PruebaModulo();

					pruebaModulo.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					pruebaModulo.setFechaCreacion(new Date());
					pruebaModulo.setModulo(modulo.get());
					pruebaModulo.setPrueba(prueba);
					pruebaModulo.setUsuCreador(pruebaDTO.getUsuCreador());

					pruebaModuloService.save(pruebaModulo);
				}
			}

			// Se calculan los módulos a desasignar
			for (Modulo moduloActual : modulosActuales) {
				boolean encontrado = false;
				for (Integer moduId : pruebaDTO.getIdModulos()) {
					if (moduloActual.getModuId().equals(moduId)) {
						encontrado = true;
						break;
					}
				}

				// Si no encontró el modulo en la nueva asignación, se valida que no tenga
				// ejecución de pruebas
				if (!encontrado) {
					PruebaModulo pruebaModulo = pruebasModuloMapa.get(moduloActual.getModuId());

					Integer cantidadEjecuciones = detallePruebaUsuarioService
							.getCantidadDeEjecucionesPorModulo(moduloActual.getModuId());
					if (cantidadEjecuciones > 0) {
						throw new Exception("No se puede desasignar el módulo (" + moduloActual.getModuId() + ") \""
								+ moduloActual.getNombre() + "\". Tiene respuestas de estudiantes");
					}

					pruebaModuloService.delete(pruebaModulo);
				}
			}

			// Usuarios

			// Se calculan los usuarios a asignar
			for (Integer usuaId : pruebaDTO.getIdUsuarios()) {
				boolean encontrado = false;
				for (Usuario usuarioActual : usuariosActuales) {
					if (usuarioActual.getUsuaId().equals(usuaId)) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					// Se asigna el usuario
					Optional<Usuario> usuario = usuarioService.findById(usuaId);
					if (!usuario.isPresent()) {
						throw new Exception("No existe el usuario: " + usuaId);
					}

					// Se consulta el estado de prueba "iniciada"
					Optional<EstadoPrueba> estadoPrueba = estadoPruebaService
							.findById(Constantes.ESTADO_PRUEBA_SIN_INICIAR);

					if (!estadoPrueba.isPresent()) {
						throw new Exception("No existe el estado prueba: " + Constantes.ESTADO_PRUEBA_SIN_INICIAR);
					}

					// Se crea la prueba usuario
					PruebaUsuario pruebaUsuario = new PruebaUsuario();

					pruebaUsuario.setEstadoPrueba(estadoPrueba.get());
					pruebaUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					pruebaUsuario.setFechaCreacion(new Date());
					pruebaUsuario.setPrueba(prueba);
					pruebaUsuario.setUsuario(usuario.get());
					pruebaUsuario.setUsuCreador(pruebaDTO.getUsuCreador());
					pruebaUsuario.setTiempoDisponible(prueba.getTiempo());
					pruebaUsuarioService.save(pruebaUsuario);
				}
			}

			// Se calculan los usuarios a desasignar
			for (Usuario usuarioActual : usuariosActuales) {
				boolean encontrado = false;
				for (Integer usuaId : pruebaDTO.getIdUsuarios()) {
					if (usuarioActual.getUsuaId().equals(usuaId)) {
						encontrado = true;
						break;
					}
				}

				// Si no encontró el usuario en la nueva asignación, se valida que no tenga
				// ejecución de pruebas
				if (!encontrado) {
					PruebaUsuario pruebaUsuario = pruebasUsuarioMapa.get(usuarioActual.getUsuaId());

					Integer cantidadEjecuciones = detallePruebaUsuarioService
							.getCantidadDeEjecucionesPorUsuario(usuarioActual.getUsuaId());
					if (cantidadEjecuciones > 0) {

						String nombreUsuario = usuarioService.getNombreUsuario(usuarioActual.getUsuaId());

						throw new Exception("No se puede desasignar el usuario (Código: " + usuarioActual.getCodigo()
								+ ") \"" + nombreUsuario + "\". Tiene respuestas ejecutadas");
					}

					pruebaUsuarioService.delete(pruebaUsuario);
				}
			}

			pruebaDTO.setPrueId(prueba.getPrueId());

			return pruebaDTO;
		} catch (Exception e) {
			log.error("Error en getPruebasDeUsuarioCreador", e);
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public String consultarReporteResultados(Integer facuId, Integer progId, Integer usuaId, Integer tiusId, Integer prueId,
			Integer esprId, Integer pregId, Integer respId, Integer respOk, Integer moduId) throws Exception {
		
		
		InputStream inputStream = null;
		ByteArrayOutputStream bos = null;
		Connection connection = null;
		
		try {
			
			//Debe venir por lo menos 1 parametro
			if (facuId == null && progId == null && usuaId == null && tiusId == null && prueId == null && esprId == null && 
					pregId == null && respId == null && respOk == null && moduId == null) {
				throw new Exception("Debe ingresar por lo menos un parámetro para generar el reporte de resultados");
			}
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			String rutaBaseReportes = globalProperties.getSUBREPORT_DIR();
			
			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes, "/reporteResultados.jasper");
			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			
			// Crea la conexión a la base de datos
			connection = dataSource.getConnection();
			
			// Se abre el reporte
			inputStream = new FileInputStream(fReporte);

			// Crea la variable de parametros
			Map<String, Object> params = new HashMap<String, Object>();

			// Asigna los parametros enviados
			params.put("pSubreportDir", (fRutaBaseReportes.getPath().endsWith("/") ? fRutaBaseReportes.getPath()
					: (fRutaBaseReportes.getPath() + "/")));
			
			params.put("pFacuId", facuId);
			params.put("pProgId", progId);
			params.put("pUsuaId", usuaId);
			params.put("pTiusId", tiusId);
			params.put("pPrueId", prueId);
			params.put("pEsprId", esprId);
			params.put("pPregId", pregId);
			params.put("pRespId", respId);
			params.put("pRespOk", respOk);
			params.put("pModuId", moduId);
			
			bos = new ByteArrayOutputStream();
			// Se rellena el reporte
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, connection);
			
			// Se exporta el reporte a pdf
			JRPdfExporter jrPdfExporter = new JRPdfExporter();

			jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
			jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			jrPdfExporter.setConfiguration(configuration);

			jrPdfExporter.exportReport();

			byte[] bytes = bos.toByteArray();

			String base64 = DatatypeConverter.printBase64Binary(bytes);
			return base64;
			
		} catch (Exception e) {
			log.error("Error consultando el reporte de resultados", e);
			throw e;
		}finally {
			if (inputStream != null)
				inputStream.close();
			if (bos != null)
				bos.close();
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public String consultarReporteResumenEstudiantes(Integer facuId, Integer progId, Integer usuaId, Integer tiusId, Integer prueId,
			Integer esprId, Integer pregId, Integer respId, Integer respOk, Integer moduId) throws Exception {
		
		
		InputStream inputStream = null;
		ByteArrayOutputStream bos = null;
		Connection connection = null;
		
		try {
			
			//Debe venir por lo menos 1 parametro
			if (facuId == null && progId == null && usuaId == null && tiusId == null && prueId == null && esprId == null && 
					pregId == null && respId == null && respOk == null && moduId == null) {
				throw new Exception("Debe ingresar por lo menos un parámetro para generar el reporte de resultados");
			}
			
			// SE CONSULTA LA RUTA BASE DE REPORTES
			String rutaBaseReportes = globalProperties.getSUBREPORT_DIR();
			
			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(rutaBaseReportes);
			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes, "/resumenEstudiantes.jasper");
			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}
			
			// Crea la conexión a la base de datos
			connection = dataSource.getConnection();
			
			// Se abre el reporte
			inputStream = new FileInputStream(fReporte);

			// Crea la variable de parametros
			Map<String, Object> params = new HashMap<String, Object>();

			// Asigna los parametros enviados
			params.put("pSubreportDir", (fRutaBaseReportes.getPath().endsWith("/") ? fRutaBaseReportes.getPath()
					: (fRutaBaseReportes.getPath() + "/")));
			
			params.put("pFacuId", facuId);
			params.put("pProgId", progId);
			params.put("pUsuaId", usuaId);
			params.put("pTiusId", tiusId);
			params.put("pPrueId", prueId);
			params.put("pEsprId", esprId);
			params.put("pPregId", pregId);
			params.put("pRespId", respId);
			params.put("pRespOk", respOk);
			params.put("pModuId", moduId);
			
			bos = new ByteArrayOutputStream();
			// Se rellena el reporte
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, connection);
			
			// Se exporta el reporte a pdf
			JRPdfExporter jrPdfExporter = new JRPdfExporter();

			jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
			jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			jrPdfExporter.setConfiguration(configuration);

			jrPdfExporter.exportReport();

			byte[] bytes = bos.toByteArray();

			String base64 = DatatypeConverter.printBase64Binary(bytes);
			return base64;
			
		} catch (Exception e) {
			log.error("Error consultando el reporte de resultados", e);
			throw e;
		}finally {
			if (inputStream != null)
				inputStream.close();
			if (bos != null)
				bos.close();
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
}
