package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaDTO;

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
public class PruebaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PruebaView.class);
    private InputText txtActivo;
    private InputText txtTiempo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdTipoPrueba_TipoPrueba;
    private InputText txtIdPrueba;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaFinal;
    private Calendar txtFechaInicial;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PruebaDTO> data;
    private PruebaDTO selectedPrueba;
    private Prueba entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PruebaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPrueba = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPrueba = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtTiempo != null) {
            txtTiempo.setValue(null);
            txtTiempo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtIdTipoPrueba_TipoPrueba != null) {
            txtIdTipoPrueba_TipoPrueba.setValue(null);
            txtIdTipoPrueba_TipoPrueba.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaFinal != null) {
            txtFechaFinal.setValue(null);
            txtFechaFinal.setDisabled(true);
        }

        if (txtFechaInicial != null) {
            txtFechaInicial.setValue(null);
            txtFechaInicial.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdPrueba != null) {
            txtIdPrueba.setValue(null);
            txtIdPrueba.setDisabled(false);
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

    public void listener_txtFechaFinal() {
        Date inputDate = (Date) txtFechaFinal.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaInicial() {
        Date inputDate = (Date) txtFechaInicial.getValue();
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
            Long idPrueba = FacesUtils.checkLong(txtIdPrueba);
            entity = (idPrueba != null)
                ? businessDelegatorView.getPrueba(idPrueba) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtTiempo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdTipoPrueba_TipoPrueba.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaFinal.setDisabled(false);
            txtFechaInicial.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdPrueba.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaFinal.setValue(entity.getFechaFinal());
            txtFechaFinal.setDisabled(false);
            txtFechaInicial.setValue(entity.getFechaInicial());
            txtFechaInicial.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtTiempo.setValue(entity.getTiempo());
            txtTiempo.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdTipoPrueba_TipoPrueba.setValue(entity.getTipoPrueba()
                                                      .getIdTipoPrueba());
            txtIdTipoPrueba_TipoPrueba.setDisabled(false);
            txtIdPrueba.setValue(entity.getIdPrueba());
            txtIdPrueba.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPrueba = (PruebaDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPrueba"));
        txtActivo.setValue(selectedPrueba.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedPrueba.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaFinal.setValue(selectedPrueba.getFechaFinal());
        txtFechaFinal.setDisabled(false);
        txtFechaInicial.setValue(selectedPrueba.getFechaInicial());
        txtFechaInicial.setDisabled(false);
        txtFechaModificacion.setValue(selectedPrueba.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtTiempo.setValue(selectedPrueba.getTiempo());
        txtTiempo.setDisabled(false);
        txtUsuCreador.setValue(selectedPrueba.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedPrueba.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdTipoPrueba_TipoPrueba.setValue(selectedPrueba.getIdTipoPrueba_TipoPrueba());
        txtIdTipoPrueba_TipoPrueba.setDisabled(false);
        txtIdPrueba.setValue(selectedPrueba.getIdPrueba());
        txtIdPrueba.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPrueba == null) && (entity == null)) {
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
            entity = new Prueba();

            Long idPrueba = FacesUtils.checkLong(txtIdPrueba);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFinal(FacesUtils.checkDate(txtFechaFinal));
            entity.setFechaInicial(FacesUtils.checkDate(txtFechaInicial));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdPrueba(idPrueba);
            entity.setTiempo(FacesUtils.checkLong(txtTiempo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setTipoPrueba((FacesUtils.checkLong(
                    txtIdTipoPrueba_TipoPrueba) != null)
                ? businessDelegatorView.getTipoPrueba(FacesUtils.checkLong(
                        txtIdTipoPrueba_TipoPrueba)) : null);
            businessDelegatorView.savePrueba(entity);
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
                Long idPrueba = new Long(selectedPrueba.getIdPrueba());
                entity = businessDelegatorView.getPrueba(idPrueba);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFinal(FacesUtils.checkDate(txtFechaFinal));
            entity.setFechaInicial(FacesUtils.checkDate(txtFechaInicial));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setTiempo(FacesUtils.checkLong(txtTiempo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setTipoPrueba((FacesUtils.checkLong(
                    txtIdTipoPrueba_TipoPrueba) != null)
                ? businessDelegatorView.getTipoPrueba(FacesUtils.checkLong(
                        txtIdTipoPrueba_TipoPrueba)) : null);
            businessDelegatorView.updatePrueba(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPrueba = (PruebaDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPrueba"));

            Long idPrueba = new Long(selectedPrueba.getIdPrueba());
            entity = businessDelegatorView.getPrueba(idPrueba);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idPrueba = FacesUtils.checkLong(txtIdPrueba);
            entity = businessDelegatorView.getPrueba(idPrueba);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePrueba(entity);
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
        Date fechaFinal, Date fechaInicial, Date fechaModificacion,
        Long idPrueba, Long tiempo, Long usuCreador, Long usuModificador,
        Long idTipoPrueba_TipoPrueba) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaFinal(FacesUtils.checkDate(fechaFinal));
            entity.setFechaInicial(FacesUtils.checkDate(fechaInicial));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setTiempo(FacesUtils.checkLong(tiempo));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updatePrueba(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PruebaView").requestRender();
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

    public InputText getTxtTiempo() {
        return txtTiempo;
    }

    public void setTxtTiempo(InputText txtTiempo) {
        this.txtTiempo = txtTiempo;
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

    public InputText getTxtIdTipoPrueba_TipoPrueba() {
        return txtIdTipoPrueba_TipoPrueba;
    }

    public void setTxtIdTipoPrueba_TipoPrueba(
        InputText txtIdTipoPrueba_TipoPrueba) {
        this.txtIdTipoPrueba_TipoPrueba = txtIdTipoPrueba_TipoPrueba;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaFinal() {
        return txtFechaFinal;
    }

    public void setTxtFechaFinal(Calendar txtFechaFinal) {
        this.txtFechaFinal = txtFechaFinal;
    }

    public Calendar getTxtFechaInicial() {
        return txtFechaInicial;
    }

    public void setTxtFechaInicial(Calendar txtFechaInicial) {
        this.txtFechaInicial = txtFechaInicial;
    }

    public Calendar getTxtFechaModificacion() {
        return txtFechaModificacion;
    }

    public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
        this.txtFechaModificacion = txtFechaModificacion;
    }

    public InputText getTxtIdPrueba() {
        return txtIdPrueba;
    }

    public void setTxtIdPrueba(InputText txtIdPrueba) {
        this.txtIdPrueba = txtIdPrueba;
    }

    public List<PruebaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPrueba();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PruebaDTO> pruebaDTO) {
        this.data = pruebaDTO;
    }

    public PruebaDTO getSelectedPrueba() {
        return selectedPrueba;
    }

    public void setSelectedPrueba(PruebaDTO prueba) {
        this.selectedPrueba = prueba;
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
