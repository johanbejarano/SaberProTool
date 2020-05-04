package com.vortexbird.sapiens.controller;

import java.util.List;

import com.vortexbird.sapiens.dto.DetallePruebaUsuarioDTO;
import com.vortexbird.sapiens.service.DetallePruebaUsuarioService;
import com.vortexbird.sapiens.service.PruebaUsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detallePruebaUsuario")
@CrossOrigin(origins = "*")
public class DetallePruebaUsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(DetallePruebaUsuarioRestController.class);
    @Autowired
    private DetallePruebaUsuarioService detallePruebaUsuarioService;
    @Autowired
    private PruebaUsuarioService pruebaUsuarioService;

    @PostMapping(value = "/responder")
    public ResponseEntity<?> responder(@RequestBody DetallePruebaUsuarioDTO detallePruebaUsuarioDTO) {
        try {
            detallePruebaUsuarioService.responder(detallePruebaUsuarioDTO.getDpruId(),
                    detallePruebaUsuarioDTO.getRespId(), detallePruebaUsuarioDTO.getUsuCreador());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if (e.getMessage().equals("Se terminó el tiempo de la prueba")) {
                try {
                    pruebaUsuarioService.finalizarPrueba(detallePruebaUsuarioDTO.getPrusId(),
                            detallePruebaUsuarioDTO.getUsuCreador());
                } catch (Exception ex) {
                    return ResponseEntity.badRequest().body(ex.getMessage());
                }
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/getPreguntasByPruebaUsuario/{prusId}")
    public ResponseEntity<?> getPreguntasByPruebaUsuario(@PathVariable("prusId") Integer prusId) {
        try {
            List<DetallePruebaUsuarioDTO> detallesPruebaUsuario = detallePruebaUsuarioService.getPreguntasByPruebaUsuarioDTO(prusId);
            return ResponseEntity.ok().body(detallesPruebaUsuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
