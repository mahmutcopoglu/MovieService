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
				if (line.startsWith(movieId+",")) {
					return line;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return line;
	}

	@Override
	public Boolean saveMovie(Movie movie) throws IOException {

		String fileLine = movie.getImdbId() + "," + movie.getTitle() + "," + movie.getType() + "," + movie.getYear()+ "," + movie.getPoster();
		FileWriter writter = new FileWriter(file,true);
		BufferedWriter bufferedWriter = new BufferedWriter(writter);
		try {
			bufferedWriter.write(fileLine.replaceAll("\"",""));
			bufferedWriter.newLine();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedWriter.close();
		}

		return false;
	}

	@Override
	public Movie getById(String movieId) throws FileNotFoundException, IOException {
		var a = fileInTheMovie(movieId);

		if (a != null) {
			return mapperForMovie(a);
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