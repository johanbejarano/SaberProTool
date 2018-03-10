package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaUsuarioDTO;

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
public class ProgramaUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProgramaUsuarioView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPrograma_Programa;
    private InputText txtIdUsuario_Usuario;
    private InputText txtIdProgramaUsuario;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProgramaUsuarioDTO> data;
    private ProgramaUsuarioDTO selectedProgramaUsuario;
    private ProgramaUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProgramaUsuarioView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedProgramaUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProgramaUsuario = null;

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

        if (txtIdPrograma_Programa != null) {
            txtIdPrograma_Programa.setValue(null);
            txtIdPrograma_Programa.setDisabled(true);
        }

        if (txtIdUsuario_Usuario != null) {
            txtIdUsuario_Usuario.setValue(null);
            txtIdUsuario_Usuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdProgramaUsuario != null) {
            txtIdProgramaUsuario.setValue(null);
            txtIdProgramaUsuario.setDisabled(false);
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
            Long idProgramaUsuario = FacesUtils.checkLong(txtIdProgramaUsuario);
            entity = (idProgramaUsuario != null)
                ? businessDelegatorView.getProgramaUsuario(idProgramaUsuario)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPrograma_Programa.setDisabled(false);
            txtIdUsuario_Usuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdProgramaUsuario.setDisabled(false);
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
            txtIdPrograma_Programa.setValue(entity.getPrograma().getIdPrograma());
            txtIdPrograma_Programa.setDisabled(false);
            txtIdUsuario_Usuario.setValue(entity.getUsuario().getIdUsuario());
            txtIdUsuario_Usuario.setDisabled(false);
            txtIdProgramaUsuario.setValue(entity.getIdProgramaUsuario());
            txtIdProgramaUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProgramaUsuario = (ProgramaUsuarioDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedProgramaUsuario"));
        txtActivo.setValue(selectedProgramaUsuario.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedProgramaUsuario.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedProgramaUsuario.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedProgramaUsuario.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedProgramaUsuario.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPrograma_Programa.setValue(selectedProgramaUsuario.getIdPrograma_Programa());
        txtIdPrograma_Programa.setDisabled(false);
        txtIdUsuario_Usuario.setValue(selectedProgramaUsuario.getIdUsuario_Usuario());
        txtIdUsuario_Usuario.setDisabled(false);
        txtIdProgramaUsuario.setValue(selectedProgramaUsuario.getIdProgramaUsuario());
        txtIdProgramaUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProgramaUsuario == null) && (entity == null)) {
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
            entity = new ProgramaUsuario();

            Long idProgramaUsuario = FacesUtils.checkLong(txtIdProgramaUsuario);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdProgramaUsuario(idProgramaUsuario);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPrograma((FacesUtils.checkLong(txtIdPrograma_Programa) != null)
                ? businessDelegatorView.getPrograma(FacesUtils.checkLong(
                        txtIdPrograma_Programa)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtIdUsuario_Usuario)) : null);
            businessDelegatorView.saveProgramaUsuario(entity);
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
                Long idProgramaUsuario = new Long(selectedProgramaUsuario.getIdProgramaUsuario());
                entity = businessDelegatorView.getProgramaUsuario(idProgramaUsuario);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPrograma((FacesUtils.checkLong(txtIdPrograma_Programa) != null)
                ? businessDelegatorView.getPrograma(FacesUtils.checkLong(
                        txtIdPrograma_Programa)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtIdUsuario_Usuario)) : null);
            businessDelegatorView.updateProgramaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProgramaUsuario = (ProgramaUsuarioDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedProgramaUsuario"));

            Long idProgramaUsuario = new Long(selectedProgramaUsuario.getIdProgramaUsuario());
            entity = businessDelegatorView.getProgramaUsuario(idProgramaUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idProgramaUsuario = FacesUtils.checkLong(txtIdProgramaUsuario);
            entity = businessDelegatorView.getProgramaUsuario(idProgramaUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProgramaUsuario(entity);
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
        Date fechaModificacion, Long idProgramaUsuario, Long usuCreador,
        Long usuModificador, Long idPrograma_Programa, Long idUsuario_Usuario)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateProgramaUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProgramaUsuarioView").requestRender();
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

    public InputText getTxtIdPrograma_Programa() {
        return txtIdPrograma_Programa;
    }

    public void setTxtIdPrograma_Programa(InputText txtIdPrograma_Programa) {
        this.txtIdPrograma_Programa = txtIdPrograma_Programa;
    }

    public InputText getTxtIdUsuario_Usuario() {
        return txtIdUsuario_Usuario;
    }

    public void setTxtIdUsuario_Usuario(InputText txtIdUsuario_Usuario) {
        this.txtIdUsuario_Usuario = txtIdUsuario_Usuario;
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

    public InputText getTxtIdProgramaUsuario() {
        return txtIdProgramaUsuario;
    }

    public void setTxtIdProgramaUsuario(InputText txtIdProgramaUsuario) {
        this.txtIdProgramaUsuario = txtIdProgramaUsuario;
    }

    public List<ProgramaUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProgramaUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProgramaUsuarioDTO> programaUsuarioDTO) {
        this.data = programaUsuarioDTO;
    }

    public ProgramaUsuarioDTO getSelectedProgramaUsuario() {
        return selectedProgramaUsuario;
    }

    public void setSelectedProgramaUsuario(ProgramaUsuarioDTO programaUsuario) {
        this.selectedProgramaUsuario = programaUsuario;
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
