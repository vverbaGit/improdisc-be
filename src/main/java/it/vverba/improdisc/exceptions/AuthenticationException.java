package it.vverba.improdisc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized!")
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
