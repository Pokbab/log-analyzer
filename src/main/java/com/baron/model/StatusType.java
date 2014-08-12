package com.baron.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 상태코드
 */
public enum StatusType {
	INVALID("10"), 
	SUCCESS("200"),
	NO_PAGE("404");
	
	private String code;
	private static final Map<String, StatusType> MAP;
	
	static {
		MAP = new HashMap<String, StatusType>();
		for (StatusType type : StatusType.values()) {
			MAP.put(type.getCode(), type);
		}
	}
	
	private StatusType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public static StatusType getStatusTypeByCode(String code) {
		return MAP.get(code);
	}
}
