package com.sabu.springsecurity.service;

import com.sabu.springsecurity.dto.UserRequestDTO;
import com.sabu.springsecurity.entity.Users;
import com.sabu.springsecurity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserRequestDTO user) {

        Users users = new Users();
        users.setName(user.getName());
        users.setUsername(user.getUsername());
        users.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(users);
    }

    @Override
    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
