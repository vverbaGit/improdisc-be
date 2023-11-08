package it.vverba.improdisc.controllers;

import it.vverba.improdisc.dto.UserDto;
import it.vverba.improdisc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    private UserDto create(UserDto dto) {
        return service.save(dto);
    }

    @PutMapping
    private UserDto update(UserDto dto) {
        return service.save(dto);
    }

    @GetMapping
    private List<UserDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/page")
    private List<UserDto> findAll(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sortDirection,
            @RequestParam(defaultValue = "username", required = false) String sortField) {

        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(sortDirection, sortField));
        return service.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    private UserDto findOne(@PathVariable UUID id) {
        return service.findOne(id);
    }
}
