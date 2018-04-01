package com.saberpro.dataaccess.dao;

import com.saberpro.dataaccess.api.DaoException;
import com.saberpro.dataaccess.api.JpaDaoImpl;

import com.saberpro.modelo.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * A data access object (DAO) providing persistence and search support for
 * Usuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Usuario
 */
@Scope("singleton")
@Repository("UsuarioDAO")
public class UsuarioDAO extends JpaDaoImpl<Usuario, Long> implements IUsuarioDAO {
    private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static IUsuarioDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IUsuarioDAO) ctx.getBean("UsuarioDAO");
    }

	@Override
	public Usuario findByCodigo(long codigo) {
		return (Usuario)entityManager.createQuery("SELECT usu FROM Usuario usu WHERE usu.codigo=:codigo").setParameter("codigo",codigo).getSingleResult();	
		
	}
	
	@Override
	public void save(Usuario usuario)throws DaoException {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		entityManager.persist(usuario);
	}
	@Override
	public void update(Usuario usuario) throws DaoException {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        entityManager.merge(usuario);
    }
    
}
