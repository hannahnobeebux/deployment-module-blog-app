package com.hannah.sprintbootblogapplication.repository;

import com.hannah.sprintbootblogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//this will save the data/entries following the structure of the Post model
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
