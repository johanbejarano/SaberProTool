package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Usuario;
import com.vortexbird.sapiens.dto.UsuarioDTO;

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
public interface UsuarioMapper {
    @Mapping(source = "programa.progId", target = "progId_Programa")
    @Mapping(source = "tipoUsuario.tiusId", target = "tiusId_TipoUsuario")
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario)
        throws Exception;

    @Mapping(source = "progId_Programa", target = "programa.progId")
    @Mapping(source = "tiusId_TipoUsuario", target = "tipoUsuario.tiusId")
    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO)
        throws Exception;

    public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios)
        throws Exception;

    public List<Usuario> listUsuarioDTOToListUsuario(
        List<UsuarioDTO> usuarioDTOs) throws Exception;
}
