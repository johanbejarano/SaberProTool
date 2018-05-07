package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.RespuestaPruebaProgramaUsuarioPreguntaDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
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
public class RespuestaPruebaProgramaUsuarioPreguntaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RespuestaPruebaProgramaUsuarioPreguntaView.class);
    
    private List<ComponentPrueba> dataPrueba;
    
    private Long id;
    
    private List<RespuestaPruebaProgramaUsuarioPregunta> data;
    
    private RespuestaPruebaProgramaUsuarioPreguntaDTO selectedRespuestaPruebaProgramaUsuarioPregunta;
    
    private RespuestaPruebaProgramaUsuarioPregunta entity;
    
    private boolean showDialog;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RespuestaPruebaProgramaUsuarioPreguntaView() {
        super();
    }
    
    
    
    
    
	@PostConstruct
	public void comprobar() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Map params = facesContext.getExternalContext().getRequestParameterMap();
			id = new Long((String) params.get("id"));
			if (id == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
			}				
			else {
				//verificar concidencia con usuario en session
				data = businessDelegatorView.findRespuestasPruebaProgramaUsuarioPreguntaByPruebaProgramaUsuario(id);
				PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(id);
				
				if(pruebaProgramaUsuario.getEstadoPrueba().getIdEstadoPrueba()==Constantes.PRUEBA_ESTADO_FINALIZADA) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
				}
			}
		} catch (Exception e) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void finalizarPrueba() {
			
		try {
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
			
			if(usuario!=null) {
				PruebaProgramaUsuario pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(id);
				pruebaProgramaUsuario.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_FINALIZADA));
				pruebaProgramaUsuario.setFechaModificacion(new Date());
				pruebaProgramaUsuario.setUsuModificador(usuario.getIdUsuario());
				
				businessDelegatorView.updatePruebaProgramaUsuario(pruebaProgramaUsuario);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
			}
			
		} catch (Exception e) {
			log.error("Error de "+e.getMessage(),e);
		}
	}
	
	public void guardarRespuesta(long idPregunta,long idRespuesta) {
		try {
			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
			if(usuario!=null) {
				RespuestaPruebaProgramaUsuarioPregunta respuestaPruebaProgramaUsuarioPregunta = businessDelegatorView.getRespuestaPruebaProgramaUsuarioPregunta(idRespuesta);
				ComponentPrueba componentePrueba = dataPrueba.get((int)idPregunta);
				Respuesta respuesta = businessDelegatorView.getRespuesta(componentePrueba.getRespuestaSelecionada());
				
				respuestaPruebaProgramaUsuarioPregunta.setRespuesta(respuesta);
				respuestaPruebaProgramaUsuarioPregunta.setFechaModificacion(new Date());
				respuestaPruebaProgramaUsuarioPregunta.setUsuModificador(usuario.getIdUsuario());
				respuestaPruebaProgramaUsuarioPregunta.setPorcentajeAsignado((long)respuesta.getPorcentajeAcierto());
				
				businessDelegatorView.updateRespuestaPruebaProgramaUsuarioPregunta(respuestaPruebaProgramaUsuarioPregunta);
			}
			
			
		} catch (Exception e) {
			log.error("Error de "+e.getMessage(),e);
		}
	}
    

    public List<RespuestaPruebaProgramaUsuarioPregunta> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.findRespuestasPruebaProgramaUsuarioPreguntaByPruebaProgramaUsuario(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<RespuestaPruebaProgramaUsuarioPregunta> respuestaPruebaProgramaUsuarioPregunta) {
        this.data = respuestaPruebaProgramaUsuarioPregunta;
    }

    public RespuestaPruebaProgramaUsuarioPreguntaDTO getSelectedRespuestaPruebaProgramaUsuarioPregunta() {
        return selectedRespuestaPruebaProgramaUsuarioPregunta;
    }

    public void setSelectedRespuestaPruebaProgramaUsuarioPregunta(
        RespuestaPruebaProgramaUsuarioPreguntaDTO respuestaPruebaProgramaUsuarioPregunta) {
        this.selectedRespuestaPruebaProgramaUsuarioPregunta = respuestaPruebaProgramaUsuarioPregunta;
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



	public List<ComponentPrueba> getDataPrueba() {
		if(dataPrueba==null) {
			try {
				dataPrueba = new ArrayList<>();
				for (int i=0;i<data.size();i++) {
					ComponentPrueba componentePrueba = new ComponentPrueba();
					
					componentePrueba.setNumeroPregunta(String.valueOf(i+1));					
					
					PruebaProgramaUsuarioPregunta pruebaProgramaUsuarioPregunta = businessDelegatorView.getPruebaProgramaUsuarioPregunta(data.get(i).getPruebaProgramaUsuarioPregunta().getIdPruebaProgramaUsuarioPregunta());
					Pregunta pregunta = businessDelegatorView.getPregunta(pruebaProgramaUsuarioPregunta.getPregunta().getIdPregunta());
					
					Object[] variable = { "pregunta.idPregunta", true,pregunta.getIdPregunta() ,"="};
					List<Respuesta> listRespuesta = businessDelegatorView.findByCriteriaInRespuesta(variable,null,null);
					
					componentePrueba.setContenidoPregunta(pregunta.getDescripcionPregunta());
					componentePrueba.setIdPregunta(i);
					componentePrueba.setIdRespuesta(data.get(i).getIdRespuestaPruebaProgramaUsuarioPregunta());
					if(data.get(i).getRespuesta()!=null)
						componentePrueba.setRespuestaSelecionada(data.get(i).getRespuesta().getIdRespuesta());
					
					List<SelectItem> lasRespuestas = new ArrayList<>();
					
					for (Respuesta respuesta : listRespuesta) {
						SelectItem item = new SelectItem(respuesta.getIdRespuesta(),respuesta.getDescripcionRespuesta());
						item.setEscape(false);
						lasRespuestas.add(item);
					}
					componentePrueba.setListRespuestas(lasRespuestas);
					
					dataPrueba.add(componentePrueba);
				}
			} catch (Exception e) {
				log.error("error en "+e.getMessage(),e);
			}
		}
		return dataPrueba;
	}



	public void setDataPrueba(List<ComponentPrueba> dataPrueba) {
		this.dataPrueba = dataPrueba;
	}
}
