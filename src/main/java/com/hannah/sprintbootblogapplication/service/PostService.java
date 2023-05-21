package com.hannah.sprintbootblogapplication.service;

import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    //get a single post by ID
    //Optional - this method will either return an existing Post or nothing if the Post doesn't exist
    //Using Optional will avoid potential null pointer exception issues and provides a safer way for this situation
    //If the Post doesn't exist, will return an Optional rather than null
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    //if we pass in a Post object, this method will attempt to persist it into the Post table in the database
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
//        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }
}
