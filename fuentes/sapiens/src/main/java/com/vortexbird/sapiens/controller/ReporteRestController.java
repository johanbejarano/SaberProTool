package com.vortexbird.sapiens.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexbird.sapiens.dto.ReporteDTO;
import com.vortexbird.sapiens.service.ReporteService;


@RestController
@RequestMapping("/api/reporte")
@CrossOrigin(origins = "*")
public class ReporteRestController {
    private static final Logger log = LoggerFactory.getLogger(ReporteRestController.class);
    @Autowired
    private ReporteService reporteService;
    
    @PostMapping(value = "/reportePruebaEstudiante")
    public ResponseEntity<?> consultarReporteResultados(@RequestBody ReporteDTO reporteDTO) {
        try {
            String reporte = reporteService.reportePruebaEstudiante(reporteDTO.getPrueId(), reporteDTO.getUsuaId());

            ReporteDTO respuesta = new ReporteDTO();
            respuesta.setPdf(reporte);

            return ResponseEntity.ok().body(respuesta);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
