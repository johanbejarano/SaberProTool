package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.dto.PruebaDTO;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface PruebaService extends GenericService<Prueba, Integer> {

	List<PruebaDTO> getPruebasDeUsuarioCreador(Long usuCreador) throws Exception;
}
