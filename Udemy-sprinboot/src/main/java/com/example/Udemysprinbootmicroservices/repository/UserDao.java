package com.example.Udemysprinbootmicroservices.repository;

import com.example.Udemysprinbootmicroservices.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDao {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(1, "sandeep", new Date()));
        users.add(new User(2, "Niri", new Date()));
        users.add(new User(3, "Anu", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return  null;
    }

    public User deleteById(int id){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getId() == id){
                it.remove();
                return user;
            }
        }
        return  null;
    }
}
