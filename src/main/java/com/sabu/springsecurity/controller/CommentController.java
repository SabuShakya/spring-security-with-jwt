package com.sabu.springsecurity.controller;

import com.sabu.springsecurity.dto.CommentRequestDTO;
import com.sabu.springsecurity.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequestDTO commentRequestDTO){
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
