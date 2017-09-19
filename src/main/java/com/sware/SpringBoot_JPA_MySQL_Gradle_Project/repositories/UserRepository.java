package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//Following is named query where we can pass value to email dynamically.
	@Query("select u from User u where u.email= ?")
	User findByEmailAddress(String emailAddress);
}
