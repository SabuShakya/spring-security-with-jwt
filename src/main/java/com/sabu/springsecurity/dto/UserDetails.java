package com.sabu.springsecurity.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {
    private String username;
    private String password;
    private String role;
}


