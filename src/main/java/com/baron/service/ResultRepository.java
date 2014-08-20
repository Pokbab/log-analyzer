package com.baron.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.StatusType;

public class ResultRepository {
	private Map<String, Integer> apiKeyMap;
	private Map<StatusType, Integer> statusCodeResultMap;
	private Map<APIServiceType, Integer> apiServiceMap;
	private Map<LocalDateTime, Integer> callTimeMap;
	private Map<BrowserType, Integer> usedBrowserMap;
	
	public ResultRepository() {
		apiKeyMap = new HashMap<String, Integer>();
		
		statusCodeResultMap = new LinkedHashMap<StatusType, Integer>();
		statusCodeResultMap.put(StatusType.INVALID, 0);
		statusCodeResultMap.put(StatusType.SUCCESS, 0);
		statusCodeResultMap.put(StatusType.NO_PAGE, 0);
		
		apiServiceMap = new HashMap<APIServiceType, Integer>();
		callTimeMap = new HashMap<LocalDateTime, Integer>();
		usedBrowserMap = new LinkedHashMap<BrowserType, Integer>();
	}
	
	public void addAnalyzeResult(LogModel model) {
		// apiKey 가산기
		addNumber(model.getApiKey(), getApiKeyMap());
		
		// status 가산기
		addNumber(model.getStatusType(), getStatusCodeResultMap());
		
		// apiService 가산기
		addNumber(model.getApiServiceType(), getApiServiceMap());
		
		// 호출시간 가산기
		addNumber(model.getCallTime(), getCallTimeMap());
		
		// 사용된브라우저 가산기
		addNumber(model.getBrowserType(), getUsedBrowserMap());
	}
	
	private <T> void addNumber(T value, Map<T, Integer> map) {
		int callNumber = 1;
		if (map.containsKey(value)) {
			callNumber += map.get(value);
		}
		map.put(value, callNumber);
	}

	public Map<String, Integer> getApiKeyMap() {
		return apiKeyMap;
	}

	public Map<StatusType, Integer> getStatusCodeResultMap() {
		return statusCodeResultMap;
	}

	public Map<APIServiceType, Integer> getApiServiceMap() {
		return apiServiceMap;
	}

	public Map<LocalDateTime, Integer> getCallTimeMap() {
		return callTimeMap;
	}

	public Map<BrowserType, Integer> getUsedBrowserMap() {
		return usedBrowserMap;
	}
}
