package com.vortexbird.sapiens.mapper;

import java.util.List;

import com.vortexbird.sapiens.domain.Facultad;
import com.vortexbird.sapiens.dto.FacultadDTO;

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
public interface FacultadMapper {
    public FacultadDTO facultadToFacultadDTO(Facultad facultad)
        throws Exception;

    public Facultad facultadDTOToFacultad(FacultadDTO facultadDTO)
        throws Exception;

    public List<FacultadDTO> listFacultadToListFacultadDTO(
        List<Facultad> facultads) throws Exception;

    public List<Facultad> listFacultadDTOToListFacultad(
        List<FacultadDTO> facultadDTOs) throws Exception;
}
