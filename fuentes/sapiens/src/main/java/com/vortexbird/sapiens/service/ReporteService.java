package com.vortexbird.sapiens.service;

public interface ReporteService {
	
	public String reportePruebaEstudiante(Integer prueId, Integer estuId) throws Exception;
	
	public String reportePruebaModulo(Integer prueId) throws Exception;
	
	public String reportePruebaPrograma(Integer prueId) throws Exception;
	
	public String reportePruebaDetalleEstudiante(Integer prueId) throws Exception;
	
	public String reportePruebaResultado(Integer prueId) throws Exception;

	String reportePruebaResultadoCualitativo(Integer prueId) throws Exception;
	
}
