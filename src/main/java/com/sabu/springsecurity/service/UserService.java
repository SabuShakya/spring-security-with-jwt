package com.sabu.springsecurity.service;

import com.sabu.springsecurity.dto.UserRequestDTO;
import com.sabu.springsecurity.entity.Users;

public interface UserService {

    public void createUser(UserRequestDTO user);

    public Users findUserByUsername(String name);
}
