package com.nathan.sat.service.mapper;

import com.nathan.sat.model.Contato;
import com.nathan.sat.service.dto.ContatoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContatoMapper extends EntityMapper<ContatoDTO, Contato> {
}
