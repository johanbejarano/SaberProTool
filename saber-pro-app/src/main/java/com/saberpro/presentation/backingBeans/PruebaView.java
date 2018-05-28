package com.saberpro.presentation.backingBeans;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaDTO;
import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
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
import javax.faces.model.SelectItem;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PruebaView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PruebaView.class);

	private CommandButton btnGenerar;

	private List<PruebaProgramaUsuario> data;

	private PruebaDTO selectedPrueba;

	private Prueba entity;

	private boolean showDialog;

	private PruebaProgramaUsuario pruebaProgramaUsuario;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PruebaView() {
		super();
	}

	/* Controles de la vista */

	public void verPrueba(long idPruebaUsuarioPrograma) {
		try {
			PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(idPruebaUsuarioPrograma);
			EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(pruebaProgramaUsuario.getEstadoPrueba().getIdEstadoPrueba());
			Prueba prueba = businessDelegatorView.getPrueba(pruebaProgramaUsuario.getPrueba().getIdPrueba());
			
			if (estadoPrueba.getIdEstadoPrueba() == Constantes.PRUEBA_ESTADO_INICIADO || estadoPrueba.getIdEstadoPrueba() == Constantes.PRUEBA_ESTADO_PENDIENTE) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("respuestaPruebaProgramaUsuarioPregunta.xhtml?id=" + idPruebaUsuarioPrograma);
			} else if (estadoPrueba.getIdEstadoPrueba() == Constantes.PRUEBA_ESTADO_FINALIZADA) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("pruebaResultado.xhtml?id=" + idPruebaUsuarioPrograma);
			} 

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public StreamedContent getReportePDF() {
		try {

			// TODO: Ojo quemado el 35

			Object[] variables = { "idPrueba", false, pruebaProgramaUsuario.getPrueba().getIdPrueba(), "=" };
			List<Prueba> pruebas = businessDelegatorView.findByCriteriaInPrueba(variables, null, null);

			if (pruebas == null || pruebas.size() == 0) {
				throw new Exception("No existe la prueba " + pruebaProgramaUsuario.getPrueba().getIdPrueba());
			}

			ByteArrayInputStream bais = businessDelegatorView.generarInformeIndividual(pruebas.get(0).getIdPrueba());
			StreamedContent archivo = new DefaultStreamedContent(bais, "application/pdf",
					"prueba-" + pruebas.get(0).getIdPrueba() + ".pdf");

			return archivo;

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el reporte");
			return null;
		}
	}

	public void action_seleccionarPrueba(PruebaProgramaUsuario pruebaProgramaUsuario) {
		this.pruebaProgramaUsuario = pruebaProgramaUsuario;
	}

	public void createEntrenamiento() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("pruebaModulo.xhtml");
			
		} catch (Exception e) {
			log.error("Error de " + e.getMessage(), e);
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	

	public int numeroModulos(long idPrueba) {
		try {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public String getTipoPrueba(long idPrueba) {
		try {
			Prueba prueba = businessDelegatorView.getPrueba(idPrueba);
			TipoPrueba tipoPrueba = businessDelegatorView.getTipoPrueba(prueba.getTipoPrueba().getIdTipoPrueba());

			return tipoPrueba.getNombre();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	public String getEstadoPrueba(long idEstadoPrueba) {
		try {
			EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(idEstadoPrueba);
			return estadoPrueba.getNombre();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	/* Getter and Setter */
	public List<PruebaProgramaUsuario> getData() {
		try {
			if (data == null) {
				Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
				if (usuario != null) {
					Object[] variable = { "usuario.idUsuario", true, usuario.getIdUsuario(), "=", "activo", true,
							Constantes.ESTADO_ACTIVO, "=" };
					ProgramaUsuario programaUsuario = businessDelegatorView
							.findByCriteriaInProgramaUsuario(variable, null, null).get(0);
					Object[] variable2 = { "programaUsuario.idProgramaUsuario", true,
							programaUsuario.getIdProgramaUsuario(), "=", "activo", true, Constantes.ESTADO_ACTIVO,
							"=" };
					data = businessDelegatorView.findByCriteriaInPruebaProgramaUsuario(variable2, null, null);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PruebaProgramaUsuario> pruebaDTO) {
		this.data = pruebaDTO;
	}

	public PruebaDTO getSelectedPrueba() {
		return selectedPrueba;
	}

	public void setSelectedPrueba(PruebaDTO prueba) {
		this.selectedPrueba = prueba;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public CommandButton getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(CommandButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}
}
