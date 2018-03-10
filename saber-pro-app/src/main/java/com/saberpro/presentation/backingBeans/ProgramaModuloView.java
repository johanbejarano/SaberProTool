package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ProgramaModuloDTO;

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
public class ProgramaModuloView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProgramaModuloView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdModulo_Modulo;
    private InputText txtIdPrograma_Programa;
    private InputText txtIdProgramaModulo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProgramaModuloDTO> data;
    private ProgramaModuloDTO selectedProgramaModulo;
    private ProgramaModulo entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProgramaModuloView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedProgramaModulo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProgramaModulo = null;

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

        if (txtIdModulo_Modulo != null) {
            txtIdModulo_Modulo.setValue(null);
            txtIdModulo_Modulo.setDisabled(true);
        }

        if (txtIdPrograma_Programa != null) {
            txtIdPrograma_Programa.setValue(null);
            txtIdPrograma_Programa.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtIdProgramaModulo != null) {
            txtIdProgramaModulo.setValue(null);
            txtIdProgramaModulo.setDisabled(false);
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
            Long idProgramaModulo = FacesUtils.checkLong(txtIdProgramaModulo);
            entity = (idProgramaModulo != null)
                ? businessDelegatorView.getProgramaModulo(idProgramaModulo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdModulo_Modulo.setDisabled(false);
            txtIdPrograma_Programa.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdProgramaModulo.setDisabled(false);
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
            txtIdModulo_Modulo.setValue(entity.getModulo().getIdModulo());
            txtIdModulo_Modulo.setDisabled(false);
            txtIdPrograma_Programa.setValue(entity.getPrograma().getIdPrograma());
            txtIdPrograma_Programa.setDisabled(false);
            txtIdProgramaModulo.setValue(entity.getIdProgramaModulo());
            txtIdProgramaModulo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProgramaModulo = (ProgramaModuloDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedProgramaModulo"));
        txtActivo.setValue(selectedProgramaModulo.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedProgramaModulo.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedProgramaModulo.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedProgramaModulo.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedProgramaModulo.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdModulo_Modulo.setValue(selectedProgramaModulo.getIdModulo_Modulo());
        txtIdModulo_Modulo.setDisabled(false);
        txtIdPrograma_Programa.setValue(selectedProgramaModulo.getIdPrograma_Programa());
        txtIdPrograma_Programa.setDisabled(false);
        txtIdProgramaModulo.setValue(selectedProgramaModulo.getIdProgramaModulo());
        txtIdProgramaModulo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProgramaModulo == null) && (entity == null)) {
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
            entity = new ProgramaModulo();

            Long idProgramaModulo = FacesUtils.checkLong(txtIdProgramaModulo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdProgramaModulo(idProgramaModulo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setPrograma((FacesUtils.checkLong(txtIdPrograma_Programa) != null)
                ? businessDelegatorView.getPrograma(FacesUtils.checkLong(
                        txtIdPrograma_Programa)) : null);
            businessDelegatorView.saveProgramaModulo(entity);
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
                Long idProgramaModulo = new Long(selectedProgramaModulo.getIdProgramaModulo());
                entity = businessDelegatorView.getProgramaModulo(idProgramaModulo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setModulo((FacesUtils.checkLong(txtIdModulo_Modulo) != null)
                ? businessDelegatorView.getModulo(FacesUtils.checkLong(
                        txtIdModulo_Modulo)) : null);
            entity.setPrograma((FacesUtils.checkLong(txtIdPrograma_Programa) != null)
                ? businessDelegatorView.getPrograma(FacesUtils.checkLong(
                        txtIdPrograma_Programa)) : null);
            businessDelegatorView.updateProgramaModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProgramaModulo = (ProgramaModuloDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedProgramaModulo"));

            Long idProgramaModulo = new Long(selectedProgramaModulo.getIdProgramaModulo());
            entity = businessDelegatorView.getProgramaModulo(idProgramaModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idProgramaModulo = FacesUtils.checkLong(txtIdProgramaModulo);
            entity = businessDelegatorView.getProgramaModulo(idProgramaModulo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProgramaModulo(entity);
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
        Date fechaModificacion, Long idProgramaModulo, Long usuCreador,
        Long usuModificador, Long idModulo_Modulo, Long idPrograma_Programa)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateProgramaModulo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProgramaModuloView").requestRender();
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

    public InputText getTxtIdModulo_Modulo() {
        return txtIdModulo_Modulo;
    }

    public void setTxtIdModulo_Modulo(InputText txtIdModulo_Modulo) {
        this.txtIdModulo_Modulo = txtIdModulo_Modulo;
    }

    public InputText getTxtIdPrograma_Programa() {
        return txtIdPrograma_Programa;
    }

    public void setTxtIdPrograma_Programa(InputText txtIdPrograma_Programa) {
        this.txtIdPrograma_Programa = txtIdPrograma_Programa;
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

    public InputText getTxtIdProgramaModulo() {
        return txtIdProgramaModulo;
    }

    public void setTxtIdProgramaModulo(InputText txtIdProgramaModulo) {
        this.txtIdProgramaModulo = txtIdProgramaModulo;
    }

    public List<ProgramaModuloDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProgramaModulo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProgramaModuloDTO> programaModuloDTO) {
        this.data = programaModuloDTO;
    }

    public ProgramaModuloDTO getSelectedProgramaModulo() {
        return selectedProgramaModulo;
    }

    public void setSelectedProgramaModulo(ProgramaModuloDTO programaModulo) {
        this.selectedProgramaModulo = programaModulo;
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
