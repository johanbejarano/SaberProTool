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
public class TipoPreguntaServiceImpl implements TipoPreguntaService{

	private static final Logger log = LoggerFactory.getLogger(TipoPreguntaServiceImpl.class);

	@Autowired
	private TipoPreguntaRepository tipoPreguntaRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(TipoPregunta tipoPregunta)throws Exception{		
		 try {
			Set<ConstraintViolation<TipoPregunta>> constraintViolations =validator.validate(tipoPregunta);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<TipoPregunta> constraintViolation : constraintViolations) {
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
	 	return tipoPreguntaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoPregunta> findAll(){
		log.debug("finding all TipoPregunta instances");
       	return tipoPreguntaRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public TipoPregunta save(TipoPregunta entity) throws Exception {
		log.debug("saving TipoPregunta instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("TipoPregunta");
		}
		
		validate(entity);	
	
		if(tipoPreguntaRepository.findById(entity.getTprgId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return tipoPreguntaRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save TipoPregunta failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(TipoPregunta entity) throws Exception {
            	log.debug("deleting TipoPregunta instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("TipoPregunta");
	    		}
    	
                                if(entity.getTprgId()==null){
                    throw new ZMessManager().new EmptyFieldException("tprgId");
                    }
                        
            	            findById(entity.getTprgId()).ifPresent(entidad->{	            	
	                	                    List<Pregunta> preguntas = entidad.getPreguntas();
	                    
	                    if(Utilities.validationsList(preguntas)==true){
                       	 	throw new ZMessManager().new DeletingException("preguntas");
                        }
	                	            });
                       

            try {
            
            tipoPreguntaRepository.deleteById(entity.getTprgId());
            log.debug("delete TipoPregunta successful");
            
            } catch (Exception e) {
            	log.error("delete TipoPregunta failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting TipoPregunta instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("tprgId");
            	}
            	if(tipoPreguntaRepository.findById(id).isPresent()){
           			delete(tipoPreguntaRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public TipoPregunta update(TipoPregunta entity) throws Exception {

				log.debug("updating TipoPregunta instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("TipoPregunta");
		    		}
		    		
	            validate(entity);
	
	            return tipoPreguntaRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update TipoPregunta failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<TipoPregunta> findById(Integer tprgId) throws Exception {            
            	log.debug("getting TipoPregunta instance");
            	return tipoPreguntaRepository.findById(tprgId);
            }
			
}
