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
@Table(name = "pregunta", schema = "public")
public class Pregunta implements java.io.Serializable {
//    @NotNull
    private Integer pregId;
    @NotNull
    private Modulo modulo;
    @NotNull
    private TipoPregunta tipoPregunta;
    private Contexto contexto;
    @NotNull
    @NotEmpty
    @Size(max = 25500)
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
    @Size(max = 25500)
    private String retroalimentacion;
    private Long valorPregunta;
    @NotNull
    private Long complejidad;
    @NotNull
    private Usuario usuario;
    private Long usuModificador;
    private List<DetallePruebaUsuario> detallePruebaUsuarios = new ArrayList<DetallePruebaUsuario>(0);
    private List<Respuesta> respuestas = new ArrayList<Respuesta>(0);

    public Pregunta() {
    }

    public Pregunta(Integer pregId, String descripcion,
        List<DetallePruebaUsuario> detallePruebaUsuarios,
        String estadoRegistro, Date fechaCreacion, Date fechaModificacion,
        Modulo modulo, List<Respuesta> respuestas, String retroalimentacion,
        TipoPregunta tipoPregunta, Usuario usuario, Long usuModificador) {
        this.pregId = pregId;
        this.modulo = modulo;
        this.tipoPregunta = tipoPregunta;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.retroalimentacion = retroalimentacion;
        this.usuario = usuario;
        this.usuModificador = usuModificador;
        this.detallePruebaUsuarios = detallePruebaUsuarios;
        this.respuestas = respuestas;
    }

    @Id
    @Column(name = "preg_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getPregId() {
        return this.pregId;
    }

    public void setPregId(Integer pregId) {
        this.pregId = pregId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modu_id")
    public Modulo getModulo() {
        return this.modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tprg_id")
    public TipoPregunta getTipoPregunta() {
        return this.tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cont_id")
    public Contexto getContexto() {
        return this.contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    @Column(name = "descripcion", nullable = false)
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

    @Column(name = "retroalimentacion", nullable = false)
    public String getRetroalimentacion() {
        return this.retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    @Column(name = "complejidad", nullable = false)
    public Long getComplejidad() {
        return this.complejidad;
    }

    public void setComplejidad(Long complejidad) {
        this.complejidad = complejidad;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_creador")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "usu_modificador")
    public Long getUsuModificador() {
        return this.usuModificador;
    }

    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pregunta")
    public List<DetallePruebaUsuario> getDetallePruebaUsuarios() {
        return this.detallePruebaUsuarios;
    }

    public void setDetallePruebaUsuarios(
        List<DetallePruebaUsuario> detallePruebaUsuarios) {
        this.detallePruebaUsuarios = detallePruebaUsuarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pregunta")
    public List<Respuesta> getRespuestas() {
        return this.respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

	public Long getValorPregunta() {
		return valorPregunta;
	}

	@Column(name = "valor_pregunta")
	public void setValorPregunta(Long valorPregunta) {
		this.valorPregunta = valorPregunta;
	}
}
