/*
 * @(#)SpringMain.java $version 2015. 1. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.baron.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baron.file.LogReader;
import com.baron.file.LogWriter;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.service.LogAnalyzer;
import com.baron.service.ResultRepository;


/**
 * @author Kanghoon Choi
 */
public class SpringMain {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
	
		LogReader reader = context.getBean(LogReader.class);
		LogAnalyzer analyzer = context.getBean(LogAnalyzer.class);
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
