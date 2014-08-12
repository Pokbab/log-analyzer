package com.baron.file;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;

public class LogWriter {

	public boolean writeLogFile(OutputModel model) {
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter("output.log");
			
			Map<StatusType, Integer> statusCodeResultMap = model.getStatusCodeResultMap();
			Map<APIServiceType, Integer> requestCountPerApiServiceMap = model.getRequestCountPerApiServiceMap();
			Map<BrowserType, Float> ratePerUsedBrowserMap = model.getRatePerUsedBrowserMap();
			
			StringBuilder result = new StringBuilder()
				.append("최다호출 APIKEY").append("\n")
				.append(model.getMostCallApikey()).append("\n\n")
				.append("상태코드 별 횟수").append("\n");

			for (StatusType type : statusCodeResultMap.keySet()) {
				result.append(type.getCode()).append(" : ").append(statusCodeResultMap.get(type)).append("\n");
			}
			
			result.append("\n").append("상위3개의 API ServiceID와 각각의 요청 수").append("\n");

			for (APIServiceType type : requestCountPerApiServiceMap.keySet()) {
				result.append(type.name().toLowerCase()).append(" : ").append(requestCountPerApiServiceMap.get(type)).append("\n");
			}
			
			result.append("\n").append("피크 시간대").append("\n")
				.append(model.getPeakHours().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))).append("\n\n")
				.append("웹 브라우저 별 사용비율").append("\n");
			
			for (BrowserType type : ratePerUsedBrowserMap.keySet()) {
				result.append(type.getDescription()).append(" : ").append(ratePerUsedBrowserMap.get(type)).append("%").append("\n");
			}
			
			writer.write(result.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
