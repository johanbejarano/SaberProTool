package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PreguntaDTO;

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
public class PreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PreguntaView.class);
    private InputText txtActivo;
    private InputText txtDescripcionPregunta;
    private InputText txtRetroalimentacion;
    private InputText txtRutaImagen;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdModulo_Modulo;
    private InputText txtIdTipoPregunta_TipoPregunta;
    private InputText txtIdPregunta;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PreguntaDTO> data;
    private PreguntaDTO selectedPregunta;
    private Pregunta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PreguntaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPregunta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPregunta = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtDescripcionPregunta != null) {
            txtDescripcionPregunta.setValue(null);
            txtDescripcionPregunta.setDisabled(true);
        }

        if (txtRetroalimentacion != null) {
            txtRetroalimentacion.setValue(null);
            txtRetroalimentacion.setDisabled(true);
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

        if (txtIdModulo_Modulo != null) {
            txtIdModulo_Modulo.setValue(null);
            txtIdModulo_Modulo.setDisabled(true);
        }

        if (txtIdTipoPregunta_TipoPregunta != null) {
            txtIdTipoPregunta_TipoPregunta.setValue(null);
            txtIdTipoPregunta_TipoPregunta.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdPregunta != null) {
            txtIdPregunta.setValue(null);
            txtIdPregunta.setDisabled(false);
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
            Long idPregunta = FacesUtils.checkLong(txtIdPregunta);
            entity = (idPregunta != null)
                ? businessDelegatorView.getPregunta(idPregunta) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcionPregunta.setDisabled(false);
            txtRetroalimentacion.setDisabled(false);
            txtRutaImagen.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdModulo_Modulo.setDisabled(false);
            txtIdTipoPregunta_TipoPregunta.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPregunta.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcionPregunta.setValue(entity.getDescripcionPregunta());
            txtDescripcionPregunta.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtRetroalimentacion.setValue(entity.getRetroalimentacion());
            txtRetroalimentacion.setDisabled(false);
            txtRutaImagen.setValue(entity.getRutaImagen());
            txtRutaImagen.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdModulo_Modulo.setValue(entity.getModulo().getIdModulo());
            txtIdModulo_Modulo.setDisabled(false);
            txtIdTipoPregunta_TipoPregunta.setValue(entity.getTipoPregunta()
                                                          .getIdTipoPregunta());
            txtIdTipoPregunta_TipoPregunta.setDisabled(false);
            txtIdPregunta.setValue(entity.getIdPregunta());
            txtIdPregunta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPregunta"));
        txtActivo.setValue(selectedPregunta.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcionPregunta.setValue(selectedPregunta.getDescripcionPregunta());
        txtDescripcionPregunta.setDisabled(false);
        txtFechaCreacion.setValue(selectedPregunta.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedPregunta.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtRetroalimentacion.setValue(selectedPregunta.getRetroalimentacion());
        txtRetroalimentacion.setDisabled(false);
        txtRutaImagen.setValue(selectedPregunta.getRutaImagen());
        txtRutaImagen.setDisabled(false);
        txtUsuCreador.setValue(selectedPregunta.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPregunta.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdModulo_Modulo.setValue(selectedPregunta.getIdModulo_Modulo());
        txtIdModulo_Modulo.setDisabled(false);
        txtIdTipoPregunta_TipoPregunta.setValue(selectedPregunta.getIdTipoPregunta_TipoPregunta());
        txtIdTipoPregunta_TipoPregunta.setDisabled(false);
        txtIdPregunta.setValue(selectedPregunta.getIdPregunta());
        txtIdPregunta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPregunta == null) && (entity == null)) {
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
            entity = new Pregunta();

            Long idPregunta = FacesUtils.checkLong(txtIdPregunta);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcionPregunta(FacesUtils.checkString(
                    txtDescripcionPregunta));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPregunta(idPregunta);
            entity.setRetroalimentacion(FacesUtils.checkString(
                    txtRetroalimentacion));
            entity.setRutaImagen(FacesUtils.checkString(txtRutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setTipoPregunta((FacesUtils.checkLong(
                    txtIdTipoPregunta_TipoPregunta) != null)
                ? businessDelegatorView.getTipoPregunta(FacesUtils.checkLong(
                        txtIdTipoPregunta_TipoPregunta)) : null);
            businessDelegatorView.savePregunta(entity);
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
                Long idPregunta = new Long(selectedPregunta.getIdPregunta());
                entity = businessDelegatorView.getPregunta(idPregunta);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcionPregunta(FacesUtils.checkString(
                    txtDescripcionPregunta));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setRetroalimentacion(FacesUtils.checkString(
                    txtRetroalimentacion));
            entity.setRutaImagen(FacesUtils.checkString(txtRutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setTipoPregunta((FacesUtils.checkLong(
                    txtIdTipoPregunta_TipoPregunta) != null)
                ? businessDelegatorView.getTipoPregunta(FacesUtils.checkLong(
                        txtIdTipoPregunta_TipoPregunta)) : null);
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPregunta = (PreguntaDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedPregunta"));

            Long idPregunta = new Long(selectedPregunta.getIdPregunta());
            entity = businessDelegatorView.getPregunta(idPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPregunta = FacesUtils.checkLong(txtIdPregunta);
            entity = businessDelegatorView.getPregunta(idPregunta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePregunta(entity);
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
        String descripcionPregunta, Date fechaCreacion, Date fechaModificacion,
        Long idPregunta, String retroalimentacion, String rutaImagen,
        Long usuCreador, Long usuModificador, Long idModulo_Modulo,
        Long idTipoPregunta_TipoPregunta) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcionPregunta(FacesUtils.checkString(
                    descripcionPregunta));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setRetroalimentacion(FacesUtils.checkString(
                    retroalimentacion));
            entity.setRutaImagen(FacesUtils.checkString(rutaImagen));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePregunta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PreguntaView").requestRender();
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

    public InputText getTxtDescripcionPregunta() {
        return txtDescripcionPregunta;
    }

    public void setTxtDescripcionPregunta(InputText txtDescripcionPregunta) {
        this.txtDescripcionPregunta = txtDescripcionPregunta;
    }

    public InputText getTxtRetroalimentacion() {
        return txtRetroalimentacion;
    }

    public void setTxtRetroalimentacion(InputText txtRetroalimentacion) {
        this.txtRetroalimentacion = txtRetroalimentacion;
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

    public InputText getTxtIdModulo_Modulo() {
        return txtIdModulo_Modulo;
    }

    public void setTxtIdModulo_Modulo(InputText txtIdModulo_Modulo) {
        this.txtIdModulo_Modulo = txtIdModulo_Modulo;
    }

    public InputText getTxtIdTipoPregunta_TipoPregunta() {
        return txtIdTipoPregunta_TipoPregunta;
    }

    public void setTxtIdTipoPregunta_TipoPregunta(
        InputText txtIdTipoPregunta_TipoPregunta) {
        this.txtIdTipoPregunta_TipoPregunta = txtIdTipoPregunta_TipoPregunta;
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

    public InputText getTxtIdPregunta() {
        return txtIdPregunta;
    }

    public void setTxtIdPregunta(InputText txtIdPregunta) {
        this.txtIdPregunta = txtIdPregunta;
    }

    public List<PreguntaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPregunta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PreguntaDTO> preguntaDTO) {
        this.data = preguntaDTO;
    }

    public PreguntaDTO getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(PreguntaDTO pregunta) {
        this.selectedPregunta = pregunta;
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
