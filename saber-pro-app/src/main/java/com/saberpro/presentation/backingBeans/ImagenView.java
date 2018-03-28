package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.ImagenDTO;

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
public class ImagenView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ImagenView.class);
    private InputText txtActivo;
    private InputText txtDescripcion;
    private InputText txtNombre;
    private InputText txtRuta;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtIdPregunta_Pregunta;
    private InputText txtIdImagen;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ImagenDTO> data;
    private ImagenDTO selectedImagen;
    private Imagen entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ImagenView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedImagen = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedImagen = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtRuta != null) {
            txtRuta.setValue(null);
            txtRuta.setDisabled(true);
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

        if (txtIdImagen != null) {
            txtIdImagen.setValue(null);
            txtIdImagen.setDisabled(false);
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
            Long idImagen = FacesUtils.checkLong(txtIdImagen);
            entity = (idImagen != null)
                ? businessDelegatorView.getImagen(idImagen) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtRuta.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtIdPregunta_Pregunta.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtIdImagen.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtRuta.setValue(entity.getRuta());
            txtRuta.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtIdPregunta_Pregunta.setValue(entity.getPregunta().getIdPregunta());
            txtIdPregunta_Pregunta.setDisabled(false);
            txtIdImagen.setValue(entity.getIdImagen());
            txtIdImagen.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedImagen = (ImagenDTO) (evt.getComponent().getAttributes()
                                         .get("selectedImagen"));
        txtActivo.setValue(selectedImagen.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcion.setValue(selectedImagen.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFechaCreacion.setValue(selectedImagen.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedImagen.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedImagen.getNombre());
        txtNombre.setDisabled(false);
        txtRuta.setValue(selectedImagen.getRuta());
        txtRuta.setDisabled(false);
        txtUsuCreador.setValue(selectedImagen.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedImagen.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtIdPregunta_Pregunta.setValue(selectedImagen.getIdPregunta_Pregunta());
        txtIdPregunta_Pregunta.setDisabled(false);
        txtIdImagen.setValue(selectedImagen.getIdImagen());
        txtIdImagen.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedImagen == null) && (entity == null)) {
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
            entity = new Imagen();

            Long idImagen = FacesUtils.checkLong(txtIdImagen);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setIdImagen(idImagen);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRuta(FacesUtils.checkString(txtRuta));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            businessDelegatorView.saveImagen(entity);
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
                Long idImagen = new Long(selectedImagen.getIdImagen());
                entity = businessDelegatorView.getImagen(idImagen);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setRuta(FacesUtils.checkString(txtRuta));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setPregunta((FacesUtils.checkLong(txtIdPregunta_Pregunta) != null)
                ? businessDelegatorView.getPregunta(FacesUtils.checkLong(
                        txtIdPregunta_Pregunta)) : null);
            businessDelegatorView.updateImagen(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedImagen = (ImagenDTO) (evt.getComponent().getAttributes()
                                             .get("selectedImagen"));

            Long idImagen = new Long(selectedImagen.getIdImagen());
            entity = businessDelegatorView.getImagen(idImagen);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idImagen = FacesUtils.checkLong(txtIdImagen);
            entity = businessDelegatorView.getImagen(idImagen);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteImagen(entity);
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

    public String action_modifyWitDTO(String activo, String descripcion,
        Date fechaCreacion, Date fechaModificacion, Long idImagen,
        String nombre, String ruta, Long usuCreador, Long usuModificador,
        Long idPregunta_Pregunta) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setRuta(FacesUtils.checkString(ruta));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateImagen(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ImagenView").requestRender();
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

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtRuta() {
        return txtRuta;
    }

    public void setTxtRuta(InputText txtRuta) {
        this.txtRuta = txtRuta;
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

    public InputText getTxtIdImagen() {
        return txtIdImagen;
    }

    public void setTxtIdImagen(InputText txtIdImagen) {
        this.txtIdImagen = txtIdImagen;
    }

    public List<ImagenDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataImagen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ImagenDTO> imagenDTO) {
        this.data = imagenDTO;
    }

    public ImagenDTO getSelectedImagen() {
        return selectedImagen;
    }

    public void setSelectedImagen(ImagenDTO imagen) {
        this.selectedImagen = imagen;
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
