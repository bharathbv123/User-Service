package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.entities.Users;
import com.app.repositories.UsersRepository;
import com.app.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UsersRepository userrepo;
	
	@Test
	public void getAllUsers() {
		List<Users> mockuser=Arrays.asList(new Users(1, "admin", "password", "ADMIN"),
				new Users(2, "user", "abcd", "USER"));
		
		when(userrepo.findAll()).thenReturn(mockuser);
		
		List<Users> response=userService.getAllUsers();
		assertEquals(2, response.size());	
		assertEquals(mockuser, response);
	}
	
	@Test
	public void getOneUser() {
		Users mockuser=new Users(1, "Bharath", "Bharathbv@123", "Developer");
		int userid=1;
		when(userrepo.findById(userid)).thenReturn(Optional.of(mockuser));
		
		ResponseEntity<Users> response=userService.getByUserId(userid);		
		assertEquals(mockuser, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void saveUser() {
		Users mockuser=new Users(1, "Bharath", "Bharathbv@123", "Developer");
		
		when(userrepo.save(mockuser)).thenReturn(mockuser);
		
		ResponseEntity<Users> resonse=userService.saveUsers(mockuser);
		assertEquals(HttpStatus.CREATED, resonse.getStatusCode());
		assertEquals(mockuser, resonse.getBody());
		
	}
	
	@Test
	public void deleteByuser() {
		Optional<Users> mockuser=Optional.of(new Users(1, "Bharath", "Bharathbv@123", "Developer"));
		int userid=1;
		when(userrepo.findById(userid)).thenReturn(mockuser);
		ResponseEntity<?> response=userService.deleteByUserId(userid);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted Successfully", response.getBody());
	}
	
}
