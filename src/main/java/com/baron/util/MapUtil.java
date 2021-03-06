package com.baron.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {
	
	public static <K, V> Map<K, V> sortByValue(Map<K, V> unsortedMap) {	 
		List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(unsortedMap.entrySet());
	 
		Collections.sort(list, new Comparator<Entry<K, V>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public int compare(Entry<K, V> entry1, Entry<K, V> entry2) {
				return ((Comparable) (entry1.getValue()))
						.compareTo((entry2).getValue());
			}
		});
	 
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
