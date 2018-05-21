package com.saberpro.modelo.dto;

import java.util.Date;

public class ResultadosModuloDTO {
	private String nombreModulo;
	private Date fechaInicio;
	private Date fechaFin;
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



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	@Override
	public String toString() {
		return "ResultadosModuloDTO [nombreModulo=" + nombreModulo + ", porcentaje=" + porcentaje + "]";
	}
	
	
}
