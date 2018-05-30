package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaModuloDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ProgramaSimulacroView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ProgramaSimulacroView.class);

	private DualListModel<Usuario> usuarios;

	private Date fechaInicial;
	private Date fechaFinal;
	private Date tiempo;

	private CommandButton btnSave;

	private List<Prueba> data;
	private List<Pregunta> lasPreguntas;

	private Prueba entity;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public ProgramaSimulacroView() {
		super();
	}

	public String action_create() {
		try {
			Usuario usuario = VariablesSession.usuario;

			if (usuario != null) {

				if (fechaFinal == null || fechaInicial == null || tiempo == null)
					throw new Exception("Los campos no pueden estar vacios");
				if (fechaFinal.getTime() < fechaInicial.getTime())
					throw new Exception("La fechas no son permitidas");
				if (tiempo.getHours() < 1)
					throw new Exception("El tiempo mínimo es de 1 hora");
				if (usuarios.getTarget().size() == 0)
					throw new Exception("Debe selecionar al menos un estudiante");

				entity = new Prueba();
				entity.setActivo(Constantes.ESTADO_ACTIVO);
				entity.setFechaCreacion(new Date());
				entity.setFechaInicial(fechaInicial);
				entity.setFechaFinal(fechaFinal);
				entity.setTipoPrueba(businessDelegatorView.getTipoPrueba(Constantes.PRUEBA_TYPE_SIMULACRO));
				entity.setUsuCreador(usuario.getIdUsuario());

				long duracion = (tiempo.getHours() * 60 * 60) + (tiempo.getMinutes() * 60);

				entity.setTiempo(duracion);

				businessDelegatorView.savePrueba(entity);

				Object[] variableModulo = { "tipoModulo.idTipoModulo", true, Constantes.MODULO_TYPE_GENERICO, "=",
						"activo", true, Constantes.ESTADO_ACTIVO, "=" };

				lasPreguntas = new ArrayList<>();

				List<Modulo> listModulo = businessDelegatorView.findByCriteriaInModulo(variableModulo, null, null);
				listModulo
						.addAll(businessDelegatorView.findByProgramaModulo(VariablesSession.programa.getIdPrograma()));

				for (Modulo modulo : listModulo) {

					PruebaModulo pruebaModulo = new PruebaModulo();

					pruebaModulo.setActivo(Constantes.ESTADO_ACTIVO);
					pruebaModulo.setFechaCreacion(new Date());
					pruebaModulo.setModulo(modulo);
					pruebaModulo.setNumeroPreguntas(modulo.getCantidadPreguntas());
					pruebaModulo.setPrueba(entity);
					pruebaModulo.setUsuCreador(usuario.getIdUsuario());

					businessDelegatorView.savePruebaModulo(pruebaModulo);

					List<Pregunta> listPregunta = businessDelegatorView.findByRandomPregunta(modulo.getIdModulo(),
							modulo.getCantidadPreguntas());

					for (Pregunta pregunta : listPregunta) {

						PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = new PruebaProgramaUsuarioPregunta();
						pruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
						pruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
						pruebaProgramaUsuarioPregunta.setPregunta(pregunta);
						pruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());

						businessDelegatorView.savePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
						lasPreguntas.add(pregunta);

					}

				}

				List<Usuario> usuarioTarget = usuarios.getTarget();

				for (Usuario estudiantes : usuarioTarget) {
					Object[] variableUsuario = { "usuario.idUsuario", true, estudiantes.getIdUsuario(), "=", "activo",
							true, Constantes.ESTADO_ACTIVO, "=" };

					PruebaProgramaUsuario pruebaProgramaUsuario = new PruebaProgramaUsuario();
					ProgramaUsuario programaUsuario = businessDelegatorView
							.findByCriteriaInProgramaUsuario(variableUsuario, null, null).get(0);

					pruebaProgramaUsuario.setActivo(Constantes.ESTADO_ACTIVO);
					pruebaProgramaUsuario
							.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_PENDIENTE));
					pruebaProgramaUsuario.setFechaCreacion(new Date());
					pruebaProgramaUsuario.setProgramaUsuario(programaUsuario);
					pruebaProgramaUsuario.setPrueba(entity);
					pruebaProgramaUsuario.setUsuCreador(usuario.getIdUsuario());

					businessDelegatorView.savePruebaProgramaUsuario(pruebaProgramaUsuario);

					for (Pregunta pregunta : lasPreguntas) {

						PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = new PruebaProgramaUsuarioPregunta();
						pruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
						pruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
						pruebaProgramaUsuarioPregunta.setPregunta(pregunta);
						pruebaProgramaUsuarioPregunta.setPruebaProgramaUsuario(pruebaProgramaUsuario);
						pruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());

						businessDelegatorView.savePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);

						RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta = new RespuestaPruebaProgramaUsuarioPregunta();
						respuestaPruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
						respuestaPruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
						respuestaPruebaProgramaUsuarioPregunta.setPorcentajeAsignado(0L);
						respuestaPruebaProgramaUsuarioPregunta
								.setPruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
						respuestaPruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());

						businessDelegatorView
								.saveRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
					}

				}
				
				data = null;
				FacesUtils.addInfoMessage("Simulacro creado exitosamente");
			}

		} catch (

		Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			log.error("Erro de crear usuario en " + e.getMessage(), e);
		}

		return "";

	}

	public List<Prueba> getData() {
		try {
			if (data == null) {
				Object[] variable = { "usuCreador", true, VariablesSession.usuario.getIdUsuario(), "=",
						"tipoPrueba.idTipoPrueba", true, Constantes.PRUEBA_TYPE_SIMULACRO, "=" };
				data = businessDelegatorView.findByCriteriaInPrueba(variable, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<Prueba> prueba) {
		this.data = prueba;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public DualListModel<Usuario> getUsuarios() {
		if (usuarios == null) {
			try {

				List<Usuario> usuarioSource = businessDelegatorView.findByTipoUsuarioProgramaUsuario(
						VariablesSession.programa.getIdPrograma(), Constantes.USER_TYPE_ESTUDIANTE);
				List<Usuario> usuarioTarget = new ArrayList<>();

				for (Usuario usuario : usuarioSource) {
					log.info(usuario.toString());
				}

				usuarios = new DualListModel<Usuario>(usuarioSource, usuarioTarget);

			} catch (Exception e) {
				log.error("error de " + e.getMessage(), e);
			}
		}
		return usuarios;
	}

	public void setUsuarios(DualListModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getTiempo() {
		return tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

}
