package com.sabu.springsecurity.repository;

import com.sabu.springsecurity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
