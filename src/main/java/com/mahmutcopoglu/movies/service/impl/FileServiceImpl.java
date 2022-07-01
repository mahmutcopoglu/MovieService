package com.mahmutcopoglu.movies.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mahmutcopoglu.movies.dto.Movie;
import com.mahmutcopoglu.movies.service.IFile;

@Component
public class FileServiceImpl implements IFile {
	private File file = new File("movies.txt");
	
	public FileServiceImpl() {
		this.init();
	}

	public void init() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String fileInTheMovie(String movieId) throws IOException {
		FileReader fileReader = new FileReader(file);
		String line = null;
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		try {
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("imdbId=" + movieId)) {
					return line;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return line = null;
	}
	
	@Override
	public Boolean save(Movie movie) throws IOException {
		if (fileInTheMovie(movie.getImdbId()) == null) {
			FileWriter writter = new FileWriter(file,true);
			BufferedWriter bufferedWriter = new BufferedWriter(writter);
			try {
				bufferedWriter.write(movie.toString().substring(5) + "\n");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				bufferedWriter.close();
			}
		}
		return false;
	}
	
	@Override
	public Movie getById(String movieId) throws FileNotFoundException, IOException {
		if (fileInTheMovie('"' + movieId + '"') != null) {
			return mapperForMovie(fileInTheMovie('"' + movieId + '"'));
		}
		return null;
	}
	
	private Movie mapperForMovie(String text) {
		List<String> movie = new ArrayList<>();

		for (String parsedText : text.split(",")) {
			parsedText.trim();
			parsedText = parsedText.substring(parsedText.indexOf("=") + 1).replace('"', ' ').trim();
			movie.add(parsedText);
		}
		return new Movie(movie.get(0), movie.get(1), movie.get(2), movie.get(3), movie.get(4));
	}
	
	

}
