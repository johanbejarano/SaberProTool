package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.TipoPrueba;
import com.vortexbird.sapiens.dto.TipoPruebaDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org/
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface TipoPruebaMapper {
    public TipoPruebaDTO tipoPruebaToTipoPruebaDTO(TipoPrueba tipoPrueba)
        throws Exception;

    public TipoPrueba tipoPruebaDTOToTipoPrueba(TipoPruebaDTO tipoPruebaDTO)
        throws Exception;

    public List<TipoPruebaDTO> listTipoPruebaToListTipoPruebaDTO(
        List<TipoPrueba> tipoPruebas) throws Exception;

    public List<TipoPrueba> listTipoPruebaDTOToListTipoPrueba(
        List<TipoPruebaDTO> tipoPruebaDTOs) throws Exception;
}
