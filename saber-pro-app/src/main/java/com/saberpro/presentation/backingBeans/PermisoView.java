package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PermisoDTO;

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
public class PermisoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PermisoView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdGrupoOpcion_GrupoOpcion;
    private InputText txtIdTipoUsuario_TipoUsuario;
    private InputText txtIdPermiso;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PermisoDTO> data;
    private PermisoDTO selectedPermiso;
    private Permiso entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PermisoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPermiso = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPermiso = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtIdGrupoOpcion_GrupoOpcion != null) {
            txtIdGrupoOpcion_GrupoOpcion.setValue(null);
            txtIdGrupoOpcion_GrupoOpcion.setDisabled(true);
        }

        if (txtIdTipoUsuario_TipoUsuario != null) {
            txtIdTipoUsuario_TipoUsuario.setValue(null);
            txtIdTipoUsuario_TipoUsuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdPermiso != null) {
            txtIdPermiso.setValue(null);
            txtIdPermiso.setDisabled(false);
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
            Long idPermiso = FacesUtils.checkLong(txtIdPermiso);
            entity = (idPermiso != null)
                ? businessDelegatorView.getPermiso(idPermiso) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdGrupoOpcion_GrupoOpcion.setDisabled(false);
            txtIdTipoUsuario_TipoUsuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPermiso.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdGrupoOpcion_GrupoOpcion.setValue(entity.getGrupoOpcion()
                                                        .getIdGrupoOpcion());
            txtIdGrupoOpcion_GrupoOpcion.setDisabled(false);
            txtIdTipoUsuario_TipoUsuario.setValue(entity.getTipoUsuario()
                                                        .getIdTipoUsuario());
            txtIdTipoUsuario_TipoUsuario.setDisabled(false);
            txtIdPermiso.setValue(entity.getIdPermiso());
            txtIdPermiso.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPermiso = (PermisoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedPermiso"));
        txtActivo.setValue(selectedPermiso.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedPermiso.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedPermiso.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedPermiso.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPermiso.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdGrupoOpcion_GrupoOpcion.setValue(selectedPermiso.getIdGrupoOpcion_GrupoOpcion());
        txtIdGrupoOpcion_GrupoOpcion.setDisabled(false);
        txtIdTipoUsuario_TipoUsuario.setValue(selectedPermiso.getIdTipoUsuario_TipoUsuario());
        txtIdTipoUsuario_TipoUsuario.setDisabled(false);
        txtIdPermiso.setValue(selectedPermiso.getIdPermiso());
        txtIdPermiso.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPermiso == null) && (entity == null)) {
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
            entity = new Permiso();

            Long idPermiso = FacesUtils.checkLong(txtIdPermiso);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPermiso(idPermiso);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setGrupoOpcion((FacesUtils.checkLong(
                    txtIdGrupoOpcion_GrupoOpcion) != null)
                ? businessDelegatorView.getGrupoOpcion(FacesUtils.checkLong(
                        txtIdGrupoOpcion_GrupoOpcion)) : null);
            entity.setTipoUsuario((FacesUtils.checkLong(
                    txtIdTipoUsuario_TipoUsuario) != null)
                ? businessDelegatorView.getTipoUsuario(FacesUtils.checkLong(
                        txtIdTipoUsuario_TipoUsuario)) : null);
            businessDelegatorView.savePermiso(entity);
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
                Long idPermiso = new Long(selectedPermiso.getIdPermiso());
                entity = businessDelegatorView.getPermiso(idPermiso);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setGrupoOpcion((FacesUtils.checkLong(
                    txtIdGrupoOpcion_GrupoOpcion) != null)
                ? businessDelegatorView.getGrupoOpcion(FacesUtils.checkLong(
                        txtIdGrupoOpcion_GrupoOpcion)) : null);
            entity.setTipoUsuario((FacesUtils.checkLong(
                    txtIdTipoUsuario_TipoUsuario) != null)
                ? businessDelegatorView.getTipoUsuario(FacesUtils.checkLong(
                        txtIdTipoUsuario_TipoUsuario)) : null);
            businessDelegatorView.updatePermiso(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPermiso = (PermisoDTO) (evt.getComponent().getAttributes()
                                               .get("selectedPermiso"));

            Long idPermiso = new Long(selectedPermiso.getIdPermiso());
            entity = businessDelegatorView.getPermiso(idPermiso);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPermiso = FacesUtils.checkLong(txtIdPermiso);
            entity = businessDelegatorView.getPermiso(idPermiso);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePermiso(entity);
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

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, Long idPermiso, Long usuCreador,
        Long usuModificador, Long idGrupoOpcion_GrupoOpcion,
        Long idTipoUsuario_TipoUsuario) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePermiso(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PermisoView").requestRender();
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

    public InputText getTxtIdGrupoOpcion_GrupoOpcion() {
        return txtIdGrupoOpcion_GrupoOpcion;
    }

    public void setTxtIdGrupoOpcion_GrupoOpcion(
        InputText txtIdGrupoOpcion_GrupoOpcion) {
        this.txtIdGrupoOpcion_GrupoOpcion = txtIdGrupoOpcion_GrupoOpcion;
    }

    public InputText getTxtIdTipoUsuario_TipoUsuario() {
        return txtIdTipoUsuario_TipoUsuario;
    }

    public void setTxtIdTipoUsuario_TipoUsuario(
        InputText txtIdTipoUsuario_TipoUsuario) {
        this.txtIdTipoUsuario_TipoUsuario = txtIdTipoUsuario_TipoUsuario;
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

    public InputText getTxtIdPermiso() {
        return txtIdPermiso;
    }

    public void setTxtIdPermiso(InputText txtIdPermiso) {
        this.txtIdPermiso = txtIdPermiso;
    }

    public List<PermisoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPermiso();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PermisoDTO> permisoDTO) {
        this.data = permisoDTO;
    }

    public PermisoDTO getSelectedPermiso() {
        return selectedPermiso;
    }

    public void setSelectedPermiso(PermisoDTO permiso) {
        this.selectedPermiso = permiso;
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
