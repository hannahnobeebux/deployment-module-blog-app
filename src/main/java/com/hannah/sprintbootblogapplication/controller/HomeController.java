package com.hannah.sprintbootblogapplication.controller;

import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {

    //dependency injection
    PostService postService;
    @Autowired
    public HomeController (PostService postService) {
        this.postService = postService;
    }

    //Making use of the UI model that is automatically injected into our controller handle
    @GetMapping("/")
    public String home(Model model) {
        //populate this model with the blog posts
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        //this is referencing the template with the name "home"
        return "home";
    }
}
