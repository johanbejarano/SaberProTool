package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.Prueba;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   PruebaRepository.
*
*/
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {
	
	List<Prueba> findByUsuCreador(Long usuCreador);
	
}
