package com.sabu.springsecurity.controller;

import com.sabu.springsecurity.constants.ResponseCodeConstants;
import com.sabu.springsecurity.dto.GenericResponse;
import com.sabu.springsecurity.dto.UserRequestDTO;
import com.sabu.springsecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/createUser")
    private ResponseEntity<?> createUser(@RequestBody UserRequestDTO user){
        userService.createUser(user);
        GenericResponse genericResponse = new GenericResponse(true, ResponseCodeConstants.SUCCESS,"User created successfully");
        return new ResponseEntity<GenericResponse>(genericResponse,HttpStatus.OK);
    }



}
