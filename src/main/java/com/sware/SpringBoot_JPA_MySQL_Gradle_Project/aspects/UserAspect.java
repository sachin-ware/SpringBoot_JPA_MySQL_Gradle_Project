package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User;

@Aspect
@Component
public class UserAspect {

	
    @Before("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User)) && args(user)")
    public void beforeUserSave(User user) {
       System.out.println("++++++++++++++++++++++++++++++Creating UserBefore Pointcut: \n"+user.toString());

    }
    
    @Around("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(..)) ")
    public void aroundUserSave(ProceedingJoinPoint joinPoint)throws Throwable {
        System.out.println("++++++++++Around Before Proceed : \n");
         long startTime=System.currentTimeMillis();
        
        joinPoint.proceed();
        long endTime=System.currentTimeMillis();
        long timeTaken=endTime-startTime;
        
        System.out.println("+++++++++++Around After Proceed :"+timeTaken);
     }
    
    @After("execution(* com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services.UserService.saveUserToDb(com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains.User)) && args(user)")
      public void aftereUserSave(User user) {
         System.out.println("++++++++++++++++++++++++++++++Creating User After pointcut: \n"+user.toString());

      }
    
    @AfterThrowing(pointcut = "execution(* com.sware.*.*.*.*(..))", throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {

      //System.out.println("@AfterThrowing::::::::::::::::::::::::::");

      Signature signature = joinPoint.getSignature();
      String methodName = signature.getName();
      String stuff = signature.toString();
      String arguments = Arrays.toString(joinPoint.getArgs());
     System.out.println("@AfterThrowing EXCEPTION::::::::::::::::::::::::::: \n the exception is: "
          + e.getMessage()+"\n At:"
          + methodName + " \n with arguments: "
          + arguments + "\n and the full toString: " + stuff );
    }
}
