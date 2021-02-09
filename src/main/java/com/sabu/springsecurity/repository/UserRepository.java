package com.sabu.springsecurity.repository;

import com.sabu.springsecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String username);
}
