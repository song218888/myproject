package com.tianchen.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHive {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";//jdbc驱动路径
    private static String url = "jdbc:hive2://192.168.18.83:10000/default";//hive库地址+库名
    private static String user = "cdh";//用户名
    private static String password = "hadooop";//密码
    private static String sql = "";
    private static ResultSet res;

    public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try {
        conn = getConn();
        System.out.println(conn);
        stmt = conn.createStatement();
                    String tableName="tab_name";//hive表名
                    sql = "select * from " + tableName;
            System.out.println("Running:" + sql);
            res = stmt.executeQuery(sql);
            System.out.println("执行 select * query 运行结果:");
            while (res.next()) {
                System.out.println(res.getInt(1) + "\t" + res.getString(2));
            }

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        System.exit(1);
    } catch (SQLException e) {
        e.printStackTrace();
        System.exit(1);
    } finally {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

private static Connection getConn() throws ClassNotFoundException,
        SQLException {
    Class.forName(driverName);
    Connection conn = DriverManager.getConnection(url, user, password);
    return conn;
}
}
