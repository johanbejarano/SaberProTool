package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.PruebaModulo;
import com.vortexbird.sapiens.dto.PruebaModuloDTO;

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
public interface PruebaModuloMapper {
    @Mapping(source = "modulo.moduId", target = "moduId_Modulo")
    @Mapping(source = "prueba.prueId", target = "prueId_Prueba")
    public PruebaModuloDTO pruebaModuloToPruebaModuloDTO(
        PruebaModulo pruebaModulo) throws Exception;

    @Mapping(source = "moduId_Modulo", target = "modulo.moduId")
    @Mapping(source = "prueId_Prueba", target = "prueba.prueId")
    public PruebaModulo pruebaModuloDTOToPruebaModulo(
        PruebaModuloDTO pruebaModuloDTO) throws Exception;

    public List<PruebaModuloDTO> listPruebaModuloToListPruebaModuloDTO(
        List<PruebaModulo> pruebaModulos) throws Exception;

    public List<PruebaModulo> listPruebaModuloDTOToListPruebaModulo(
        List<PruebaModuloDTO> pruebaModuloDTOs) throws Exception;
}
