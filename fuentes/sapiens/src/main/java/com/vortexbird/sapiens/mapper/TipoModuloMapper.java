package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.TipoModulo;
import com.vortexbird.sapiens.dto.TipoModuloDTO;

import org.mapstruct.Mapper;


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
public interface TipoModuloMapper {
    public TipoModuloDTO tipoModuloToTipoModuloDTO(TipoModulo tipoModulo)
        throws Exception;

    public TipoModulo tipoModuloDTOToTipoModulo(TipoModuloDTO tipoModuloDTO)
        throws Exception;

    public List<TipoModuloDTO> listTipoModuloToListTipoModuloDTO(
        List<TipoModulo> tipoModulos) throws Exception;

    public List<TipoModulo> listTipoModuloDTOToListTipoModulo(
        List<TipoModuloDTO> tipoModuloDTOs) throws Exception;
}
