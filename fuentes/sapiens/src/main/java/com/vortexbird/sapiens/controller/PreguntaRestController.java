package com.vortexbird.sapiens.controller;

import java.util.List;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.domain.Respuesta;
import com.vortexbird.sapiens.dto.GuardarPreguntaDTO;
import com.vortexbird.sapiens.dto.PreguntaDTO;
import com.vortexbird.sapiens.dto.RespuestaDTO;
import com.vortexbird.sapiens.mapper.PreguntaMapper;
import com.vortexbird.sapiens.mapper.RespuestaMapper;
import com.vortexbird.sapiens.service.PreguntaService;
import com.vortexbird.sapiens.service.RespuestaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/pregunta")
@CrossOrigin(origins = "*")
public class PreguntaRestController {

	private static final Logger log = LoggerFactory.getLogger(PreguntaRestController.class);

	@Autowired
	private PreguntaService preguntaService;
	
	@Autowired
	private RespuestaService respuestaService;

	@Autowired
	private PreguntaMapper preguntaMapper;
	
	@Autowired
	private RespuestaMapper respuestaMapper;

	@GetMapping(value = "/findById/{pregId}")
	public ResponseEntity<?> findById(@PathVariable("pregId") Integer pregId) {
		log.debug("Request to findById() Pregunta");

		try {
			//Se consulta la pregunta
			Pregunta pregunta = preguntaService.findById(pregId).get();
			
			PreguntaDTO preguntaDTO = preguntaMapper.preguntaToPreguntaDTO(pregunta);
			
			//Se consultan las respuestas
			List<Respuesta> respuestas = respuestaService.findRespuestasDePregunta(pregId);
			List<RespuestaDTO> respuestasDTO = respuestaMapper.listRespuestaToListRespuestaDTO(respuestas);
			
			preguntaDTO.setRespuestasDTO(respuestasDTO);

			return ResponseEntity.ok().body(preguntaDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		log.debug("Request to findAll() Pregunta");

		try {
			return ResponseEntity.ok().body(preguntaMapper.listPreguntaToListPreguntaDTO(preguntaService.findAll()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody PreguntaDTO preguntaDTO) {
		log.debug("Request to save Pregunta: {}", preguntaDTO);

		try {
			Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);
			pregunta = preguntaService.save(pregunta);

			return ResponseEntity.ok().body(preguntaMapper.preguntaToPreguntaDTO(pregunta));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody PreguntaDTO preguntaDTO) {
		log.debug("Request to update Pregunta: {}", preguntaDTO);

		try {
			Pregunta pregunta = preguntaMapper.preguntaDTOToPregunta(preguntaDTO);
			pregunta = preguntaService.update(pregunta);

			return ResponseEntity.ok().body(preguntaMapper.preguntaToPreguntaDTO(pregunta));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{pregId}")
	public ResponseEntity<?> delete(@PathVariable("pregId") Integer pregId) throws Exception {
		log.debug("Request to delete Pregunta");

		try {
			Pregunta pregunta = preguntaService.findById(pregId).get();

			preguntaService.delete(pregunta);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(preguntaService.count());
	}
	
	@GetMapping(value = "/getPregunta/{pregId}")
	public ResponseEntity<?> getPregunta(@PathVariable("pregId") Integer pregId) {
		log.debug("Request to findById() Pregunta");

		try {
			//Se consulta la pregunta
			PreguntaDTO preguntaDTO = preguntaService.getPregunta(pregId);
			
			//Se consultan las respuestas
			List<Respuesta> respuestas = respuestaService.findRespuestasDePregunta(pregId);
			List<RespuestaDTO> respuestasDTO = respuestaMapper.listRespuestaToListRespuestaDTO(respuestas);
			
			preguntaDTO.setRespuestasDTO(respuestasDTO);

			return ResponseEntity.ok().body(preguntaDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/guardarPregunta")
	public ResponseEntity<?> guardarPregunta(@RequestBody GuardarPreguntaDTO guardarPreguntaDTO) {
		log.debug("Request to guardarPregunta: ", guardarPreguntaDTO);

		try {
			
			Pregunta pregunta = preguntaService.guardarPregunta(guardarPreguntaDTO);

			PreguntaDTO preguntaDTO = preguntaMapper.preguntaToPreguntaDTO(pregunta);
			
			//Se consultan las respuestas
			List<Respuesta> respuestas = respuestaService.findRespuestasDePregunta(pregunta.getPregId());
			List<RespuestaDTO> respuestasDTO = respuestaMapper.listRespuestaToListRespuestaDTO(respuestas);
			
			preguntaDTO.setRespuestasDTO(respuestasDTO);

			return ResponseEntity.ok().body(preguntaDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/actualizarPregunta")
	public ResponseEntity<?> actualizarPregunta(@RequestBody GuardarPreguntaDTO guardarPreguntaDTO) {
		log.debug("Request to actualizarPregunta: ", guardarPreguntaDTO);

		try {
			
			Pregunta pregunta = preguntaService.actualizarPregunta(guardarPreguntaDTO);
			
			PreguntaDTO preguntaDTO = preguntaMapper.preguntaToPreguntaDTO(pregunta);
			
			//Se consultan las respuestas
			List<Respuesta> respuestas = respuestaService.findRespuestasDePregunta(pregunta.getPregId());
			List<RespuestaDTO> respuestasDTO = respuestaMapper.listRespuestaToListRespuestaDTO(respuestas);
			
			preguntaDTO.setRespuestasDTO(respuestasDTO);

			return ResponseEntity.ok().body(preguntaDTO);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/getPreguntasPorUsuario/{usuaId}")
	public ResponseEntity<?> getPreguntasPorUsuario(@PathVariable("usuaId") Integer usuaId) {
		log.debug("Request to getPreguntasPorUsuario()");

		try {
			return ResponseEntity.ok().body(preguntaService.getPreguntasPorUsuario(usuaId));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
