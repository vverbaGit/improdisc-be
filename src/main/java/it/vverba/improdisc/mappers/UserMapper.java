package it.vverba.improdisc.mappers;

import it.vverba.improdisc.config.security.dto.UserDetailsDto;
import it.vverba.improdisc.dto.UserDto;
import it.vverba.improdisc.entities.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<User, UserDto> {

    UserDetailsDto toUserDetails(User user);

    @AfterMapping
    default void mapAuthorities(User user, @MappingTarget UserDetailsDto userDetailsDto) {
        userDetailsDto.setAuthorities(Optional.ofNullable(user)
                .map(User::getUserType)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .stream()
                .toList());
    }
}
