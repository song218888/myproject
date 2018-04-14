package com.tiancheng.jsonpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JsonPathDemo01 {
	public static void main(String[] args) {
		String json = FileUtils.readFileByLines("e:/demo.json");
        ReadContext context = JsonPath.parse(json);

        //1 返回所有name
        List<String> names = context.read("$.result.records[*].name");
        //["张三","李四","王五"]
        System.out.println(names);

        //2 返回所有数组的值
        List<Map<String, String>> objs = context.read("$.result.records[*]");
        //[{"name":"张三","pid":"500234199212121212","mobile":"18623456789","applied_at":"3","confirmed_at":"5","confirm_type":"overdue","loan_type":"1","test":"mytest","all":"2"},{"name":"李四","pid":"500234199299999999","mobile":"13098765432","applied_at":"1","confirmed_at":"","confirm_type":"overdue","loan_type":"3","all":"3"},{"name":"王五","pid":"50023415464654659","mobile":"1706454894","applied_at":"-1","confirmed_at":"","confirm_type":"overdue","loan_type":"3"}]
        System.out.println("======== : " + objs);

        //3 返回第一个的name
        String name0 = context.read("$.result.records[0].name");
        //张三
        System.out.println(name0);

        //4 返回下标为0 和 2 的数组值
        List<String> name0and2 = context.read("$.result.records[0,2].name");
        //["张三","王五"]
        System.out.println(name0and2);

        //5 返回下标为0 到 下标为1的 的数组值  这里[0:2] 表示包含0 但是 不包含2
        List<String> name0to2 = context.read("$.result.records[0:2].name");
        //["张三","李四"]
        System.out.println(name0to2);

        //6 返回数组的最后两个值
        List<String> lastTwoName = context.read("$.result.records[-2:].name");
        //["李四","王五"]
        System.out.println(lastTwoName);

        //7 返回下标为1之后的所有数组值 包含下标为1的
        List<String> nameFromOne = context.read("$.result.records[1:].name");
        //["李四","王五"]
        System.out.println(nameFromOne);

        //8 返回下标为3之前的所有数组值  不包含下标为3的
        List<String> nameEndTwo = context.read("$.result.records[:3].name");
        //["张三","李四","王五"]
        System.out.println(nameEndTwo);

        //9 返回applied_at大于等于2的值
        List<Map<String, String>> records = context.read("$.result.records[?(@.applied_at >= '2')]");
        //[{"name":"张三","pid":"500234199212121212","mobile":"18623456789","applied_at":"3","confirmed_at":"5","confirm_type":"overdue","loan_type":"1","test":"mytest","all":"2"}]
        System.out.println(records);

        //10 返回name等于李四的值
        List<Map<String, String>> records0 = context.read("$.result.records[?(@.name == '李四')]");
        //[{"name":"李四","pid":"500234199299999999","mobile":"13098765432","applied_at":"1","confirmed_at":"","confirm_type":"overdue","loan_type":"3"}]
        System.out.println(records0);

        //11 返回有test属性的数组
        List<Map<String, String>> records1 = context.read("$.result.records[?(@.test)]");
        //[{"name":"张三","pid":"500234199212121212","mobile":"18623456789","applied_at":"3","confirmed_at":"5","confirm_type":"overdue","loan_type":"1","test":"mytest","all":"2"}]
        System.out.println(records1);

        //12 返回有test属性的数组
        List<String> list = context.read("$..all");
        //["1","4","2","3"]
        System.out.println(list);

        //12 以当前json的某个值为条件查询 这里ok为1 取出records数组中applied_at等于1的数组
        List<String> ok = context.read("$.result.records[?(@.applied_at == $['ok'])]");
        //["1","4","2","3"]
        System.out.println(ok);

        //13 正则匹配
        List<String> regexName = context.read("$.result.records[?(@.pid =~ /.*999/i)]");
        //[{"name":"李四","pid":"500234199299999999","mobile":"13098765432","applied_at":"1","confirmed_at":"","confirm_type":"overdue","loan_type":"3","all":"3"}]
        System.out.println(regexName);

        //14 多条件
        List<String> mobile = context.read("$.result.records[?(@.all == '2' || @.name == '李四' )].mobile");
        //["18623456789","13098765432"]
        System.out.println(mobile);

        //14 查询数组长度
        Integer length01 = context.read("$.result.records.length()");
        //3
        System.out.println(length01);

        //15 查询list里面每个对象长度
        List<Integer> length02 = context.read("$.result.records[?(@.all == '2' || @.name == '李四' )].length()");
        //[9,8]
        System.out.println(length02);

        //16 最大值
        Object maxV = context.read("$.max($.result.records[0].loan_type,$.result.records[1].loan_type,$.result.records[2].loan_type)");
        //3.0
        System.out.println(maxV);

        //17 最小值
        Object minV = context.read("$.min($.result.records[0].loan_type,$.result.records[1].loan_type,$.result.records[2].loan_type)");
        //1.0
        System.out.println(minV);

        //18 平均值
        double avgV = context.read("$.avg($.result.records[0].loan_type,$.result.records[1].loan_type,$.result.records[2].loan_type)");
        //2.3333333333333335
        System.out.println(avgV);

        //19 标准差
        double stddevV = context.read("$.stddev($.result.records[0].loan_type,$.result.records[1].loan_type,$.result.records[2].loan_type)");
        //0.9428090415820636
        System.out.println(stddevV);

        //20 读取一个不存在的
//        String haha = context.read("$.result.haha");
        //抛出异常
        //Exception in thread "main" com.jayway.jsonpath.PathNotFoundException: No results for path: $['result']['haha']
        //at com.jayway.jsonpath.internal.path.EvaluationContextImpl.getValue(EvaluationContextImpl.java:133)
        //at com.jayway.jsonpath.JsonPath.read(JsonPath.java:187)
        //at com.jayway.jsonpath.internal.JsonContext.read(JsonContext.java:102)
        //at com.jayway.jsonpath.internal.JsonContext.read(JsonContext.java:89)
        //at cn.lijie.jsonpath.JsonPathDemo.main(JsonPathDemo.java:58)
        //at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        //at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //at java.lang.reflect.Method.invoke(Method.java:498)
        //at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
//        System.out.println(haha);
    }
}





class FileUtils {
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        BufferedReader reader = null;
        String str = "";
        try {
            InputStream is = new FileInputStream(new File(fileName));
            reader = new BufferedReader(new InputStreamReader(is));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                str += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return str;
    }
}
