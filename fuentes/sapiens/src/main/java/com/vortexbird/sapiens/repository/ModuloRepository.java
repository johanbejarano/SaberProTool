package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Modulo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


/**
* Interface for   ModuloRepository.
*
*/
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
	List<Modulo> findByTipoModulo_timoId(Integer timoId);
}
