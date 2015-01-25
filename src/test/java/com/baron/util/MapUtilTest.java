package com.baron.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class MapUtilTest {

	@Test
	public void testSortByValue_내림차순() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("첫번째", 5);
		map.put("두번째", 3);
		map.put("세번째", 8);
		map.put("네번째", 1);
		map.put("다섯번째", 20);
		
		Map<String, Integer> actual = MapUtil.sortByValue(map, MapUtil.SortType.ASC);
		
		assertEquals("{네번째=1, 두번째=3, 첫번째=5, 세번째=8, 다섯번째=20}", actual.toString());
	}
	
	@Test
	public void testSortByValue_오름차순() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("첫번째", 5);
		map.put("두번째", 3);
		map.put("세번째", 8);
		map.put("네번째", 1);
		map.put("다섯번째", 20);
		
		Map<String, Integer> actual = MapUtil.sortByValue(map, MapUtil.SortType.DESC);
		
		assertEquals("{다섯번째=20, 세번째=8, 첫번째=5, 두번째=3, 네번째=1}", actual.toString());
	}
}
