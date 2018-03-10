package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.RespuestaDTO;

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
public class RespuestaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaView.class);
    private InputText txtActivo;
    private InputText txtDescripcionRespuesta;
    private InputText txtPorcentajeAcierto;
    private InputText txtRutaImagen;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPregunta_Pregunta;
    private InputText txtIdRespuesta;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RespuestaDTO> data;
    private RespuestaDTO selectedRespuesta;
    private Respuesta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RespuestaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedRespuesta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRespuesta = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtDescripcionRespuesta != null) {
            txtDescripcionRespuesta.setValue(null);
            txtDescripcionRespuesta.setDisabled(true);
        }

        if (txtPorcentajeAcierto != null) {
            txtPorcentajeAcierto.setValue(null);
            txtPorcentajeAcierto.setDisabled(true);
        }

        if (txtRutaImagen != null) {
            txtRutaImagen.setValue(null);
            txtRutaImagen.setDisabled(true);
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

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdRespuesta != null) {
            txtIdRespuesta.setValue(null);
            txtIdRespuesta.setDisabled(false);
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
            Long idRespuesta = FacesUtils.checkLong(txtIdRespuesta);
            entity = (idRespuesta != null)
                ? businessDelegatorView.getRespuesta(idRespuesta) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcionRespuesta.setDisabled(false);
            txtPorcentajeAcierto.setDisabled(false);
            txtRutaImagen.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPregunta_Pregunta.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdRespuesta.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcionRespuesta.setValue(entity.getDescripcionRespuesta());
            txtDescripcionRespuesta.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtPorcentajeAcierto.setValue(entity.getPorcentajeAcierto());
            txtPorcentajeAcierto.setDisabled(false);
            txtRutaImagen.setValue(entity.getRutaImagen());
            txtRutaImagen.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdPregunta_Pregunta.setValue(entity.getPregunta().getIdPregunta());
            txtIdPregunta_Pregunta.setDisabled(false);
            txtIdRespuesta.setValue(entity.getIdRespuesta());
            txtIdRespuesta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRespuesta = (RespuestaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedRespuesta"));
        txtActivo.setValue(selectedRespuesta.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcionRespuesta.setValue(selectedRespuesta.getDescripcionRespuesta());
        txtDescripcionRespuesta.setDisabled(false);
        txtFechaCreacion.setValue(selectedRespuesta.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedRespuesta.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtPorcentajeAcierto.setValue(selectedRespuesta.getPorcentajeAcierto());
        txtPorcentajeAcierto.setDisabled(false);
        txtRutaImagen.setValue(selectedRespuesta.getRutaImagen());
        txtRutaImagen.setDisabled(false);
        txtUsuCreador.setValue(selectedRespuesta.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedRespuesta.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPregunta_Pregunta.setValue(selectedRespuesta.getIdPregunta_Pregunta());
        txtIdPregunta_Pregunta.setDisabled(false);
        txtIdRespuesta.setValue(selectedRespuesta.getIdRespuesta());
        txtIdRespuesta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRespuesta == null) && (entity == null)) {
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
            entity = new Respuesta();

            Long idRespuesta = FacesUtils.checkLong(txtIdRespuesta);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    txtDescripcionRespuesta));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdRespuesta(idRespuesta);
            entity.setPorcentajeAcierto(FacesUtils.checkInteger(
                    txtPorcentajeAcierto));
            entity.setRutaImagen(FacesUtils.checkString(txtRutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            businessDelegatorView.saveRespuesta(entity);
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
                Long idRespuesta = new Long(selectedRespuesta.getIdRespuesta());
                entity = businessDelegatorView.getRespuesta(idRespuesta);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    txtDescripcionRespuesta));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setPorcentajeAcierto(FacesUtils.checkInteger(
                    txtPorcentajeAcierto));
            entity.setRutaImagen(FacesUtils.checkString(txtRutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            businessDelegatorView.updateRespuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRespuesta = (RespuestaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedRespuesta"));

            Long idRespuesta = new Long(selectedRespuesta.getIdRespuesta());
            entity = businessDelegatorView.getRespuesta(idRespuesta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idRespuesta = FacesUtils.checkLong(txtIdRespuesta);
            entity = businessDelegatorView.getRespuesta(idRespuesta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRespuesta(entity);
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

    public String action_modifyWitDTO(String activo,
        String descripcionRespuesta, Date fechaCreacion,
        Date fechaModificacion, Long idRespuesta, Integer porcentajeAcierto,
        String rutaImagen, Long usuCreador, Long usuModificador,
        Long idPregunta_Pregunta) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcionRespuesta(FacesUtils.checkString(
                    descripcionRespuesta));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setPorcentajeAcierto(FacesUtils.checkInteger(
                    porcentajeAcierto));
            entity.setRutaImagen(FacesUtils.checkString(rutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateRespuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RespuestaView").requestRender();
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

    public InputText getTxtDescripcionRespuesta() {
        return txtDescripcionRespuesta;
    }

    public void setTxtDescripcionRespuesta(InputText txtDescripcionRespuesta) {
        this.txtDescripcionRespuesta = txtDescripcionRespuesta;
    }

    public InputText getTxtPorcentajeAcierto() {
        return txtPorcentajeAcierto;
    }

    public void setTxtPorcentajeAcierto(InputText txtPorcentajeAcierto) {
        this.txtPorcentajeAcierto = txtPorcentajeAcierto;
    }

    public InputText getTxtRutaImagen() {
        return txtRutaImagen;
    }

    public void setTxtRutaImagen(InputText txtRutaImagen) {
        this.txtRutaImagen = txtRutaImagen;
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

    public InputText getTxtIdRespuesta() {
        return txtIdRespuesta;
    }

    public void setTxtIdRespuesta(InputText txtIdRespuesta) {
        this.txtIdRespuesta = txtIdRespuesta;
    }

    public List<RespuestaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRespuesta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RespuestaDTO> respuestaDTO) {
        this.data = respuestaDTO;
    }

    public RespuestaDTO getSelectedRespuesta() {
        return selectedRespuesta;
    }

    public void setSelectedRespuesta(RespuestaDTO respuesta) {
        this.selectedRespuesta = respuesta;
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
