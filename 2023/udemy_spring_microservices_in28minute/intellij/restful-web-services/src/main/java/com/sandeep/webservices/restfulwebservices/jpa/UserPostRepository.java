package com.sandeep.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandeep.webservices.restfulwebservices.user.User;
import com.sandeep.webservices.restfulwebservices.user.UserPost;

public interface UserPostRepository extends JpaRepository<UserPost, Integer>{

}
