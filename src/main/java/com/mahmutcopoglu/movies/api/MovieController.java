package com.mahmutcopoglu.movies.api;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mahmutcopoglu.movies.dto.Movie;
import com.mahmutcopoglu.movies.dto.ServiceResponseData;
import com.mahmutcopoglu.movies.service.impl.MovieServiceImpl;

@RestController
public class MovieController {
	
	private MovieServiceImpl movieService;

	@Autowired
	public MovieController(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}
	
    @GetMapping("/movies/search")
    public List<Movie> search(@RequestParam(name = "movie_name") String movieName)throws JsonMappingException, JsonProcessingException{
    	return this.movieService.getMovieByName(movieName);
        
    }
    
    @PostMapping("/movies/saveToList/{id}")
	public ServiceResponseData addToList(@PathVariable(name = "id") String id) throws IOException {
		return this.movieService.getMovieSearchById(id);
	}

    @PostMapping("/movies/detail/{id}")
    public ServiceResponseData detail(@PathVariable(name = "id") String id)throws FileNotFoundException, IOException{
    	return this.movieService.getDetailById(id);
    }
}
	

