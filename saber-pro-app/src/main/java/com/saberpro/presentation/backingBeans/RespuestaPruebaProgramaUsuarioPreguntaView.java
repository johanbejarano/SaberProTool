package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.RespuestaPruebaProgramaUsuarioPreguntaDTO;

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
public class RespuestaPruebaProgramaUsuarioPreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaPruebaProgramaUsuarioPreguntaView.class);
    private InputText txtActivo;
    private InputText txtPorcentajeAsignado;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta;
    private InputText txtIdRespuesta_Respuesta;
    private InputText txtIdRespuestaPruebaProgramaUsuarioPregunta;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RespuestaPruebaProgramaUsuarioPreguntaDTO> data;
    private RespuestaPruebaProgramaUsuarioPreguntaDTO selectedRespuestaPruebaProgramaUsuarioPregunta;
    private RespuestaPruebaProgramaUsuarioPregunta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RespuestaPruebaProgramaUsuarioPreguntaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedRespuestaPruebaProgramaUsuarioPregunta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRespuestaPruebaProgramaUsuarioPregunta = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtPorcentajeAsignado != null) {
            txtPorcentajeAsignado.setValue(null);
            txtPorcentajeAsignado.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta != null) {
            txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setValue(null);
            txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setDisabled(true);
        }

        if (txtIdRespuesta_Respuesta != null) {
            txtIdRespuesta_Respuesta.setValue(null);
            txtIdRespuesta_Respuesta.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdRespuestaPruebaProgramaUsuarioPregunta != null) {
            txtIdRespuestaPruebaProgramaUsuarioPregunta.setValue(null);
            txtIdRespuestaPruebaProgramaUsuarioPregunta.setDisabled(false);
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
            Long idRespuestaPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdRespuestaPruebaProgramaUsuarioPregunta);
            entity = (idRespuestaPruebaProgramaUsuarioPregunta != null)
                ? businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtPorcentajeAsignado.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setDisabled(false);
            txtIdRespuesta_Respuesta.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdRespuestaPruebaProgramaUsuarioPregunta.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtPorcentajeAsignado.setValue(entity.getPorcentajeAsignado());
            txtPorcentajeAsignado.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setValue(entity.getPruebaProgramaUsuarioPregunta()
                                                                                            .getIdPruebaProgramaUsuarioPregunta());
            txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setDisabled(false);
            txtIdRespuesta_Respuesta.setValue(entity.getRespuesta()
                                                    .getIdRespuesta());
            txtIdRespuesta_Respuesta.setDisabled(false);
            txtIdRespuestaPruebaProgramaUsuarioPregunta.setValue(entity.getIdRespuestaPruebaProgramaUsuarioPregunta());
            txtIdRespuestaPruebaProgramaUsuarioPregunta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRespuestaPruebaProgramaUsuarioPregunta = (RespuestaPruebaProgramaUsuarioPreguntaDTO) (evt.getComponent()
                                                                                                         .getAttributes()
                                                                                                         .get("selectedRespuestaPruebaProgramaUsuarioPregunta"));
        txtActivo.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtPorcentajeAsignado.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getPorcentajeAsignado());
        txtPorcentajeAsignado.setDisabled(false);
        txtUsuCreador.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta());
        txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta.setDisabled(false);
        txtIdRespuesta_Respuesta.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getIdRespuesta_Respuesta());
        txtIdRespuesta_Respuesta.setDisabled(false);
        txtIdRespuestaPruebaProgramaUsuarioPregunta.setValue(selectedRespuestaPruebaProgramaUsuarioPregunta.getIdRespuestaPruebaProgramaUsuarioPregunta());
        txtIdRespuestaPruebaProgramaUsuarioPregunta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRespuestaPruebaProgramaUsuarioPregunta == null) &&
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
            entity = new RespuestaPruebaProgramaUsuarioPregunta();

            Long idRespuestaPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdRespuestaPruebaProgramaUsuarioPregunta);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);
            entity.setPorcentajeAsignado(FacesUtils.checkLong(
                    txtPorcentajeAsignado));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPruebaProgramaUsuarioPregunta((FacesUtils.checkLong(
                    txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta) != null)
                ? businessDelegatorView.getPruebaProgramaUsuarioPregunta(
                    FacesUtils.checkLong(
                        txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta))
                : null);
            entity.setRespuesta((FacesUtils.checkLong(txtIdRespuesta_Respuesta) != null)
                ? businessDelegatorView.getRespuesta(FacesUtils.checkLong(
                        txtIdRespuesta_Respuesta)) : null);
            businessDelegatorView.saveRespuestaPruebaProgramaUsuarioPregunta(entity);
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
                Long idRespuestaPruebaProgramaUsuarioPregunta = new Long(selectedRespuestaPruebaProgramaUsuarioPregunta.getIdRespuestaPruebaProgramaUsuarioPregunta());
                entity = businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setPorcentajeAsignado(FacesUtils.checkLong(
                    txtPorcentajeAsignado));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPruebaProgramaUsuarioPregunta((FacesUtils.checkLong(
                    txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta) != null)
                ? businessDelegatorView.getPruebaProgramaUsuarioPregunta(
                    FacesUtils.checkLong(
                        txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta))
                : null);
            entity.setRespuesta((FacesUtils.checkLong(txtIdRespuesta_Respuesta) != null)
                ? businessDelegatorView.getRespuesta(FacesUtils.checkLong(
                        txtIdRespuesta_Respuesta)) : null);
            businessDelegatorView.updateRespuestaPruebaProgramaUsuarioPregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRespuestaPruebaProgramaUsuarioPregunta = (RespuestaPruebaProgramaUsuarioPreguntaDTO) (evt.getComponent()
                                                                                                             .getAttributes()
                                                                                                             .get("selectedRespuestaPruebaProgramaUsuarioPregunta"));

            Long idRespuestaPruebaProgramaUsuarioPregunta = new Long(selectedRespuestaPruebaProgramaUsuarioPregunta.getIdRespuestaPruebaProgramaUsuarioPregunta());
            entity = businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idRespuestaPruebaProgramaUsuarioPregunta = FacesUtils.checkLong(txtIdRespuestaPruebaProgramaUsuarioPregunta);
            entity = businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuestaPruebaProgramaUsuarioPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRespuestaPruebaProgramaUsuarioPregunta(entity);
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
        Date fechaModificacion, Long idRespuestaPruebaProgramaUsuarioPregunta,
        Long porcentajeAsignado, Long usuCreador, Long usuModificador,
        Long idPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta,
        Long idRespuesta_Respuesta) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setPorcentajeAsignado(FacesUtils.checkLong(
                    porcentajeAsignado));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateRespuestaPruebaProgramaUsuarioPregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RespuestaPruebaProgramaUsuarioPreguntaView").requestRender();
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

    public InputText getTxtPorcentajeAsignado() {
        return txtPorcentajeAsignado;
    }

    public void setTxtPorcentajeAsignado(InputText txtPorcentajeAsignado) {
        this.txtPorcentajeAsignado = txtPorcentajeAsignado;
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

    public InputText getTxtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta() {
        return txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta;
    }

    public void setTxtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta(
        InputText txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta) {
        this.txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta = txtIdPruebaProgramaUsuarioPregunta_PruebaProgramaUsuarioPregunta;
    }

    public InputText getTxtIdRespuesta_Respuesta() {
        return txtIdRespuesta_Respuesta;
    }

    public void setTxtIdRespuesta_Respuesta(InputText txtIdRespuesta_Respuesta) {
        this.txtIdRespuesta_Respuesta = txtIdRespuesta_Respuesta;
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

    public InputText getTxtIdRespuestaPruebaProgramaUsuarioPregunta() {
        return txtIdRespuestaPruebaProgramaUsuarioPregunta;
    }

    public void setTxtIdRespuestaPruebaProgramaUsuarioPregunta(
        InputText txtIdRespuestaPruebaProgramaUsuarioPregunta) {
        this.txtIdRespuestaPruebaProgramaUsuarioPregunta = txtIdRespuestaPruebaProgramaUsuarioPregunta;
    }

    public List<RespuestaPruebaProgramaUsuarioPreguntaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRespuestaPruebaProgramaUsuarioPregunta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<RespuestaPruebaProgramaUsuarioPreguntaDTO> respuestaPruebaProgramaUsuarioPreguntaDTO) {
        this.data = respuestaPruebaProgramaUsuarioPreguntaDTO;
    }

    public RespuestaPruebaProgramaUsuarioPreguntaDTO getSelectedRespuestaPruebaProgramaUsuarioPregunta() {
        return selectedRespuestaPruebaProgramaUsuarioPregunta;
    }

    public void setSelectedRespuestaPruebaProgramaUsuarioPregunta(
        RespuestaPruebaProgramaUsuarioPreguntaDTO respuestaPruebaProgramaUsuarioPregunta) {
        this.selectedRespuestaPruebaProgramaUsuarioPregunta = respuestaPruebaProgramaUsuarioPregunta;
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
