package com.hannah.sprintbootblogapplication.repository;

import com.hannah.sprintbootblogapplication.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
