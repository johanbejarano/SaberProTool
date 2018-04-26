package com.saberpro.dataaccess.dao;

import com.saberpro.dataaccess.api.JpaDaoImpl;
import com.saberpro.modelo.EstadoPrueba;
import com.saberpro.modelo.Modulo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Modulo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Modulo
 */
@Scope("singleton")
@Repository("ModuloDAO")
public class ModuloDAO extends JpaDaoImpl<Modulo, Long> implements IModuloDAO {
    private static final Logger log = LoggerFactory.getLogger(ModuloDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public static IModuloDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IModuloDAO) ctx.getBean("ModuloDAO");
    }
    
    @Override
	public Modulo findByNombre(String nombre) {
		return (Modulo)entityManager.createQuery("SELECT est FROM Modulo est WHERE UPPER(sinacentos(est.nombre))=UPPER(sinacentos(:nombre))").setParameter("nombre",nombre).getSingleResult();
	}
}
