package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Grupo;
import com.vortexbird.sapiens.domain.GrupoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


/**
* Interface for   GrupoUsuarioRepository.
*
*/
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Long> {
	List<GrupoUsuario> findByGrupoAndEstadoRegistro(Grupo grupo, String estado);
}
