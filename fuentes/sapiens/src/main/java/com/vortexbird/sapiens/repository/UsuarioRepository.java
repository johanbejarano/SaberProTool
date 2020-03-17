package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
* Interface for   UsuarioRepository.
*
*/
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findByCodigo(String codigo);
	
}
