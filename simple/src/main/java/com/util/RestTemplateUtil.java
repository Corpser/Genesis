package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class RestTemplateUtil {

	@Autowired
	private RestTemplate restTemplate;
	
	public List<?> postForList(String url,Object request){
		return restTemplate.postForObject(url, request, List.class);
	}
	
	public Object postForObject(String url,Object request,Class<?> classes){
		return restTemplate.postForObject(url, request, classes);
	}
	
	public List<?> getForList(String url,Map<String,String> parmMap){
		return restTemplate.getForObject(url, List.class, parmMap);
	}
	
	public Object getForObject(String url,Map<String,String> parmMap,Class<?> classes){
		return restTemplate.getForObject(url, classes, parmMap);
	}
}
