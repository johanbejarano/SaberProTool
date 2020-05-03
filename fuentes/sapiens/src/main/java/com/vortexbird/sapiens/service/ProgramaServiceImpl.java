package  com.vortexbird.sapiens.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.ProgramaRepository;
import com.vortexbird.sapiens.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
* 
*/

@Scope("singleton")
@Service
public class ProgramaServiceImpl implements ProgramaService{

	private static final Logger log = LoggerFactory.getLogger(ProgramaServiceImpl.class);

	@Autowired
	private ProgramaRepository programaRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Programa programa)throws Exception{		
		 try {
			Set<ConstraintViolation<Programa>> constraintViolations =validator.validate(programa);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<Programa> constraintViolation : constraintViolations) {
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
	 	return programaRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Programa> findAll(){
		log.debug("finding all Programa instances");
       	return programaRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public Programa save(Programa entity) throws Exception {
		log.debug("saving Programa instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Programa");
		}
		
		validate(entity);	
	
		if(programaRepository.findById(entity.getProgId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return programaRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save Programa failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(Programa entity) throws Exception {
            	log.debug("deleting Programa instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Programa");
	    		}
    	
                                if(entity.getProgId()==null){
                    throw new ZMessManager().new EmptyFieldException("progId");
                    }
                        
            	            findById(entity.getProgId()).ifPresent(entidad->{	            	
	                	                    List<ProgramaModulo> programaModulos = entidad.getProgramaModulos();
	                    
	                    if(Utilities.validationsList(programaModulos)==true){
                       	 	throw new ZMessManager().new DeletingException("programaModulos");
                        }
	                	                    List<Usuario> usuarios = entidad.getUsuarios();
	                    
	                    if(Utilities.validationsList(usuarios)==true){
                       	 	throw new ZMessManager().new DeletingException("usuarios");
                        }
	                	            });
                       

            try {
            
            programaRepository.deleteById(entity.getProgId());
            log.debug("delete Programa successful");
            
            } catch (Exception e) {
            	log.error("delete Programa failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Programa instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("progId");
            	}
            	if(programaRepository.findById(id).isPresent()){
           			delete(programaRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public Programa update(Programa entity) throws Exception {

				log.debug("updating Programa instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Programa");
		    		}
		    		
	            validate(entity);
	
	            return programaRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update Programa failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Programa> findById(Integer progId) throws Exception {            
            	log.debug("getting Programa instance");
            	return programaRepository.findById(progId);
            }
			
}
