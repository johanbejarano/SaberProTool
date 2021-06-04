package com.vortexbird.sapiens.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vortexbird.sapiens.domain.PruebaPregunta;
import com.vortexbird.sapiens.dto.PruebaPreguntaDTO;

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
public interface PruebaPreguntaMapper {
    @Mapping(source = "pregunta.pregId", target = "pregId_Pregunta")
    @Mapping(source = "prueba.prueId", target = "prueId_Prueba")
    public PruebaPreguntaDTO pruebaPreguntaToPruebaPreguntaDTO(
        PruebaPregunta pruebaPregunta) throws Exception;

    @Mapping(source = "pregId_Pregunta", target = "pregunta.pregId")
    @Mapping(source = "prueId_Prueba", target = "prueba.prueId")
    public PruebaPregunta pruebaPreguntaDTOToPruebaPregunta(
        PruebaPreguntaDTO pruebaPreguntaDTO) throws Exception;

    public List<PruebaPreguntaDTO> listPruebaPreguntaToListPruebaPreguntaDTO(
        List<PruebaPregunta> pruebaPreguntas) throws Exception;

    public List<PruebaPregunta> listPruebaPreguntaDTOToListPruebaPregunta(
        List<PruebaPreguntaDTO> pruebaPreguntaDTOs) throws Exception;
}
