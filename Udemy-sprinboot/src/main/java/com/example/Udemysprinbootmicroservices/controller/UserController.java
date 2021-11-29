package com.example.Udemysprinbootmicroservices.controller;

import com.example.Udemysprinbootmicroservices.entity.User;
import com.example.Udemysprinbootmicroservices.exception.UserNotFoundException;
import com.example.Udemysprinbootmicroservices.repository.UserDao;
import com.example.Udemysprinbootmicroservices.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService userService;

    //Internationalization
    @GetMapping("/")
    public String helloStringInternationalize(
            //@RequestHeader(name = "Accept-Language", required = false)Locale locale
    ){
        return messageSource.getMessage("good.morning.message", null,"Default Message" , LocaleContextHolder.getLocale());
    }

    @GetMapping("/user")
    public List<User> getUser(){
        return userDao.findAll();

    }

    @GetMapping("/user/{id}")
    public EntityModel<User> getUser(@PathVariable  int id){
        User user =  userDao.findOne(id);

        //static filter of REST header
        /*SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
        FilterProvider filters = new SimpleFilterProvider().addFilter("someFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);*/


        //custom exception
        if(user == null){
            throw  new UserNotFoundException("id" + user.getId());
        }

        //get link of all user using hateos
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser());
        model.add(link.withRel("all-user"));
        return  model;
        //return  mapping;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> saveUser(@Valid  @RequestBody User user){
        User saverUser = userDao.save(user);

        //to get response of created entity
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saverUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/user/{id}")
    public User deleteById(@PathVariable int id){

        User user = userDao.deleteById(id);

        if(user == null){
            throw  new UserNotFoundException("id " + id);

        }
        return  user;
    }


}
