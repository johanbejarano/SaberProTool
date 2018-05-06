package com.saberpro.presentation.backingBeans;

import com.saberpro.exceptions.*;

import com.saberpro.modelo.*;
import com.saberpro.modelo.dto.PruebaModuloDTO;

import com.saberpro.presentation.businessDelegate.*;

import com.saberpro.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.context.Theme;

import java.io.IOException;
import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PruebaModuloView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PruebaModuloView.class);

	private Long id;

	private DualListModel<Modulo> modulos;

	private List<PruebaModuloDTO> data;

	private PruebaModuloDTO selectedPruebaModulo;

	private PruebaProgramaUsuario pruebaProgramaUsuario;
	private Prueba prueba;

	private PruebaModulo entity;

	private boolean showDialog;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public PruebaModuloView() {
		super();
	}

	@PostConstruct
	public void comprobar() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			Map params = facesContext.getExternalContext().getRequestParameterMap();
			id = new Long((String) params.get("id"));
			if (id == null)
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
		} catch (Exception e) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void generar(boolean realizar) {
		try {

			Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");

			if (usuario != null) {
				
				List<Modulo> list = modulos.getTarget();
				pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(id);	
				prueba = businessDelegatorView.getPrueba(pruebaProgramaUsuario.getPrueba().getIdPrueba());			
				
				if(list.size()<1) {
					FacesUtils.addErrorMessage("Debe selecionar al menos 1 modulo para entrenar");
				}
				else {
					for (Modulo modulo : list) {
						PruebaModulo pruebaModulo = new PruebaModulo();
						pruebaModulo.setActivo(Constantes.ESTADO_ACTIVO);
						pruebaModulo.setFechaCreacion(new Date());
						pruebaModulo.setModulo(modulo);
						pruebaModulo.setNumeroPreguntas(modulo.getCantidadPreguntas());
						pruebaModulo.setPrueba(prueba);
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
						}
					}
					
					if(realizar) {
						pruebaProgramaUsuario.setFechaModificacion(new Date());
						pruebaProgramaUsuario.setUsuModificador(usuario.getIdUsuario());
						pruebaProgramaUsuario.setEstadoPrueba(businessDelegatorView.getEstadoPrueba(Constantes.PRUEBA_ESTADO_INICIADO));
						businessDelegatorView.updatePruebaProgramaUsuario(pruebaProgramaUsuario);
						
						FacesContext.getCurrentInstance().getExternalContext().redirect("respuestaPruebaProgramaUsuarioPregunta.xhtml?id="+pruebaProgramaUsuario.getIdPruebaProgramaUsuario());
					}
					else {
						FacesContext.getCurrentInstance().getExternalContext().redirect("prueba.xhtml");
					}
				}
			}

		} catch (Exception e) {
			log.error("Error generando preguntas " + e.getMessage(), e);
		}
	}

	public void generarEmpezar() {

	}

	/* Getter and Setter */
	public List<PruebaModuloDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataPruebaModulo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<PruebaModuloDTO> pruebaModuloDTO) {
		this.data = pruebaModuloDTO;
	}

	public PruebaModuloDTO getSelectedPruebaModulo() {
		return selectedPruebaModulo;
	}

	public void setSelectedPruebaModulo(PruebaModuloDTO pruebaModulo) {
		this.selectedPruebaModulo = pruebaModulo;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public DualListModel<Modulo> getModulos() {
		if (modulos == null) {
			try {
				
				Usuario usuario = (Usuario) FacesUtils.getfromSession("usuario");
				
				List<Modulo> modulosSource = new ArrayList<Modulo>();
				List<Modulo> modulosTarget = new ArrayList<Modulo>();
				
				Object[] variable2 = {"usuario.idUsuario",true,usuario.getIdUsuario(),"=","activo",true,Constantes.ESTADO_ACTIVO,"="};
				ProgramaUsuario entityPrograma = businessDelegatorView.findByCriteriaInProgramaUsuario(variable2,null,null).get(0);	

				Object[] variable = { "tipoModulo.idTipoModulo", true, Constantes.MODULO_TYPE_GENERICO, "=", "activo",
						true, Constantes.ESTADO_ACTIVO, "=" };

				List<Modulo> list = businessDelegatorView.findByCriteriaInModulo(variable, null, null);
				list.addAll(businessDelegatorView.findByProgramaModulo(entityPrograma.getPrograma().getIdPrograma()));
				for (Modulo modulo : list) {
					modulosSource.add(modulo);
					log.info("modulo es " + modulo.getNombre());

				}

				modulos = new DualListModel<Modulo>(modulosSource, modulosTarget);

			} catch (Exception e) {
				log.error("error de " + e.getMessage(), e);
			}
		}
		return modulos;
	}

	public void setModulos(DualListModel<Modulo> modulos) {
		this.modulos = modulos;
	}

	public PruebaProgramaUsuario getPruebaProgramaUsuario() {
		if (pruebaProgramaUsuario == null) {
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				Map params = facesContext.getExternalContext().getRequestParameterMap();
				Long id = new Long((String) params.get("id"));

				pruebaProgramaUsuario = businessDelegatorView.getPruebaProgramaUsuario(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return pruebaProgramaUsuario;
	}

	public void setPruebaProgramaUsuario(PruebaProgramaUsuario pruebaProgramaUsuario) {
		this.pruebaProgramaUsuario = pruebaProgramaUsuario;
	}
}
