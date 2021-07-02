package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "programa", schema = "public")
public class Programa implements java.io.Serializable {
    @NotNull
    private Integer progId;
    @NotNull
    private Facultad facultad;
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
    private Long usuCreador;
    private Long usuModificador;
    private Long creditos;
    private List<ProgramaModulo> programaModulos = new ArrayList<ProgramaModulo>(0);
    private List<Usuario> usuarios = new ArrayList<Usuario>(0);

    public Programa() {
    }

    public Programa(Integer progId, String descripcion, String estadoRegistro,
        Facultad facultad, Date fechaCreacion, Date fechaModificacion,
        String nombre, List<ProgramaModulo> programaModulos, Long usuCreador,
        Long usuModificador, List<Usuario> usuarios) {
        this.progId = progId;
        this.facultad = facultad;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombre = nombre;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.programaModulos = programaModulos;
        this.usuarios = usuarios;
    }

    @Id
    @Column(name = "prog_id", unique = true, nullable = false)
    public Integer getProgId() {
        return this.progId;
    }

    public void setProgId(Integer progId) {
        this.progId = progId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facu_id")
    public Facultad getFacultad() {
        return this.facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
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
    
    @Column(name = "creditos")
    public Long getCreditos() {
		return creditos;
	}

	public void setCreditos(Long creditos) {
		this.creditos = creditos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programa")
    public List<ProgramaModulo> getProgramaModulos() {
        return this.programaModulos;
    }

    public void setProgramaModulos(List<ProgramaModulo> programaModulos) {
        this.programaModulos = programaModulos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programa")
    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
