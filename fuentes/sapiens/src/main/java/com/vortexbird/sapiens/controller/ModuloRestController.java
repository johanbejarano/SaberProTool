package com.vortexbird.sapiens.controller;

import java.util.List;

import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.dto.ModuloDTO;
import com.vortexbird.sapiens.mapper.ModuloMapper;
import com.vortexbird.sapiens.service.ModuloService;

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
@RequestMapping("/api/modulo")
@CrossOrigin(origins = "*")
public class ModuloRestController {
    private static final Logger log = LoggerFactory.getLogger(ModuloRestController.class);
    @Autowired
    private ModuloService moduloService;
    @Autowired
    private ModuloMapper moduloMapper;

    @GetMapping(value = "/findById/{moduId}")
    public ResponseEntity<?> findById(@PathVariable("moduId")
    Integer moduId) {
        log.debug("Request to findById() Modulo");

        try {
            Modulo modulo = moduloService.findById(moduId).get();

            return ResponseEntity.ok()
                                 .body(moduloMapper.moduloToModuloDTO(modulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Modulo");

        try {
            return ResponseEntity.ok()
                                 .body(moduloMapper.listModuloToListModuloDTO(
                    moduloService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    ModuloDTO moduloDTO) {
        log.debug("Request to save Modulo: {}", moduloDTO);

        try {
            Modulo modulo = moduloMapper.moduloDTOToModulo(moduloDTO);
            modulo = moduloService.save(modulo);

            return ResponseEntity.ok()
                                 .body(moduloMapper.moduloToModuloDTO(modulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    ModuloDTO moduloDTO) {
        log.debug("Request to update Modulo: {}", moduloDTO);

        try {
            Modulo modulo = moduloMapper.moduloDTOToModulo(moduloDTO);
            modulo = moduloService.update(modulo);

            return ResponseEntity.ok()
                                 .body(moduloMapper.moduloToModuloDTO(modulo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{moduId}")
    public ResponseEntity<?> delete(@PathVariable("moduId")
    Integer moduId) throws Exception {
        log.debug("Request to delete Modulo");

        try {
            Modulo modulo = moduloService.findById(moduId).get();

            moduloService.delete(modulo);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(moduloService.count());
    }
    
    @GetMapping(value = "/findByTipoModulo/{timoId}")
    public ResponseEntity<?> findByTipoModulo(@PathVariable("timoId")
    Integer timoId) {
        log.debug("Request to findByTipoModulo()");

        try {
            List<Modulo> modulos = moduloService.findByTipoModulo(timoId);

            return ResponseEntity.ok()
                                 .body(moduloMapper.listModuloToListModuloDTO(modulos));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/guardar")
	public ResponseEntity<?> guardar(@RequestBody ModuloDTO moduloDTO) {
		try {
			moduloService.guardar(moduloDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
