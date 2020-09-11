package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Contexto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


/**
* Interface for   ContextoRepository.
*
*/
public interface ContextoRepository extends JpaRepository<Contexto, Integer> {

	List<Contexto> findByModulo_moduIdAndEstadoRegistro(Integer moduId, String estado);
}
