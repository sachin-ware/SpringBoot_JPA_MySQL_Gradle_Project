package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;

@Aspect
@Component
public class UserAspect {

	
    @Before("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User)) && args(user)")

//com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(User user)
	 //@Before("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(..))")
    public void beforeUserSave(User user) {
       System.out.println("++++++++++++++++++++++++++++++Creating UserBefore Pointcut: \n"+user.toString());

    }
    
    
    
    @After("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User)) && args(user)")

  //com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(User user)
  	 //@Before("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(..))")
      public void aftereUserSave(User user) {
         System.out.println("++++++++++++++++++++++++++++++Creating User After pointcut: \n"+user.toString());

      }
}
