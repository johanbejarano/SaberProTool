package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Programa;
import com.vortexbird.sapiens.dto.ProgramaDTO;

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
public interface ProgramaMapper {
    @Mapping(source = "facultad.facuId", target = "facuId_Facultad")
    public ProgramaDTO programaToProgramaDTO(Programa programa)
        throws Exception;

    @Mapping(source = "facuId_Facultad", target = "facultad.facuId")
    public Programa programaDTOToPrograma(ProgramaDTO programaDTO)
        throws Exception;

    public List<ProgramaDTO> listProgramaToListProgramaDTO(
        List<Programa> programas) throws Exception;

    public List<Programa> listProgramaDTOToListPrograma(
        List<ProgramaDTO> programaDTOs) throws Exception;
}
