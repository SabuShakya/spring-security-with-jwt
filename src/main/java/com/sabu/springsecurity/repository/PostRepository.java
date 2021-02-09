package com.sabu.springsecurity.repository;

import com.sabu.springsecurity.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
