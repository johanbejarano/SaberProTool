package com.saberpro.modelo;

import org.hibernate.validator.constraints.*;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "parametro", schema = "public")
public class Parametro implements java.io.Serializable {
    @NotNull
    private Long idParametro;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String activo;
    private String descripcion;
    @NotNull
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String nombre;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;
    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private String valor;

    public Parametro() {
    }

    public Parametro(Long idParametro, String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, String nombre,
        Long usuCreador, Long usuModificador, String valor) {
        this.idParametro = idParametro;
        this.activo = activo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombre = nombre;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.valor = valor;
    }

    @Id
    @Column(name = "id_parametro", unique = true, nullable = false)
    public Long getIdParametro() {
        return this.idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    @Column(name = "activo", nullable = false)
    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Column(name = "valor", nullable = false)
    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
