--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-05-14 15:14:23 -05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3329 (class 0 OID 34432)
-- Dependencies: 223
-- Data for Name: estado_prueba; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estado_prueba (espr_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, descripcion) VALUES (2, '2020-03-21 00:00:00', NULL, 1, NULL, 'A', 'INICIADA');
INSERT INTO public.estado_prueba (espr_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, descripcion) VALUES (3, '2020-03-21 00:00:00', NULL, 1, NULL, 'A', 'PAUSADA');
INSERT INTO public.estado_prueba (espr_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, descripcion) VALUES (1, '2020-03-21 00:00:00', NULL, 1, NULL, 'A', 'PENDIENTE');
INSERT INTO public.estado_prueba (espr_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, descripcion) VALUES (4, '2020-03-21 00:00:00', NULL, 1, NULL, 'A', 'FINALIZADA');


--
-- TOC entry 3309 (class 0 OID 34318)
-- Dependencies: 203
-- Data for Name: facultad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (1, 'INGENIERÍA', 'INGENIERÍA', '2020-03-16 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (2, 'CIENCIAS ECONÓMICAS', 'CIENCIAS ECONÓMICAS', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (3, 'ARQUITECTURA ARTE Y DISEÑO', 'ARQUITECTURA ARTE Y DISEÑO', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (4, 'PSICOLOGÍA', 'PSICOLOGÍA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (5, 'DERECHO', 'DERECHO', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.facultad (facu_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (6, 'EDUCACIÓN', 'EDUCACIÓN', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');


--
-- TOC entry 3321 (class 0 OID 34388)
-- Dependencies: 215
-- Data for Name: tipo_modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_modulo (timo_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (1, 'COMPETENCIA GENERICA', 'Categoria de modulos aplicada todas por igual a todos los programas', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.tipo_modulo (timo_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (2, 'COMPETENCIA ESPECÍFICA', 'Categoria de modulos aplicada de acuerdo al programa', '2020-03-01 00:00:00', NULL, 1, NULL, 'A');


--
-- TOC entry 3323 (class 0 OID 34399)
-- Dependencies: 217
-- Data for Name: modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (8, 'RAZONAMIENTO CUANTITATIVO', 10, 1, '?', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (9, 'DISEÑO', 10, 1, '?', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (10, 'ARGUMENTACIÓN', 10, 1, '?', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (11, 'FILOSOFÍA', 10, 1, '?', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (1, 'COMPETENCIAS CIUDADANAS', 10, 1, 'Las competencias ciudadanas son un conjunto de habilidades cognitivas, emocionales y comunicativas, que debemos desarrollar desde pequeños para saber vivir con los otros y sobre todo, para actuar de manera constructiva en la sociedad', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (2, 'COMUNICACIÓN ESCRITA', 10, 1, 'La comunicación escrita es el proceso escrito mediante el cual un emisor (periodista, escritor, poeta etc.) dirige un mensaje a un receptor', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (3, 'INGLÉS', 10, 1, 'El modulo de inglé es para evaluar las capacidades frente al conocimiento que posean frente a la lenguaje o idioma', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (4, 'LECTURA CRÍTICA', 10, 1, 'La lectura crítica es la lectura realizada de un modo analítico. Esto significa que además de comprender los que se dice en un texto determinado, se intentará analizar lo expresado para verificar sus aciertos, sus errores y los modos en que se presenta la información', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (5, 'DISEÑO DE SOFTWARE', 10, 1, 'El diseño de sistemas es el arte de definir la arquitectura de hardware y software, componentes, módulos y datos de un sistema de cómputo, a efectos de satisfacer ciertos requerimientos. ... La importancia del software multiplataforma ha incrementado la ingeniería de software a costa de los diseños de sistemas', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (6, 'FORMULACIÓN DE PROyECTOS, CONCEPTO DE FORMULACIÓN Y EVALUACIÓN DE PROYECTOS', 10, 1, 'Es el conjunto organizado de acciones, realizadas ordenadamente durante un período de tiempo determinado, que responden a una demanda o problema, con el propósito de ofrecer una solución.', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.modulo (modu_id, nombre, cantidad_preguntas, prioridad, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, timo_id) VALUES (7, 'PENSAMIENTO CIENTÍFICO,MATEMÁTICO Y ESTADÍSTICO', 10, 1, 'La ciencia es un conjunto de técnicas y métodos que permiten organizar el conocimiento sobre la estructura de hechos objetivos y accesibles a distintos observadores. El pensamiento, por su parte, es el producto de la mente, aquello traído a la existencia por medio de la actividad intelectual,onocimiento matemático; teorema matemático; cálculo matemático y conocimientos de estadistica', '2020-03-20 00:00:00', NULL, 1, NULL, 'A', 2);


--
-- TOC entry 3339 (class 0 OID 34487)
-- Dependencies: 233
-- Data for Name: tipo_pregunta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_pregunta (tprg_id, nombre, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (1, 'CERRADA', '2020-03-15 00:00:00', NULL, 1, NULL, 'ACTIVO');


--
-- TOC entry 3325 (class 0 OID 34410)
-- Dependencies: 219
-- Data for Name: pregunta; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3311 (class 0 OID 34329)
-- Dependencies: 205
-- Data for Name: programa; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (1, 'INGENIERIA DE SISTEMAS', 'INGENIERIA DE SISTEMAS', '2020-03-16 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (2, 'INGENIERÍA ELECTRÓNICA', 'INGENIERÍA ELECTRÓNICA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (5, 'INGENIERÍA AGROINDSTRIAL', 'INGENIERÍA AGROINDSTRIAL', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (6, 'INGENIERÍA INDUSTRIAL', 'INGENIERÍA INDUSTRIAL', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (7, 'INGENIERÍA MULTIMEDIA', 'INGENIERÍA MULTIMEDIA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 1);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (8, 'ECONOMÍA', 'ECONOMÍA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (9, 'ADMINISTRACIÓN DE NEGOCIOS', 'ADMINISTRACIÓN DE NEGOCIOS', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (10, 'CONTADURÍA', 'CONTADURÍA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 2);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (11, 'ARQUITECTURA', 'ARQUITECTURA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 3);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (13, 'DISEÑO DE VESTUARIO', 'DISEÑO DE VESTUARIO', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 3);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (14, 'PSICOLOGÍA', 'PSICOLOGÍA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 4);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (15, 'DERECHO', 'DERECHO', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 5);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (16, 'GOBIERNO Y RELACIONES INTERINSTITUCIONALES', 'GOBIERNO Y RELACIONES INTERINSTITUCIONALES', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 5);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (17, 'LICENCIATURA EN EDUCACIÓN INFANTÍL', 'LICENCIATURA EN EDUCACIÓN INFANTÍL', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 6);
INSERT INTO public.programa (prog_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, facu_id) VALUES (18, 'LICENCIATURA EN EDUCACIÓN FÍSICA', 'LICENCIATURA EN EDUCACIÓN FÍSICA', '2020-03-01 00:00:00', NULL, 1, NULL, 'A', 6);


--
-- TOC entry 3317 (class 0 OID 34366)
-- Dependencies: 211
-- Data for Name: tipo_prueba; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_prueba (tipr_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (1, 'ENTRENAMIENTO', 'ENTRENAMIENTO', '2020-03-21 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.tipo_prueba (tipr_id, nombre, descripcion, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (2, 'SIMULACRO', 'SIMULACRO', '2020-03-21 00:00:00', NULL, 1, NULL, 'A');


--
-- TOC entry 3319 (class 0 OID 34377)
-- Dependencies: 213
-- Data for Name: prueba; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3313 (class 0 OID 34340)
-- Dependencies: 207
-- Data for Name: tipo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_usuario (tius_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (1, '2020-03-16 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.tipo_usuario (tius_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (2, '2020-03-18 00:00:00', NULL, 1, NULL, 'A');
INSERT INTO public.tipo_usuario (tius_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro) VALUES (3, '2020-03-18 00:00:00', NULL, 1, NULL, 'A');


--
-- TOC entry 3315 (class 0 OID 34351)
-- Dependencies: 209
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (usua_id, nombre, apellido, genero, codigo, identificacion, celular, correo, password, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, tius_id) VALUES (2, 'JOHAN', 'BEJARANO', 'M', '983695', 16935984, '2345', 'askdjasd@akshdjas.com', 'LpY/T9RU/T0JPFnwxPm3iHnH0HjElo65Q1zuqRLZ4zsC4eA2luOijBT9IpOuUBmrzUUpWMch7VucHvRwhKqyIA==', '2020-03-16 00:00:00', NULL, 1, NULL, 'A', 1, 1);


--
-- TOC entry 3335 (class 0 OID 34465)
-- Dependencies: 229
-- Data for Name: prueba_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3331 (class 0 OID 34443)
-- Dependencies: 225
-- Data for Name: respuesta; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3337 (class 0 OID 34476)
-- Dependencies: 231
-- Data for Name: detalle_prueba_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3327 (class 0 OID 34421)
-- Dependencies: 221
-- Data for Name: programa_modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (1, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 1);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (2, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 2);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (3, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 3);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (4, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 4);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (5, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 5);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (6, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 6);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (7, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 7);
INSERT INTO public.programa_modulo (prmo_id, fecha_creacion, fecha_modificacion, usu_creador, usu_modificador, estado_registro, prog_id, modu_id) VALUES (8, '2020-03-17 00:00:00', NULL, 1, NULL, 'A', 1, 8);


--
-- TOC entry 3333 (class 0 OID 34454)
-- Dependencies: 227
-- Data for Name: prueba_modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 230
-- Name: detalle_prueba_usuario_dpru_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_prueba_usuario_dpru_id_seq', 51, true);


--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 222
-- Name: estado_prueba_espr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_prueba_espr_id_seq', 4, true);


--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 202
-- Name: facultad_facu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.facultad_facu_id_seq', 6, true);


--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 216
-- Name: modulo_modu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.modulo_modu_id_seq', 15, true);


--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 218
-- Name: pregunta_preg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pregunta_preg_id_seq', 14, true);


--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 220
-- Name: programa_modulo_prmo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_modulo_prmo_id_seq', 9, true);


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 204
-- Name: programa_prog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.programa_prog_id_seq', 18, true);


--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 226
-- Name: prueba_modulo_prmo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prueba_modulo_prmo_id_seq', 45, true);


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 212
-- Name: prueba_prue_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prueba_prue_id_seq', 17, true);


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 228
-- Name: prueba_usuario_prus_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prueba_usuario_prus_id_seq', 183, true);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 224
-- Name: respuesta_resp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.respuesta_resp_id_seq', 132, true);


--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_modulo_timo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_modulo_timo_id_seq', 2, true);


--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 232
-- Name: tipo_pregunta_tprg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_pregunta_tprg_id_seq', 1, true);


--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 210
-- Name: tipo_prueba_tipr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_prueba_tipr_id_seq', 2, true);


--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 206
-- Name: tipo_usuario_tius_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_usuario_tius_id_seq', 3, true);


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 208
-- Name: usuario_usua_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_usua_id_seq', 215, true);


-- Completed on 2020-05-14 15:14:23 -05

--
-- PostgreSQL database dump complete
--

