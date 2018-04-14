package com.tiancheng.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo01 {
	// json字符串-简单对象型
	private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

	// json字符串-数组类型
	private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

	// 复杂格式json字符串
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

	public static void main(String[] args) {
//		FastJsonDemo01.testJSONObjectToJSONStr();
//		FastJsonDemo01.testJSONStrToJSONObject();
//		FastJsonDemo01.testJSONStrToJSONArray();
		FastJsonDemo01.testJSONArrayToJSONStr();
	}
	
	
	
	/**
	 * json字符串-简单对象型到JSONObject的转换
	 */
	public static void testJSONStrToJSONObject() {

	    JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

	    System.out.println("studentName:  " + jsonObject.getString("studentName") + " | " + "  studentAge:  "
	            + jsonObject.getInteger("studentAge"));

	}

	/**
	 * JSONObject到json字符串-简单对象型的转换
	 */
	public static void testJSONObjectToJSONStr() {

	    //已知JSONObject,目标要转换为json字符串
	    JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
	    // 第一种方式
	    String jsonString = JSONObject.toJSONString(jsonObject);

	    // 第二种方式
	    //String jsonString = jsonObject.toJSONString();
	    System.out.println(jsonString);
	}
	
	
	/**
	 * json字符串-数组类型到JSONArray的转换
	 */
	public static void testJSONStrToJSONArray() {

	    JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
	    System.out.println(JSON_ARRAY_STR);

	    //遍历方式1
	    int size = jsonArray.size();
	    for (int i = 0; i < size; i++) {

	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
	                + jsonObject.getInteger("studentAge"));
	    }

	    //遍历方式2
	    for (Object obj : jsonArray) {

	        JSONObject jsonObject = (JSONObject) obj;
	        System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
	                + jsonObject.getInteger("studentAge"));
	    }
	}

	/**
	 * JSONArray到json字符串-数组类型的转换
	 */
	public static void testJSONArrayToJSONStr() {
		System.out.println(JSON_ARRAY_STR);

	    //已知JSONArray,目标要转换为json字符串
	    JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
	    //第一种方式
	    String jsonString = JSONArray.toJSONString(jsonArray);

	    // 第二种方式
	    //String jsonString = jsonArray.toJSONString(jsonArray);
	    System.out.println(jsonString);
	}
}
