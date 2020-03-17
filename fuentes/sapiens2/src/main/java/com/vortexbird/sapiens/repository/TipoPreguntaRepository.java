package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.TipoPregunta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   TipoPreguntaRepository.
*
*/
public interface TipoPreguntaRepository extends JpaRepository<TipoPregunta, Integer> {
}
