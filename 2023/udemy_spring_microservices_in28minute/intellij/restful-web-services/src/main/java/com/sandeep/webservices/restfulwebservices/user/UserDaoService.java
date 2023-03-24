package com.sandeep.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	static int userIdCount = 0;
	static {
		users.add(new User(++userIdCount, "sandeep", LocalDate.now().minusYears(30)));
		users.add(new User(++userIdCount, "niri", LocalDate.now().minusYears(25)));
		users.add(new User(++userIdCount, "anup", LocalDate.now().minusYears(28)));
		users.add(new User(++userIdCount, "rakshi", LocalDate.now().minusYears(33)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOneById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		//return users.stream().filter(predicate).findFirst().get();
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User addUsr(User user) {
		user.setId(++userIdCount);
		users.add(user);
		return user;
		
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);				
	}

}
