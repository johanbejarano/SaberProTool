package com.vortexbird.sapiens.service;

import java.util.Date;
import java.util.List;

public interface EmailService {

	public void sendRecuperarContrasena(String correo, String token) throws Exception;
	public void sendNuevaContrasena(String correo, String clave) throws Exception;
	public void sendCrearUsuario(String correo, String usuario, String clave) throws Exception;
	
	public void sendPruebaCreada(List<String> correos, Integer numeroPrueba, Date fechaInicial, Date fechaFinal) throws Exception;

}
