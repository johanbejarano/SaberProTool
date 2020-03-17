package com.vortexbird.sapiens.repository;

import com.vortexbird.sapiens.domain.Pregunta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   PreguntaRepository.
*
*/
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
}
