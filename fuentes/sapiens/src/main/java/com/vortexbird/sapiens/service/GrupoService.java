package com.vortexbird.sapiens.service;

import com.vortexbird.sapiens.domain.Grupo;

import java.math.*;

import java.util.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface GrupoService extends GenericService<Grupo, Long> {

	List<Grupo> consultarGrupos();
}
