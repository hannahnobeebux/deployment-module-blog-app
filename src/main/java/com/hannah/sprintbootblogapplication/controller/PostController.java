package com.hannah.sprintbootblogapplication.controller;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.service.AccountService;
import com.hannah.sprintbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    //Using an Optional because it's not necessary to have an account
    @GetMapping("/posts/new")
    public String createNewPost(Model model) {
//        using an existing email in the database to test endpoint
        Optional<Account> optionalAccount = accountService.findByEmail("anonymous@email.com");
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

    //being redirected to a new form if you want to edit a post
    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // add the post as an attribute to the model
            model.addAttribute("post", post);
            return "editPost";
        } else {
            return "404";
        }
    }

    //changing the data of the specified post to the data recieved by the user to edit that post
    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model) {

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());

            postService.save(existingPost);
        }
        return "redirect:/posts/" + post.getId();
    }

    //only those with ADMIN role can delete a post
    @GetMapping("/posts/{id}/delete")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id) {

        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            postService.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }
}
