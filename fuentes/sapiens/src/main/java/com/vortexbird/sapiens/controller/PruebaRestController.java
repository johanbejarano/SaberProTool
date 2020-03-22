package com.vortexbird.sapiens.controller;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.PruebaDTO;
import com.vortexbird.sapiens.mapper.PruebaMapper;
import com.vortexbird.sapiens.service.PruebaService;

import java.util.List;

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
@RequestMapping("/api/prueba")
@CrossOrigin(origins = "*")
public class PruebaRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaRestController.class);
    @Autowired
    private PruebaService pruebaService;
    @Autowired
    private PruebaMapper pruebaMapper;

    @GetMapping(value = "/findById/{prueId}")
    public ResponseEntity<?> findById(@PathVariable("prueId")
    Integer prueId) {
        log.debug("Request to findById() Prueba");

        try {
            Prueba prueba = pruebaService.findById(prueId).get();

            return ResponseEntity.ok()
                                 .body(pruebaMapper.pruebaToPruebaDTO(prueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Prueba");

        try {
            return ResponseEntity.ok()
                                 .body(pruebaMapper.listPruebaToListPruebaDTO(
                    pruebaService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    PruebaDTO pruebaDTO) {
        log.debug("Request to save Prueba: {}", pruebaDTO);

        try {
            Prueba prueba = pruebaMapper.pruebaDTOToPrueba(pruebaDTO);
            prueba = pruebaService.save(prueba);

            return ResponseEntity.ok()
                                 .body(pruebaMapper.pruebaToPruebaDTO(prueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    PruebaDTO pruebaDTO) {
        log.debug("Request to update Prueba: {}", pruebaDTO);

        try {
            Prueba prueba = pruebaMapper.pruebaDTOToPrueba(pruebaDTO);
            prueba = pruebaService.update(prueba);

            return ResponseEntity.ok()
                                 .body(pruebaMapper.pruebaToPruebaDTO(prueba));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prueId}")
    public ResponseEntity<?> delete(@PathVariable("prueId")
    Integer prueId) throws Exception {
        log.debug("Request to delete Prueba");

        try {
            Prueba prueba = pruebaService.findById(prueId).get();

            pruebaService.delete(prueba);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(pruebaService.count());
    }
    
    @GetMapping(value = "/getPruebasDeUsuarioCreador/{usuCreador}")
    public ResponseEntity<?> getPruebasDeUsuarioCreador(@PathVariable("usuCreador")
    	Long usuCreador) {
    	
    	try {
			
    		List<PruebaDTO> pruebas = pruebaService.getPruebasDeUsuarioCreador(usuCreador);
        	return ResponseEntity.ok()
                    .body(pruebas);
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
		}
    	
    }
    
    @PostMapping(value = "/guardarPrueba")
    public ResponseEntity<?> guardarPrueba(@RequestBody
    		PruebaDTO pruebaDTO) {
    	
        log.debug("Request to guardarPrueba:", pruebaDTO);

        try {
            
        	pruebaDTO = pruebaService.guardarPrueba(pruebaDTO);
        	
            return ResponseEntity.ok()
                                 .body(pruebaDTO);
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping(value = "/modificarPrueba")
    public ResponseEntity<?> modificarPrueba(@RequestBody
    PruebaDTO pruebaDTO) {
        log.debug("Request to modificarPrueba", pruebaDTO);

        try {
            pruebaDTO = pruebaService.modificarPrueba(pruebaDTO);

            return ResponseEntity.ok()
                                 .body(pruebaDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping(value = "/getPrueba/{prueId}")
    public ResponseEntity<?> getPrueba(@PathVariable("prueId")
    Integer prueId) {
        log.debug("Request to getPrueba()");

        try {
            PruebaDTO pruebaDTO = pruebaService.getPrueba(prueId);

            return ResponseEntity.ok()
                                 .body(pruebaDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
