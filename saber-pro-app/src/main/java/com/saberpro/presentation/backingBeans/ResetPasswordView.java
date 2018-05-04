package com.saberpro.presentation.backingBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import com.saberpro.presentation.businessDelegate.IBusinessDelegatorView;

@ViewScoped
@ManagedBean(name = "resetPasswordView")
public class ResetPasswordView {

	private String email;
	private boolean showDialog;
	private CommandButton btnReset;
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;	
	
	public ResetPasswordView() {
		super();		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	public String reset() {
		return "login.xhtml";
	}
	
	public void resetPassword() {
		try {
			
			businessDelegatorView.resetByEmailUsuario(email);
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_INFO ,"se le envio un correo para recuperar su contraseña", ""));
			btnReset.setValue("Se envio el correo corectamente");
			btnReset.setDisabled(true);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se encontro el correo", ""));  
			
        }
	}

	public CommandButton getBtnReset() {
		return btnReset;
	}

	public void setBtnReset(CommandButton btnReset) {
		this.btnReset = btnReset;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
}
