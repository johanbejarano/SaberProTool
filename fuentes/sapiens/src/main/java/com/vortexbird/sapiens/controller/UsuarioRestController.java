package com.vortexbird.sapiens.controller;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.CargueMasivoDTO;
import com.vortexbird.sapiens.dto.UsuarioDTO;
import com.vortexbird.sapiens.mapper.UsuarioMapper;
import com.vortexbird.sapiens.service.UsuarioService;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.PasswordGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioRestController {
	private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
	
	private static GsonFactory gsonFactory;
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioMapper usuarioMapper;

	@GetMapping(value = "/findById/{usuaId}")
	public ResponseEntity<?> findById(@PathVariable("usuaId") Integer usuaId) {
		log.debug("Request to findById() Usuario");

		try {
			Usuario usuario = usuarioService.findById(usuaId).get();

			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		log.debug("Request to findAll() Usuario");

		try {
			return ResponseEntity.ok().body(usuarioMapper.listUsuarioToListUsuarioDTO(usuarioService.findAll()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDTO) {
		log.debug("Request to save Usuario: {}", usuarioDTO);

		try {
			Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuario = usuarioService.save(usuario);

			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO) {
		log.debug("Request to update Usuario: {}", usuarioDTO);

		try {
			Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
			usuario = usuarioService.update(usuario);

			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{usuaId}")
	public ResponseEntity<?> delete(@PathVariable("usuaId") Integer usuaId) throws Exception {
		log.debug("Request to delete Usuario");

		try {
			Usuario usuario = usuarioService.findById(usuaId).get();

			usuarioService.delete(usuario);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(usuarioService.count());
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
		log.debug("Request to login", usuarioDTO);

		try {

			usuarioDTO = usuarioService.login(usuarioDTO.getCodigo(), usuarioDTO.getPassword());

			if (usuarioDTO != null) {

				return ResponseEntity.ok().body(usuarioDTO);
			} else {
				throw new Exception("No se encontró el usuario");
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/md5/{string}")
	public ResponseEntity<?> findById(@PathVariable("string") String string) {
		return ResponseEntity.ok().body(PasswordGenerator.hashPassword(string));
	}

	@GetMapping(value = "/getUsuariosPorTipo/{tiusId}/{semestre}/{progId}/{facuId}/{grupId}/{filtro}/{pageNumber}/{pageSize}")
	public ResponseEntity<?> getUsuariosPorTipo(@PathVariable("tiusId") Integer tiusId,
			@PathVariable("semestre") Integer semestre,
			@PathVariable("progId") Integer progId,
			@PathVariable("facuId") Integer facuId,
			@PathVariable("grupId") Integer grupId,
			@PathVariable("filtro") String filtro, @PathVariable("pageNumber") int pageNumber,
			@PathVariable("pageSize") int pageSize) {

		log.debug("Request to getUsuariosPorTipo()");

		try {

			Page<UsuarioDTO> usuarios = usuarioService.getUsuariosPorTipo(tiusId, semestre, progId, facuId, grupId, filtro, pageNumber, pageSize);

			return ResponseEntity.ok().body(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/getAllUsuariosPorTipo/{tiusId}/{semestre}/{progId}/{facuId}/{grupId}/{filtro}")
	public ResponseEntity<?> getAllUsuariosPorTipo(@PathVariable("tiusId") Integer tiusId,
			@PathVariable("semestre") Integer semestre,
			@PathVariable("progId") Integer progId,
			@PathVariable("facuId") Integer facuId,
			@PathVariable("grupId") Integer grupId,
			@PathVariable("filtro") String filtro) {

		log.debug("Request to getAllUsuariosPorTipo()");

		try {

			List<Integer> usuarios = usuarioService.getAllUsuariosPorTipo(tiusId, semestre, progId, facuId, grupId, filtro);

			return ResponseEntity.ok().body(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			usuarioService.guardar(usuarioDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/solicitarClave/{codigo}")
	public ResponseEntity<?> solicitarClave(@PathVariable("codigo") String codigo) {
		try {
			String correo = usuarioService.solicitarClave(codigo);
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setCorreo(correo);
			return ResponseEntity.ok().body(usuario);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/recuperarClave/{token}")
	public ResponseEntity<?> recuperarClave(@PathVariable("token") String token) {
		try {
			usuarioService.recuperarClave(token);
			return ResponseEntity.ok().body("<script>window.close();</script>");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/cambiarClave")
	public ResponseEntity<?> cambiarClave(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			usuarioService.cambiarClave(usuarioDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/cargar")
	public ResponseEntity<?> cargar(@RequestBody CargueMasivoDTO request) {
		try {
			
			return ResponseEntity.ok().body(
					usuarioService.cargar(request));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/recrearClaves")
	public ResponseEntity<?> recrearClaves(@RequestBody CargueMasivoDTO request) {
		try {
			usuarioService.recrearClaves(request.getUsuarioCreador());
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/getUsuariosPorGrupo")
	public ResponseEntity<?> getUsuariosPorGrupo(@RequestBody Integer request) {

		log.debug("Request to getUsuariosPorGrupo()");

		try {

			List<UsuarioDTO> usuarios = usuarioService.getUsuariosPorGrupo(request);

			return ResponseEntity.ok().body(usuarios);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	@PostMapping(value = "/getCorreoUsuarioPorCodigo")
//	public ResponseEntity<?> getCorreoUsuarioPorCodigo(@RequestBody String codigo) {
//		log.debug("Request to getCorreoUsuarioPorCodigo() Usuario");
//		try {
//			
//			return ResponseEntity.ok().body(usuarioService.getCorreoUsuarioPorCodigo(codigo));
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	
	@PostMapping(value = "/getCorreoUsuarioPorCodigo")
	public ResponseEntity<?> getCorreoUsuarioPorCodigo(@RequestBody String codigo) {
		log.debug("Request to getCorreoUsuarioPorCodigo() Usuario");
		try {
			gsonFactory = gsonFactory.getDefaultInstance();
			
			GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
			GoogleCloudDialogflowV2WebhookRequest request = gsonFactory.createJsonParser(codigo).parse(GoogleCloudDialogflowV2WebhookRequest.class);
			
			Map<String, Object> params = request.getQueryResult().getParameters();
			
			String CodigoBusqueda = (String) params.get("codigo");
			
			String correo = usuarioService.getCorreoUsuarioPorCodigo(CodigoBusqueda);
			
			response.setFulfillmentText(correo);
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}