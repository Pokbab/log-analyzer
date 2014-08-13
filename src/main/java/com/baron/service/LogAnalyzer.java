package com.baron.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;

public class LogAnalyzer {
	private Map<String, Integer> apiKeyMap;
	private Map<StatusType, Integer> statusCodeResultMap;
	private Map<APIServiceType, Integer> apiServiceMap;
	private Map<LocalDateTime, Integer> callTimeMap;
	private Map<BrowserType, Integer> usedBrowserMap;

	public LogAnalyzer() {
		apiKeyMap = new HashMap<String, Integer>();
		
		statusCodeResultMap = new LinkedHashMap<StatusType, Integer>();
		statusCodeResultMap.put(StatusType.INVALID, 0);
		statusCodeResultMap.put(StatusType.SUCCESS, 0);
		statusCodeResultMap.put(StatusType.NO_PAGE, 0);
		
		apiServiceMap = new HashMap<APIServiceType, Integer>();
		callTimeMap = new HashMap<LocalDateTime, Integer>();
		usedBrowserMap = new LinkedHashMap<BrowserType, Integer>();
	}
	
	
	public LogModel convertLog(String log) {

		LogModel model = new LogModel();
		String[] logItems = splitLog(log);
		
		
		//[200]
		//[http://apis.daum.net/search/vclip?apikey=2jdc&q=daum]
		//[IE]
		//[2009-06-10 08:00:04]

		for (int i = 0; i < logItems.length; i++) {
			String logItem = logItems[i];
			
			switch (i) {
			case 0:
				model.setStatusType(StatusType.getStatusTypeByCode(logItem));
				break;

			case 1:
				String[] urlItems = logItem.split("\\?");
				String apiServiceId = urlItems[0].replaceFirst("http://apis.daum.net/search/", "");
				model.setApiServiceType(APIServiceType.valueOf(apiServiceId.toUpperCase()));
				
				String[] paramItems = urlItems[1].split("&");
				String apiKey = paramItems[0].split("=")[1];
				String query = paramItems[1].split("=")[1];
				model.setApiKey(apiKey);
				model.setQuery(query);
				break;
				
			case 2:
				model.setBrowserType(BrowserType.valueOf(logItem.toUpperCase()));
				break;
				
			case 3:
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime callTime = LocalDateTime.parse(logItem, formatter);
				model.setCallTime(callTime);
				break;
			}
		}

		return model;
	}

	private String[] splitLog(String log) {
		return log.substring(1, log.length() - 1).split("\\]\\[");
	}

	public OutputModel analyze(LogModel model) {
		OutputModel result = new OutputModel();
		
		// apiKey 가산기
		addNumber(model.getApiKey(), this.apiKeyMap);
		
		// status 가산기
		addNumber(model.getStatusType(), this.statusCodeResultMap);
		
		// apiService 가산기
		addNumber(model.getApiServiceType(), this.apiServiceMap);
		
		// 호출시간 가산기
		addNumber(model.getCallTime(), this.callTimeMap);
		
		// 사용된브라우저 가산기
		addNumber(model.getBrowserType(), this.usedBrowserMap);
		
		
		TreeMap tp = new 
		
		
		
		return result;
	}

	private <T> void addNumber(T value, Map<T, Integer> map) {
		int callNumber = 1;
		if (map.containsKey(value)) {
			callNumber += map.get(value);
		}
		map.put(value, callNumber);
	}
}
