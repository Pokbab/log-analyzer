package com.baron.service;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.LogModel;
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
	public void testConvertLog_상태코드가_200이_아니면_더이상의_로그분석을_하지않는다() throws Exception {
		String log = "[404][http://apis.daum.net/search/aaaaapikey=jg9k&q=daum][Opera][2009-06-10 09:58:45]";
		
		LogModel actual = analyzer.convertLog(log);
		
		assertEquals(StatusType.NO_PAGE, actual.getStatusType());
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
