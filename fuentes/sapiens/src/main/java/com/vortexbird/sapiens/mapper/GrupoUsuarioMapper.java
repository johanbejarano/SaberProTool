package com.vortexbird.sapiens.mapper;

import com.vortexbird.sapiens.domain.GrupoUsuario;
import com.vortexbird.sapiens.dto.GrupoUsuarioDTO;

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
public interface GrupoUsuarioMapper {
    @Mapping(source = "grupo.grupId", target = "grupId_Grupo")
    @Mapping(source = "usuario.usuaId", target = "usuaId_Usuario")
    public GrupoUsuarioDTO grupoUsuarioToGrupoUsuarioDTO(
        GrupoUsuario grupoUsuario) throws Exception;

    @Mapping(source = "grupId_Grupo", target = "grupo.grupId")
    @Mapping(source = "usuaId_Usuario", target = "usuario.usuaId")
    public GrupoUsuario grupoUsuarioDTOToGrupoUsuario(
        GrupoUsuarioDTO grupoUsuarioDTO) throws Exception;

    public List<GrupoUsuarioDTO> listGrupoUsuarioToListGrupoUsuarioDTO(
        List<GrupoUsuario> grupoUsuarios) throws Exception;

    public List<GrupoUsuario> listGrupoUsuarioDTOToListGrupoUsuario(
        List<GrupoUsuarioDTO> grupoUsuarioDTOs) throws Exception;
}
