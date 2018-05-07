package com.saberpro.utilities;

import java.util.List;
import javax.faces.model.SelectItem;



public class ComponentPrueba {

	private long idPregunta;
	private String numeroPregunta;
	private String contenidoPregunta;
	
	private List<SelectItem> listRespuestas;
	private String respuestaSelecionada;
	
	
	public ComponentPrueba() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComponentPrueba(long idPregunta,String numeroPregunta, String contenidoPregunta,
			List<SelectItem> listRespuestas, String respuestaSelecionada) {
		super();
		this.idPregunta = idPregunta;
		this.numeroPregunta = numeroPregunta;
		this.contenidoPregunta = contenidoPregunta;
		this.listRespuestas = listRespuestas;
		this.respuestaSelecionada = respuestaSelecionada;
	}

	public long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getNumeroPregunta() {
		return numeroPregunta;
	}

	public void setNumeroPregunta(String numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}

	public String getContenidoPregunta() {
		return contenidoPregunta;
	}

	public void setContenidoPregunta(String contenidoPregunta) {
		this.contenidoPregunta = contenidoPregunta;
	}

	public List<SelectItem> getListRespuestas() {
		return listRespuestas;
	}

	public void setListRespuestas(List<SelectItem> listRespuestas) {
		this.listRespuestas = listRespuestas;
	}

	public String getRespuestaSelecionada() {
		return respuestaSelecionada;
	}

	public void setRespuestaSelecionada(String respuestaSelecionada) {
		this.respuestaSelecionada = respuestaSelecionada;
	}
	
	
	
}
