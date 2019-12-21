package com.cf.jedis.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

public class JedisTest {

	/**
	* @Description: 单机版测试1
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 11:17:48
	*/
	@Test
	public void testJedisSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("120.79.178.18", 7003);
		// 调用jedis对象的方法，方法名称和redis命令一致
		jedis.set("key1", "jedis test");
		String result = jedis.get("key1");
		System.out.println(result);
		// 关闭jedis
		jedis.close();
	}

	/**
	* @Description: 单机版测试2（连接池）
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 11:17:38
	*/
	@Test
	public void testJedisPool() {
		JedisPool pool = new JedisPool("120.79.178.18", 6379);  //jedis连接池
		// 创建一个jedis对象
		Jedis jedis = pool.getResource();
		// 调用jedis对象的方法，方法名称和redis命令一致
		jedis.set("key2", "jedis test");
		String result = jedis.get("key2");
		System.out.println(result);
		// 关闭jedis
		jedis.close();
		pool.close();
	}

	/**
	* @Description: redis集群测试
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 11:18:01
	*/
	@Test
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("120.79.178.18", 7001));
		nodes.add(new HostAndPort("120.79.178.18", 7002));
		nodes.add(new HostAndPort("120.79.178.18", 7003));
		nodes.add(new HostAndPort("120.79.178.18", 7004));
		nodes.add(new HostAndPort("120.79.178.18", 7005));
		nodes.add(new HostAndPort("120.79.178.18", 7006));

		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("clusterKey", "1000");
		String result = cluster.get("clusterKey");
		System.out.println(result);
		cluster.close();
	}

	/**
	* @Description: spring与redis"单机版"测试
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 11:13:46
	*/
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) app.getBean("redisClient");  //在容器中获取bean
		Jedis jedis = pool.getResource();  // 创建一个jedis对象
		String result = jedis.get("key2"); // 调用jedis对象的方法，方法名称和redis命令一致
		System.out.println(result);
		// 关闭jedis
		jedis.close();
		pool.close();
	}

	/**
	* @Description: spring与redis"集群版"测试
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 11:13:21
	*/
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster)app.getBean("redisClient");
		cluster.hdel("UserInfo", "1");
		String result = cluster.get("clusterKey");
		System.out.println(result);
		cluster.close();
	}
}