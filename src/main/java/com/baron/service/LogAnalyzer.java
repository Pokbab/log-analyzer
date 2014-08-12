package com.baron.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;

public class LogAnalyzer {
	private Map<String, Integer> apiKeyMap;
	private Map<StatusType, Integer> statusCodeResultMap;

	public LogAnalyzer() {
		apiKeyMap = new HashMap<String, Integer>();
//		statusCodeResultMap = new 
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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
		
		String apiKey = model.getApiKey();
		int callNumber = 1;
		if (apiKeyMap.containsKey(apiKey)) {
			callNumber += apiKeyMap.get(apiKey);
		}
		apiKeyMap.put(apiKey, callNumber);
		
		
		//TODO : 분석기 개발작업 진행할 차례
		
		
		
		return result;
	}
}
