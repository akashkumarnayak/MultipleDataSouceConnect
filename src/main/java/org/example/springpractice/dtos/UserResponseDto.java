package org.example.springpractice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springpractice.models.user.Status;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String email;
    private Status status;
}
