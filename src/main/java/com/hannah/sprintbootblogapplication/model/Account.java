package com.hannah.sprintbootblogapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//naming this entity Account so that it doesn't get confused with "User" in Spring Security
//avoid needing to override the class definitions
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    //linking this object to many other objects
    //one user can have many posts
    @OneToMany(mappedBy = "account")
    private List<Post> posts;




}
