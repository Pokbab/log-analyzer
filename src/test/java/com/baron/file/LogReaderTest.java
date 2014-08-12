package com.baron.file;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class LogReaderTest {

	@Test
	public void testReadLogFile() throws Exception {
		String path = "src/main/resources/input.log";
		int interval = 1000;
		int page = 1;
		
		LogReader reader = new LogReader();
		List<String> actual = reader.readLogFile(path, interval, page);
		
		assertNotNull(actual);
		assertEquals(1000, actual.size());
	}
}
