package com.saberpro.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.saberpro.dataaccess.api.DaoException;
import com.saberpro.dataaccess.dao.IModuloDAO;
import com.saberpro.dataaccess.dao.IPreguntaDAO;
import com.saberpro.dataaccess.dao.ITipoPreguntaDAO;
import com.saberpro.modelo.Pregunta;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

public class TestPreguntaDao {

	private final static Logger log = LoggerFactory.getLogger(TestPreguntaDao.class);
	private static long preId = 5L;
	
	@Autowired
	ITipoPreguntaDAO tipoPreguntaDao;
	
	@Autowired
	IModuloDAO moduloDao;
	
	@Autowired
	IPreguntaDAO preguntaDao;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testCreate() throws DaoException {
		
		assertNotNull("El preguntDao es null",preguntaDao);
		assertNotNull("El moduloDao es null",moduloDao);
		assertNotNull("El tipoPreguntaDao es null",tipoPreguntaDao);
		
		
		
		Pregunta pregunta = preguntaDao.findById(preId);
		
		assertNull("tipoPregunta ya existe",pregunta);
		
		pregunta = new Pregunta();
		pregunta.setActivo("S");
		pregunta.setDescripcionPregunta("esto es una prueba");
		pregunta.setFechaCreacion(new Date());
		pregunta.setModulo(moduloDao.findById(1L));
		pregunta.setUsuCreador(0L);		
		pregunta.setTipoPregunta(tipoPreguntaDao.findById(1L));
		
		preguntaDao.save(pregunta);
		preId = pregunta.getIdPregunta();
		
		log.info("Se creo la pregunta "+pregunta.toString());		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() throws DaoException {
		
		assertNotNull("El preguntDao es null",preguntaDao);
		assertNotNull("El moduloDao es null",moduloDao);
		assertNotNull("El tipoPreguntaDao es null",tipoPreguntaDao);		
		
		
		Pregunta pregunta = preguntaDao.findById(preId);
		
		assertNotNull("pregunta no ya existe",pregunta);	
			
		pregunta.setDescripcionPregunta("2+2");		
				
		preguntaDao.update(pregunta);		
		
		log.info("Se actualizo la  pregunta "+pregunta.toString());
		
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() throws DaoException {
		
		assertNotNull("El preguntDao es null",preguntaDao);
		assertNotNull("El moduloDao es null",moduloDao);
		assertNotNull("El tipoPreguntaDao es null",tipoPreguntaDao);		
		
		
		Pregunta pregunta = preguntaDao.findById(preId);
		
		assertNotNull("pregunta no ya existe",pregunta);			
			
				
		preguntaDao.deleteById(preId);
		//preguntaDao.delete(pregunta);
		
		log.info("Se borro la pregunta "+pregunta.toString());		
		
	}
	
	@Test	
	@Transactional(readOnly = true)
	public void testConsulta() {

		assertNotNull("El preguntDao es null",preguntaDao);
		assertNotNull("El moduloDao es null",moduloDao);
		assertNotNull("El tipoPreguntaDao es null",tipoPreguntaDao);
		
		
		List<Pregunta> list = preguntaDao.findAll();
		
		for (Pregunta pregunta : list) {
			log.info(pregunta.toString());
		}
		

	}

}
