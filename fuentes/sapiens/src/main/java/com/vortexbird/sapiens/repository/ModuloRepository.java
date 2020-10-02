package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.Modulo;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   ModuloRepository.
*
*/
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
	List<Modulo> findByTipoModulo_timoIdAndEstadoRegistro(Integer timoId, String estado);

	List<Modulo> findByEstadoRegistro(String estado);
}
