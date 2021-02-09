package com.sabu.springsecurity.service;

import com.sabu.springsecurity.dto.PostRequestDTO;
import com.sabu.springsecurity.entity.Post;
import com.sabu.springsecurity.entity.Users;
import com.sabu.springsecurity.exception.UserNotFoundException;
import com.sabu.springsecurity.repository.PostRepository;
import com.sabu.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addPost(PostRequestDTO post) {
        Optional<Users> users = Optional.ofNullable(userRepository.findByUsername(post.getUsername()));

        if (users.isPresent()) {
            Post postData = new Post();

            postData.setTitle(post.getTitle());
            postData.setDescription(post.getDescription());
            postData.setUser_id(users.get());

            postRepository.save(postData);
        } else {
            throw new UserNotFoundException("No user available");
        }
    }
}
