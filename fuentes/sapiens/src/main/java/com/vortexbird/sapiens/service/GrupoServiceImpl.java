package  com.vortexbird.sapiens.service;


import java.math.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vortexbird.sapiens.exception.*;
import com.vortexbird.sapiens.mapper.GrupoMapper;
import com.vortexbird.sapiens.repository.*;
import com.vortexbird.sapiens.utility.Constantes;
import com.vortexbird.sapiens.utility.Utilities;

import com.vortexbird.sapiens.domain.*;
import com.vortexbird.sapiens.dto.GrupoDTO;
import com.vortexbird.sapiens.dto.UsuarioDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
* 
*/

@Scope("singleton")
@Service
public class GrupoServiceImpl implements GrupoService{

	private static final Logger log = LoggerFactory.getLogger(GrupoServiceImpl.class);

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoUsuarioRepository grupoUsuarioRepository;
	
	@Autowired
	private GrupoMapper grupoMapper;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoUsuarioService grupoUsuarioService;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Grupo grupo)throws Exception{		
		 try {
			Set<ConstraintViolation<Grupo>> constraintViolations =validator.validate(grupo);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Grupo> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}
				 throw new Exception(strMessage.toString());
			 }
		 }catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return grupoRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Grupo> findAll(){
		log.debug("finding all Grupo instances");
       	return grupoRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Grupo save(Grupo entity) throws Exception {
		log.debug("saving Grupo instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Grupo");
		} 
	
	    return grupoRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Grupo failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Grupo entity) throws Exception {
            	log.debug("deleting Grupo instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Grupo");
	    		}
    	
                                if(entity.getGrupId()==null){
                    throw new ZMessManager().new EmptyFieldException("grupId");
                    }
                        
            	            findById(entity.getGrupId()).ifPresent(entidad->{	            	
	                													List<GrupoUsuario> grupoUsuarios = entidad.getGrupoUsuarios();
							                    if(Utilities.validationsList(grupoUsuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("grupoUsuarios");
                        }
	                	            });
                       

            try {
            
            grupoRepository.deleteById(entity.getGrupId());
            log.debug("delete Grupo successful");
            
            } catch (Exception e) {
            	log.error("delete Grupo failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Long id) throws Exception {            
            	log.debug("deleting Grupo instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("grupId");
            	}
            	if(grupoRepository.findById(id).isPresent()){
           			delete(grupoRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Grupo update(Grupo entity) throws Exception {

				log.debug("updating Grupo instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Grupo");
		    		}
		    		
	            validate(entity);
	
	            return grupoRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Grupo failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Grupo> findById(Long grupId) throws Exception {            
            	log.debug("getting Grupo instance");
            	return grupoRepository.findById(grupId);
            }
			
			@Override
			@Transactional(readOnly=true)
			public List<Grupo> consultarGrupos(){
				log.debug("finding all Grupo instances");
		       	return grupoRepository.findByEstadoRegistro(Constantes.ESTADO_ACTIVO);
		    }

			@Override
			@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
		    public GrupoDTO guardarGrupo(GrupoDTO grupoDTO) throws Exception {
				log.debug("saving Grupo instance");
			    try {
			    
			    if(grupoDTO==null){
					throw new ZMessManager().new NullEntityExcepcion("Grupo");
				} 
			    
			    if(grupoDTO.getNombre() == null || grupoDTO.getNombre().trim().equals("")) {
			    	throw new Exception("El nombre no debe ser vacío");
			    }
			    
			    if(grupoDTO.getDescripcion() == null || grupoDTO.getDescripcion().trim().equals("")) {
			    	throw new Exception("La descripción no debe ser vacía");
			    }
			    
			    if(grupoDTO.getListUsuarios() == null || grupoDTO.getListUsuarios().size() == 0 || grupoDTO.getListUsuarios().isEmpty()) {
			    	throw new Exception("Debe seleccionar la población objetivo del grupo");
			    }
			    
			    Grupo grupo = new Grupo();
			    
			    grupo.setNombre(grupoDTO.getNombre());
			    grupo.setDescripcion(grupoDTO.getDescripcion());
			    
			    Optional<Usuario> usuarioCreadorOpt = usuarioService.findById(Integer.parseInt(grupoDTO.getUsuCreador().toString()));
			    if(usuarioCreadorOpt.isEmpty()) {
			    	throw new Exception("El usuario no existe");
			    }
			    
			    grupo.setUsuCreador(grupoDTO.getUsuCreador());
			    grupo.setFechaCreacion(new Date());
			    grupo.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
			    
			    Grupo grupoResult = save(grupo);
			    
			    for (Long usuaId : grupoDTO.getListUsuarios()) {
					Optional<Usuario> usuarioOpt = usuarioService.findById(Integer.parseInt(usuaId.toString()));
					if(usuarioOpt.isEmpty()) {
						throw new Exception("El usuario no existe");
					}
					
					GrupoUsuario grupoUsuario = new GrupoUsuario();
					grupoUsuario.setUsuario(usuarioOpt.get());
					grupoUsuario.setGrupo(grupoResult);
					grupoUsuario.setFechaCreacion(new Date());
					grupoUsuario.setUsuCreador(grupoDTO.getUsuCreador());
					grupoUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
					
					grupoUsuarioService.save(grupoUsuario);
			    	
				}
			    
			
			    return grupoMapper.grupoToGrupoDTO(grupoResult);
			    
			    } catch (Exception e) {
			    	log.error("save Grupo failed", e);
			    	throw e;
			    }
		    }
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public GrupoDTO actualizarGrupo(GrupoDTO grupoDTO) throws Exception {

				log.debug("updating Grupo instance");
				
	            try {
	            
	            	if(grupoDTO==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Grupo");
		    		}
	            	
	            	if(grupoDTO.getGrupId() == null) {
	            		throw new Exception("El id del grupo no debe ser vacío");
	            	}
		    		
	            	if(grupoDTO.getNombre() == null || grupoDTO.getNombre().trim().equals("")) {
				    	throw new Exception("El nombre no debe ser vacío");
				    }
				    
				    if(grupoDTO.getDescripcion() == null || grupoDTO.getDescripcion().trim().equals("")) {
				    	throw new Exception("La descripción no debe ser vacía");
				    }
				    
				    if(grupoDTO.getListUsuarios() == null || grupoDTO.getListUsuarios().size() == 0 || grupoDTO.getListUsuarios().isEmpty()) {
				    	throw new Exception("Debe seleccionar la población objetivo del grupo");
				    }
				    
				    Optional<Grupo> grupoOpt = grupoService.findById(grupoDTO.getGrupId());
				    if(grupoOpt.isEmpty()) {
				    	throw new Exception("El grupo no existe");
				    }
				    
				    Grupo grupo = grupoOpt.get();
				    
				    grupo.setNombre(grupoDTO.getNombre());
				    grupo.setDescripcion(grupoDTO.getDescripcion());
				    grupo.setFechaModificacion(new Date());
				    grupo.setUsuModificador(grupoDTO.getUsuModificador());
	            	
				    Grupo grupoResult = save(grupo);
				    
//				    List<UsuarioDTO> listUsuarioAntigua = usuarioRepository.getUsuariosPorGrupo(Constantes.ESTADO_ACTIVO, Integer.parseInt(grupoDTO.getGrupId().toString()));
				    List<GrupoUsuario> listGrupoUsuario = grupoUsuarioRepository.findByGrupoAndEstadoRegistro(grupoResult, Constantes.ESTADO_ACTIVO);
				    List<Long> listUsuarioNueva = grupoDTO.getListUsuarios();
				    
				    TreeMap<Long, Long> usuarioMap = new TreeMap<>();
				    
				    if(listGrupoUsuario != null && !listGrupoUsuario.isEmpty()) {
				    	for (GrupoUsuario grupoUsuario : listGrupoUsuario) {
				    		
				    		Long usuario = listUsuarioNueva.stream().filter(usu -> grupoUsuario.getUsuario().getUsuaId().equals(Integer.parseInt(usu.toString()))).findFirst().orElse(null);
				    		
				    		if(usuario == null && grupoUsuario.getEstadoRegistro() !=null 
				    				&& grupoUsuario.getEstadoRegistro().equals(Constantes.ESTADO_ACTIVO)) {
				    			
				    			grupoUsuario.setEstadoRegistro(Constantes.ESTADO_INACTIVO);
				    			grupoUsuario.setFechaModificacion(new Date());
				    			grupoUsuario.setUsuModificador(grupoDTO.getUsuModificador());
				    			
				    			grupoUsuarioService.save(grupoUsuario);
				    			
				    		}else {
				    			if(usuario != null) {
				    				
				    				grupoUsuario.setFechaModificacion(new Date());
				    				grupoUsuario.setUsuModificador(grupoDTO.getUsuModificador());
				    				
				    				grupoUsuarioService.save(grupoUsuario);
				    				
				    				usuarioMap.put(usuario, usuario);
				    				
				    			}
				    		}
				    		
				    	}
				    }
				    
				    for(Long usuario : listUsuarioNueva) {
				    	if(!usuarioMap.containsKey(usuario)) {
				    		Optional<Usuario> usuarioOpt = usuarioService.findById(Integer.parseInt(usuario.toString()));
				    		if(usuarioOpt.isEmpty()) {
				    			throw new Exception("El usuario no existe");
				    		}
				    		Usuario usuarioNuevo = usuarioOpt.get();
				    		
				    		GrupoUsuario grupoUsuario = new GrupoUsuario();
				    		grupoUsuario.setGrupo(grupoResult);
				    		grupoUsuario.setUsuario(usuarioNuevo);
				    		grupoUsuario.setFechaCreacion(new Date());
				    		grupoUsuario.setUsuCreador(grupoDTO.getUsuModificador());
				    		grupoUsuario.setEstadoRegistro(Constantes.ESTADO_ACTIVO);
				    		
				    		grupoUsuarioService.save(grupoUsuario);
				    	}
				    }

				    
				    return grupoMapper.grupoToGrupoDTO(grupoResult);
	            
	            } catch (Exception e) {
	            	log.error("update Grupo failed", e);
	            	throw e;		
	            }
            }
}
