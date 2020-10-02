package com.vortexbird.sapiens.service;

public interface ReporteService {
	
	public String reportePruebaEstudiante(Integer prueId, Integer estuId) throws Exception;
	
	public String reportePruebaModulo(Long prueId) throws Exception;
	
	public String reportePruebaPrograma(Long prueId) throws Exception;
	
	public String reportePruebaDetalleEstudiante(Long prueId) throws Exception;
	
}
