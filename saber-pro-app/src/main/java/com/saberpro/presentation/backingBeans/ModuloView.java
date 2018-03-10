package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ModuloDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ModuloView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ModuloView.class);
    private InputText txtActivo;
    private InputText txtCantidadPreguntas;
    private InputText txtDescripcion;
    private InputText txtNombre;
    private InputText txtPrioridad;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdTipoModulo_TipoModulo;
    private InputText txtIdModulo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ModuloDTO> data;
    private ModuloDTO selectedModulo;
    private Modulo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ModuloView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedModulo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedModulo = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtCantidadPreguntas != null) {
            txtCantidadPreguntas.setValue(null);
            txtCantidadPreguntas.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtPrioridad != null) {
            txtPrioridad.setValue(null);
            txtPrioridad.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtIdTipoModulo_TipoModulo != null) {
            txtIdTipoModulo_TipoModulo.setValue(null);
            txtIdTipoModulo_TipoModulo.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdModulo != null) {
            txtIdModulo.setValue(null);
            txtIdModulo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaModificacion() {
        Date inputDate = (Date) txtFechaModificacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idModulo = FacesUtils.checkLong(txtIdModulo);
            entity = (idModulo != null)
                ? businessDelegatorView.getModulo(idModulo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtCantidadPreguntas.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtPrioridad.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdTipoModulo_TipoModulo.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdModulo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtCantidadPreguntas.setValue(entity.getCantidadPreguntas());
            txtCantidadPreguntas.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtPrioridad.setValue(entity.getPrioridad());
            txtPrioridad.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdTipoModulo_TipoModulo.setValue(entity.getTipoModulo()
                                                      .getIdTipoModulo());
            txtIdTipoModulo_TipoModulo.setDisabled(false);
            txtIdModulo.setValue(entity.getIdModulo());
            txtIdModulo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedModulo = (ModuloDTO) (evt.getComponent().getAttributes()
                                         .get("selectedModulo"));
        txtActivo.setValue(selectedModulo.getActivo());
        txtActivo.setDisabled(false);
        txtCantidadPreguntas.setValue(selectedModulo.getCantidadPreguntas());
        txtCantidadPreguntas.setDisabled(false);
        txtDescripcion.setValue(selectedModulo.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFechaCreacion.setValue(selectedModulo.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedModulo.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedModulo.getNombre());
        txtNombre.setDisabled(false);
        txtPrioridad.setValue(selectedModulo.getPrioridad());
        txtPrioridad.setDisabled(false);
        txtUsuCreador.setValue(selectedModulo.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedModulo.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdTipoModulo_TipoModulo.setValue(selectedModulo.getIdTipoModulo_TipoModulo());
        txtIdTipoModulo_TipoModulo.setDisabled(false);
        txtIdModulo.setValue(selectedModulo.getIdModulo());
        txtIdModulo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedModulo == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Modulo();

            Long idModulo = FacesUtils.checkLong(txtIdModulo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setCantidadPreguntas(FacesUtils.checkLong(
                    txtCantidadPreguntas));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdModulo(idModulo);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrioridad(FacesUtils.checkLong(txtPrioridad));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setTipoModulo((FacesUtils.checkLong(
                    txtIdTipoModulo_TipoModulo) != null)
                ? businessDelegatorView.getTipoModulo(FacesUtils.checkLong(
                        txtIdTipoModulo_TipoModulo)) : null);
            businessDelegatorView.saveModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long idModulo = new Long(selectedModulo.getIdModulo());
                entity = businessDelegatorView.getModulo(idModulo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setCantidadPreguntas(FacesUtils.checkLong(
                    txtCantidadPreguntas));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrioridad(FacesUtils.checkLong(txtPrioridad));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setTipoModulo((FacesUtils.checkLong(
                    txtIdTipoModulo_TipoModulo) != null)
                ? businessDelegatorView.getTipoModulo(FacesUtils.checkLong(
                        txtIdTipoModulo_TipoModulo)) : null);
            businessDelegatorView.updateModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedModulo = (ModuloDTO) (evt.getComponent().getAttributes()
                                             .get("selectedModulo"));

            Long idModulo = new Long(selectedModulo.getIdModulo());
            entity = businessDelegatorView.getModulo(idModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idModulo = FacesUtils.checkLong(txtIdModulo);
            entity = businessDelegatorView.getModulo(idModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(String activo, Long cantidadPreguntas,
        String descripcion, Date fechaCreacion, Date fechaModificacion,
        Long idModulo, String nombre, Long prioridad, Long usuCreador,
        Long usuModificador, Long idTipoModulo_TipoModulo)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setCantidadPreguntas(FacesUtils.checkLong(cantidadPreguntas));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPrioridad(FacesUtils.checkLong(prioridad));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ModuloView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActivo() {
        return txtActivo;
    }

    public void setTxtActivo(InputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public InputText getTxtCantidadPreguntas() {
        return txtCantidadPreguntas;
    }

    public void setTxtCantidadPreguntas(InputText txtCantidadPreguntas) {
        this.txtCantidadPreguntas = txtCantidadPreguntas;
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtPrioridad() {
        return txtPrioridad;
    }

    public void setTxtPrioridad(InputText txtPrioridad) {
        this.txtPrioridad = txtPrioridad;
    }

    public InputText getTxtUsuCreador() {
        return txtUsuCreador;
    }

    public void setTxtUsuCreador(InputText txtUsuCreador) {
        this.txtUsuCreador = txtUsuCreador;
    }

    public InputText getTxtUsuModificador() {
        return txtUsuModificador;
    }

    public void setTxtUsuModificador(InputText txtUsuModificador) {
        this.txtUsuModificador = txtUsuModificador;
    }

    public InputText getTxtIdTipoModulo_TipoModulo() {
        return txtIdTipoModulo_TipoModulo;
    }

    public void setTxtIdTipoModulo_TipoModulo(
        InputText txtIdTipoModulo_TipoModulo) {
        this.txtIdTipoModulo_TipoModulo = txtIdTipoModulo_TipoModulo;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaModificacion() {
        return txtFechaModificacion;
    }

    public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
        this.txtFechaModificacion = txtFechaModificacion;
    }

    public InputText getTxtIdModulo() {
        return txtIdModulo;
    }

    public void setTxtIdModulo(InputText txtIdModulo) {
        this.txtIdModulo = txtIdModulo;
    }

    public List<ModuloDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataModulo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ModuloDTO> moduloDTO) {
        this.data = moduloDTO;
    }

    public ModuloDTO getSelectedModulo() {
        return selectedModulo;
    }

    public void setSelectedModulo(ModuloDTO modulo) {
        this.selectedModulo = modulo;
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

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
