package org.myproject.redis.transation;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TransactionDemo {
	private static Jedis jedis;

	public static void main(String[] args) {
		jedis = new Jedis("127.0.0.1",6379);  
        
        Transaction transaction=jedis.multi();//返回一个事务控制对象  
          
        //预先在事务对象中装入要执行的操作  
        transaction.set("k4", "v4");  
        transaction.set("k5", "v5");  
          
        transaction.exec();//执行  
	}
}
