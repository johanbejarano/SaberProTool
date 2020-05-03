package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.DetallePruebaUsuario;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


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
public interface DetallePruebaUsuarioMapper {
    @Mapping(source = "pruebaUsuario.prusId", target = "prusId")
    @Mapping(source = "respuesta.respId", target = "respId")
    @Mapping(source = "pregunta.pregId", target = "pregId")
    public DetallePruebaUsuarioDTO detallePruebaUsuarioToDetallePruebaUsuarioDTO(
        DetallePruebaUsuario detallePruebaUsuario) throws Exception;

    @Mapping(source = "prusId", target = "pruebaUsuario.prusId")
    @Mapping(source = "respId", target = "respuesta.respId")
    @Mapping(source = "pregId", target = "pregunta.pregId")
    public DetallePruebaUsuario detallePruebaUsuarioDTOToDetallePruebaUsuario(
        DetallePruebaUsuarioDTO detallePruebaUsuarioDTO)
        throws Exception;

    public List<DetallePruebaUsuarioDTO> listDetallePruebaUsuarioToListDetallePruebaUsuarioDTO(
        List<DetallePruebaUsuario> detallePruebaUsuarios)
        throws Exception;

    public List<DetallePruebaUsuario> listDetallePruebaUsuarioDTOToListDetallePruebaUsuario(
        List<DetallePruebaUsuarioDTO> detallePruebaUsuarioDTOs)
        throws Exception;
}
