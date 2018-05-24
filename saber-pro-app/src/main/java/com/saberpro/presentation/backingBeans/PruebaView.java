package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaDTO;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioDTO;
import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
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
    
    private List<PruebaProgramaUsuario> data;   
    
    private PruebaDTO selectedPrueba;
    
    private Prueba entity;
    
    private boolean showDialog;
    
    private PruebaProgramaUsuario pruebaProgramaUsuario;
    
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
    public void verPrueba(long idPruebaUsuarioPrograma) {
    	try {    		
    		PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(idPruebaUsuarioPrograma);
    		EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(pruebaProgramaUsuario.getEstadoPrueba().getIdEstadoPrueba());  
    		if(estadoPrueba.getIdEstadoPrueba()==Constantes.PRUEBA_ESTADO_INICIADO) {
    			FacesContext.getCurrentInstance().getExternalContext().redirect("respuestaPruebaProgramaUsuarioPregunta.xhtml?id="+idPruebaUsuarioPrograma);
    		}
    		else if(estadoPrueba.getIdEstadoPrueba()==Constantes.PRUEBA_ESTADO_FINALIZADA){
    			FacesContext.getCurrentInstance().getExternalContext().redirect("pruebaResultado.xhtml?id="+idPruebaUsuarioPrograma);
    		}
    		else if(estadoPrueba.getIdEstadoPrueba()==Constantes.PRUEBA_ESTADO_PENDIENTE){
    			FacesContext.getCurrentInstance().getExternalContext().redirect("pruebaModulo.xhtml?id="+idPruebaUsuarioPrograma);
    		}
    		
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    }
    
    public String getTipoPrueba(long idPrueba) {
    	try {
    		Prueba prueba = businessDelegatorView.getPrueba(idPrueba);
    		TipoPrueba tipoPrueba = businessDelegatorView.getTipoPrueba(prueba.getTipoPrueba().getIdTipoPrueba());
    		
    		return tipoPrueba.getNombre();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return "";
		}
    	
    }
    
    public String getEstadoPrueba(long idEstadoPrueba) {
    	try {
    		EstadoPrueba estadoPrueba = businessDelegatorView.getEstadoPrueba(idEstadoPrueba);    		
    		return estadoPrueba.getNombre();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return "";
		}
    	
    }
    
    public StreamedContent getReportePDF() {
    	try {
			
    		//TODO: Ojo quemado el 35
    		
    		Object[] variables = {"idPrueba", false, pruebaProgramaUsuario.getPrueba().getIdPrueba(), "="};
    		List<Prueba> pruebas = businessDelegatorView.findByCriteriaInPrueba(variables, null, null);
    		
    		if (pruebas==null || pruebas.size()==0) {
    			throw new Exception("No existe la prueba " + pruebaProgramaUsuario.getPrueba().getIdPrueba());
    		}
    		
    		ByteArrayInputStream bais = businessDelegatorView.generarInformeIndividual(pruebas.get(0).getIdPrueba());
    		StreamedContent archivo = new DefaultStreamedContent(bais, "application/pdf",
					"prueba-"+pruebas.get(0).getIdPrueba()+".pdf");
    		
    		return archivo;
    		
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error generando el reporte");
			return null;
		}
    }
    
    public void action_seleccionarPrueba(PruebaProgramaUsuario pruebaProgramaUsuario) {
    	this.pruebaProgramaUsuario = pruebaProgramaUsuario;
    }
    
    public void createPrueba() {
    	try {
    		Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
    		
    		Object[] variable = {"usuario.idUsuario",true,usuario.getIdUsuario(),"=","activo",true,Constantes.ESTADO_ACTIVO,"="};    		
    		
    		ProgramaUsuario programaUsuario = businessDelegatorView.findByCriteriaInProgramaUsuario(variable,null,null).get(0);
    		
    		for (PruebaProgramaUsuario pruebaProgramaUsuario :data) {
				if(pruebaProgramaUsuario.getEstadoPrueba().getIdEstadoPrueba()==Constantes.PRUEBA_ESTADO_INICIADO) {
					throw new Exception("No puede realizar ya empezo una prueba terminela"); 
				}
			}
    		
    		if (usuario != null && programaUsuario!=null) {
	    		long idTipoPrueba = Long.parseLong(FacesUtils.checkString(somTipoPrueba));
	    		
	    		if(Constantes.PRUEBA_TYPE_SIMULACRO==idTipoPrueba) {
	    			newSimulacro(usuario,programaUsuario);
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
    private void createSimulacro(Usuario usuario,ProgramaUsuario programaUsuario) throws Exception {
    	//si existe
    	Object[] variablePrueba = {"tipoPrueba.idTipoPrueba",true,Constantes.PRUEBA_TYPE_SIMULACRO,"="};
    	List<Prueba> listPrueba = businessDelegatorView.findByCriteriaInPrueba(variablePrueba, null, null);
    	if(listPrueba.size()!=0) {
    		for (Prueba prueba : listPrueba) {
    			Object[] variablePruebaPrograma = {"prueba.idPrueba",true,prueba.getIdPrueba(),"=","programaUsuario.idProgramaUsuario",true,programaUsuario.getIdProgramaUsuario(),"<>"};
    			List<PruebaProgramaUsuario> listPruebaPrograma = businessDelegatorView.findByCriteriaInPruebaProgramaUsuario(variablePruebaPrograma, null, null);
    			if(listPruebaPrograma.size()>0) {
    				PruebaProgramaUsuario pruebaProgramaUsuario= new PruebaProgramaUsuario();
    				pruebaProgramaUsuario.setActivo(Constantes.ESTADO_ACTIVO);
    				pruebaProgramaUsuario.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_INICIADO));
    				pruebaProgramaUsuario.setFechaCreacion(new Date());
    				pruebaProgramaUsuario.setProgramaUsuario(programaUsuario);
    				pruebaProgramaUsuario.setPrueba(prueba);
    				pruebaProgramaUsuario.setUsuCreador(usuario.getIdUsuario());
    				
    				businessDelegatorView.savePruebaProgramaUsuario(pruebaProgramaUsuario);
    				
    				Object[] variablePruebaPregunta = {"pruebaProgramaUsuario.idPruebaProgramaUsuario",true,listPruebaPrograma.get(0).getIdPruebaProgramaUsuario(),"="};
    				List<PruebaProgramaUsuarioPregunta> listPruebaPregunta = businessDelegatorView.findByCriteriaInPruebaProgramaUsuarioPregunta(variablePruebaPregunta,null,null);
    				
    				for (PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta : listPruebaPregunta) {
						pruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
						pruebaProgramaUsuarioPregunta.setPruebaProgramaUsuario(pruebaProgramaUsuario);
						pruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());
						
						businessDelegatorView.savePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
						
						RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta = new RespuestaPruebaProgramaUsuarioPregunta();
						respuestaPruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
						respuestaPruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
						respuestaPruebaProgramaUsuarioPregunta.setPorcentajeAsignado(0L);
						respuestaPruebaProgramaUsuarioPregunta.setPruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
						respuestaPruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());
						
						businessDelegatorView.saveRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
					}
    				FacesContext.getCurrentInstance().getExternalContext().redirect("respuestaPruebaProgramaUsuarioPregunta.xhtml?id="+pruebaProgramaUsuario.getIdPruebaProgramaUsuario());
    				return;
    			}
			}
    	}//nueva
    	  		
    	newSimulacro(usuario, programaUsuario);
    	
    	
    }
    
    private void newSimulacro(Usuario usuario,ProgramaUsuario programaUsuario) throws Exception {
    	
    	entity = new Prueba();
    	entity.setActivo(Constantes.ESTADO_ACTIVO);
    	entity.setFechaCreacion(new Date());
    	entity.setTipoPrueba(businessDelegatorView.getTipoPrueba(Constantes.PRUEBA_TYPE_SIMULACRO));
    	entity.setUsuCreador(usuario.getIdUsuario());
    	entity.setTiempo(Constantes.TIME_PRUEBA);
    	
    	businessDelegatorView.savePrueba(entity);
    	
    	PruebaProgramaUsuario pruebaProgramaUsuario = new PruebaProgramaUsuario();
    	
    	pruebaProgramaUsuario.setActivo(Constantes.ESTADO_ACTIVO);
    	pruebaProgramaUsuario.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_INICIADO));
    	pruebaProgramaUsuario.setFechaCreacion(new Date());
    	pruebaProgramaUsuario.setProgramaUsuario(programaUsuario);
    	pruebaProgramaUsuario.setPrueba(entity);
    	pruebaProgramaUsuario.setUsuCreador(usuario.getIdUsuario());
    	
    	businessDelegatorView.savePruebaProgramaUsuario(pruebaProgramaUsuario);
		Object[] variableModulo = { "tipoModulo.idTipoModulo", true, Constantes.MODULO_TYPE_GENERICO, "=", "activo",
		true, Constantes.ESTADO_ACTIVO, "=" };

		List<Modulo> listModulo = businessDelegatorView.findByCriteriaInModulo(variableModulo, null, null);
		listModulo.addAll(businessDelegatorView.findByProgramaModulo(programaUsuario.getPrograma().getIdPrograma()));

		for (Modulo modulo : listModulo) {
			PruebaModulo pruebaModulo = new PruebaModulo();
			pruebaModulo.setActivo(Constantes.ESTADO_ACTIVO);
			pruebaModulo.setFechaCreacion(new Date());
			pruebaModulo.setModulo(modulo);
			pruebaModulo.setNumeroPreguntas(modulo.getCantidadPreguntas());
			pruebaModulo.setPrueba(entity);
			pruebaModulo.setUsuCreador(usuario.getIdUsuario());
			
			businessDelegatorView.savePruebaModulo(pruebaModulo);
			
			List<Pregunta> listPregunta = businessDelegatorView.findByRandomPregunta(modulo.getIdModulo(),modulo.getCantidadPreguntas());
			for (Pregunta pregunta : listPregunta) {
				PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = new PruebaProgramaUsuarioPregunta();
				pruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
				pruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
				pruebaProgramaUsuarioPregunta.setPregunta(pregunta);
				pruebaProgramaUsuarioPregunta.setPruebaProgramaUsuario(pruebaProgramaUsuario);
				pruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());
				
				businessDelegatorView.savePruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
				
				RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta = new RespuestaPruebaProgramaUsuarioPregunta();
				respuestaPruebaProgramaUsuarioPregunta.setActivo(Constantes.ESTADO_ACTIVO);
				respuestaPruebaProgramaUsuarioPregunta.setFechaCreacion(new Date());
				respuestaPruebaProgramaUsuarioPregunta.setPorcentajeAsignado(0L);
				respuestaPruebaProgramaUsuarioPregunta.setPruebaProgramaUsuarioPregunta(pruebaProgramaUsuarioPregunta);
				respuestaPruebaProgramaUsuarioPregunta.setUsuCreador(usuario.getIdUsuario());
				
				businessDelegatorView.saveRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect("respuestaPruebaProgramaUsuarioPregunta.xhtml?id="+pruebaProgramaUsuario.getIdPruebaProgramaUsuario());
    }
    
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
    public List<PruebaProgramaUsuario> getData() {
        try {
            if (data == null) {
            	Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
        		if(usuario!=null) {
        			Object[] variable = {"usuario.idUsuario",true,usuario.getIdUsuario(),"=","activo",true,Constantes.ESTADO_ACTIVO,"="};   		
        			ProgramaUsuario programaUsuario = businessDelegatorView.findByCriteriaInProgramaUsuario(variable,null,null).get(0);
        			Object[] variable2 = {"programaUsuario.idProgramaUsuario",true,programaUsuario.getIdProgramaUsuario(),"=","activo",true,Constantes.ESTADO_ACTIVO,"="};
        			data = businessDelegatorView.findByCriteriaInPruebaProgramaUsuario(variable2, null, null);
        		}
        		
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PruebaProgramaUsuario> pruebaDTO) {
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
