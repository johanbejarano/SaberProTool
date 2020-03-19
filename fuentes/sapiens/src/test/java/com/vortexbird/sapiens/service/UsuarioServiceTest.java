package com.vortexbird.sapiens.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.domain.TipoUsuario;
import com.vortexbird.sapiens.domain.Usuario;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@SpringBootTest
public class UsuarioServiceTest {
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceTest.class);
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ProgramaService programaService;
    
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

//    @Test
//    @DisplayName("findAll")
//    public void findAll() {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("save")
//    public void save() throws Exception {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("delete")
//    public void delete() throws Exception {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("deleteById")
//    public void deleteById() throws Exception {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("update")
//    public void update() throws Exception {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("findById")
//    public void findById() throws Exception {
//        assertNotNull(usuarioService);
//    }
//
//    @Test
//    @DisplayName("count")
//    public void count() throws Exception {
//        assertNotNull(usuarioService);
//    }
    
//    @Test
//    @DisplayName("save")
    public void guardarUsuarios() {
    	
    	try {
    		
    		//Se consulta el programa
    		Optional<Programa> programa = programaService.findById(1);
    		
    		Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(2);
			
    		for (int i = 1; i <= 200; i++) {
    			Usuario usuario = new Usuario();
				
    			usuario.setApellido("BEJARANO 1");
    			usuario.setCelular(""+i);
    			usuario.setCodigo((9836700+i)+"");
    			usuario.setCorreo("email"+i+"@hotmail.com");
    			usuario.setEstadoRegistro("A");
    			usuario.setFechaCreacion(new Date());
    			usuario.setGenero("M");
    			usuario.setIdentificacion((16935984L+i));
    			usuario.setNombre("NOMBRE " + i);
    			usuario.setPassword("LpY/T9RU/T0JPFnwxPm3iHnH0HjElo65Q1zuqRLZ4zsC4eA2luOijBT9IpOuUBmrzUUpWMch7VucHvRwhKqyIA==");
    			usuario.setPrograma(programa.get());
    			usuario.setTipoUsuario(tipoUsuario.get());
    			usuario.setUsuCreador(1L);
    			
    			usuarioService.save(usuario);
    			
			}
    		
    		
		} catch (Exception e) {
			log.error("Error", e);
			fail();
		}
    	
    }
}
