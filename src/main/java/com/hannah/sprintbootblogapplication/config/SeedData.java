package com.hannah.sprintbootblogapplication.config;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.service.AccountService;
import com.hannah.sprintbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//this annotation will tell our application that this file must run and interact with the database once the SpringBoot application is spun up
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    //We need to override the "run" method that is associated with CommandLine runner
    @Override
    public void run(String... args) throws Exception {
        //returning all existing posts
        List<Post> posts = postService.getAll();

        //database is empty
        if (posts.size() == 0) {

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("john");
            account1.setLastName("doe");
            account1.setEmail("john.doe@email.com");
            account1.setPassword("password123");

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@email.com");
            account2.setPassword("password");


            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Title of post1");
            post1.setBody("Body of post1");
            post1.setAccount(account1);


            Post post2 = new Post();
            post2.setTitle("Title of post2");
            post2.setBody("Body of post2");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }

}
