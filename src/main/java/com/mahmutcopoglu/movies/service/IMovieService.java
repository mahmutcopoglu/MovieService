package com.mahmutcopoglu.movies.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mahmutcopoglu.movies.dto.Movie;
import com.mahmutcopoglu.movies.dto.ServiceResponseData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IMovieService {
	
	List<Movie> getMovieByName(String movieName) throws JsonMappingException, JsonProcessingException ;
	
	ServiceResponseData getMovieSearchById(String id) throws IOException;
	
	ServiceResponseData getDetailById(String id)throws FileNotFoundException, IOException;

}
