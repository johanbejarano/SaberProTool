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
public class ModuloServiceImpl implements ModuloService{

	private static final Logger log = LoggerFactory.getLogger(ModuloServiceImpl.class);

	@Autowired
	private ModuloRepository moduloRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Modulo modulo)throws Exception{		
		 try {
			Set<ConstraintViolation<Modulo>> constraintViolations =validator.validate(modulo);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Modulo> constraintViolation : constraintViolations) {
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
	 	return moduloRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Modulo> findAll(){
		log.debug("finding all Modulo instances");
       	return moduloRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Modulo save(Modulo entity) throws Exception {
		log.debug("saving Modulo instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Modulo");
		}
		
		validate(entity);	
	
		if(moduloRepository.findById(entity.getModuId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return moduloRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Modulo failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Modulo entity) throws Exception {
            	log.debug("deleting Modulo instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Modulo");
	    		}
    	
                                if(entity.getModuId()==null){
                    throw new ZMessManager().new EmptyFieldException("moduId");
                    }
                        
            	            findById(entity.getModuId()).ifPresent(entidad->{	            	
	                	                    List<Pregunta> preguntas = entidad.getPreguntas();
	                    
	                    if(Utilities.validationsList(preguntas)==true){
                       	 	throw new ZMessManager().new DeletingException("preguntas");
                        }
	                	                    List<ProgramaModulo> programaModulos = entidad.getProgramaModulos();
	                    
	                    if(Utilities.validationsList(programaModulos)==true){
                       	 	throw new ZMessManager().new DeletingException("programaModulos");
                        }
	                	                    List<PruebaModulo> pruebaModulos = entidad.getPruebaModulos();
	                    
	                    if(Utilities.validationsList(pruebaModulos)==true){
                       	 	throw new ZMessManager().new DeletingException("pruebaModulos");
                        }
	                	            });
                       

            try {
            
            moduloRepository.deleteById(entity.getModuId());
            log.debug("delete Modulo successful");
            
            } catch (Exception e) {
            	log.error("delete Modulo failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Modulo instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("moduId");
            	}
            	if(moduloRepository.findById(id).isPresent()){
           			delete(moduloRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Modulo update(Modulo entity) throws Exception {

				log.debug("updating Modulo instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Modulo");
		    		}
		    		
	            validate(entity);
	
	            return moduloRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Modulo failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Modulo> findById(Integer moduId) throws Exception {            
            	log.debug("getting Modulo instance");
            	return moduloRepository.findById(moduId);
            }
			
}
