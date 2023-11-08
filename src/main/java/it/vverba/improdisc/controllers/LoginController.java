package it.vverba.improdisc.controllers;

import it.vverba.improdisc.config.security.dto.LoginRequest;
import it.vverba.improdisc.config.security.dto.TokenResponse;
import it.vverba.improdisc.config.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public TokenResponse authenticateAndGetToken(@RequestBody LoginRequest authRequest) {
        return TokenResponse.builder()
                .token(service.authenticateAndGetToken(authRequest))
                .build();
    }
}
