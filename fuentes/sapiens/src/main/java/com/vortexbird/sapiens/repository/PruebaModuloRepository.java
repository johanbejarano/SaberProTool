package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.domain.PruebaModulo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   PruebaModuloRepository.
*
*/
public interface PruebaModuloRepository extends JpaRepository<PruebaModulo, Integer> {
	
	List<Prueba> findByUsuCreadorAndModulo_TipoModulo_timoId(Long usuCreador, Integer timoId);
	
	List<PruebaModulo> findByModulo_TipoModulo_timoId(Integer timoId);
	
	List<PruebaModulo> findByModulo_nombre(String nombre);
}
