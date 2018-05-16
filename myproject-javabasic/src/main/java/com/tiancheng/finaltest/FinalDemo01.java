package com.tiancheng.finaltest;

import java.util.ArrayList;
import java.util.List;

public class FinalDemo01 {
	public static void main(String[] args) {
		final List<String> strList = new ArrayList<>();
		 strList.add("Hello");
		 strList.add("world");  
//		 List<String> unmodifiableStrList = (List<String>)List.of("hello", "world");
//		 unmodifiableStrList.add("again");
		 
		 
		 StringBuffer sb = new StringBuffer();
	}
}
