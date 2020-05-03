package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Modulo;
import com.vortexbird.sapiens.dto.ModuloDTO;

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
public interface ModuloMapper {
    @Mapping(source = "tipoModulo.timoId", target = "timoId_TipoModulo")
    public ModuloDTO moduloToModuloDTO(Modulo modulo) throws Exception;

    @Mapping(source = "timoId_TipoModulo", target = "tipoModulo.timoId")
    public Modulo moduloDTOToModulo(ModuloDTO moduloDTO)
        throws Exception;

    public List<ModuloDTO> listModuloToListModuloDTO(List<Modulo> modulos)
        throws Exception;

    public List<Modulo> listModuloDTOToListModulo(List<ModuloDTO> moduloDTOs)
        throws Exception;
}
