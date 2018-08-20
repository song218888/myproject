package com.tianchen.util;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.log4j.Logger;

import com.tianchen.dao.UsersDAO;
import com.tianchen.model.User;

public class UsersTool {

	private static final Logger log = Logger.getLogger(UsersTool.class);
	
	private static final String QUOREM = "192.168.18.76,192.168.18.82,192.168.18.83";
	private static final String CLIENT_PORT = "2181";
	private HBaseAdmin admin;
	private Configuration conf;
	
	
	public HBaseAdmin getHBaseAdmin(){
		getConfiguration();
        try {
			admin = new HBaseAdmin(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin; 
	}
	
	public Configuration getConfiguration(){
		if(conf == null){
			conf = HBaseConfiguration.create();
	        conf.set("hbase.zookeeper.quorum", QUOREM);   
	        conf.set("hbase.zookeeper.property.clientPort", CLIENT_PORT);   
		}
		return conf;
	}
	

	public static void main(String[] args) throws IOException {
		
		UsersTool tools = new UsersTool();
		tools.getConfiguration();
		

//		HTablePool pool = new HTablePool();
//		UsersDAO dao = new UsersDAO(pool);
//
//		log.debug(String.format("Getting user %s", args[1]));
//		User u = dao.getUser(args[1]);
//		System.out.println(u);
//
//		dao.addUser(args[1], args[2], args[3], args[4]);
//		User u2 = dao.getUser(args[1]);
//		System.out.println("Successfully added user " + u2);
//
//		List<User> users = dao.getUsers();
//		log.info(String.format("Found %s users.", users.size()));
//		for (User u3 : users) {
//			System.out.println(u3);
//		}
//
//		pool.closeTablePool(UsersDAO.TABLE_NAME);
	}
}
