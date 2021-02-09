package com.sabu.springsecurity.security;

import com.sabu.springsecurity.entity.Users;
import com.sabu.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userByUsername = userService.findUserByUsername(username);
        if (userByUsername == null)
            throw new UsernameNotFoundException("User " + username + " not found.");
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        UserDetails userDetails = new User(userByUsername.getUsername(),
                userByUsername.getPassword(), Collections.singletonList(grantedAuthority));
        return userDetails;
    }
}
