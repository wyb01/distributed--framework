package com.cf.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void doGet() throws ClientProtocolException, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个get对象
		HttpGet get = new HttpGet("http://localhost:8082/rest/user/2");
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(get);
		
		// 取得响应结果
		HttpEntity entity = reponse.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
	
	@Test
	public void doGetWithParam() throws ClientProtocolException, IOException, URISyntaxException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("http://localhost:8082/rest/user");
		uriBuilder.addParameter("name", "123456");
		HttpGet get = new HttpGet(uriBuilder.build());
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(get);
		
		// 取得响应结果
		HttpEntity entity = reponse.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
	
	@Test
	public void doPost() throws Exception {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个get对象
		HttpPost post = new HttpPost("http://localhost:8082/rest/user/register");
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(post);
		
		// 取得响应结果
		HttpEntity entity = reponse.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
	
	@Test
	public void doPostWithParam() throws Exception {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个get对象
		HttpPost post = new HttpPost("http://localhost:8082/rest/user/register");
		
		// 创建entity，模拟提交一个表单内容
		List<NameValuePair> paraList = new ArrayList<>();
		paraList.add(new BasicNameValuePair("id", "3"));
		paraList.add(new BasicNameValuePair("username", "王五"));
		paraList.add(new BasicNameValuePair("password", "123"));
				
		StringEntity entity = new UrlEncodedFormEntity(paraList, "UTF-8");
		post.setEntity(entity);
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(post);
		
		// 取得响应结果
		String result = EntityUtils.toString(reponse.getEntity(), "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
	
	@Test
	public void doPutWithParam() throws Exception {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个get对象
		HttpPut put = new HttpPut("http://localhost:8082/rest/user/update");
		
		// 创建entity，模拟提交一个表单内容
		List<NameValuePair> paraList = new ArrayList<>();
		paraList.add(new BasicNameValuePair("id", "3"));
		paraList.add(new BasicNameValuePair("username", "test2"));
		paraList.add(new BasicNameValuePair("password", "123"));
				
		StringEntity entity = new UrlEncodedFormEntity(paraList, "UTF-8");
		put.setEntity(entity);
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(put);
		
		// 取得响应结果
		String result = EntityUtils.toString(reponse.getEntity(), "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
	
	@Test
	public void doDelete() throws ClientProtocolException, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建一个get对象
		HttpDelete delete = new HttpDelete("http://localhost:8082/rest/user/1");
		
		// 执行请求
		CloseableHttpResponse reponse = httpClient.execute(delete);
		
		// 取得响应结果
		HttpEntity entity = reponse.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		
		// 打印结果
		System.out.println("---------------------------------" + result);
		
		// 关闭链接
		reponse.close();
		httpClient.close();
	}
}
