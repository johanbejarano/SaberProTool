package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.PruebaUsuario;
import com.vortexbird.sapiens.dto.PruebaUsuarioDTO;

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
public interface PruebaUsuarioMapper {
    @Mapping(source = "estadoPrueba.esprId", target = "esprId_EstadoPrueba")
    @Mapping(source = "usuario.usuaId", target = "usuaId_Usuario")
    @Mapping(source = "prueba.prueId", target = "prueId_Prueba")
    public PruebaUsuarioDTO pruebaUsuarioToPruebaUsuarioDTO(
        PruebaUsuario pruebaUsuario) throws Exception;

    @Mapping(source = "esprId_EstadoPrueba", target = "estadoPrueba.esprId")
    @Mapping(source = "usuaId_Usuario", target = "usuario.usuaId")
    @Mapping(source = "prueId_Prueba", target = "prueba.prueId")
    public PruebaUsuario pruebaUsuarioDTOToPruebaUsuario(
        PruebaUsuarioDTO pruebaUsuarioDTO) throws Exception;

    public List<PruebaUsuarioDTO> listPruebaUsuarioToListPruebaUsuarioDTO(
        List<PruebaUsuario> pruebaUsuarios) throws Exception;

    public List<PruebaUsuario> listPruebaUsuarioDTOToListPruebaUsuario(
        List<PruebaUsuarioDTO> pruebaUsuarioDTOs) throws Exception;
}
