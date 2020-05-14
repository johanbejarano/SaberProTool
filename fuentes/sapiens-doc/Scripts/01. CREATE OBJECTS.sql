--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-05-14 14:39:31 -05

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
-- TOC entry 3345 (class 1262 OID 33550)
-- Name: sapiens; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE sapiens WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE sapiens OWNER TO postgres;

\connect sapiens

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 231 (class 1259 OID 34476)
-- Name: detalle_prueba_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_prueba_usuario (
    dpru_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    preg_id integer NOT NULL,
    resp_id integer,
    prus_id integer NOT NULL
);


ALTER TABLE public.detalle_prueba_usuario OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 34474)
-- Name: detalle_prueba_usuario_dpru_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_prueba_usuario_dpru_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_prueba_usuario_dpru_id_seq OWNER TO postgres;

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 230
-- Name: detalle_prueba_usuario_dpru_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_prueba_usuario_dpru_id_seq OWNED BY public.detalle_prueba_usuario.dpru_id;


--
-- TOC entry 223 (class 1259 OID 34432)
-- Name: estado_prueba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_prueba (
    espr_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    descripcion character varying(255)
);


ALTER TABLE public.estado_prueba OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 34430)
-- Name: estado_prueba_espr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_prueba_espr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_prueba_espr_id_seq OWNER TO postgres;

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 222
-- Name: estado_prueba_espr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_prueba_espr_id_seq OWNED BY public.estado_prueba.espr_id;


--
-- TOC entry 203 (class 1259 OID 34318)
-- Name: facultad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad (
    facu_id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL
);


ALTER TABLE public.facultad OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 34316)
-- Name: facultad_facu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.facultad_facu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.facultad_facu_id_seq OWNER TO postgres;

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 202
-- Name: facultad_facu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.facultad_facu_id_seq OWNED BY public.facultad.facu_id;


--
-- TOC entry 217 (class 1259 OID 34399)
-- Name: modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modulo (
    modu_id integer NOT NULL,
    nombre text NOT NULL,
    cantidad_preguntas numeric(5,0) NOT NULL,
    prioridad numeric(5,0) NOT NULL,
    descripcion text,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    timo_id integer NOT NULL
);


ALTER TABLE public.modulo OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 34397)
-- Name: modulo_modu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modulo_modu_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modulo_modu_id_seq OWNER TO postgres;

--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 216
-- Name: modulo_modu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modulo_modu_id_seq OWNED BY public.modulo.modu_id;


--
-- TOC entry 219 (class 1259 OID 34410)
-- Name: pregunta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pregunta (
    preg_id integer NOT NULL,
    descripcion text NOT NULL,
    retroalimentacion text NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    modu_id integer NOT NULL,
    tprg_id integer NOT NULL
);


ALTER TABLE public.pregunta OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 34408)
-- Name: pregunta_preg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pregunta_preg_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pregunta_preg_id_seq OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 218
-- Name: pregunta_preg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pregunta_preg_id_seq OWNED BY public.pregunta.preg_id;


--
-- TOC entry 205 (class 1259 OID 34329)
-- Name: programa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.programa (
    prog_id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    facu_id integer NOT NULL
);


ALTER TABLE public.programa OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 34421)
-- Name: programa_modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.programa_modulo (
    prmo_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    prog_id integer NOT NULL,
    modu_id integer NOT NULL
);


ALTER TABLE public.programa_modulo OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 34419)
-- Name: programa_modulo_prmo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.programa_modulo_prmo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.programa_modulo_prmo_id_seq OWNER TO postgres;

--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 220
-- Name: programa_modulo_prmo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_modulo_prmo_id_seq OWNED BY public.programa_modulo.prmo_id;


--
-- TOC entry 204 (class 1259 OID 34327)
-- Name: programa_prog_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.programa_prog_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.programa_prog_id_seq OWNER TO postgres;

--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 204
-- Name: programa_prog_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.programa_prog_id_seq OWNED BY public.programa.prog_id;


--
-- TOC entry 213 (class 1259 OID 34377)
-- Name: prueba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prueba (
    prue_id integer NOT NULL,
    fecha_inicial timestamp without time zone NOT NULL,
    fecha_final timestamp without time zone NOT NULL,
    tiempo numeric(19,0),
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    tipr_id integer NOT NULL
);


ALTER TABLE public.prueba OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 34454)
-- Name: prueba_modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prueba_modulo (
    prmo_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    modu_id integer NOT NULL,
    prue_id integer NOT NULL
);


ALTER TABLE public.prueba_modulo OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 34452)
-- Name: prueba_modulo_prmo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prueba_modulo_prmo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prueba_modulo_prmo_id_seq OWNER TO postgres;

--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 226
-- Name: prueba_modulo_prmo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prueba_modulo_prmo_id_seq OWNED BY public.prueba_modulo.prmo_id;


--
-- TOC entry 212 (class 1259 OID 34375)
-- Name: prueba_prue_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prueba_prue_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prueba_prue_id_seq OWNER TO postgres;

--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 212
-- Name: prueba_prue_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prueba_prue_id_seq OWNED BY public.prueba.prue_id;


--
-- TOC entry 229 (class 1259 OID 34465)
-- Name: prueba_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prueba_usuario (
    prus_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    usua_id integer NOT NULL,
    espr_id integer NOT NULL,
    prue_id integer NOT NULL,
    total_preguntas numeric,
    total_respuestas_correctas numeric,
    fecha_inicio timestamp without time zone,
    tiempo_disponible numeric
);


ALTER TABLE public.prueba_usuario OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 34463)
-- Name: prueba_usuario_prus_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prueba_usuario_prus_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prueba_usuario_prus_id_seq OWNER TO postgres;

--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 228
-- Name: prueba_usuario_prus_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prueba_usuario_prus_id_seq OWNED BY public.prueba_usuario.prus_id;


--
-- TOC entry 225 (class 1259 OID 34443)
-- Name: respuesta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.respuesta (
    resp_id integer NOT NULL,
    descripcion text NOT NULL,
    retroalimentacion text NOT NULL,
    correcta numeric(1,0) NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    preg_id integer NOT NULL
);


ALTER TABLE public.respuesta OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 34441)
-- Name: respuesta_resp_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.respuesta_resp_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.respuesta_resp_id_seq OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 224
-- Name: respuesta_resp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.respuesta_resp_id_seq OWNED BY public.respuesta.resp_id;


--
-- TOC entry 215 (class 1259 OID 34388)
-- Name: tipo_modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_modulo (
    timo_id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL
);


ALTER TABLE public.tipo_modulo OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 34386)
-- Name: tipo_modulo_timo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_modulo_timo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_modulo_timo_id_seq OWNER TO postgres;

--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_modulo_timo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_modulo_timo_id_seq OWNED BY public.tipo_modulo.timo_id;


--
-- TOC entry 233 (class 1259 OID 34487)
-- Name: tipo_pregunta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_pregunta (
    tprg_id integer NOT NULL,
    nombre text NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL
);


ALTER TABLE public.tipo_pregunta OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 34485)
-- Name: tipo_pregunta_tprg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_pregunta_tprg_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_pregunta_tprg_id_seq OWNER TO postgres;

--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 232
-- Name: tipo_pregunta_tprg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_pregunta_tprg_id_seq OWNED BY public.tipo_pregunta.tprg_id;


--
-- TOC entry 211 (class 1259 OID 34366)
-- Name: tipo_prueba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_prueba (
    tipr_id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL
);


ALTER TABLE public.tipo_prueba OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 34364)
-- Name: tipo_prueba_tipr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_prueba_tipr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_prueba_tipr_id_seq OWNER TO postgres;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 210
-- Name: tipo_prueba_tipr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_prueba_tipr_id_seq OWNED BY public.tipo_prueba.tipr_id;


--
-- TOC entry 207 (class 1259 OID 34340)
-- Name: tipo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_usuario (
    tius_id integer NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL
);


ALTER TABLE public.tipo_usuario OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 34338)
-- Name: tipo_usuario_tius_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_usuario_tius_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_usuario_tius_id_seq OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 206
-- Name: tipo_usuario_tius_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_usuario_tius_id_seq OWNED BY public.tipo_usuario.tius_id;


--
-- TOC entry 209 (class 1259 OID 34351)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usua_id integer NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    genero text NOT NULL,
    codigo text NOT NULL,
    identificacion numeric(19,0) NOT NULL,
    celular text,
    correo text NOT NULL,
    password text NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    usu_creador numeric(5,0) NOT NULL,
    usu_modificador numeric(5,0),
    estado_registro text NOT NULL,
    prog_id integer NOT NULL,
    tius_id integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 34349)
-- Name: usuario_usua_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_usua_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_usua_id_seq OWNER TO postgres;

--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 208
-- Name: usuario_usua_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_usua_id_seq OWNED BY public.usuario.usua_id;


--
-- TOC entry 3126 (class 2604 OID 34479)
-- Name: detalle_prueba_usuario dpru_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_prueba_usuario ALTER COLUMN dpru_id SET DEFAULT nextval('public.detalle_prueba_usuario_dpru_id_seq'::regclass);


--
-- TOC entry 3122 (class 2604 OID 34435)
-- Name: estado_prueba espr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_prueba ALTER COLUMN espr_id SET DEFAULT nextval('public.estado_prueba_espr_id_seq'::regclass);


--
-- TOC entry 3112 (class 2604 OID 34321)
-- Name: facultad facu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facultad ALTER COLUMN facu_id SET DEFAULT nextval('public.facultad_facu_id_seq'::regclass);


--
-- TOC entry 3119 (class 2604 OID 34402)
-- Name: modulo modu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo ALTER COLUMN modu_id SET DEFAULT nextval('public.modulo_modu_id_seq'::regclass);


--
-- TOC entry 3120 (class 2604 OID 34413)
-- Name: pregunta preg_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pregunta ALTER COLUMN preg_id SET DEFAULT nextval('public.pregunta_preg_id_seq'::regclass);


--
-- TOC entry 3113 (class 2604 OID 34332)
-- Name: programa prog_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa ALTER COLUMN prog_id SET DEFAULT nextval('public.programa_prog_id_seq'::regclass);


--
-- TOC entry 3121 (class 2604 OID 34424)
-- Name: programa_modulo prmo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa_modulo ALTER COLUMN prmo_id SET DEFAULT nextval('public.programa_modulo_prmo_id_seq'::regclass);


--
-- TOC entry 3117 (class 2604 OID 34380)
-- Name: prueba prue_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba ALTER COLUMN prue_id SET DEFAULT nextval('public.prueba_prue_id_seq'::regclass);


--
-- TOC entry 3124 (class 2604 OID 34457)
-- Name: prueba_modulo prmo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_modulo ALTER COLUMN prmo_id SET DEFAULT nextval('public.prueba_modulo_prmo_id_seq'::regclass);


--
-- TOC entry 3125 (class 2604 OID 34468)
-- Name: prueba_usuario prus_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_usuario ALTER COLUMN prus_id SET DEFAULT nextval('public.prueba_usuario_prus_id_seq'::regclass);


--
-- TOC entry 3123 (class 2604 OID 34446)
-- Name: respuesta resp_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.respuesta ALTER COLUMN resp_id SET DEFAULT nextval('public.respuesta_resp_id_seq'::regclass);


--
-- TOC entry 3118 (class 2604 OID 34391)
-- Name: tipo_modulo timo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_modulo ALTER COLUMN timo_id SET DEFAULT nextval('public.tipo_modulo_timo_id_seq'::regclass);


--
-- TOC entry 3127 (class 2604 OID 34490)
-- Name: tipo_pregunta tprg_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pregunta ALTER COLUMN tprg_id SET DEFAULT nextval('public.tipo_pregunta_tprg_id_seq'::regclass);


--
-- TOC entry 3116 (class 2604 OID 34369)
-- Name: tipo_prueba tipr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_prueba ALTER COLUMN tipr_id SET DEFAULT nextval('public.tipo_prueba_tipr_id_seq'::regclass);


--
-- TOC entry 3114 (class 2604 OID 34343)
-- Name: tipo_usuario tius_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario ALTER COLUMN tius_id SET DEFAULT nextval('public.tipo_usuario_tius_id_seq'::regclass);


--
-- TOC entry 3115 (class 2604 OID 34354)
-- Name: usuario usua_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN usua_id SET DEFAULT nextval('public.usuario_usua_id_seq'::regclass);


--
-- TOC entry 3161 (class 2606 OID 34484)
-- Name: detalle_prueba_usuario detalle_prueba_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_prueba_usuario
    ADD CONSTRAINT detalle_prueba_usuario_pkey PRIMARY KEY (dpru_id);


--
-- TOC entry 3153 (class 2606 OID 34440)
-- Name: estado_prueba estado_prueba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_prueba
    ADD CONSTRAINT estado_prueba_pkey PRIMARY KEY (espr_id);


--
-- TOC entry 3129 (class 2606 OID 34326)
-- Name: facultad facultad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.facultad
    ADD CONSTRAINT facultad_pkey PRIMARY KEY (facu_id);


--
-- TOC entry 3147 (class 2606 OID 34407)
-- Name: modulo modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT modulo_pkey PRIMARY KEY (modu_id);


--
-- TOC entry 3149 (class 2606 OID 34418)
-- Name: pregunta pregunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (preg_id);


--
-- TOC entry 3151 (class 2606 OID 34429)
-- Name: programa_modulo programa_modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa_modulo
    ADD CONSTRAINT programa_modulo_pkey PRIMARY KEY (prmo_id);


--
-- TOC entry 3131 (class 2606 OID 34337)
-- Name: programa programa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT programa_pkey PRIMARY KEY (prog_id);


--
-- TOC entry 3157 (class 2606 OID 34462)
-- Name: prueba_modulo prueba_modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_modulo
    ADD CONSTRAINT prueba_modulo_pkey PRIMARY KEY (prmo_id);


--
-- TOC entry 3143 (class 2606 OID 34385)
-- Name: prueba prueba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba
    ADD CONSTRAINT prueba_pkey PRIMARY KEY (prue_id);


--
-- TOC entry 3159 (class 2606 OID 34473)
-- Name: prueba_usuario prueba_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_usuario
    ADD CONSTRAINT prueba_usuario_pkey PRIMARY KEY (prus_id);


--
-- TOC entry 3155 (class 2606 OID 34451)
-- Name: respuesta respuesta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT respuesta_pkey PRIMARY KEY (resp_id);


--
-- TOC entry 3145 (class 2606 OID 34396)
-- Name: tipo_modulo tipo_modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_modulo
    ADD CONSTRAINT tipo_modulo_pkey PRIMARY KEY (timo_id);


--
-- TOC entry 3163 (class 2606 OID 34495)
-- Name: tipo_pregunta tipo_pregunta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_pregunta
    ADD CONSTRAINT tipo_pregunta_pkey PRIMARY KEY (tprg_id);


--
-- TOC entry 3141 (class 2606 OID 34374)
-- Name: tipo_prueba tipo_prueba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_prueba
    ADD CONSTRAINT tipo_prueba_pkey PRIMARY KEY (tipr_id);


--
-- TOC entry 3133 (class 2606 OID 34348)
-- Name: tipo_usuario tipo_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_usuario
    ADD CONSTRAINT tipo_usuario_pkey PRIMARY KEY (tius_id);


--
-- TOC entry 3135 (class 2606 OID 34361)
-- Name: usuario usuario_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_codigo_key UNIQUE (codigo);


--
-- TOC entry 3137 (class 2606 OID 34363)
-- Name: usuario usuario_identificacion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_identificacion_key UNIQUE (identificacion);


--
-- TOC entry 3139 (class 2606 OID 34359)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usua_id);


--
-- TOC entry 3178 (class 2606 OID 34551)
-- Name: prueba_usuario fk_espr_prus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_usuario
    ADD CONSTRAINT fk_espr_prus FOREIGN KEY (espr_id) REFERENCES public.estado_prueba(espr_id);


--
-- TOC entry 3164 (class 2606 OID 34581)
-- Name: programa fk_facu_prog; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa
    ADD CONSTRAINT fk_facu_prog FOREIGN KEY (facu_id) REFERENCES public.facultad(facu_id);


--
-- TOC entry 3169 (class 2606 OID 34511)
-- Name: pregunta fk_modu_preg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_modu_preg FOREIGN KEY (modu_id) REFERENCES public.modulo(modu_id);


--
-- TOC entry 3175 (class 2606 OID 34536)
-- Name: prueba_modulo fk_modu_prmo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_modulo
    ADD CONSTRAINT fk_modu_prmo FOREIGN KEY (modu_id) REFERENCES public.modulo(modu_id);


--
-- TOC entry 3171 (class 2606 OID 34516)
-- Name: programa_modulo fk_modu_prog; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa_modulo
    ADD CONSTRAINT fk_modu_prog FOREIGN KEY (modu_id) REFERENCES public.modulo(modu_id);


--
-- TOC entry 3180 (class 2606 OID 34561)
-- Name: detalle_prueba_usuario fk_preg_dpru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_prueba_usuario
    ADD CONSTRAINT fk_preg_dpru FOREIGN KEY (preg_id) REFERENCES public.pregunta(preg_id);


--
-- TOC entry 3173 (class 2606 OID 34526)
-- Name: respuesta fk_preg_resp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.respuesta
    ADD CONSTRAINT fk_preg_resp FOREIGN KEY (preg_id) REFERENCES public.pregunta(preg_id);


--
-- TOC entry 3172 (class 2606 OID 34521)
-- Name: programa_modulo fk_prog_prmo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.programa_modulo
    ADD CONSTRAINT fk_prog_prmo FOREIGN KEY (prog_id) REFERENCES public.programa(prog_id);


--
-- TOC entry 3166 (class 2606 OID 34576)
-- Name: usuario fk_prog_usua; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_prog_usua FOREIGN KEY (prog_id) REFERENCES public.programa(prog_id);


--
-- TOC entry 3174 (class 2606 OID 34531)
-- Name: prueba_modulo fk_prue_prmo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_modulo
    ADD CONSTRAINT fk_prue_prmo FOREIGN KEY (prue_id) REFERENCES public.prueba(prue_id);


--
-- TOC entry 3176 (class 2606 OID 34541)
-- Name: prueba_usuario fk_prue_prus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_usuario
    ADD CONSTRAINT fk_prue_prus FOREIGN KEY (prue_id) REFERENCES public.prueba(prue_id);


--
-- TOC entry 3179 (class 2606 OID 34556)
-- Name: detalle_prueba_usuario fk_prus_dpru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_prueba_usuario
    ADD CONSTRAINT fk_prus_dpru FOREIGN KEY (prus_id) REFERENCES public.prueba_usuario(prus_id);


--
-- TOC entry 3181 (class 2606 OID 34566)
-- Name: detalle_prueba_usuario fk_resp_dpru; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_prueba_usuario
    ADD CONSTRAINT fk_resp_dpru FOREIGN KEY (resp_id) REFERENCES public.respuesta(resp_id);


--
-- TOC entry 3168 (class 2606 OID 34506)
-- Name: modulo fk_timo_modu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modulo
    ADD CONSTRAINT fk_timo_modu FOREIGN KEY (timo_id) REFERENCES public.tipo_modulo(timo_id);


--
-- TOC entry 3167 (class 2606 OID 34501)
-- Name: prueba fk_tipr_prue; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba
    ADD CONSTRAINT fk_tipr_prue FOREIGN KEY (tipr_id) REFERENCES public.tipo_prueba(tipr_id);


--
-- TOC entry 3165 (class 2606 OID 34496)
-- Name: usuario fk_tius_usua; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_tius_usua FOREIGN KEY (tius_id) REFERENCES public.tipo_usuario(tius_id);


--
-- TOC entry 3170 (class 2606 OID 34571)
-- Name: pregunta fk_tprg_preg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT fk_tprg_preg FOREIGN KEY (tprg_id) REFERENCES public.tipo_pregunta(tprg_id);


--
-- TOC entry 3177 (class 2606 OID 34546)
-- Name: prueba_usuario fk_usua_prus; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prueba_usuario
    ADD CONSTRAINT fk_usua_prus FOREIGN KEY (usua_id) REFERENCES public.usuario(usua_id);


-- Completed on 2020-05-14 14:39:31 -05

--
-- PostgreSQL database dump complete
--

