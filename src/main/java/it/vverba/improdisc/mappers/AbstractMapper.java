package it.vverba.improdisc.mappers;

public interface AbstractMapper<ENTITY, DTO> {

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
