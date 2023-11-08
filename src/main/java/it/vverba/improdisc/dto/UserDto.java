package it.vverba.improdisc.dto;

import it.vverba.improdisc.enums.UserType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String username;
    private String password;
    private UserType userType;
}
