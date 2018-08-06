package com.tianchen.concurrency.threadlocal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EntryDemo {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "abc");
		map.put(2, "bcd");
		map.put(3, "cde");
		map.put(4, "def");
		map.put(5, "efg");

		Set<Integer> keys = map.keySet();
		if (keys != null) {
			Iterator<Integer> iterator = keys.iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				Object value = map.get(key);
				System.out.println(key + " -------- " + value);

			}
		}

		Set<Entry<Integer, String>> entries = map.entrySet();
		if (entries != null) {
			Iterator<Entry<Integer, String>> iterator = entries.iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, String> entry = iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
			}
		}

	}

}
