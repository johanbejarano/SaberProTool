package com.saberpro.modelo.dto;

public class ResultadosModuloDTO {
	private String nombreModulo;
	private long porcentaje;
	
	
	
	public ResultadosModuloDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ResultadosModuloDTO(String nombreModulo,long porcentaje) {
		super();
		this.nombreModulo = nombreModulo;
		this.porcentaje = porcentaje;
	}



	public String getNombreModulo() {
		return nombreModulo;
	}
	
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}
	public long getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(long porcentaje) {
		this.porcentaje = porcentaje;
	}



	@Override
	public String toString() {
		return "ResultadosModuloDTO [nombreModulo=" + nombreModulo + ", porcentaje=" + porcentaje + "]";
	}
	
	
}
