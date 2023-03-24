package com.sandeep.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
public class UserController {

	private UserDaoService userDaoService;
	
	public UserController(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
		
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id){
		
		User user = userDaoService.findOneById(id);
		if(user == null) {
			throw new UserNotFoundException("id : " + id);
		}
		return user;
	}
	
	@PostMapping("/users")	
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.addUsr(user);
				
		// => /users/{id}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri(); 
		
		//return ResponseEntity.created(null).build();
		return ResponseEntity.created(location ).build();		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id){
		
		userDaoService.deleteById(id);
		
	}
}
