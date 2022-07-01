package com.mahmutcopoglu.movies.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mahmutcopoglu.movies.constant.Mapper;
import com.mahmutcopoglu.movies.dto.Movie;
import com.mahmutcopoglu.movies.dto.ServiceResponseData;
import com.mahmutcopoglu.movies.service.IMovieService;

@Component
public class MovieServiceImpl implements IMovieService {

	private CollectApiServiceImpl collectApiServiceImpl;
	private FileServiceImpl fileServiceImpl;
	private Mapper mapper;

	@Autowired
	public MovieServiceImpl(CollectApiServiceImpl collectApiServiceImpl, Mapper mapper, FileServiceImpl fileServiceImpl) {
		this.collectApiServiceImpl = collectApiServiceImpl;
		this.mapper = mapper;
		this.fileServiceImpl = fileServiceImpl;
	}
	
	@Override
	public List<Movie> getMovieByName(String movieName) throws JsonMappingException, JsonProcessingException {
		String body = this.collectApiServiceImpl.searchByName(movieName);
		List<Movie> movies = this.mapper.mappedMovieName(body);
		return movies;
	}

	@Override
	public ServiceResponseData getMovieSearchById(String id) throws IOException {
		if (this.fileServiceImpl.getById(id) != null) {
			Movie movie = this.fileServiceImpl.getById(id);
			var serviceResponseData = new ServiceResponseData();
	    	serviceResponseData.setMessage("Dosyadan okundu!");
	    	serviceResponseData.setMovies(movie);
			return serviceResponseData;
		}
		String body = this.collectApiServiceImpl.searchById(id);
		Movie movie = this.mapper.mappedMovieId(body);
		var serviceResponseData = new ServiceResponseData();
    	serviceResponseData.setMessage("API ile dış servisten okundu!");
    	serviceResponseData.setMovies(movie);
		return serviceResponseData;
		
	}

	@Override
	public ServiceResponseData getDetailById(String id)throws FileNotFoundException, IOException {
		if (this.fileServiceImpl.getById(id) != null) {
			Movie movie = this.fileServiceImpl.getById(id);
			var serviceResponseData = new ServiceResponseData();
	    	serviceResponseData.setMessage("Dosyadan okundu!");
	    	serviceResponseData.setMovies(movie);
			return serviceResponseData;
		}
		String body = this.collectApiServiceImpl.searchById(id);
		Movie movie = this.mapper.mappedMovieId(body);
		var serviceResponseData = new ServiceResponseData();
    	serviceResponseData.setMessage("API ile dış servisten okundu!");
    	serviceResponseData.setMovies(movie);
		return serviceResponseData;
	}
	
	
	
	
	
	
	
	
}
