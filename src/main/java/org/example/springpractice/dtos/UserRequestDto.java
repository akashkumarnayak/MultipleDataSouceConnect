package org.example.springpractice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private String name;
    private String email;
    private String password;
}
