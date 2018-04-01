package com.saberpro.utilities;

public class Constantes {

	//Generales
	public static final String ESTADO_ACTIVO = "S";
	public static final String ESTADO_INACTIVO = "N";
	
	//Roles
	public static final long USER_TYPE_ESTUDIANTE = 1L;
	public static final long USER_TYPE_DOCENTE = 2L;
	public static final long USER_TYPE_DIRECTOR = 3L;
	public static final long USER_TYPE_DECANO = 4L;
	public static final long USER_TYPE_ADMIN = 5L;
	
	//Modulos
	public static final long MODULO_TYPE_GENERICO = 1L;
	public static final long MODULO_TYPE_ESPECIFICO = 2L;
	
	//Pregunta
	public static final long PREGUNTA_TYPE_ABIERTA = 1L;
	public static final long PREGUNTA_TYPE_MULTIPLE = 2L;
	
	//Prueba
	public static final long PRUEBA_TYPE_SIMULACRO = 1L;
	public static final long PRUEBA_TYPE_ENTRENAMIENTO = 2L;
	
	//Estados
	public static final long PRUEBA_ESTADO_INICIADO = 1L;
	public static final long PRUEBA_ESTADO_PAUSADA = 2L;
	public static final long PRUEBA_ESTADO_FINALIZADA = 3L;
	public static final long PRUEBA_ESTADO_REINICIADA = 4L;
	public static final long PRUEBA_ESTADO_PENDIENTE = 5L;
}
