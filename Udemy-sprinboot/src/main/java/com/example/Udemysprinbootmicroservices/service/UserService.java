package com.example.Udemysprinbootmicroservices.service;

import com.example.Udemysprinbootmicroservices.entity.User;

import java.util.List;

public interface UserService {
    

    public List<User> getUser();

    public User saveUser(User user);

    public User getUserById(int id);

    public  void deleteById(int id);
}
