package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.FacultadDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;

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
public class FacultadView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(FacultadView.class);

	private InputTextarea txtDescripcion;
	private InputText txtNombre;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnClear;
	private SelectOneMenu somActivo;
	private List<FacultadDTO> data;
	private FacultadDTO selectedFacultad;
	private Facultad entity;
	private boolean showDialog;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public FacultadView() {
		super();
	}

	public void listener_txtId() {
		try {
			String nombreFacultad = txtNombre.getValue().toString().trim();

			entity = businessDelegatorView.findByNombreFacultad(nombreFacultad);

		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {

			txtDescripcion.setDisabled(false);
			somActivo.setDisabled(false);
			btnSave.setDisabled(false);
			btnModify.setDisabled(true);

		} else {

			txtNombre.setValue(entity.getNombre());
			txtDescripcion.setValue(entity.getDescripcion());
			somActivo.setValue(entity.getActivo());
			txtDescripcion.setDisabled(false);
			btnSave.setDisabled(true);
			btnModify.setDisabled(false);
		}
	}

	public String action_clear() {

		entity = null;
		selectedFacultad = null;

		btnSave.setDisabled(true);
		btnModify.setDisabled(true);
		txtDescripcion.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();

		return "";
	}

	public String action_create() {
		try {
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");

			if (usuario != null) {

				entity = new Facultad();

				entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
				entity.setNombre(FacesUtils.checkString(txtNombre).toUpperCase());
				entity.setActivo(FacesUtils.checkString(somActivo));
				entity.setFechaCreacion(new Date());
				entity.setUsuCreador(usuario.getIdUsuario());

				businessDelegatorView.saveFacultad(entity);

				data = null;

				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				action_clear();

			}

		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {

		try {
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
			String nombreFacultad = FacesUtils.checkString(txtNombre);

			if (usuario != null) {

				entity = businessDelegatorView.findByNombreFacultad(nombreFacultad);

				entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
				entity.setNombre(FacesUtils.checkString(txtNombre).toUpperCase());
				entity.setActivo(FacesUtils.checkString(somActivo));
				entity.setFechaModificacion(new Date());
				entity.setUsuModificador(usuario.getIdUsuario());

				businessDelegatorView.updateFacultad(entity);

				data = null;

				FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
				action_clear();

			}

		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}	

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteFacultad(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}	

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public List<FacultadDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataFacultad();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<FacultadDTO> facultadDTO) {
		this.data = facultadDTO;
	}

	public FacultadDTO getSelectedFacultad() {
		return selectedFacultad;
	}

	public void setSelectedFacultad(FacultadDTO facultad) {
		this.selectedFacultad = facultad;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
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

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

}
