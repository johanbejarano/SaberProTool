package com.saberpro.presentation.backingBeans;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.saberpro.modelo.Usuario;
import com.saberpro.presentation.businessDelegate.IBusinessDelegatorView;
import com.saberpro.utilities.Constantes;
import com.saberpro.utilities.FacesUtils;

@ViewScoped
@ManagedBean(name = "cambiarPasswordView")
public class CambiarPasswordView {

	private String password;
	private String passwordRepeat;	
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}	
	
	public void cambiarPassword() {
		
		try {
			
			Usuario usuario = (Usuario)FacesUtils.getfromSession("usuario");
			
			
			if(password.isEmpty() || passwordRepeat.isEmpty()) {
				FacesUtils.addErrorMessage("Escriba una contraseña"); 
			}
			else {
				if(password.equals(passwordRepeat)) {
					usuario.setPassword(businessDelegatorView.encodePasswordUsuario(password));
					usuario.setActivo(Constantes.ESTADO_ACTIVO);
					
					businessDelegatorView.updateUsuario(usuario);
					
					FacesContext.getCurrentInstance().getExternalContext().redirect("/saber-pro-app/XHTML/initialMenu.xhtml");
				}
				else {
					FacesUtils.addErrorMessage("Las contraseñas no conciden"); 
				}
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), ""));
			e.printStackTrace();
			
		}
	}
}
