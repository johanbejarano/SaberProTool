package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.Pregunta;
import com.vortexbird.sapiens.dto.PreguntaDTO;

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
public interface PreguntaMapper {
    @Mapping(source = "modulo.moduId", target = "moduId_Modulo")
    @Mapping(source = "tipoPregunta.tprgId", target = "tprgId_TipoPregunta")
    public PreguntaDTO preguntaToPreguntaDTO(Pregunta pregunta)
        throws Exception;

    @Mapping(source = "moduId_Modulo", target = "modulo.moduId")
    @Mapping(source = "tprgId_TipoPregunta", target = "tipoPregunta.tprgId")
    public Pregunta preguntaDTOToPregunta(PreguntaDTO preguntaDTO)
        throws Exception;

    public List<PreguntaDTO> listPreguntaToListPreguntaDTO(
        List<Pregunta> preguntas) throws Exception;

    public List<Pregunta> listPreguntaDTOToListPregunta(
        List<PreguntaDTO> preguntaDTOs) throws Exception;
}
