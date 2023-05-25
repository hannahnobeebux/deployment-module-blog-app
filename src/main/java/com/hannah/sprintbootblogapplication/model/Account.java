package com.hannah.sprintbootblogapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//naming this entity Account so that it doesn't get confused with "User" in Spring Security
//avoid needing to override the class definitions that involve the word "USER"
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

//    Creating a relationship between Account and Authority - This is essentially the "Role" of the account
//    In Spring Security, the Role name is developer-defined
//    Each user can have multiple "Authorities" attached to it


//    Always fetch the relationship from the database
//    Creating a reference table between account and authority
//    Setting up a join table between authorities and account

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_authority",
        joinColumns = {@JoinColumn(name="account_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    //mapping over the data values received to the Account model
    @Override
    public String toString() {
        return "Account{" +
            ", firstName= '" + firstName + "'" +
            ", lastName = '" + lastName + "'" +
            ", email = '" + email + "'" +
            ", authorities = " + authorities +
            "}";
    }



}
