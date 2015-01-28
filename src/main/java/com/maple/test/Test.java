/*
 * @(#)Test.java $version 2015. 1. 28.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.maple.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kanghoon Choi
 */
public class Test {

	
	public static void main(String[] args) {
		
//		DogHouse dogHouse = new DogHouse();
//		
//		Dog dog1 = new 진돗개();
//		Dog dog2 = new 푸들();
//		
//		System.out.println(dogHouse);
//		System.out.println(dog1);
//		System.out.println(dog2);
		
		ClassPathXmlApplicationContext context = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
		
		DogHouse dogHouse = context.getBean(DogHouse.class);
		Dog dog1 = (Dog)context.getBean("dog1");
		Dog dog2 = (Dog)context.getBean("dog2");
		
		System.out.println(dogHouse);
		System.out.println(dog1);
		System.out.println(dog2);
		
		dogHouse.개를짖게함();
	}
}
