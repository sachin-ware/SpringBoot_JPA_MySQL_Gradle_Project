package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public List<User> getAllUsers(){

		 List<User> userList=new ArrayList<>();
		try {
			userList=userRepository.findAll();
			
		} catch (Exception e) {
			System.out.println("Exception in UserRepostory:"+e.getMessage());
		}
		return userList;
	}
	public User saveUserToDb(User user) {
		User usr=new User();
		try {
			Date now =new Date();
			user.setCreatedDate(now);
			user.setModifiedDate(now);
			usr=userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Exception in seveUserToDb:"+e.getMessage());
		}
		return usr;
	}
	public User getUserById(Integer id) {
		User usr=new User();
		try {
			usr=userRepository.findOne(id);
		} catch (Exception e) {
			System.out.println("Exception in getUserById:"+e.getMessage());
		}
		return usr;
	}
	public User updateUser(User user) {
		try {
			user.setModifiedDate(new Date());
			user=userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Exception in updateUser:"+e.getMessage());
		}
		return user;
	}

}
