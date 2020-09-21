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

	public String getSUBREPORT_DIR() {
		return SUBREPORT_DIR;
	}

	public void setSUBREPORT_DIR(String SUBREPORT_DIR) {
		this.SUBREPORT_DIR = SUBREPORT_DIR;
	}
	
	
	
}
