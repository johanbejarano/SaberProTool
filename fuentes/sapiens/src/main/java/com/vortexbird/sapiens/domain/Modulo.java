package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "modulo", schema = "public")
public class Modulo implements java.io.Serializable {
    @NotNull
    private Integer moduId;
    @NotNull
    private TipoModulo tipoModulo;
    @NotNull
    private Long cantidadPreguntas;
    private String descripcion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String estadoRegistro;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombre;
    @NotNull
    private Long prioridad;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;
    private List<Pregunta> preguntas = new ArrayList<Pregunta>(0);
    private List<ProgramaModulo> programaModulos = new ArrayList<ProgramaModulo>(0);
    private List<PruebaModulo> pruebaModulos = new ArrayList<PruebaModulo>(0);

    public Modulo() {
    }

    public Modulo(Integer moduId, Long cantidadPreguntas, String descripcion,
        String estadoRegistro, Date fechaCreacion, Date fechaModificacion,
        String nombre, List<Pregunta> preguntas, Long prioridad,
        List<ProgramaModulo> programaModulos, List<PruebaModulo> pruebaModulos,
        TipoModulo tipoModulo, Long usuCreador, Long usuModificador) {
        this.moduId = moduId;
        this.tipoModulo = tipoModulo;
        this.cantidadPreguntas = cantidadPreguntas;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.preguntas = preguntas;
        this.programaModulos = programaModulos;
        this.pruebaModulos = pruebaModulos;
    }

    @Id
    @Column(name = "modu_id", unique = true, nullable = false)
    public Integer getModuId() {
        return this.moduId;
    }

    public void setModuId(Integer moduId) {
        this.moduId = moduId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timo_id")
    public TipoModulo getTipoModulo() {
        return this.tipoModulo;
    }

    public void setTipoModulo(TipoModulo tipoModulo) {
        this.tipoModulo = tipoModulo;
    }

    @Column(name = "cantidad_preguntas", nullable = false)
    public Long getCantidadPreguntas() {
        return this.cantidadPreguntas;
    }

    public void setCantidadPreguntas(Long cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "estado_registro", nullable = false)
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Column(name = "fecha_creacion", nullable = false)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "prioridad", nullable = false)
    public Long getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(Long prioridad) {
        this.prioridad = prioridad;
    }

    @Column(name = "usu_creador", nullable = false)
    public Long getUsuCreador() {
        return this.usuCreador;
    }

    public void setUsuCreador(Long usuCreador) {
        this.usuCreador = usuCreador;
    }

    @Column(name = "usu_modificador")
    public Long getUsuModificador() {
        return this.usuModificador;
    }

    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
    public List<Pregunta> getPreguntas() {
        return this.preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
    public List<ProgramaModulo> getProgramaModulos() {
        return this.programaModulos;
    }

    public void setProgramaModulos(List<ProgramaModulo> programaModulos) {
        this.programaModulos = programaModulos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modulo")
    public List<PruebaModulo> getPruebaModulos() {
        return this.pruebaModulos;
    }

    public void setPruebaModulos(List<PruebaModulo> pruebaModulos) {
        this.pruebaModulos = pruebaModulos;
    }
}
