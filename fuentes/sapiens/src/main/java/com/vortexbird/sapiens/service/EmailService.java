package com.vortexbird.sapiens.service;

public interface EmailService {

	public void sendRecuperarContrasena(String correo, String token) throws Exception;
	public void sendNuevaContrasena(String correo, String clave) throws Exception;

}
