package com.sabu.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    @GetMapping(value = "/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/public/user")
    public String getUser() {
        return "List of public users fetched";
    }
}
