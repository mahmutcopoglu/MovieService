package com.mahmutcopoglu.movies.constant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mahmutcopoglu.movies.dto.Movie;

@Component
public class Mapper {
	public List<Movie> mappedMovieName(String body) throws JsonMappingException, JsonProcessingException{

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(body);
		JsonNode result = jsonNode.get("result");

		List<Movie> movieList = new ArrayList<>();

		if (result.isArray()) {

			ArrayNode arrayNode = (ArrayNode) result;

			for (int i = 0; i < arrayNode.size(); i++) {

				JsonNode node = arrayNode.get(i);
				movieList.add(new Movie(node.get("imdbID").toString(), node.get("Title").toString(), node.get("Year").toString(),node.get("Type").toString(), node.get("Poster").toString()));

			}
		}
		return movieList;
	}
	
	public Movie mappedMovieId(String body) throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(body);
		JsonNode result = jsonNode.get("result");

		Movie movie = new Movie(result.get("imdbID").toString(), result.get("Title").toString(),
				result.get("Year").toString(), result.get("Type").toString(), result.get("Poster").toString());

		return movie;
	}

}
