package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Users;
import com.app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<Users> registerUser(@RequestBody Users user){
		return userService.saveUsers(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getByUserId(@PathVariable int id){
		return userService.getByUserId(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Users> updateUsers(@RequestBody Users user){
		return userService.updateUsers(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteByUserId(@PathVariable int id){
		return userService.deleteByUserId(id);
	}
		
	@GetMapping("")
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}
}
