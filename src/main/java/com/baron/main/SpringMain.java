/*
 * @(#)SpringMain.java $version 2015. 1. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.baron.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baron.file.LogWriter;
import com.baron.service.LogAnalyzer;

/**
 * @author Kanghoon Choi
 */
public class SpringMain {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = 
			new ClassPathXmlApplicationContext("/applicationContext.xml");
		
		
		DogHouse dogHouse = (DogHouse)context.getBean(DogHouse.class);
		dogHouse.singDogSound();
	}
}
