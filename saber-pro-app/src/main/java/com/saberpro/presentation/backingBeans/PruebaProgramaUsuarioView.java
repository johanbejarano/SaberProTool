package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioDTO;

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
public class PruebaProgramaUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaProgramaUsuarioView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdEstadoPrueba_EstadoPrueba;
    private InputText txtIdProgramaUsuario_ProgramaUsuario;
    private InputText txtIdPrueba_Prueba;
    private InputText txtIdPruebaProgramaUsuario;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PruebaProgramaUsuarioDTO> data;
    private PruebaProgramaUsuarioDTO selectedPruebaProgramaUsuario;
    private PruebaProgramaUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PruebaProgramaUsuarioView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPruebaProgramaUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPruebaProgramaUsuario = null;

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

        if (txtIdEstadoPrueba_EstadoPrueba != null) {
            txtIdEstadoPrueba_EstadoPrueba.setValue(null);
            txtIdEstadoPrueba_EstadoPrueba.setDisabled(true);
        }

        if (txtIdProgramaUsuario_ProgramaUsuario != null) {
            txtIdProgramaUsuario_ProgramaUsuario.setValue(null);
            txtIdProgramaUsuario_ProgramaUsuario.setDisabled(true);
        }

        if (txtIdPrueba_Prueba != null) {
            txtIdPrueba_Prueba.setValue(null);
            txtIdPrueba_Prueba.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdPruebaProgramaUsuario != null) {
            txtIdPruebaProgramaUsuario.setValue(null);
            txtIdPruebaProgramaUsuario.setDisabled(false);
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
            Long idPruebaProgramaUsuario = FacesUtils.checkLong(txtIdPruebaProgramaUsuario);
            entity = (idPruebaProgramaUsuario != null)
                ? businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdEstadoPrueba_EstadoPrueba.setDisabled(false);
            txtIdProgramaUsuario_ProgramaUsuario.setDisabled(false);
            txtIdPrueba_Prueba.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPruebaProgramaUsuario.setDisabled(false);
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
            txtIdEstadoPrueba_EstadoPrueba.setValue(entity.getEstadoPrueba()
                                                          .getIdEstadoPrueba());
            txtIdEstadoPrueba_EstadoPrueba.setDisabled(false);
            txtIdProgramaUsuario_ProgramaUsuario.setValue(entity.getProgramaUsuario()
                                                                .getIdProgramaUsuario());
            txtIdProgramaUsuario_ProgramaUsuario.setDisabled(false);
            txtIdPrueba_Prueba.setValue(entity.getPrueba().getIdPrueba());
            txtIdPrueba_Prueba.setDisabled(false);
            txtIdPruebaProgramaUsuario.setValue(entity.getIdPruebaProgramaUsuario());
            txtIdPruebaProgramaUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPruebaProgramaUsuario = (PruebaProgramaUsuarioDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedPruebaProgramaUsuario"));
        txtActivo.setValue(selectedPruebaProgramaUsuario.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedPruebaProgramaUsuario.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedPruebaProgramaUsuario.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedPruebaProgramaUsuario.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPruebaProgramaUsuario.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdEstadoPrueba_EstadoPrueba.setValue(selectedPruebaProgramaUsuario.getIdEstadoPrueba_EstadoPrueba());
        txtIdEstadoPrueba_EstadoPrueba.setDisabled(false);
        txtIdProgramaUsuario_ProgramaUsuario.setValue(selectedPruebaProgramaUsuario.getIdProgramaUsuario_ProgramaUsuario());
        txtIdProgramaUsuario_ProgramaUsuario.setDisabled(false);
        txtIdPrueba_Prueba.setValue(selectedPruebaProgramaUsuario.getIdPrueba_Prueba());
        txtIdPrueba_Prueba.setDisabled(false);
        txtIdPruebaProgramaUsuario.setValue(selectedPruebaProgramaUsuario.getIdPruebaProgramaUsuario());
        txtIdPruebaProgramaUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPruebaProgramaUsuario == null) && (entity == null)) {
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
            entity = new PruebaProgramaUsuario();

            Long idPruebaProgramaUsuario = FacesUtils.checkLong(txtIdPruebaProgramaUsuario);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPruebaProgramaUsuario(idPruebaProgramaUsuario);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setEstadoPrueba((FacesUtils.checkLong(
                    txtIdEstadoPrueba_EstadoPrueba) != null)
                ? businessDelegatorView.getEstadoPrueba(FacesUtils.checkLong(
                        txtIdEstadoPrueba_EstadoPrueba)) : null);
            entity.setProgramaUsuario((FacesUtils.checkLong(
                    txtIdProgramaUsuario_ProgramaUsuario) != null)
                ? businessDelegatorView.getProgramaUsuario(FacesUtils.checkLong(
                        txtIdProgramaUsuario_ProgramaUsuario)) : null);
            entity.setPrueba((FacesUtils.checkLong(txtIdPrueba_Prueba) != null)
                ? businessDelegatorView.getPrueba(FacesUtils.checkLong(
                        txtIdPrueba_Prueba)) : null);
            businessDelegatorView.savePruebaProgramaUsuario(entity);
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
                Long idPruebaProgramaUsuario = new Long(selectedPruebaProgramaUsuario.getIdPruebaProgramaUsuario());
                entity = businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setEstadoPrueba((FacesUtils.checkLong(
                    txtIdEstadoPrueba_EstadoPrueba) != null)
                ? businessDelegatorView.getEstadoPrueba(FacesUtils.checkLong(
                        txtIdEstadoPrueba_EstadoPrueba)) : null);
            entity.setProgramaUsuario((FacesUtils.checkLong(
                    txtIdProgramaUsuario_ProgramaUsuario) != null)
                ? businessDelegatorView.getProgramaUsuario(FacesUtils.checkLong(
                        txtIdProgramaUsuario_ProgramaUsuario)) : null);
            entity.setPrueba((FacesUtils.checkLong(txtIdPrueba_Prueba) != null)
                ? businessDelegatorView.getPrueba(FacesUtils.checkLong(
                        txtIdPrueba_Prueba)) : null);
            businessDelegatorView.updatePruebaProgramaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPruebaProgramaUsuario = (PruebaProgramaUsuarioDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedPruebaProgramaUsuario"));

            Long idPruebaProgramaUsuario = new Long(selectedPruebaProgramaUsuario.getIdPruebaProgramaUsuario());
            entity = businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPruebaProgramaUsuario = FacesUtils.checkLong(txtIdPruebaProgramaUsuario);
            entity = businessDelegatorView.getPruebaProgramaUsuario(idPruebaProgramaUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePruebaProgramaUsuario(entity);
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
        Date fechaModificacion, Long idPruebaProgramaUsuario, Long usuCreador,
        Long usuModificador, Long idEstadoPrueba_EstadoPrueba,
        Long idProgramaUsuario_ProgramaUsuario, Long idPrueba_Prueba)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePruebaProgramaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PruebaProgramaUsuarioView").requestRender();
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

    public InputText getTxtIdEstadoPrueba_EstadoPrueba() {
        return txtIdEstadoPrueba_EstadoPrueba;
    }

    public void setTxtIdEstadoPrueba_EstadoPrueba(
        InputText txtIdEstadoPrueba_EstadoPrueba) {
        this.txtIdEstadoPrueba_EstadoPrueba = txtIdEstadoPrueba_EstadoPrueba;
    }

    public InputText getTxtIdProgramaUsuario_ProgramaUsuario() {
        return txtIdProgramaUsuario_ProgramaUsuario;
    }

    public void setTxtIdProgramaUsuario_ProgramaUsuario(
        InputText txtIdProgramaUsuario_ProgramaUsuario) {
        this.txtIdProgramaUsuario_ProgramaUsuario = txtIdProgramaUsuario_ProgramaUsuario;
    }

    public InputText getTxtIdPrueba_Prueba() {
        return txtIdPrueba_Prueba;
    }

    public void setTxtIdPrueba_Prueba(InputText txtIdPrueba_Prueba) {
        this.txtIdPrueba_Prueba = txtIdPrueba_Prueba;
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

    public InputText getTxtIdPruebaProgramaUsuario() {
        return txtIdPruebaProgramaUsuario;
    }

    public void setTxtIdPruebaProgramaUsuario(
        InputText txtIdPruebaProgramaUsuario) {
        this.txtIdPruebaProgramaUsuario = txtIdPruebaProgramaUsuario;
    }

    public List<PruebaProgramaUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPruebaProgramaUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PruebaProgramaUsuarioDTO> pruebaProgramaUsuarioDTO) {
        this.data = pruebaProgramaUsuarioDTO;
    }

    public PruebaProgramaUsuarioDTO getSelectedPruebaProgramaUsuario() {
        return selectedPruebaProgramaUsuario;
    }

    public void setSelectedPruebaProgramaUsuario(
        PruebaProgramaUsuarioDTO pruebaProgramaUsuario) {
        this.selectedPruebaProgramaUsuario = pruebaProgramaUsuario;
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
