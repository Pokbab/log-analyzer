package com.baron.service;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;


public class LogAnalyzerTest {
	private LogAnalyzer analyzer = new LogAnalyzer();
	
	@Test
	public void testConvertLog() throws Exception {
		String log = "[200][http://apis.daum.net/search/blog?apikey=e3ea&q=daum][IE][2009-06-10 10:11:10]";
		
		LogModel actual = analyzer.convertLog(log);
		
		assertTrue(getDummyLogModel().equals(actual));
	}
	
	@Test
	public void testAnalyze() throws Exception {
		LogModel model = getDummyLogModel();
		
		OutputModel actual = analyzer.analyze(model);
		
		assertNotNull(actual);
	}
	
	private LogModel getDummyLogModel() {
		LogModel model = new LogModel();
		model.setStatusType(StatusType.SUCCESS);
		model.setApiServiceType(APIServiceType.BLOG);
		model.setApiKey("e3ea");
		model.setQuery("daum");
		model.setBrowserType(BrowserType.IE);
		model.setCallTime(LocalDateTime.of(2009, 06, 10, 10, 11));
		
		return model;
	}
}
