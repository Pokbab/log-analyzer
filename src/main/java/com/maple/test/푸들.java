/*
 * @(#)푸들.java $version 2015. 1. 28.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.maple.test;

import org.springframework.stereotype.Component;

/**
 * @author Kanghoon Choi
 */
@Component("dog2")
public class 푸들 implements Dog {

	/**
	 * @return
	 * @see com.maple.test.Dog#bark()
	 */
	@Override
	public String bark() {
		return "깨갱";
	}

}
