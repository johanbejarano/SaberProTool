package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ResultadoRealDTO;

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
public class ResultadoRealView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ResultadoRealView.class);
    private InputText txtActivo;
    private InputText txtPercentilGrupo;
    private InputText txtPercentilNacional;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdMatricula_Matricula;
    private InputText txtIdModulo_Modulo;
    private InputText txtIdResultadoReal;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ResultadoRealDTO> data;
    private ResultadoRealDTO selectedResultadoReal;
    private ResultadoReal entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ResultadoRealView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedResultadoReal = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedResultadoReal = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtPercentilGrupo != null) {
            txtPercentilGrupo.setValue(null);
            txtPercentilGrupo.setDisabled(true);
        }

        if (txtPercentilNacional != null) {
            txtPercentilNacional.setValue(null);
            txtPercentilNacional.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtIdMatricula_Matricula != null) {
            txtIdMatricula_Matricula.setValue(null);
            txtIdMatricula_Matricula.setDisabled(true);
        }

        if (txtIdModulo_Modulo != null) {
            txtIdModulo_Modulo.setValue(null);
            txtIdModulo_Modulo.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdResultadoReal != null) {
            txtIdResultadoReal.setValue(null);
            txtIdResultadoReal.setDisabled(false);
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
            Long idResultadoReal = FacesUtils.checkLong(txtIdResultadoReal);
            entity = (idResultadoReal != null)
                ? businessDelegatorView.getResultadoReal(idResultadoReal) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtPercentilGrupo.setDisabled(false);
            txtPercentilNacional.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdMatricula_Matricula.setDisabled(false);
            txtIdModulo_Modulo.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdResultadoReal.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtPercentilGrupo.setValue(entity.getPercentilGrupo());
            txtPercentilGrupo.setDisabled(false);
            txtPercentilNacional.setValue(entity.getPercentilNacional());
            txtPercentilNacional.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdMatricula_Matricula.setValue(entity.getMatricula()
                                                    .getIdMatricula());
            txtIdMatricula_Matricula.setDisabled(false);
            txtIdModulo_Modulo.setValue(entity.getModulo().getIdModulo());
            txtIdModulo_Modulo.setDisabled(false);
            txtIdResultadoReal.setValue(entity.getIdResultadoReal());
            txtIdResultadoReal.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedResultadoReal = (ResultadoRealDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedResultadoReal"));
        txtActivo.setValue(selectedResultadoReal.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedResultadoReal.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedResultadoReal.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtPercentilGrupo.setValue(selectedResultadoReal.getPercentilGrupo());
        txtPercentilGrupo.setDisabled(false);
        txtPercentilNacional.setValue(selectedResultadoReal.getPercentilNacional());
        txtPercentilNacional.setDisabled(false);
        txtUsuCreador.setValue(selectedResultadoReal.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedResultadoReal.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdMatricula_Matricula.setValue(selectedResultadoReal.getIdMatricula_Matricula());
        txtIdMatricula_Matricula.setDisabled(false);
        txtIdModulo_Modulo.setValue(selectedResultadoReal.getIdModulo_Modulo());
        txtIdModulo_Modulo.setDisabled(false);
        txtIdResultadoReal.setValue(selectedResultadoReal.getIdResultadoReal());
        txtIdResultadoReal.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedResultadoReal == null) && (entity == null)) {
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
            entity = new ResultadoReal();

            Long idResultadoReal = FacesUtils.checkLong(txtIdResultadoReal);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdResultadoReal(idResultadoReal);
            entity.setPercentilGrupo(FacesUtils.checkLong(txtPercentilGrupo));
            entity.setPercentilNacional(FacesUtils.checkLong(
                    txtPercentilNacional));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setMatricula((FacesUtils.checkLong(txtIdMatricula_Matricula) != null)
                ? businessDelegatorView.getMatricula(FacesUtils.checkLong(
                        txtIdMatricula_Matricula)) : null);
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            businessDelegatorView.saveResultadoReal(entity);
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
                Long idResultadoReal = new Long(selectedResultadoReal.getIdResultadoReal());
                entity = businessDelegatorView.getResultadoReal(idResultadoReal);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setPercentilGrupo(FacesUtils.checkLong(txtPercentilGrupo));
            entity.setPercentilNacional(FacesUtils.checkLong(
                    txtPercentilNacional));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setMatricula((FacesUtils.checkLong(txtIdMatricula_Matricula) != null)
                ? businessDelegatorView.getMatricula(FacesUtils.checkLong(
                        txtIdMatricula_Matricula)) : null);
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            businessDelegatorView.updateResultadoReal(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedResultadoReal = (ResultadoRealDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedResultadoReal"));

            Long idResultadoReal = new Long(selectedResultadoReal.getIdResultadoReal());
            entity = businessDelegatorView.getResultadoReal(idResultadoReal);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idResultadoReal = FacesUtils.checkLong(txtIdResultadoReal);
            entity = businessDelegatorView.getResultadoReal(idResultadoReal);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteResultadoReal(entity);
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
        Date fechaModificacion, Long idResultadoReal, Long percentilGrupo,
        Long percentilNacional, Long usuCreador, Long usuModificador,
        Long idMatricula_Matricula, Long idModulo_Modulo)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setPercentilGrupo(FacesUtils.checkLong(percentilGrupo));
            entity.setPercentilNacional(FacesUtils.checkLong(percentilNacional));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateResultadoReal(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ResultadoRealView").requestRender();
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

    public InputText getTxtPercentilGrupo() {
        return txtPercentilGrupo;
    }

    public void setTxtPercentilGrupo(InputText txtPercentilGrupo) {
        this.txtPercentilGrupo = txtPercentilGrupo;
    }

    public InputText getTxtPercentilNacional() {
        return txtPercentilNacional;
    }

    public void setTxtPercentilNacional(InputText txtPercentilNacional) {
        this.txtPercentilNacional = txtPercentilNacional;
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

    public InputText getTxtIdMatricula_Matricula() {
        return txtIdMatricula_Matricula;
    }

    public void setTxtIdMatricula_Matricula(InputText txtIdMatricula_Matricula) {
        this.txtIdMatricula_Matricula = txtIdMatricula_Matricula;
    }

    public InputText getTxtIdModulo_Modulo() {
        return txtIdModulo_Modulo;
    }

    public void setTxtIdModulo_Modulo(InputText txtIdModulo_Modulo) {
        this.txtIdModulo_Modulo = txtIdModulo_Modulo;
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

    public InputText getTxtIdResultadoReal() {
        return txtIdResultadoReal;
    }

    public void setTxtIdResultadoReal(InputText txtIdResultadoReal) {
        this.txtIdResultadoReal = txtIdResultadoReal;
    }

    public List<ResultadoRealDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataResultadoReal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ResultadoRealDTO> resultadoRealDTO) {
        this.data = resultadoRealDTO;
    }

    public ResultadoRealDTO getSelectedResultadoReal() {
        return selectedResultadoReal;
    }

    public void setSelectedResultadoReal(ResultadoRealDTO resultadoReal) {
        this.selectedResultadoReal = resultadoReal;
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
