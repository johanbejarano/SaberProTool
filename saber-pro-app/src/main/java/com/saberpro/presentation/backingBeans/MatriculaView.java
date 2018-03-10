package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.MatriculaDTO;

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
public class MatriculaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MatriculaView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPruebaReal_PruebaReal;
    private InputText txtIdUsuario_Usuario;
    private InputText txtIdMatricula;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MatriculaDTO> data;
    private MatriculaDTO selectedMatricula;
    private Matricula entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MatriculaView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedMatricula = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMatricula = null;

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

        if (txtIdPruebaReal_PruebaReal != null) {
            txtIdPruebaReal_PruebaReal.setValue(null);
            txtIdPruebaReal_PruebaReal.setDisabled(true);
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

        if (txtIdMatricula != null) {
            txtIdMatricula.setValue(null);
            txtIdMatricula.setDisabled(false);
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
            Long idMatricula = FacesUtils.checkLong(txtIdMatricula);
            entity = (idMatricula != null)
                ? businessDelegatorView.getMatricula(idMatricula) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPruebaReal_PruebaReal.setDisabled(false);
            txtIdUsuario_Usuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdMatricula.setDisabled(false);
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
            txtIdPruebaReal_PruebaReal.setValue(entity.getPruebaReal()
                                                      .getIdPruebaReal());
            txtIdPruebaReal_PruebaReal.setDisabled(false);
            txtIdUsuario_Usuario.setValue(entity.getUsuario().getIdUsuario());
            txtIdUsuario_Usuario.setDisabled(false);
            txtIdMatricula.setValue(entity.getIdMatricula());
            txtIdMatricula.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMatricula = (MatriculaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedMatricula"));
        txtActivo.setValue(selectedMatricula.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedMatricula.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedMatricula.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedMatricula.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedMatricula.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPruebaReal_PruebaReal.setValue(selectedMatricula.getIdPruebaReal_PruebaReal());
        txtIdPruebaReal_PruebaReal.setDisabled(false);
        txtIdUsuario_Usuario.setValue(selectedMatricula.getIdUsuario_Usuario());
        txtIdUsuario_Usuario.setDisabled(false);
        txtIdMatricula.setValue(selectedMatricula.getIdMatricula());
        txtIdMatricula.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMatricula == null) && (entity == null)) {
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
            entity = new Matricula();

            Long idMatricula = FacesUtils.checkLong(txtIdMatricula);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdMatricula(idMatricula);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPruebaReal((FacesUtils.checkLong(
                    txtIdPruebaReal_PruebaReal) != null)
                ? businessDelegatorView.getPruebaReal(FacesUtils.checkLong(
                        txtIdPruebaReal_PruebaReal)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtIdUsuario_Usuario)) : null);
            businessDelegatorView.saveMatricula(entity);
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
                Long idMatricula = new Long(selectedMatricula.getIdMatricula());
                entity = businessDelegatorView.getMatricula(idMatricula);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPruebaReal((FacesUtils.checkLong(
                    txtIdPruebaReal_PruebaReal) != null)
                ? businessDelegatorView.getPruebaReal(FacesUtils.checkLong(
                        txtIdPruebaReal_PruebaReal)) : null);
            entity.setUsuario((FacesUtils.checkLong(txtIdUsuario_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtIdUsuario_Usuario)) : null);
            businessDelegatorView.updateMatricula(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMatricula = (MatriculaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedMatricula"));

            Long idMatricula = new Long(selectedMatricula.getIdMatricula());
            entity = businessDelegatorView.getMatricula(idMatricula);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMatricula = FacesUtils.checkLong(txtIdMatricula);
            entity = businessDelegatorView.getMatricula(idMatricula);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMatricula(entity);
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
        Date fechaModificacion, Long idMatricula, Long usuCreador,
        Long usuModificador, Long idPruebaReal_PruebaReal,
        Long idUsuario_Usuario) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateMatricula(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MatriculaView").requestRender();
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

    public InputText getTxtIdPruebaReal_PruebaReal() {
        return txtIdPruebaReal_PruebaReal;
    }

    public void setTxtIdPruebaReal_PruebaReal(
        InputText txtIdPruebaReal_PruebaReal) {
        this.txtIdPruebaReal_PruebaReal = txtIdPruebaReal_PruebaReal;
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

    public InputText getTxtIdMatricula() {
        return txtIdMatricula;
    }

    public void setTxtIdMatricula(InputText txtIdMatricula) {
        this.txtIdMatricula = txtIdMatricula;
    }

    public List<MatriculaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMatricula();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MatriculaDTO> matriculaDTO) {
        this.data = matriculaDTO;
    }

    public MatriculaDTO getSelectedMatricula() {
        return selectedMatricula;
    }

    public void setSelectedMatricula(MatriculaDTO matricula) {
        this.selectedMatricula = matricula;
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
