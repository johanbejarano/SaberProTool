package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.Prueba;
import com.vortexbird.sapiens.dto.PruebaDTO;

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
public interface PruebaMapper {
    @Mapping(source = "tipoPrueba.tiprId", target = "tiprId_TipoPrueba")
    @Mapping(source = "tipoPrueba.nombre", target = "nombreTipoPrueba")
    public PruebaDTO pruebaToPruebaDTO(Prueba prueba) throws Exception;

    @Mapping(source = "tiprId_TipoPrueba", target = "tipoPrueba.tiprId")
    public Prueba pruebaDTOToPrueba(PruebaDTO pruebaDTO)
        throws Exception;

    public List<PruebaDTO> listPruebaToListPruebaDTO(List<Prueba> pruebas)
        throws Exception;

    public List<Prueba> listPruebaDTOToListPrueba(List<PruebaDTO> pruebaDTOs)
        throws Exception;
}
