//package com.cf.jedis.test;
//
//import java.util.HashSet;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//public class JedisTest {
//
//	/**
//	 * 单机版测试
//	 *
//	 */
//	@Test
//	public void testJedisSingle() {
//		// 创建一个jedis对象
//		Jedis jedis = new Jedis("192.168.241.128", 6379);
//		// 调用jedis对象的方法，方法名称和redis命令一致
//		jedis.set("key1", "jedis test");
//		String result = jedis.get("key1");
//
//		System.out.println(result);
//		// 关闭jedis
//		jedis.close();
//	}
//
//	/**
//	 * 单机版测试
//	 *
//	 */
//	@Test
//	public void testJedisPool() {
//		JedisPool pool = new JedisPool("192.168.241.128", 6379);
//
//		// 创建一个jedis对象
//		Jedis jedis = pool.getResource();
//		// 调用jedis对象的方法，方法名称和redis命令一致
//		jedis.set("key2", "jedis test");
//		String result = jedis.get("key2");
//
//		System.out.println(result);
//		// 关闭jedis
//		jedis.close();
//
//		pool.close();
//	}
//
//	/**
//	 * 集群测试
//	 */
//	@Test
//	public void testJedisCluster() {
//		HashSet<HostAndPort> nodes = new HashSet<>();
//		nodes.add(new HostAndPort("192.168.241.128", 7001));
//		nodes.add(new HostAndPort("192.168.241.128", 7002));
//		nodes.add(new HostAndPort("192.168.241.128", 7003));
//		nodes.add(new HostAndPort("192.168.241.128", 7004));
//		nodes.add(new HostAndPort("192.168.241.128", 7005));
//		nodes.add(new HostAndPort("192.168.241.128", 7006));
//
//		JedisCluster cluster = new JedisCluster(nodes);
//
//		cluster.set("clusterKey", "1000");
//
//		String result = cluster.get("clusterKey");
//		System.out.println(result);
//		cluster.close();
//	}
//
//
//	/**
//	 * spring与redis单机版测试
//	 *
//	 */
//	@Test
//	public void testSpringJedisSingle() {
//		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//
//		JedisPool pool = (JedisPool) app.getBean("redisClient");
//
//		// 创建一个jedis对象
//		Jedis jedis = pool.getResource();
//		// 调用jedis对象的方法，方法名称和redis命令一致
//		String result = jedis.get("key2");
//
//		System.out.println(result);
//		// 关闭jedis
//		jedis.close();
//
//		pool.close();
//	}
//
//	/**
//	 * spring与redis集群版测试
//	 *
//	 */
//	@Test
//	public void testSpringJedisCluster() {
//		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//
//		JedisCluster cluster = (JedisCluster)app.getBean("redisClient");
//
//		cluster.hdel("UserInfo", "1");
//		String result = cluster.get("clusterKey");
//		System.out.println(result);
//		cluster.close();
//	}
//}
