package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.ProgramaModulo;
import com.vortexbird.sapiens.dto.ProgramaModuloDTO;

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
public interface ProgramaModuloMapper {
    @Mapping(source = "modulo.moduId", target = "moduId_Modulo")
    @Mapping(source = "programa.progId", target = "progId_Programa")
    public ProgramaModuloDTO programaModuloToProgramaModuloDTO(
        ProgramaModulo programaModulo) throws Exception;

    @Mapping(source = "moduId_Modulo", target = "modulo.moduId")
    @Mapping(source = "progId_Programa", target = "programa.progId")
    public ProgramaModulo programaModuloDTOToProgramaModulo(
        ProgramaModuloDTO programaModuloDTO) throws Exception;

    public List<ProgramaModuloDTO> listProgramaModuloToListProgramaModuloDTO(
        List<ProgramaModulo> programaModulos) throws Exception;

    public List<ProgramaModulo> listProgramaModuloDTOToListProgramaModulo(
        List<ProgramaModuloDTO> programaModuloDTOs) throws Exception;
}
