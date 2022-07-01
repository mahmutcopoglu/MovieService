package com.mahmutcopoglu.movies.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mahmutcopoglu.movies.service.ICollectApi;
import org.springframework.stereotype.Component;


@Component
public class CollectApiServiceImpl implements ICollectApi {
	
	
	@Value(value = "${collect.api.contentType}")
	private String contentType;	
	@Value(value = "${collect.api.authorization}")
	private String authorization;
	@Value(value = "${collect.api.getMovieByName}")
	private String getMovieByName;
	@Value(value = "${collect.api.getMovieById}")
	private String getMovieById;
	
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CollectApiServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	public String searchByName(String movieName) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", contentType);
		headers.add("authorization", authorization);
		
	    HttpEntity<String> entity = new HttpEntity<>(headers);
		

		ResponseEntity<String> response = restTemplate.exchange(getMovieByName + movieName, HttpMethod.GET, entity,String.class);		
		String body = response.getBody();
	
		return body;

	}
	
	public String searchById(String movieId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", contentType);
		headers.set("authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(getMovieById + movieId, HttpMethod.GET, entity, String.class);
		String body = response.getBody();
		
		return body;
	}
	
}
