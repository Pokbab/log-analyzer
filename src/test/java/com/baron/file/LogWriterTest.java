package com.baron.file;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.baron.model.APIServiceType;
import com.baron.model.BrowserType;
import com.baron.model.OutputModel;
import com.baron.model.StatusType;

public class LogWriterTest {
	
	@Test
	public void testWriteLogFile_입력받은_문자열을_파일로_출력한다() throws Exception {
		
		OutputModel model = new OutputModel();
		model.setMostCallApikey("f83e");
		
		Map<StatusType, Integer> statusCodeResultMap = new LinkedHashMap<StatusType,Integer>();
		statusCodeResultMap.put(StatusType.INVALID, 66);
		statusCodeResultMap.put(StatusType.SUCCESS, 3785);
		statusCodeResultMap.put(StatusType.NO_PAGE, 105);
		model.setStatusCodeResultMap(statusCodeResultMap);
		
		Map<APIServiceType, Integer> requestCountPerApiServiceMap = new LinkedHashMap<APIServiceType, Integer>();
		requestCountPerApiServiceMap.put(APIServiceType.BLOG, 1224);
		requestCountPerApiServiceMap.put(APIServiceType.VDIP, 871);
		requestCountPerApiServiceMap.put(APIServiceType.IMAGE, 705);
		model.setRequestCountPerApiServiceMap(requestCountPerApiServiceMap);
		
		LocalDateTime peakHours = LocalDateTime.of(2009, 9, 10, 8, 51);
		model.setPeakHours(peakHours);
		
		Map<BrowserType, Float> ratePerUsedBrowserMap = new LinkedHashMap<BrowserType, Float>();
		ratePerUsedBrowserMap.put(BrowserType.IE, 79.1f);
		ratePerUsedBrowserMap.put(BrowserType.FIREFOX, 9.4f);
		ratePerUsedBrowserMap.put(BrowserType.SAFARI, 3.1f);
		ratePerUsedBrowserMap.put(BrowserType.CHROME, 7.2f);
		ratePerUsedBrowserMap.put(BrowserType.OPERA, 1.2f);
		model.setRatePerUsedBrowserMap(ratePerUsedBrowserMap);
		
		LogWriter writer = new LogWriter();
		
		boolean actual = writer.writeLogFile(model);
		
		assertTrue(actual);
	}
}
