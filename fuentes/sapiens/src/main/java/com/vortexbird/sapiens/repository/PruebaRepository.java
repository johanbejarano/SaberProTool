package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Prueba;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


/**
* Interface for   PruebaRepository.
*
*/
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {
	
	List<Prueba> findByUsuCreador(Long usuCreador);
	
}
