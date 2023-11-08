package it.vverba.improdisc.config.security.service;

import it.vverba.improdisc.entities.User;
import it.vverba.improdisc.exceptions.AuthenticationException;
import it.vverba.improdisc.exceptions.UserNotFoundException;
import it.vverba.improdisc.mappers.UserMapper;
import it.vverba.improdisc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(UserNotFoundException::new);
            return userMapper.toUserDetails(user);
        } catch (UserNotFoundException exception) {
            throw new AuthenticationException(exception);
        }
    }
}
