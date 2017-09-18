package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

		
}
