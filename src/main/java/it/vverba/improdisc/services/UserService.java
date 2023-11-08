package it.vverba.improdisc.services;

import it.vverba.improdisc.dto.UserDto;
import it.vverba.improdisc.entities.User;
import it.vverba.improdisc.exceptions.UserNotFoundException;
import it.vverba.improdisc.mappers.UserMapper;
import it.vverba.improdisc.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<User, UserDto> {

    private static final String FUNCTIONAL_MODULE = "USER_SERVICE";

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected String getFunctionalModule() {
        return FUNCTIONAL_MODULE;
    }

    @Override
    public UserDto save(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return super.save(dto);
    }

    public UserDto findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }
}
