/*
 * @(#)진돗개.java $version 2015. 1. 28.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.maple.test;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Kanghoon Choi
 */
@Component("dog1")
public class 진돗개 implements Dog {

	
	/**
	 * @return
	 * @see com.maple.test.Dog#bark()
	 */
	@Override
	public String bark() {
		return "왈왈";
	}

}
