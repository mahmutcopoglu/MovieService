package com.mahmutcopoglu.movies.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.mahmutcopoglu.movies.dto.Movie;

public interface IFile {
	
	Boolean saveMovie(Movie movie) throws IOException;

	Movie getById(String movieId) throws FileNotFoundException, IOException;

}
