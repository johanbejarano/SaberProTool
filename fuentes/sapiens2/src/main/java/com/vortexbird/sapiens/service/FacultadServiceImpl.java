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
public class FacultadServiceImpl implements FacultadService{

	private static final Logger log = LoggerFactory.getLogger(FacultadServiceImpl.class);

	@Autowired
	private FacultadRepository facultadRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Facultad facultad)throws Exception{		
		 try {
			Set<ConstraintViolation<Facultad>> constraintViolations =validator.validate(facultad);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Facultad> constraintViolation : constraintViolations) {
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
	 	return facultadRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Facultad> findAll(){
		log.debug("finding all Facultad instances");
       	return facultadRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Facultad save(Facultad entity) throws Exception {
		log.debug("saving Facultad instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Facultad");
		}
		
		validate(entity);	
	
		if(facultadRepository.findById(entity.getFacuId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return facultadRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Facultad failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Facultad entity) throws Exception {
            	log.debug("deleting Facultad instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Facultad");
	    		}
    	
                                if(entity.getFacuId()==null){
                    throw new ZMessManager().new EmptyFieldException("facuId");
                    }
                        
            	            findById(entity.getFacuId()).ifPresent(entidad->{	            	
	                	                    List<Programa> programas = entidad.getProgramas();
	                    
	                    if(Utilities.validationsList(programas)==true){
                       	 	throw new ZMessManager().new DeletingException("programas");
                        }
	                	            });
                       

            try {
            
            facultadRepository.deleteById(entity.getFacuId());
            log.debug("delete Facultad successful");
            
            } catch (Exception e) {
            	log.error("delete Facultad failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Facultad instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("facuId");
            	}
            	if(facultadRepository.findById(id).isPresent()){
           			delete(facultadRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Facultad update(Facultad entity) throws Exception {

				log.debug("updating Facultad instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Facultad");
		    		}
		    		
	            validate(entity);
	
	            return facultadRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Facultad failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Facultad> findById(Integer facuId) throws Exception {            
            	log.debug("getting Facultad instance");
            	return facultadRepository.findById(facuId);
            }
			
}
