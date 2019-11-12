package com.cf.portal.service.impl;

import org.springframework.stereotype.Service;

import com.cf.portal.service.IndexService;
import com.cf.utils.HttpClientUtil;

@Service
public class IndexServiceImpl implements IndexService {

	@Override
	public String get(Long id) {
		// 
		String json = HttpClientUtil.doGet("http://localhost:8082/rest/user/" + id);
		
		return json;
	}

}
