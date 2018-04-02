package com.tiancheng.hystrix.demo01.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.tiancheng.hystrix.demo01.HystrixCommand4RequestCache;

public class HystrixCommand4RequestCacheTest {
	
//  @Test
  public void testWithoutCacheHits() {
      HystrixRequestContext context = HystrixRequestContext.initializeContext();
      try {
    	  
    	  System.out.println(new HystrixCommand4RequestCache(2,"aaaa"));
          assertTrue(new HystrixCommand4RequestCache(2,"HLX").execute());
          assertFalse(new HystrixCommand4RequestCache(1,"HLX").execute());
          assertTrue(new HystrixCommand4RequestCache(0,"HLX").execute());
          assertTrue(new HystrixCommand4RequestCache(58672,"HLX").execute());
      } finally {
          context.shutdown();
      }
  }

//  @Test
  public void testWithCacheHits() {
      HystrixRequestContext context = HystrixRequestContext.initializeContext();
      try {
          HystrixCommand4RequestCache command2a = new HystrixCommand4RequestCache(2,"HLX");
          HystrixCommand4RequestCache command2b = new HystrixCommand4RequestCache(2,"HLX");
          HystrixCommand4RequestCache command2c = new HystrixCommand4RequestCache(2,"HLX1");

          assertTrue(command2a.execute());
          // this is the first time we've executed this command with the value of "2" so it should not be from cache
          assertFalse(command2a.isResponseFromCache());

          assertTrue(command2b.execute());
          // this is the second time we've executed this command with the same value so it should return from cache
          assertTrue(command2b.isResponseFromCache());
          
          assertTrue(command2c.execute());
          assertFalse(command2c.isResponseFromCache());
      } finally {
          context.shutdown();
      }

      // start a new request context
      context = HystrixRequestContext.initializeContext();
      try {
          HystrixCommand4RequestCache command3a = new HystrixCommand4RequestCache(2,"HLX");
          HystrixCommand4RequestCache command3b = new HystrixCommand4RequestCache(2,"HLX");
          assertTrue(command3a.execute());
          // this is a new request context so this should not come from cache
          assertFalse(command3a.isResponseFromCache());

          // 没有command3b.execute()，command3b.isResponseFromCache()就一直为false
          assertFalse(command3b.isResponseFromCache());
      } finally {
          context.shutdown();
      }
  }
}
