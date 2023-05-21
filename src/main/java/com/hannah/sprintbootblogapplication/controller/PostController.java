package com.hannah.sprintbootblogapplication.controller;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.service.AccountService;
import com.hannah.sprintbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        //Pulls out the path of the post with this Id

        //find the post by id
        Optional<Post> optionalPost = postService.getById(id);
        //if the post exists, populate the UI model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        } else {
            //error page
            return "404";
        }
    }

    //checking whether the author has already registered - this may not be the case, hence an optional is returned
    @GetMapping("/posts/new")
    public String createNewPost(Model model) {
//        using an existing email in the database to test endpoint
        Optional<Account> optionalAccount = accountService.findByEmail("john.doe@email.com");
        if (optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "newPost";
        } else {
            return "404";
        }
    }

    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }
}
