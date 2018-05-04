package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
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
import javax.faces.model.SelectItem;


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
    
    private SelectOneMenu somTipoPrueba;
    
    private CommandButton btnGenerar;
    
    private List<SelectItem> lasTipoPruebaSelectItem;
    
    private List<PruebaDTO> data;
    
    private PruebaDTO selectedPrueba;
    
    private Prueba entity;
    
    private boolean showDialog;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PruebaView() {
        super();
    }
    
    /* Controles de la vista */
    
    public void verificar() {
		try {	
			btnGenerar.setDisabled(false);
			
			if (FacesUtils.checkString(somTipoPrueba) == null)
				btnGenerar.setDisabled(true);

		} catch (Exception e) {
			log.debug(e.getMessage());
		}

	}
    
    public void createPrueba() {
    	try {
    		Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
    		
    		Object[] variable = {"usuario.idUsuario",true,usuario.getIdUsuario(),"="};
    		
    		ProgramaUsuario programaUsuario = businessDelegatorView.findByCriteriaInProgramaUsuario(variable,null,null).get(0);
    		
    		if (usuario != null && programaUsuario!=null) {
	    		long idTipoPrueba = Long.parseLong(FacesUtils.checkString(somTipoPrueba));
	    		
	    		if(Constantes.PRUEBA_TYPE_SIMULACRO==idTipoPrueba) {
	    			
	    		}
	    		else if(Constantes.PRUEBA_TYPE_ENTRENAMIENTO==idTipoPrueba) {    			
	    			createEntrenamiento(usuario,programaUsuario);
	    		}
	    		else {
	    			FacesUtils.addErrorMessage("No es posible generar ese tipo de prueba");
	    		}
    		
    		}
		} catch (Exception e) {
			log.error("Error de "+e.getMessage(),e);
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	
    }
   /* Sub procesos*/
    private void createEntrenamiento(Usuario usuario,ProgramaUsuario programaUsuario) throws Exception {
    	    	
    	entity = new Prueba();
    	entity.setActivo(Constantes.ESTADO_ACTIVO);
    	entity.setFechaCreacion(new Date());
    	entity.setTipoPrueba(businessDelegatorView.getTipoPrueba(Constantes.PRUEBA_TYPE_ENTRENAMIENTO));
    	entity.setUsuCreador(usuario.getIdUsuario());
    	
    	businessDelegatorView.savePrueba(entity);
    	
    	PruebaProgramaUsuario pruebaProgramaUsuario = new PruebaProgramaUsuario();
    	
    	pruebaProgramaUsuario.setActivo(Constantes.ESTADO_ACTIVO);
    	pruebaProgramaUsuario.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_PENDIENTE));
    	pruebaProgramaUsuario.setFechaCreacion(new Date());
    	pruebaProgramaUsuario.setProgramaUsuario(programaUsuario);
    	pruebaProgramaUsuario.setPrueba(entity);
    	pruebaProgramaUsuario.setUsuCreador(usuario.getIdUsuario());
    	
    	businessDelegatorView.savePruebaProgramaUsuario(pruebaProgramaUsuario);
    	
    	FacesContext.getCurrentInstance().getExternalContext().redirect("pruebaModulo.xhtml?id="+pruebaProgramaUsuario.getIdPruebaProgramaUsuario());
    	
    	
    }

   /* Getter and Setter*/
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




	public SelectOneMenu getSomTipoPrueba() {
		return somTipoPrueba;
	}




	public void setSomTipoPrueba(SelectOneMenu somTipoPrueba) {
		this.somTipoPrueba = somTipoPrueba;
	}




	public List<SelectItem> getLasTipoPruebaSelectItem() {
		if(lasTipoPruebaSelectItem==null) {				
			Object[] variable = {"activo",true,Constantes.ESTADO_ACTIVO,"="};
			lasTipoPruebaSelectItem = new ArrayList<>();				
			try {
				List<TipoPrueba> list = businessDelegatorView.findByCriteriaInTipoPrueba(variable,null,null);
				for (TipoPrueba tipoPrueba : list) {
					lasTipoPruebaSelectItem.add(new SelectItem(tipoPrueba.getIdTipoPrueba(),tipoPrueba.getNombre()));
				}
			} catch (Exception e) {
				
				log.debug("Error" + e.getMessage());
			}
		}	
		return lasTipoPruebaSelectItem;
	}




	public void setLasTipoPruebaSelectItem(List<SelectItem> lasTipoPruebaSelectItem) {
		this.lasTipoPruebaSelectItem = lasTipoPruebaSelectItem;
	}




	public CommandButton getBtnGenerar() {
		return btnGenerar;
	}




	public void setBtnGenerar(CommandButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}
}
