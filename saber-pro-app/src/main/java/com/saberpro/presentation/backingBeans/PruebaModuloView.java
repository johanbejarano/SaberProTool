package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaModuloDTO;

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
public class PruebaModuloView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaModuloView.class);
    private InputText txtActivo;
    private InputText txtNumeroPreguntas;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdModulo_Modulo;
    private InputText txtIdPrueba_Prueba;
    private InputText txtIdPruebaModulo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PruebaModuloDTO> data;
    private PruebaModuloDTO selectedPruebaModulo;
    private PruebaModulo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PruebaModuloView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPruebaModulo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPruebaModulo = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtNumeroPreguntas != null) {
            txtNumeroPreguntas.setValue(null);
            txtNumeroPreguntas.setDisabled(true);
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

        if (txtIdPruebaModulo != null) {
            txtIdPruebaModulo.setValue(null);
            txtIdPruebaModulo.setDisabled(false);
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
            Long idPruebaModulo = FacesUtils.checkLong(txtIdPruebaModulo);
            entity = (idPruebaModulo != null)
                ? businessDelegatorView.getPruebaModulo(idPruebaModulo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtNumeroPreguntas.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdModulo_Modulo.setDisabled(false);
            txtIdPrueba_Prueba.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPruebaModulo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtNumeroPreguntas.setValue(entity.getNumeroPreguntas());
            txtNumeroPreguntas.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdModulo_Modulo.setValue(entity.getModulo().getIdModulo());
            txtIdModulo_Modulo.setDisabled(false);
            txtIdPrueba_Prueba.setValue(entity.getPrueba().getIdPrueba());
            txtIdPrueba_Prueba.setDisabled(false);
            txtIdPruebaModulo.setValue(entity.getIdPruebaModulo());
            txtIdPruebaModulo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPruebaModulo = (PruebaModuloDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedPruebaModulo"));
        txtActivo.setValue(selectedPruebaModulo.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedPruebaModulo.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedPruebaModulo.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNumeroPreguntas.setValue(selectedPruebaModulo.getNumeroPreguntas());
        txtNumeroPreguntas.setDisabled(false);
        txtUsuCreador.setValue(selectedPruebaModulo.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPruebaModulo.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdModulo_Modulo.setValue(selectedPruebaModulo.getIdModulo_Modulo());
        txtIdModulo_Modulo.setDisabled(false);
        txtIdPrueba_Prueba.setValue(selectedPruebaModulo.getIdPrueba_Prueba());
        txtIdPrueba_Prueba.setDisabled(false);
        txtIdPruebaModulo.setValue(selectedPruebaModulo.getIdPruebaModulo());
        txtIdPruebaModulo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPruebaModulo == null) && (entity == null)) {
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
            entity = new PruebaModulo();

            Long idPruebaModulo = FacesUtils.checkLong(txtIdPruebaModulo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPruebaModulo(idPruebaModulo);
            entity.setNumeroPreguntas(FacesUtils.checkLong(txtNumeroPreguntas));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setPrueba((FacesUtils.checkLong(txtIdPrueba_Prueba) != null)
                ? businessDelegatorView.getPrueba(FacesUtils.checkLong(
                        txtIdPrueba_Prueba)) : null);
            businessDelegatorView.savePruebaModulo(entity);
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
                Long idPruebaModulo = new Long(selectedPruebaModulo.getIdPruebaModulo());
                entity = businessDelegatorView.getPruebaModulo(idPruebaModulo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNumeroPreguntas(FacesUtils.checkLong(txtNumeroPreguntas));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setPrueba((FacesUtils.checkLong(txtIdPrueba_Prueba) != null)
                ? businessDelegatorView.getPrueba(FacesUtils.checkLong(
                        txtIdPrueba_Prueba)) : null);
            businessDelegatorView.updatePruebaModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPruebaModulo = (PruebaModuloDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedPruebaModulo"));

            Long idPruebaModulo = new Long(selectedPruebaModulo.getIdPruebaModulo());
            entity = businessDelegatorView.getPruebaModulo(idPruebaModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPruebaModulo = FacesUtils.checkLong(txtIdPruebaModulo);
            entity = businessDelegatorView.getPruebaModulo(idPruebaModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePruebaModulo(entity);
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
        Date fechaModificacion, Long idPruebaModulo, Long numeroPreguntas,
        Long usuCreador, Long usuModificador, Long idModulo_Modulo,
        Long idPrueba_Prueba) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNumeroPreguntas(FacesUtils.checkLong(numeroPreguntas));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePruebaModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PruebaModuloView").requestRender();
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

    public InputText getTxtNumeroPreguntas() {
        return txtNumeroPreguntas;
    }

    public void setTxtNumeroPreguntas(InputText txtNumeroPreguntas) {
        this.txtNumeroPreguntas = txtNumeroPreguntas;
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

    public InputText getTxtIdPruebaModulo() {
        return txtIdPruebaModulo;
    }

    public void setTxtIdPruebaModulo(InputText txtIdPruebaModulo) {
        this.txtIdPruebaModulo = txtIdPruebaModulo;
    }

    public List<PruebaModuloDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPruebaModulo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PruebaModuloDTO> pruebaModuloDTO) {
        this.data = pruebaModuloDTO;
    }

    public PruebaModuloDTO getSelectedPruebaModulo() {
        return selectedPruebaModulo;
    }

    public void setSelectedPruebaModulo(PruebaModuloDTO pruebaModulo) {
        this.selectedPruebaModulo = pruebaModulo;
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
