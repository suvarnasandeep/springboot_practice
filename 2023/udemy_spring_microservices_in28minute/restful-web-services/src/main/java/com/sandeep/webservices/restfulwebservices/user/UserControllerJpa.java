package com.sandeep.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sandeep.webservices.restfulwebservices.jpa.UserPostRepository;
import com.sandeep.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserControllerJpa {

	private UserRepository userRepository;
	private UserPostRepository userPostRepository;

	public UserControllerJpa(UserRepository userRepository, UserPostRepository userPostRepository) {
		this.userRepository = userRepository;
		this.userPostRepository=userPostRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User getUserById(@PathVariable int id){

		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
		return user.get();
	}

	@PostMapping("/jpa/users")	
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// => /users/{id}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri(); 

		//return ResponseEntity.created(null).build();
		return ResponseEntity.created(location ).build();		
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id){

		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<UserPost> getAllUsersPost(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
				
		return user.get().getUserPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<UserPost> createAllUsersPost(@PathVariable int id, @Valid @RequestBody UserPost userPost){
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id : " + id);
		}
				
		userPost.setUser(user.get());
		
		UserPost savetPost =  userPostRepository.save(userPost);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savetPost.getId())
				.toUri(); 

		//return ResponseEntity.created(null).build();
		return ResponseEntity.created(location ).build();
		
		
		
	}
}
