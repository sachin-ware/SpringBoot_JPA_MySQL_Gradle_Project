package com.sware.SpringBoot_JPA_MySQL_Gradle_Project.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class UserServiceTest {

	@Test
	public void  testAdd() {
		UserService userservice=new UserService();
		int a=5,b=2;
		
		assertEquals(7, userservice.add(a, b));
		System.out.println("test is run*****");
	}
}
