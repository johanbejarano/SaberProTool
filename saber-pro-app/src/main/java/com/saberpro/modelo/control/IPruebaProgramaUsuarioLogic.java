package com.saberpro.modelo.control;

import com.saberpro.modelo.PruebaProgramaUsuario;
import com.saberpro.modelo.dto.PruebaProgramaUsuarioDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IPruebaProgramaUsuarioLogic {
    public List<PruebaProgramaUsuario> getPruebaProgramaUsuario()
        throws Exception;

    /**
         * Save an new PruebaProgramaUsuario entity
         */
    public void savePruebaProgramaUsuario(PruebaProgramaUsuario entity)
        throws Exception;

    /**
         * Delete an existing PruebaProgramaUsuario entity
         *
         */
    public void deletePruebaProgramaUsuario(PruebaProgramaUsuario entity)
        throws Exception;

    /**
        * Update an existing PruebaProgramaUsuario entity
        *
        */
    public void updatePruebaProgramaUsuario(PruebaProgramaUsuario entity)
        throws Exception;

    /**
         * Load an existing PruebaProgramaUsuario entity
         *
         */
    public PruebaProgramaUsuario getPruebaProgramaUsuario(
        Long idPruebaProgramaUsuario) throws Exception;

    public List<PruebaProgramaUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<PruebaProgramaUsuario> findPagePruebaProgramaUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberPruebaProgramaUsuario()
        throws Exception;

    public List<PruebaProgramaUsuarioDTO> getDataPruebaProgramaUsuario()
        throws Exception;

    public void validatePruebaProgramaUsuario(
        PruebaProgramaUsuario pruebaProgramaUsuario) throws Exception;
}
