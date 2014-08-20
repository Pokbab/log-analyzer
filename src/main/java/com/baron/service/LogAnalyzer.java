package com.baron.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;

public class LogAnalyzer {

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

	public void addResultToRepository(List<LogModel> logModelList, ResultRepository repository) {
		for (LogModel logModel : logModelList) {
			repository.addAnalyzeResult(logModel);
		}
	}

	public OutputModel analyze(ResultRepository repository) {

		
		return null;
	}
}
