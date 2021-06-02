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
import com.vortexbird.sapiens.repository.*;
import com.vortexbird.sapiens.utility.Utilities;

import com.vortexbird.sapiens.domain.*;

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
		
		validate(entity);	
	
		if(grupoRepository.findById(entity.getGrupId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
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
			
}
