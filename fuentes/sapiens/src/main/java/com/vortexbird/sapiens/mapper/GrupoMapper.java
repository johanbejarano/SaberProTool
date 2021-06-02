package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.Grupo;
import com.vortexbird.sapiens.dto.GrupoDTO;

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
public interface GrupoMapper {
    public GrupoDTO grupoToGrupoDTO(Grupo grupo) throws Exception;

    public Grupo grupoDTOToGrupo(GrupoDTO grupoDTO) throws Exception;

    public List<GrupoDTO> listGrupoToListGrupoDTO(List<Grupo> grupos)
        throws Exception;

    public List<Grupo> listGrupoDTOToListGrupo(List<GrupoDTO> grupoDTOs)
        throws Exception;
}
