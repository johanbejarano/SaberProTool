package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Respuesta;
import com.vortexbird.sapiens.dto.RespuestaDTO;

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
public interface RespuestaMapper {
    @Mapping(source = "pregunta.pregId", target = "pregId_Pregunta")
    public RespuestaDTO respuestaToRespuestaDTO(Respuesta respuesta)
        throws Exception;

    @Mapping(source = "pregId_Pregunta", target = "pregunta.pregId")
    public Respuesta respuestaDTOToRespuesta(RespuestaDTO respuestaDTO)
        throws Exception;

    public List<RespuestaDTO> listRespuestaToListRespuestaDTO(
        List<Respuesta> respuestas) throws Exception;

    public List<Respuesta> listRespuestaDTOToListRespuesta(
        List<RespuestaDTO> respuestaDTOs) throws Exception;
}
