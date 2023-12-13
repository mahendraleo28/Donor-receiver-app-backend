package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@CrossOrigin
public class RegistrationController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
	    System.out.println("register page");

	    String username = user.getUsername();
	    String password = user.getPassword();
	    String mobile = user.getMobile();
	    String email = user.getEmail();

	    if (username.trim().length() < 8 || username.trim().length() > 30) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in username");
	    }
	    else if (password.trim().length() < 8 || password.trim().length() > 30) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in password");
	    }
	    else if ((mobile.trim().length() < 10 || mobile.trim().length() > 10 || !mobile.matches("^[6-9]\\d{9}$"))) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Mobile");
	    }
	    
	    else if (email.trim().length() > 50) {
	    	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in Email");
	    }

	    if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() ||
	            user.getMobile() == null || user.getAddress() == null || user.getEmail() == null || user.getRole() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incomplete user data");
	    }

	    if (userService.findUserByEmail(user.getEmail()) != null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
	    } else {
	        userService.saveUser(user);
	        return ResponseEntity.ok("User " + user.getEmail() + " registered successfully");
	    }
	}


	@PostMapping("/user")
	public ResponseEntity<?> getUserByEmailAndPassword(@RequestBody User user) {
		User foundUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (foundUser != null) {
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.findAll();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
}
