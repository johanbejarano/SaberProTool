package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Contexto;
import com.vortexbird.sapiens.dto.ContextoDTO;

import org.mapstruct.Mapper;


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
public interface ContextoMapper {
    public ContextoDTO contextoToContextoDTO(Contexto contexto)
        throws Exception;

    public Contexto contextoDTOToContexto(
        ContextoDTO contextoDTO) throws Exception;

    public List<ContextoDTO> listContextoToListContextoDTO(
        List<Contexto> contextos) throws Exception;

    public List<Contexto> listContextoDTOToListContexto(
        List<ContextoDTO> contextoDTOs) throws Exception;
}
