package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   TipoUsuarioRepository.
*
*/
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}
