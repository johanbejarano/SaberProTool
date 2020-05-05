package com.vortexbird.sapiens.controller;

import java.util.List;

import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;
import com.vortexbird.sapiens.mapper.PruebaUsuarioMapper;
import com.vortexbird.sapiens.service.PruebaUsuarioService;

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
@RequestMapping("/api/pruebaUsuario")
@CrossOrigin(origins = "*")
public class PruebaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(PruebaUsuarioRestController.class);
    @Autowired
    private PruebaUsuarioService pruebaUsuarioService;
    @Autowired
    private PruebaUsuarioMapper pruebaUsuarioMapper;

    @GetMapping(value = "/findById/{prusId}")
    public ResponseEntity<?> findById(@PathVariable("prusId") Integer prusId) {
        log.debug("Request to findById() PruebaUsuario");

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioService.findById(prusId).get();

            return ResponseEntity.ok().body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() PruebaUsuario");

        try {
            return ResponseEntity.ok()
                    .body(pruebaUsuarioMapper.listPruebaUsuarioToListPruebaUsuarioDTO(pruebaUsuarioService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody PruebaUsuarioDTO pruebaUsuarioDTO) {
        log.debug("Request to save PruebaUsuario: {}", pruebaUsuarioDTO);

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioMapper.pruebaUsuarioDTOToPruebaUsuario(pruebaUsuarioDTO);
            pruebaUsuario = pruebaUsuarioService.save(pruebaUsuario);

            return ResponseEntity.ok().body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody PruebaUsuarioDTO pruebaUsuarioDTO) {
        log.debug("Request to update PruebaUsuario: {}", pruebaUsuarioDTO);

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioMapper.pruebaUsuarioDTOToPruebaUsuario(pruebaUsuarioDTO);
            pruebaUsuario = pruebaUsuarioService.update(pruebaUsuario);

            return ResponseEntity.ok().body(pruebaUsuarioMapper.pruebaUsuarioToPruebaUsuarioDTO(pruebaUsuario));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{prusId}")
    public ResponseEntity<?> delete(@PathVariable("prusId") Integer prusId) throws Exception {
        log.debug("Request to delete PruebaUsuario");

        try {
            PruebaUsuario pruebaUsuario = pruebaUsuarioService.findById(prusId).get();

            pruebaUsuarioService.delete(pruebaUsuario);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(pruebaUsuarioService.count());
    }

    @GetMapping(value = "/getPruebas/{usuaId}/{prusId}")
    public ResponseEntity<?> getPruebas(@PathVariable("usuaId") Long usuaId, @PathVariable("prusId") Long prusId) {
        try {
            List<PruebaUsuarioDTO> pruebasUsuario = pruebaUsuarioService.getPruebas(usuaId, prusId);
            return ResponseEntity.ok().body(pruebasUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "/iniciarPrueba")
    public ResponseEntity<?> iniciarPrueba(@RequestBody PruebaUsuarioDTO pruebaUsuarioDTO) {
        try {
            pruebaUsuarioService.iniciarPrueba(pruebaUsuarioDTO.getPrusId(), pruebaUsuarioDTO.getUsuCreador());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().equals("Se terminó el tiempo de la prueba")) {
                try {
                    pruebaUsuarioService.finalizarPrueba(pruebaUsuarioDTO.getPrusId(),
                            pruebaUsuarioDTO.getUsuCreador());
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body(ex.getMessage());
                }
            }
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/pausarPrueba")
    public ResponseEntity<?> pausarPrueba(@RequestBody PruebaUsuarioDTO pruebaUsuarioDTO) {
        try {
            pruebaUsuarioService.pausarPrueba(pruebaUsuarioDTO.getPrusId(), pruebaUsuarioDTO.getUsuCreador());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/finalizarPrueba")
    public ResponseEntity<?> finalizarPrueba(@RequestBody PruebaUsuarioDTO pruebaUsuarioDTO) {
        try {
            pruebaUsuarioService.finalizarPrueba(pruebaUsuarioDTO.getPrusId(), pruebaUsuarioDTO.getUsuCreador());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
