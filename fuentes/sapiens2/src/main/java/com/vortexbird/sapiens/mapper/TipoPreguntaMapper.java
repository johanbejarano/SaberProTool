package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.TipoPregunta;
import com.vortexbird.sapiens.dto.TipoPreguntaDTO;

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
public interface TipoPreguntaMapper {
    public TipoPreguntaDTO tipoPreguntaToTipoPreguntaDTO(
        TipoPregunta tipoPregunta) throws Exception;

    public TipoPregunta tipoPreguntaDTOToTipoPregunta(
        TipoPreguntaDTO tipoPreguntaDTO) throws Exception;

    public List<TipoPreguntaDTO> listTipoPreguntaToListTipoPreguntaDTO(
        List<TipoPregunta> tipoPreguntas) throws Exception;

    public List<TipoPregunta> listTipoPreguntaDTOToListTipoPregunta(
        List<TipoPreguntaDTO> tipoPreguntaDTOs) throws Exception;
}
