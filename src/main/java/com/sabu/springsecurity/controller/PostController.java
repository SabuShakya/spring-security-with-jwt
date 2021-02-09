package com.sabu.springsecurity.controller;


import com.sabu.springsecurity.constants.ResponseCodeConstants;
import com.sabu.springsecurity.dto.GenericResponse;
import com.sabu.springsecurity.dto.PostRequestDTO;
import com.sabu.springsecurity.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody PostRequestDTO postRequestDTO){
        log.debug("Adding posts started.");
        postService.addPost(postRequestDTO);
        return new ResponseEntity<>(new GenericResponse(true, ResponseCodeConstants.SUCCESS,"Post added successfully"), HttpStatus.CREATED);
    }

}
