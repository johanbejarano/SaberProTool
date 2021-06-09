package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.DetallePruebaUsuarioRespuesta;
import com.vortexbird.sapiens.dto.DetallePruebaUsuarioRespuestaDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/
@Mapper
public interface DetallePruebaUsuarioRespuestaMapper {
    @Mapping(source = "detallePruebaUsuario.dpruId", target = "dpruId_DetallePruebaUsuario")
    @Mapping(source = "respuesta.respId", target = "respId_Respuesta")
    public DetallePruebaUsuarioRespuestaDTO detallePruebaUsuarioRespuestaToDetallePruebaUsuarioRespuestaDTO(
        DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuesta)
        throws Exception;

    @Mapping(source = "dpruId_DetallePruebaUsuario", target = "detallePruebaUsuario.dpruId")
    @Mapping(source = "respId_Respuesta", target = "respuesta.respId")
    public DetallePruebaUsuarioRespuesta detallePruebaUsuarioRespuestaDTOToDetallePruebaUsuarioRespuesta(
        DetallePruebaUsuarioRespuestaDTO detallePruebaUsuarioRespuestaDTO)
        throws Exception;

    public List<DetallePruebaUsuarioRespuestaDTO> listDetallePruebaUsuarioRespuestaToListDetallePruebaUsuarioRespuestaDTO(
        List<DetallePruebaUsuarioRespuesta> detallePruebaUsuarioRespuestas)
        throws Exception;

    public List<DetallePruebaUsuarioRespuesta> listDetallePruebaUsuarioRespuestaDTOToListDetallePruebaUsuarioRespuesta(
        List<DetallePruebaUsuarioRespuestaDTO> detallePruebaUsuarioRespuestaDTOs)
        throws Exception;
}
