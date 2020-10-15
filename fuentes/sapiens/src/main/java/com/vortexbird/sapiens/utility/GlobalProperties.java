package com.vortexbird.sapiens.utility;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties
@Validated
public class GlobalProperties {

	@NotEmpty
	private String SUBREPORT_DIR;
	
	@NotEmpty
	private String URL_RECUPERAR_CONTRASENA;
	
	@NotEmpty
	private String URL_SISTEMA;

	public String getSUBREPORT_DIR() {
		return SUBREPORT_DIR;
	}

	public void setSUBREPORT_DIR(String SUBREPORT_DIR) {
		this.SUBREPORT_DIR = SUBREPORT_DIR;
	}

	public String getURL_RECUPERAR_CONTRASENA() {
		return URL_RECUPERAR_CONTRASENA;
	}

	public void setURL_RECUPERAR_CONTRASENA(String uRL_RECUPERAR_CONTRASENA) {
		URL_RECUPERAR_CONTRASENA = uRL_RECUPERAR_CONTRASENA;
	}

	public String getURL_SISTEMA() {
		return URL_SISTEMA;
	}

	public void setURL_SISTEMA(String uRL_SISTEMA) {
		URL_SISTEMA = uRL_SISTEMA;
	}
	
}
