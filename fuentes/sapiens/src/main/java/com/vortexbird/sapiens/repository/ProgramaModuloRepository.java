package com.vortexbird.sapiens.repository;

import java.util.List;

import com.vortexbird.sapiens.domain.ProgramaModulo;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   ProgramaModuloRepository.
*
*/
public interface ProgramaModuloRepository extends JpaRepository<ProgramaModulo, Integer> {

	List<ProgramaModulo> findByPrograma_progId(Integer progId);
}
