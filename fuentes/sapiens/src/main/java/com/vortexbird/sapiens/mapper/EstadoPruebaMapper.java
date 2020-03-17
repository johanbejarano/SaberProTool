package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.EstadoPrueba;
import com.vortexbird.sapiens.dto.EstadoPruebaDTO;

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
public interface EstadoPruebaMapper {
    public EstadoPruebaDTO estadoPruebaToEstadoPruebaDTO(
        EstadoPrueba estadoPrueba) throws Exception;

    public EstadoPrueba estadoPruebaDTOToEstadoPrueba(
        EstadoPruebaDTO estadoPruebaDTO) throws Exception;

    public List<EstadoPruebaDTO> listEstadoPruebaToListEstadoPruebaDTO(
        List<EstadoPrueba> estadoPruebas) throws Exception;

    public List<EstadoPrueba> listEstadoPruebaDTOToListEstadoPrueba(
        List<EstadoPruebaDTO> estadoPruebaDTOs) throws Exception;
}
