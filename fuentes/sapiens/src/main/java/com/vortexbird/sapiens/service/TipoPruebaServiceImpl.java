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
public class TipoPruebaServiceImpl implements TipoPruebaService{

	private static final Logger log = LoggerFactory.getLogger(TipoPruebaServiceImpl.class);

	@Autowired
	private TipoPruebaRepository tipoPruebaRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(TipoPrueba tipoPrueba)throws Exception{		
		 try {
			Set<ConstraintViolation<TipoPrueba>> constraintViolations =validator.validate(tipoPrueba);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<TipoPrueba> constraintViolation : constraintViolations) {
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
	 	return tipoPruebaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoPrueba> findAll(){
		log.debug("finding all TipoPrueba instances");
       	return tipoPruebaRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public TipoPrueba save(TipoPrueba entity) throws Exception {
		log.debug("saving TipoPrueba instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("TipoPrueba");
		}
		
		validate(entity);	
	
		if(tipoPruebaRepository.findById(entity.getTiprId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return tipoPruebaRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save TipoPrueba failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(TipoPrueba entity) throws Exception {
            	log.debug("deleting TipoPrueba instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("TipoPrueba");
	    		}
    	
                                if(entity.getTiprId()==null){
                    throw new ZMessManager().new EmptyFieldException("tiprId");
                    }
                        
            	            findById(entity.getTiprId()).ifPresent(entidad->{	            	
	                	                    List<Prueba> pruebas = entidad.getPruebas();
	                    
	                    if(Utilities.validationsList(pruebas)==true){
                       	 	throw new ZMessManager().new DeletingException("pruebas");
                        }
	                	            });
                       

            try {
            
            tipoPruebaRepository.deleteById(entity.getTiprId());
            log.debug("delete TipoPrueba successful");
            
            } catch (Exception e) {
            	log.error("delete TipoPrueba failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting TipoPrueba instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("tiprId");
            	}
            	if(tipoPruebaRepository.findById(id).isPresent()){
           			delete(tipoPruebaRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public TipoPrueba update(TipoPrueba entity) throws Exception {

				log.debug("updating TipoPrueba instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("TipoPrueba");
		    		}
		    		
	            validate(entity);
	
	            return tipoPruebaRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update TipoPrueba failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<TipoPrueba> findById(Integer tiprId) throws Exception {            
            	log.debug("getting TipoPrueba instance");
            	return tipoPruebaRepository.findById(tiprId);
            }
			
}
