package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.UsuarioDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtApellido;
    private InputText txtCelular;
    private InputText txtCodigo;
    private InputText txtCorreo;
    private InputText txtGenero;
    private InputText txtIdentificacion;
    private InputText txtNombre;     
    private InputText txtIdUsuario;  
    private SelectOneMenu somTipoUsuario;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private List<SelectItem> losTipoUsuarioSelectItem;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView(){
        super();        
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;       

        if (txtApellido != null) {
            txtApellido.setValue(null);
            txtApellido.setDisabled(true);
        }

        if (txtCelular != null) {
            txtCelular.setValue(null);
            txtCelular.setDisabled(true);
        }

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(true);
        }

        if (txtCorreo != null) {
            txtCorreo.setValue(null);
            txtCorreo.setDisabled(true);
        }

        if (txtGenero != null) {
            txtGenero.setValue(null);
            txtGenero.setDisabled(true);
        }

        if (txtIdentificacion != null) {
            txtIdentificacion.setValue(null);
            txtIdentificacion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }   

        if (txtIdUsuario != null) {
            txtIdUsuario.setValue(null);
            txtIdUsuario.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }    

    public void listener_txtId() {
        try {
            Long idUsuario = FacesUtils.checkLong(txtIdUsuario);
            entity = (idUsuario != null)
                ? businessDelegatorView.getUsuario(idUsuario) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellido.setDisabled(false);
            txtCelular.setDisabled(false);
            txtCodigo.setDisabled(false);
            txtCorreo.setDisabled(false);
            txtGenero.setDisabled(false);
            txtIdentificacion.setDisabled(false);
            txtNombre.setDisabled(false);                      
            txtIdUsuario.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellido.setValue(entity.getApellido());
            txtApellido.setDisabled(false);
            txtCelular.setValue(entity.getCelular());
            txtCelular.setDisabled(false);
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(false);
            txtCorreo.setValue(entity.getCorreo());
            txtCorreo.setDisabled(false);            
            txtGenero.setValue(entity.getGenero());
            txtGenero.setDisabled(false);
            txtIdentificacion.setValue(entity.getIdentificacion());
            txtIdentificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);                    
            txtIdUsuario.setValue(entity.getIdUsuario());
            txtIdUsuario.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtApellido.setValue(selectedUsuario.getApellido());
        txtApellido.setDisabled(false);
        txtCelular.setValue(selectedUsuario.getCelular());
        txtCelular.setDisabled(false);
        txtCodigo.setValue(selectedUsuario.getCodigo());
        txtCodigo.setDisabled(false);
        txtCorreo.setValue(selectedUsuario.getCorreo());
        txtCorreo.setDisabled(false);       
        txtGenero.setValue(selectedUsuario.getGenero());
        txtGenero.setDisabled(false);
        txtIdentificacion.setValue(selectedUsuario.getIdentificacion());
        txtIdentificacion.setDisabled(false);
        txtNombre.setValue(selectedUsuario.getNombre());
        txtNombre.setDisabled(false);              
        txtIdUsuario.setValue(selectedUsuario.getIdUsuario());
        txtIdUsuario.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
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
            entity = new Usuario();

            Long idUsuario = FacesUtils.checkLong(txtIdUsuario);
            
            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setCelular(FacesUtils.checkLong(txtCelular));
            entity.setCodigo(FacesUtils.checkLong(txtCodigo));
            entity.setCorreo(FacesUtils.checkString(txtCorreo));            
            entity.setGenero(FacesUtils.checkString(txtGenero));
            entity.setIdUsuario(idUsuario);
            entity.setIdentificacion(FacesUtils.checkLong(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));                      
            businessDelegatorView.saveUsuario(entity);
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
                Long idUsuario = new Long(selectedUsuario.getIdUsuario());
                entity = businessDelegatorView.getUsuario(idUsuario);
            }
            
            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setCelular(FacesUtils.checkLong(txtCelular));
            entity.setCodigo(FacesUtils.checkLong(txtCodigo));
            entity.setCorreo(FacesUtils.checkString(txtCorreo));            
            entity.setGenero(FacesUtils.checkString(txtGenero));
            entity.setIdentificacion(FacesUtils.checkLong(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));            
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Long idUsuario = new Long(selectedUsuario.getIdUsuario());
            entity = businessDelegatorView.getUsuario(idUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idUsuario = FacesUtils.checkLong(txtIdUsuario);
            entity = businessDelegatorView.getUsuario(idUsuario);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
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

    public String action_modifyWitDTO(String activo, String apellido,
        Long celular, Long codigo, String correo, Date fechaCreacion,
        Date fechaModificacion, String genero, Long idUsuario,
        Long identificacion, String nombre, String password, Long usuCreador,
        Long usuModificador, Long idTipoUsuario_TipoUsuario)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setApellido(FacesUtils.checkString(apellido));
            entity.setCelular(FacesUtils.checkLong(celular));
            entity.setCodigo(FacesUtils.checkLong(codigo));
            entity.setCorreo(FacesUtils.checkString(correo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setGenero(FacesUtils.checkString(genero));
            entity.setIdentificacion(FacesUtils.checkLong(identificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setPassword(FacesUtils.checkString(password));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }    

    public InputText getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(InputText txtApellido) {
        this.txtApellido = txtApellido;
    }

    public InputText getTxtCelular() {
        return txtCelular;
    }

    public void setTxtCelular(InputText txtCelular) {
        this.txtCelular = txtCelular;
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public InputText getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(InputText txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public InputText getTxtGenero() {
        return txtGenero;
    }

    public void setTxtGenero(InputText txtGenero) {
        this.txtGenero = txtGenero;
    }

    public InputText getTxtIdentificacion() {
        return txtIdentificacion;
    }

    public void setTxtIdentificacion(InputText txtIdentificacion) {
        this.txtIdentificacion = txtIdentificacion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }    

    public InputText getTxtIdUsuario() {
        return txtIdUsuario;
    }

    public void setTxtIdUsuario(InputText txtIdUsuario) {
        this.txtIdUsuario = txtIdUsuario;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
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

	public SelectOneMenu getSomTipoUsuario() {
		return somTipoUsuario;
	}

	public void setSomTipoUsuario(SelectOneMenu somTipoUsuario) {
		this.somTipoUsuario = somTipoUsuario;
	}

	public List<SelectItem> getLosTipoUsuarioSelectItem() {
		return losTipoUsuarioSelectItem;
	}

	public void setLosTipoUsuarioSelectItem(List<SelectItem> losTipoUsuarioSelectItem) {
		this.losTipoUsuarioSelectItem = losTipoUsuarioSelectItem;
	}
}
