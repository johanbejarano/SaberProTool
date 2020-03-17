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
public class EstadoPruebaServiceImpl implements EstadoPruebaService{

	private static final Logger log = LoggerFactory.getLogger(EstadoPruebaServiceImpl.class);

	@Autowired
	private EstadoPruebaRepository estadoPruebaRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(EstadoPrueba estadoPrueba)throws Exception{		
		 try {
			Set<ConstraintViolation<EstadoPrueba>> constraintViolations =validator.validate(estadoPrueba);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<EstadoPrueba> constraintViolation : constraintViolations) {
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
	 	return estadoPruebaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<EstadoPrueba> findAll(){
		log.debug("finding all EstadoPrueba instances");
       	return estadoPruebaRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public EstadoPrueba save(EstadoPrueba entity) throws Exception {
		log.debug("saving EstadoPrueba instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("EstadoPrueba");
		}
		
		validate(entity);	
	
		if(estadoPruebaRepository.findById(entity.getEsprId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return estadoPruebaRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save EstadoPrueba failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(EstadoPrueba entity) throws Exception {
            	log.debug("deleting EstadoPrueba instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("EstadoPrueba");
	    		}
    	
                                if(entity.getEsprId()==null){
                    throw new ZMessManager().new EmptyFieldException("esprId");
                    }
                        
            	            findById(entity.getEsprId()).ifPresent(entidad->{	            	
	                	                    List<PruebaUsuario> pruebaUsuarios = entidad.getPruebaUsuarios();
	                    
	                    if(Utilities.validationsList(pruebaUsuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("pruebaUsuarios");
                        }
	                	            });
                       

            try {
            
            estadoPruebaRepository.deleteById(entity.getEsprId());
            log.debug("delete EstadoPrueba successful");
            
            } catch (Exception e) {
            	log.error("delete EstadoPrueba failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting EstadoPrueba instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("esprId");
            	}
            	if(estadoPruebaRepository.findById(id).isPresent()){
           			delete(estadoPruebaRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public EstadoPrueba update(EstadoPrueba entity) throws Exception {

				log.debug("updating EstadoPrueba instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("EstadoPrueba");
		    		}
		    		
	            validate(entity);
	
	            return estadoPruebaRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update EstadoPrueba failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<EstadoPrueba> findById(Integer esprId) throws Exception {            
            	log.debug("getting EstadoPrueba instance");
            	return estadoPruebaRepository.findById(esprId);
            }
			
}
