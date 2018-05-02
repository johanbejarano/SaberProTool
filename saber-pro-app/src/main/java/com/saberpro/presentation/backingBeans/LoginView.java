package com.saberpro.presentation.backingBeans;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.saberpro.utilities.Constantes;
import com.saberpro.utilities.FacesUtils;
import com.saberpro.modelo.Usuario;
import com.saberpro.modelo.dto.UsuarioDTO;


@ViewScoped
@ManagedBean(name = "loginView")
public class LoginView {
	
	private static final Logger log = LoggerFactory.getLogger(LoginView.class);
	
    private String userId;
    private String password;
    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String login() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUserId(),this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);

            FacesUtils.getHttpSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            Usuario usuario = (Usuario)FacesUtils.getfromSession("usuario");
            
            if(usuario.getActivo().equals(Constantes.ESTADO_PENDIENTE)) {
            	return "/cambiarPassword.xhtml";
            }
            else if(usuario.getActivo().equals(Constantes.ESTADO_ACTIVO)) {
            	return "/XHTML/initialMenu.xhtml\";";
            }
            else if(usuario.getActivo().equals(Constantes.ESTADO_INACTIVO)) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cuenta no esta activa", ""));
            }            
            
        } catch (AuthenticationException e) {
        	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        	log.error("error de "+e.getMessage(),e);
        	
        }
        
        return "/login.xhtml";
        
    }
}
