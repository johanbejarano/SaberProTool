package com.vortexbird.sapiens.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioDTO.class);
    private String apellido;
    private String celular;
    private String codigo;
    private String correo;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String genero;
    private Long identificacion;
    private String nombre;
    private String password;
    private Long usuCreador;
    private Long usuModificador;
    private Integer usuaId;
    private Integer progId_Programa;
    private Integer tiusId_TipoUsuario;
    
    private Integer facuId;
    private String nombreFacultad;
    private Integer progId;
    private String nombrePrograma;
    
    private String passwordNueva;
    
    public UsuarioDTO() {
		super();
	}
    
	public UsuarioDTO(String apellido, String celular, String codigo, String correo, String estadoRegistro,
			Date fechaCreacion, Date fechaModificacion, String genero, Long identificacion, String nombre,
			String password, Long usuCreador, Long usuModificador, Integer usuaId, Integer progId_Programa,
			Integer tiusId_TipoUsuario, Integer facuId, String nombreFacultad, Integer progId, String nombrePrograma) {
		super();
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
		this.usuaId = usuaId;
		this.progId_Programa = progId_Programa;
		this.tiusId_TipoUsuario = tiusId_TipoUsuario;
		this.facuId = facuId;
		this.nombreFacultad = nombreFacultad;
		this.progId = progId;
		this.nombrePrograma = nombrePrograma;
	}
	
	public UsuarioDTO(Integer usuaId) {
		super();
		this.usuaId = usuaId;
	}

	
	public UsuarioDTO(Integer usuaId, String codigo, Long identificacion, String nombre, String apellido, String correo, Integer tiusId_TipoUsuario) {
		super();
		
		this.apellido = apellido;
		this.codigo = codigo;
		this.correo = correo;
		this.identificacion = identificacion;
		this.nombre = nombre;
        this.usuaId = usuaId;
        this.tiusId_TipoUsuario = tiusId_TipoUsuario;
	}

	public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUsuCreador() {
        return usuCreador;
    }

    public void setUsuCreador(Long usuCreador) {
        this.usuCreador = usuCreador;
    }

    public Long getUsuModificador() {
        return usuModificador;
    }

    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Integer getProgId_Programa() {
        return progId_Programa;
    }

    public void setProgId_Programa(Integer progId_Programa) {
        this.progId_Programa = progId_Programa;
    }

    public Integer getTiusId_TipoUsuario() {
        return tiusId_TipoUsuario;
    }

    public void setTiusId_TipoUsuario(Integer tiusId_TipoUsuario) {
        this.tiusId_TipoUsuario = tiusId_TipoUsuario;
    }
    
    public Integer getFacuId() {
		return facuId;
	}

	public void setFacuId(Integer facuId) {
		this.facuId = facuId;
	}

	public String getNombreFacultad() {
		return nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}

	public Integer getProgId() {
		return progId;
	}

	public void setProgId(Integer progId) {
		this.progId = progId;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	@Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());

            return super.toString();
        }
    }

	public String getPasswordNueva() {
		return passwordNueva;
	}

	public void setPasswordNueva(String passwordNueva) {
		this.passwordNueva = passwordNueva;
	}
}
