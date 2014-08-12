package com.baron.model;

/**
 * 브라우저 타입
 */
public enum BrowserType {
	IE("IE"), 
	FIREFOX("Firefox"), 
	SAFARI("Safari"), 
	CHROME("Chrome"), 
	OPERA("Opera");
	
	private String description;
	
	private BrowserType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
