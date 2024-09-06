package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.app.entities.Users;
import com.app.exceptions.DuplicateExceptions;
import com.app.exceptions.NotFoundException;
import com.app.repositories.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	private UsersRepository userrepo;
//	@Autowired
//	private KafkaTemplate<String, String> kafkatemplate;
	
	public ResponseEntity<Users> saveUsers(Users user) {
		if(userrepo.existsById(user.getId())) {
			throw new DuplicateExceptions("400", "User with Id "+user.getId()+" already existed");
		}else {
			Users saveduser=userrepo.save(user);
//			kafkatemplate.send("user-events", "User registered: "+user.getUsername());
			return new ResponseEntity<Users>(saveduser,HttpStatus.CREATED);
		}
	}
	
	public ResponseEntity<Users> getByUserId(int userid) {
		
		Optional<Users> opt=userrepo.findById(userid);
		if(!opt.isPresent()) {
			throw new NotFoundException("404", "User with Id "+userid+ " Not found");
		}else {
			return new ResponseEntity<Users>(opt.get(),HttpStatus.OK);
		}
		
	}
	
	public ResponseEntity<Users> updateUsers(Users user){
		if(!userrepo.existsById(user.getId())) {
			throw new NotFoundException("404", "User Not Found");
		}else {
			Users updatedUser=userrepo.save(user);
			return new ResponseEntity<Users>(updatedUser,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<String> deleteByUserId(int id){
		Optional<Users> opt=userrepo.findById(id);
		if(!opt.isPresent()) {
			throw new NotFoundException("404", "User with Id "+id+" not found to delete");
		}else {
			userrepo.deleteById(id);
			return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
		}
	}

	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}
	
	

}
