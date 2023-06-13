package com.ifsp.lp.work.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifsp.lp.work.service.UserService;
import com.ifsp.lp.work.dto.UserDTO;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/users")
public class userController {
	 	@Autowired
	    private UserService userService;
		@CrossOrigin
	    @GetMapping("find/{id}")
	    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id) throws IOException {
	    	UserDTO user = userService.getUserById(id);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping
	    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
	    	UserDTO createdUser = userService.createUser(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	    }
		@CrossOrigin
	    @PutMapping("/{id}")
	    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody UserDTO user) {
	    	UserDTO updatedUser = userService.updateUser(id, user);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
	        boolean deleted = userService.deleteUser(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
			@CrossOrigin
	    @GetMapping("allUsers")
	    public ResponseEntity<List<UserDTO>> getAllUsers() throws IOException {

	        List<UserDTO> users = userService.getAllUsers();
	        return ResponseEntity.ok(users);
	    }
	@CrossOrigin
	@GetMapping("initDataBase")
	public ResponseEntity.BodyBuilder initDB() {

		List<UserDTO> users = userService.initDB();
		return ResponseEntity.ok();
	}
}
