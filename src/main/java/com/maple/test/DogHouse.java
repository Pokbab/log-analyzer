/*
 * @(#)DogHouse.java $version 2015. 1. 28.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.maple.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author Kanghoon Choi
 */
@Service
public class DogHouse {

	//@Autowired
	@Resource(name = "dog1")
	private Dog dog;
	
//	public void setDog(Dog dog) {
//		this.dog = dog;
//	}
//	
//	public DogHouse(Dog dog) {
//		this.dog = dog;
//	}
	
	public void 개를짖게함() {
		System.out.println(dog.bark());
	}
}
