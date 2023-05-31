package com.hannah.sprintbootblogapplication.config;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.model.Authority;
import com.hannah.sprintbootblogapplication.model.Post;
import com.hannah.sprintbootblogapplication.repository.AuthorityRepository;
import com.hannah.sprintbootblogapplication.service.AccountService;
import com.hannah.sprintbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//this annotation will tell our application that this file must run and interact with the database once the SpringBoot application is spun up
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    //We need to override the "run" method that is associated with CommandLine runner
    @Override
    public void run(String... args) throws Exception {
        //returning all existing posts
        List<Post> posts = postService.getAll();

        //database is empty
        if (posts.size() == 0) {

//            Creating the Roles as these are developer defined
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("anonymousUser");
            account1.setLastName("user");
            account1.setEmail("anonymous@email.com");
            account1.setPassword("password123");

//            Giving the test account a Role of "USER"
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@email.com");
            account2.setPassword("password");

//            Giving the admin account a Role of "ADMIN" and "USER" so it has all possible access rights
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);


            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post();
            post1.setTitle("Europe Travel Tips for Summer 2023 ☀️");
            post1.setBody("Integer vestibulum vitae nunc ut ornare. Aenean luctus tempor velit, eu pellentesque dolor finibus et. Integer ut lacinia nibh. Sed et leo sem. Mauris rutrum, enim in auctor iaculis, felis massa congue ipsum, a cursus felis eros quis ex. Morbi aliquam sapien id convallis eleifend. Vivamus viverra et sapien quis tincidunt. Mauris luctus lectus leo, ut ornare ligula consectetur ut. Morbi sollicitudin auctor auctor. Aenean tincidunt pharetra risus ut volutpat. Nunc quam tellus, convallis eu auctor nec, ullamcorper nec sapien. Phasellus non elit sit amet lorem dictum scelerisque vel at turpis. Proin at nibh sed sem laoreet aliquam. Vestibulum a pulvinar mauris. Nunc lectus mauris, cursus sed urna pharetra, scelerisque ultricies elit.");
            post1.setAccount(account1);


            Post post2 = new Post();
            post2.setTitle("How WFH has changed society 👩‍💻");
            post2.setBody("Curabitur sed risus placerat, blandit ex maximus, accumsan augue. Fusce hendrerit diam turpis, nec fringilla leo aliquam sodales. Phasellus laoreet, massa sit amet efficitur vehicula, nisi nulla dictum diam, et eleifend nulla elit ac nunc. Donec tristique ultrices sagittis. Pellentesque eget libero ac elit faucibus eleifend vel quis lectus. Donec feugiat tellus a lectus facilisis, eget posuere lacus mattis. Vivamus vestibulum fringilla bibendum.");
            post2.setAccount(account2);

            postService.save(post1);
            postService.save(post2);
        }
    }

}
