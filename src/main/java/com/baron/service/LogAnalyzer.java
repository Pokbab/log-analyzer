package com.baron.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;
import com.baron.util.MapUtil;

@Service
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
				StatusType statusType = StatusType.getStatusTypeByCode(logItem);
				model.setStatusType(statusType);
				if (statusType != StatusType.SUCCESS) {
					return model;
				}
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

	public void addResultToRepository(List<LogModel> logModelList, ResultRepository repository) {
		for (LogModel logModel : logModelList) {
			repository.addAnalyzeResult(logModel);
		}
	}

	public OutputModel analyze(ResultRepository repository) {
		OutputModel model = new OutputModel();

		LinkedHashMap<String, Integer> apiKeyMap = MapUtil.sortByValue(repository.getApiKeyMap(), MapUtil.SortType.DESC);
		model.setMostCallApikey((String)apiKeyMap.keySet().toArray()[0]);
		
		LinkedHashMap<LocalDateTime, Integer> callTimeMap = MapUtil.sortByValue(repository.getCallTimeMap(), MapUtil.SortType.DESC);
		model.setPeakHours((LocalDateTime)callTimeMap.keySet().toArray()[0]);
		
		Map<BrowserType, Integer> usedBrowserMap = repository.getUsedBrowserMap();
		Map<BrowserType, Float> ratePerUsedBrowserMap = new LinkedHashMap<BrowserType, Float>();
		for (BrowserType type : usedBrowserMap.keySet()) {
			float ratePerUsedBrowser = (float)usedBrowserMap.get(type) / (float)repository.getTotalCallNumber() * 100f;
			ratePerUsedBrowserMap.put(type, ratePerUsedBrowser);
		}
		model.setRatePerUsedBrowserMap(ratePerUsedBrowserMap);
		
		model.setRequestCountPerApiServiceMap(repository.getApiServiceMap());
		
		model.setStatusCodeResultMap(repository.getStatusCodeResultMap());
		
		return model;
	}
}
