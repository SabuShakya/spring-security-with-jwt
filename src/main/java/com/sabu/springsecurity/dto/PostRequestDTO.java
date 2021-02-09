package com.sabu.springsecurity.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {

    private String title;

    private String description;

    private String username;
}
