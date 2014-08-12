package com.baron.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReader {

	public List<String> readLogFile(String path, int interval, int page) {
		BufferedReader reader = null;
		List<String> result = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(path));
			
			for (int i = (interval * (page - 1)) + 1; i < (interval * page) + 1; i++) {
				result.add(reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
