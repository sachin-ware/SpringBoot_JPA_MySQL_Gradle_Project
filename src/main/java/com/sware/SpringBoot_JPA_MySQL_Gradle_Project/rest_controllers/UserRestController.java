package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService;

@RestController
@RequestMapping("/")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> userList=null;
		try {
			userList=userService.getAllUsers();
			System.out.println(userList);
		} catch (Exception e) {
			System.out.println("Exception in GetAllUsers:"+e.getMessage());
		}
		return  userList;
	}
	
	
	@GetMapping("/")
	public String home(){
		return "This is Default response from SpringBootJpaMySqlGradleProjectApplication !!!";
	}
	
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id){
		User tempUser=null;
		try {
			tempUser=userService.getUserById(id);
		} catch (Exception e) {
			System.out.println("EXCEPTION getAllUsers in UserRestController:-----------------------------\n"+e.getMessage());
		}
		return tempUser;
	}
	
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user){
		User tempUser=null;
		try {
			tempUser=userService.saveUserToDb(user);
			
		} catch (Exception e) {
			System.out.println("EXCEPTION saveUser in UserRestController:-----------------------------\n"+e.getMessage());
		}
		return tempUser;
	}
	

}
