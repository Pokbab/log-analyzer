package com.baron.main;

import java.util.ArrayList;
import java.util.List;

import com.baron.file.LogReader;
import com.baron.file.LogWriter;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.service.LogAnalyzer;
import com.baron.service.ResultRepository;

public class Main {

	public static void main(String[] args) {
		
		LogReader reader = new LogReader();
		LogAnalyzer analyzer = new LogAnalyzer();
		ResultRepository repository = new ResultRepository();
		
		for (int page = 1; page <= 5; page++) {
			List<String> logStringList = reader.readLogFile("src/main/resources/input.log", 1000, page);
			
			List<LogModel> logModelList = new ArrayList<LogModel>();
			
			for (String logString : logStringList) {
				LogModel logModel = analyzer.convertLog(logString);
				logModelList.add(logModel);
			}
			
			analyzer.addResultToRepository(logModelList, repository);
		}
		
		OutputModel outputModel = analyzer.analyze(repository);
		
		LogWriter writer = new LogWriter();
		writer.writeLogFile(outputModel);
	}
}
