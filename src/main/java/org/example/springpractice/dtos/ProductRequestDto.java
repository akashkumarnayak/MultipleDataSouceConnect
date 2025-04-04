package org.example.springpractice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    private Long id;
    private String name;
    private String description;
    private Float price;
}
