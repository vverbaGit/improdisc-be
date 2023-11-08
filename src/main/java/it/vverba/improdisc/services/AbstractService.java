package it.vverba.improdisc.services;

import it.vverba.improdisc.exceptions.NotFoundException;
import it.vverba.improdisc.mappers.AbstractMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
public abstract class AbstractService<ENTITY, DTO> {

    protected abstract JpaRepository<ENTITY, UUID> getRepository();

    protected abstract AbstractMapper<ENTITY, DTO> getMapper();

    protected abstract String getFunctionalModule();

    public DTO save(DTO dto) {
        log.debug("Start save dto = {}, in functional module = {}", dto, getFunctionalModule());
        DTO result = Optional.ofNullable(dto)
                .map(getMapper()::toEntity)
                .map(getRepository()::save)
                .map(getMapper()::toDto)
                .orElseThrow(NotFoundException::new);
        log.debug("Finish save dto = {}, in functional module = {}", result, getFunctionalModule());
        return result;
    }

    public List<DTO> findAll() {
        return getRepository()
                .findAll()
                .stream()
                .map(getMapper()::toDto)
                .toList();
    }

    public List<DTO> findAll(Pageable pageable) {
        return getRepository()
                .findAll(pageable)
                .stream()
                .map(getMapper()::toDto)
                .toList();
    }

    public DTO findOne(UUID id) {
        return getRepository()
                .findById(id)
                .map(getMapper()::toDto)
                .orElseThrow(NotFoundException::new);
    }
}
