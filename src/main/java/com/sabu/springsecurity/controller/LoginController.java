package com.sabu.springsecurity.controller;


import com.sabu.springsecurity.dto.UsernameAndPasswordAuthenticationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void loginUser(@RequestBody UsernameAndPasswordAuthenticationRequest request){
        System.out.println("Inside login api");;
    }
}
