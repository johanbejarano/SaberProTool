package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Grupo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


/**
* Interface for   GrupoRepository.
*
*/
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	List<Grupo> findByNombre(String nombre);
	List<Grupo> findByEstadoRegistro(String estadoRegistro);
}
