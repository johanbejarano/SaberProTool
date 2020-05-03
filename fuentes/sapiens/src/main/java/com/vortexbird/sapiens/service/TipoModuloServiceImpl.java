package  com.vortexbird.sapiens.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.domain.TipoModulo;
import com.vortexbird.sapiens.exception.ZMessManager;
import com.vortexbird.sapiens.repository.TipoModuloRepository;
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
public class TipoModuloServiceImpl implements TipoModuloService{

	private static final Logger log = LoggerFactory.getLogger(TipoModuloServiceImpl.class);

	@Autowired
	private TipoModuloRepository tipoModuloRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(TipoModulo tipoModulo)throws Exception{		
		 try {
			Set<ConstraintViolation<TipoModulo>> constraintViolations =validator.validate(tipoModulo);
			 if(constraintViolations.size()>0){
				 StringBuilder strMessage=new StringBuilder();
				 for (ConstraintViolation<TipoModulo> constraintViolation : constraintViolations) {
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
	 	return tipoModuloRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoModulo> findAll(){
		log.debug("finding all TipoModulo instances");
       	return tipoModuloRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)			
    public TipoModulo save(TipoModulo entity) throws Exception {
		log.debug("saving TipoModulo instance");
	    try {
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("TipoModulo");
		}
		
		validate(entity);	
	
		if(tipoModuloRepository.findById(entity.getTimoId()).isPresent()){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return tipoModuloRepository.save(entity);
	    
	    } catch (Exception e) {
	    	log.error("save TipoModulo failed", e);
	    	throw e;
	    }
    }
			
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void delete(TipoModulo entity) throws Exception {
            	log.debug("deleting TipoModulo instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("TipoModulo");
	    		}
    	
                                if(entity.getTimoId()==null){
                    throw new ZMessManager().new EmptyFieldException("timoId");
                    }
                        
            	            findById(entity.getTimoId()).ifPresent(entidad->{	            	
	                	                    List<Modulo> modulos = entidad.getModulos();
	                    
	                    if(Utilities.validationsList(modulos)==true){
                       	 	throw new ZMessManager().new DeletingException("modulos");
                        }
	                	            });
                       

            try {
            
            tipoModuloRepository.deleteById(entity.getTimoId());
            log.debug("delete TipoModulo successful");
            
            } catch (Exception e) {
            	log.error("delete TipoModulo failed", e);
            	throw e;
            }
            	
            }
            
            @Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting TipoModulo instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("timoId");
            	}
            	if(tipoModuloRepository.findById(id).isPresent()){
           			delete(tipoModuloRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
            public TipoModulo update(TipoModulo entity) throws Exception {

				log.debug("updating TipoModulo instance");
				
	            try {
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("TipoModulo");
		    		}
		    		
	            validate(entity);
	
	            return tipoModuloRepository.save(entity);
	            
	            } catch (Exception e) {
	            	log.error("update TipoModulo failed", e);
	            	throw e;		
	            }
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<TipoModulo> findById(Integer timoId) throws Exception {            
            	log.debug("getting TipoModulo instance");
            	return tipoModuloRepository.findById(timoId);
            }
			
}
