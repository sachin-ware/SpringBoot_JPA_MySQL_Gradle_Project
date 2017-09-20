package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;
import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.projections.UserWithFnameAndLname;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//Following is named query where we can pass value to email dynamically.
	@Query("select u from User u where u.email= ?")
	User findByEmailAddress(String emailAddress);
	
	//Native Query:The @Query annotation allows to execute native queries by setting the nativeQuery flag to true.
	@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	  User findByEmailId(String emailAddress);
	
	
	Collection<UserWithFnameAndLname> findByFname(String fName);
}
