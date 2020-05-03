package com.vortexbird.sapiens.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vortexbird.sapiens.dto.UsuarioDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/

@NamedNativeQueries({
	@NamedNativeQuery(name = "Usuario.getUsuariosPorTipo", query = "", resultSetMapping = "UsuarioDTO"),
	@NamedNativeQuery(name = "Usuario.getUsuariosPorTipo.count", query = "", resultSetMapping = "UsuarioDTOCount") })
@SqlResultSetMappings({
	@SqlResultSetMapping(name = "UsuarioDTO", classes = {
			@ConstructorResult(targetClass = UsuarioDTO.class, columns = {
					@ColumnResult(name = "usuaId", type = Integer.class),
					@ColumnResult(name = "codigo", type = String.class),
					@ColumnResult(name = "identificacion", type = Long.class),
					@ColumnResult(name = "nombre", type = String.class),
					@ColumnResult(name = "apellido", type = String.class),
					@ColumnResult(name = "correo", type = String.class), }) }),
	@SqlResultSetMapping(name = "UsuarioDTOCount", columns = {
			@ColumnResult(name = "total", type = Integer.class) }), })
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {
//    @NotNull
    private Integer usuaId;
    @NotNull
    private Programa programa;
    @NotNull
    private TipoUsuario tipoUsuario;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String apellido;
    private String celular;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String codigo;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String correo;
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
    private String genero;
    @NotNull
    private Long identificacion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String nombre;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String password;
    @NotNull
    private Long usuCreador;
    private Long usuModificador;
    private List<PruebaUsuario> pruebaUsuarios = new ArrayList<PruebaUsuario>(0);

    public Usuario() {
    }

    public Usuario(Integer usuaId, String apellido, String celular,
        String codigo, String correo, String estadoRegistro,
        Date fechaCreacion, Date fechaModificacion, String genero,
        Long identificacion, String nombre, String password, Programa programa,
        List<PruebaUsuario> pruebaUsuarios, TipoUsuario tipoUsuario,
        Long usuCreador, Long usuModificador) {
        this.usuaId = usuaId;
        this.programa = programa;
        this.tipoUsuario = tipoUsuario;
        this.apellido = apellido;
        this.celular = celular;
        this.codigo = codigo;
        this.correo = correo;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.genero = genero;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.password = password;
        this.usuCreador = usuCreador;
        this.usuModificador = usuModificador;
        this.pruebaUsuarios = pruebaUsuarios;
    }

    @Id
    @Column(name = "usua_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getUsuaId() {
        return this.usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prog_id")
    public Programa getPrograma() {
        return this.programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tius_id")
    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Column(name = "apellido", nullable = false)
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "celular")
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Column(name = "codigo", nullable = false)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "correo", nullable = false)
    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Column(name = "genero", nullable = false)
    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Column(name = "identificacion", nullable = false)
    public Long getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public List<PruebaUsuario> getPruebaUsuarios() {
        return this.pruebaUsuarios;
    }

    public void setPruebaUsuarios(List<PruebaUsuario> pruebaUsuarios) {
        this.pruebaUsuarios = pruebaUsuarios;
    }
}
