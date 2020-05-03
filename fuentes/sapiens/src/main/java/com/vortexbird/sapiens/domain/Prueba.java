package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "prueba", schema = "public")
public class Prueba implements java.io.Serializable {
//    @NotNull
    private Integer prueId;
    @NotNull
    private TipoPrueba tipoPrueba;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String estadoRegistro;
    @NotNull
    private Date fechaCreacion;
    @NotNull
    private Date fechaFinal;
    @NotNull
    private Date fechaInicial;
    private Date fechaModificacion;
    private Long tiempo;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;
    private List<PruebaModulo> pruebaModulos = new ArrayList<PruebaModulo>(0);
    private List<PruebaUsuario> pruebaUsuarios = new ArrayList<PruebaUsuario>(0);

    public Prueba() {
    }

    public Prueba(Integer prueId, String estadoRegistro, Date fechaCreacion,
        Date fechaFinal, Date fechaInicial, Date fechaModificacion,
        List<PruebaModulo> pruebaModulos, List<PruebaUsuario> pruebaUsuarios,
        Long tiempo, TipoPrueba tipoPrueba, Long usuCreador, Long usuModificador) {
        this.prueId = prueId;
        this.tipoPrueba = tipoPrueba;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinal = fechaFinal;
        this.fechaInicial = fechaInicial;
        this.fechaModificacion = fechaModificacion;
        this.tiempo = tiempo;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.pruebaModulos = pruebaModulos;
        this.pruebaUsuarios = pruebaUsuarios;
    }

    @Id
    @Column(name = "prue_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getPrueId() {
        return this.prueId;
    }

    public void setPrueId(Integer prueId) {
        this.prueId = prueId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipr_id")
    public TipoPrueba getTipoPrueba() {
        return this.tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
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

    @Column(name = "fecha_final", nullable = false)
    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Column(name = "fecha_inicial", nullable = false)
    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    @Column(name = "fecha_modificacion")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Column(name = "tiempo")
    public Long getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prueba")
    public List<PruebaModulo> getPruebaModulos() {
        return this.pruebaModulos;
    }

    public void setPruebaModulos(List<PruebaModulo> pruebaModulos) {
        this.pruebaModulos = pruebaModulos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prueba")
    public List<PruebaUsuario> getPruebaUsuarios() {
        return this.pruebaUsuarios;
    }

    public void setPruebaUsuarios(List<PruebaUsuario> pruebaUsuarios) {
        this.pruebaUsuarios = pruebaUsuarios;
    }
}
