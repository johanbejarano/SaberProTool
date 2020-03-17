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
public class PreguntaServiceImpl implements PreguntaService{

	private static final Logger log = LoggerFactory.getLogger(PreguntaServiceImpl.class);

	@Autowired
	private PreguntaRepository preguntaRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Pregunta pregunta)throws Exception{		
		 try {
			Set<ConstraintViolation<Pregunta>> constraintViolations =validator.validate(pregunta);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Pregunta> constraintViolation : constraintViolations) {
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
	 	return preguntaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pregunta> findAll(){
		log.debug("finding all Pregunta instances");
       	return preguntaRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Pregunta save(Pregunta entity) throws Exception {
		log.debug("saving Pregunta instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Pregunta");
		}
		
		validate(entity);	
	
		if(preguntaRepository.findById(entity.getPregId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return preguntaRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Pregunta failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Pregunta entity) throws Exception {
            	log.debug("deleting Pregunta instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Pregunta");
	    		}
    	
                                if(entity.getPregId()==null){
                    throw new ZMessManager().new EmptyFieldException("pregId");
                    }
                        
            	            findById(entity.getPregId()).ifPresent(entidad->{	            	
	                	                    List<DetallePruebaUsuario> detallePruebaUsuarios = entidad.getDetallePruebaUsuarios();
	                    
	                    if(Utilities.validationsList(detallePruebaUsuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("detallePruebaUsuarios");
                        }
	                	                    List<Respuesta> respuestas = entidad.getRespuestas();
	                    
	                    if(Utilities.validationsList(respuestas)==true){
                       	 	throw new ZMessManager().new DeletingException("respuestas");
                        }
	                	            });
                       

            try {
            
            preguntaRepository.deleteById(entity.getPregId());
            log.debug("delete Pregunta successful");
            
            } catch (Exception e) {
            	log.error("delete Pregunta failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Pregunta instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("pregId");
            	}
            	if(preguntaRepository.findById(id).isPresent()){
           			delete(preguntaRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Pregunta update(Pregunta entity) throws Exception {

				log.debug("updating Pregunta instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Pregunta");
		    		}
		    		
	            validate(entity);
	
	            return preguntaRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Pregunta failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Pregunta> findById(Integer pregId) throws Exception {            
            	log.debug("getting Pregunta instance");
            	return preguntaRepository.findById(pregId);
            }
			
}
