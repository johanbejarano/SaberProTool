package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioPreguntaDTO;

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
public class PruebaProgramaUsuarioPreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaProgramaUsuarioPreguntaView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPregunta_Pregunta;
    private InputText txtIdPruebaProgramaUsuario_PruebaProgramaUsuario;
    private InputText txtIdPruebaProgramaUsuarioPregunta;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PruebaProgramaUsuarioPreguntaDTO> data;
    private PruebaProgramaUsuarioPreguntaDTO selectedPruebaProgramaUsuarioPregunta;
    private PruebaProgramaUsuarioPregunta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PruebaProgramaUsuarioPreguntaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPruebaProgramaUsuarioPregunta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPruebaProgramaUsuarioPregunta = null;

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

        if (txtIdPregunta_Pregunta != null) {
            txtIdPregunta_Pregunta.setValue(null);
            txtIdPregunta_Pregunta.setDisabled(true);
        }

        if (txtIdPruebaProgramaUsuario_PruebaProgramaUsuario != null) {
            txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setValue(null);
            txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdPruebaProgramaUsuarioPregunta != null) {
            txtIdPruebaProgramaUsuarioPregunta.setValue(null);
            txtIdPruebaProgramaUsuarioPregunta.setDisabled(false);
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
            Long idPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdPruebaProgramaUsuarioPregunta);
            entity = (idPruebaProgramaUsuarioPregunta != null)
                ? businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPregunta_Pregunta.setDisabled(false);
            txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPruebaProgramaUsuarioPregunta.setDisabled(false);
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
            txtIdPregunta_Pregunta.setValue(entity.getPregunta().getIdPregunta());
            txtIdPregunta_Pregunta.setDisabled(false);
            txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setValue(entity.getPruebaProgramaUsuario()
                                                                            .getIdPruebaProgramaUsuario());
            txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setDisabled(false);
            txtIdPruebaProgramaUsuarioPregunta.setValue(entity.getIdPruebaProgramaUsuarioPregunta());
            txtIdPruebaProgramaUsuarioPregunta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPruebaProgramaUsuarioPregunta = (PruebaProgramaUsuarioPreguntaDTO) (evt.getComponent()
                                                                                       .getAttributes()
                                                                                       .get("selectedPruebaProgramaUsuarioPregunta"));
        txtActivo.setValue(selectedPruebaProgramaUsuarioPregunta.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedPruebaProgramaUsuarioPregunta.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedPruebaProgramaUsuarioPregunta.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedPruebaProgramaUsuarioPregunta.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPruebaProgramaUsuarioPregunta.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPregunta_Pregunta.setValue(selectedPruebaProgramaUsuarioPregunta.getIdPregunta_Pregunta());
        txtIdPregunta_Pregunta.setDisabled(false);
        txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setValue(selectedPruebaProgramaUsuarioPregunta.getIdPruebaProgramaUsuario_PruebaProgramaUsuario());
        txtIdPruebaProgramaUsuario_PruebaProgramaUsuario.setDisabled(false);
        txtIdPruebaProgramaUsuarioPregunta.setValue(selectedPruebaProgramaUsuarioPregunta.getIdPruebaProgramaUsuarioPregunta());
        txtIdPruebaProgramaUsuarioPregunta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPruebaProgramaUsuarioPregunta == null) &&
                    (entity == null)) {
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
            entity = new PruebaProgramaUsuarioPregunta();

            Long idPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdPruebaProgramaUsuarioPregunta);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            entity.setPruebaProgramaUsuario((FacesUtils.checkLong(
                    txtIdPruebaProgramaUsuario_PruebaProgramaUsuario) != null)
                ? businessDelegatorView.getPruebaProgramaUsuario(
                    FacesUtils.checkLong(
                        txtIdPruebaProgramaUsuario_PruebaProgramaUsuario)) : null);
            businessDelegatorView.savePruebaProgramaUsuarioPregunta(entity);
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
                Long idPruebaProgramaUsuarioPregunta = new Long(selectedPruebaProgramaUsuarioPregunta.getIdPruebaProgramaUsuarioPregunta());
                entity = businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            entity.setPruebaProgramaUsuario((FacesUtils.checkLong(
                    txtIdPruebaProgramaUsuario_PruebaProgramaUsuario) != null)
                ? businessDelegatorView.getPruebaProgramaUsuario(
                    FacesUtils.checkLong(
                        txtIdPruebaProgramaUsuario_PruebaProgramaUsuario)) : null);
            businessDelegatorView.updatePruebaProgramaUsuarioPregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPruebaProgramaUsuarioPregunta = (PruebaProgramaUsuarioPreguntaDTO) (evt.getComponent()
                                                                                           .getAttributes()
                                                                                           .get("selectedPruebaProgramaUsuarioPregunta"));

            Long idPruebaProgramaUsuarioPregunta = new Long(selectedPruebaProgramaUsuarioPregunta.getIdPruebaProgramaUsuarioPregunta());
            entity = businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdPruebaProgramaUsuarioPregunta);
            entity = businessDelegatorView.getPruebaProgramaUsuarioPregunta(idPruebaProgramaUsuarioPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePruebaProgramaUsuarioPregunta(entity);
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
        Date fechaModificacion, Long idPruebaProgramaUsuarioPregunta,
        Long usuCreador, Long usuModificador, Long idPregunta_Pregunta,
        Long idPruebaProgramaUsuario_PruebaProgramaUsuario)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePruebaProgramaUsuarioPregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PruebaProgramaUsuarioPreguntaView").requestRender();
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

    public InputText getTxtIdPregunta_Pregunta() {
        return txtIdPregunta_Pregunta;
    }

    public void setTxtIdPregunta_Pregunta(InputText txtIdPregunta_Pregunta) {
        this.txtIdPregunta_Pregunta = txtIdPregunta_Pregunta;
    }

    public InputText getTxtIdPruebaProgramaUsuario_PruebaProgramaUsuario() {
        return txtIdPruebaProgramaUsuario_PruebaProgramaUsuario;
    }

    public void setTxtIdPruebaProgramaUsuario_PruebaProgramaUsuario(
        InputText txtIdPruebaProgramaUsuario_PruebaProgramaUsuario) {
        this.txtIdPruebaProgramaUsuario_PruebaProgramaUsuario = txtIdPruebaProgramaUsuario_PruebaProgramaUsuario;
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

    public InputText getTxtIdPruebaProgramaUsuarioPregunta() {
        return txtIdPruebaProgramaUsuarioPregunta;
    }

    public void setTxtIdPruebaProgramaUsuarioPregunta(
        InputText txtIdPruebaProgramaUsuarioPregunta) {
        this.txtIdPruebaProgramaUsuarioPregunta = txtIdPruebaProgramaUsuarioPregunta;
    }

    public List<PruebaProgramaUsuarioPreguntaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPruebaProgramaUsuarioPregunta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<PruebaProgramaUsuarioPreguntaDTO> pruebaProgramaUsuarioPreguntaDTO) {
        this.data = pruebaProgramaUsuarioPreguntaDTO;
    }

    public PruebaProgramaUsuarioPreguntaDTO getSelectedPruebaProgramaUsuarioPregunta() {
        return selectedPruebaProgramaUsuarioPregunta;
    }

    public void setSelectedPruebaProgramaUsuarioPregunta(
        PruebaProgramaUsuarioPreguntaDTO pruebaProgramaUsuarioPregunta) {
        this.selectedPruebaProgramaUsuarioPregunta = pruebaProgramaUsuarioPregunta;
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
