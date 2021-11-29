package com.example.Udemysprinbootmicroservices.controller;

import com.example.Udemysprinbootmicroservices.entity.User;
import com.example.Udemysprinbootmicroservices.exception.UserNotFoundException;
import com.example.Udemysprinbootmicroservices.repository.UserDao;
import com.example.Udemysprinbootmicroservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserJPAController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService userService;


    @GetMapping("/jpa/user")
    public List<User> getUser(){

        return userService.getUser();
    }

    @GetMapping("/jpa/user/{id}")
    public User getUser(@PathVariable  int id){
        return userService.getUserById(id);
    }

    @PostMapping("/jpa/user")
    public User saveUser(@Valid  @RequestBody User user){
        User saverUser = userService.saveUser(user);

        return saverUser;
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteById(@PathVariable int id){

        userService.deleteById(id);

    }


}
