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
public class PruebaUsuarioServiceImpl implements PruebaUsuarioService{

	private static final Logger log = LoggerFactory.getLogger(PruebaUsuarioServiceImpl.class);

	@Autowired
	private PruebaUsuarioRepository pruebaUsuarioRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(PruebaUsuario pruebaUsuario)throws Exception{		
		 try {
			Set<ConstraintViolation<PruebaUsuario>> constraintViolations =validator.validate(pruebaUsuario);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<PruebaUsuario> constraintViolation : constraintViolations) {
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
	 	return pruebaUsuarioRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PruebaUsuario> findAll(){
		log.debug("finding all PruebaUsuario instances");
       	return pruebaUsuarioRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public PruebaUsuario save(PruebaUsuario entity) throws Exception {
		log.debug("saving PruebaUsuario instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
		}
		
		validate(entity);	
	
//		if(pruebaUsuarioRepository.findById(entity.getPrusId()).isPresent()){
//           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//        }    
	
	    return pruebaUsuarioRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save PruebaUsuario failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(PruebaUsuario entity) throws Exception {
            	log.debug("deleting PruebaUsuario instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
	    		}
    	
                                if(entity.getPrusId()==null){
                    throw new ZMessManager().new EmptyFieldException("prusId");
                    }
                        
            	            findById(entity.getPrusId()).ifPresent(entidad->{	            	
	                	                    List<DetallePruebaUsuario> detallePruebaUsuarios = entidad.getDetallePruebaUsuarios();
	                    
	                    if(Utilities.validationsList(detallePruebaUsuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("detallePruebaUsuarios");
                        }
	                	            });
                       

            try {
            
            pruebaUsuarioRepository.deleteById(entity.getPrusId());
            log.debug("delete PruebaUsuario successful");
            
            } catch (Exception e) {
            	log.error("delete PruebaUsuario failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting PruebaUsuario instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("prusId");
            	}
            	if(pruebaUsuarioRepository.findById(id).isPresent()){
           			delete(pruebaUsuarioRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public PruebaUsuario update(PruebaUsuario entity) throws Exception {

				log.debug("updating PruebaUsuario instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("PruebaUsuario");
		    		}
		    		
	            validate(entity);
	
	            return pruebaUsuarioRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update PruebaUsuario failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<PruebaUsuario> findById(Integer prusId) throws Exception {            
            	log.debug("getting PruebaUsuario instance");
            	return pruebaUsuarioRepository.findById(prusId);
            }
			
}
