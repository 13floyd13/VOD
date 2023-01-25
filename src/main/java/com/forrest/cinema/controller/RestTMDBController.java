package com.forrest.cinema.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.forrest.cinema.entities.MovieTMDB;
import com.forrest.cinema.exceptions.ApiTMDBException;

import reactor.core.publisher.Mono;

@RestController
public class RestTMDBController {

	@Value("${tmdb-api.key}")
	private String apiKey;
	
	@Value("${posters-directory.path}")
	private String posterDirectoryPath;
	
	@Value("${tmdb-poster.url}")
	private String posterURL;

	private static final Logger Logger = LoggerFactory.getLogger(RestTMDBController.class);
	private WebClient webClient;

	public RestTMDBController(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<MovieTMDB> getInfosFromApiTMDB(String movieId) throws ApiTMDBException {

		webClient = WebClient.create();

		try {
			Mono<MovieTMDB> movieMono = webClient.get()
					.uri("https://api.themoviedb.org/3/movie/{movieId}?api_key={apiKey}&language=fr-FR", movieId,
							apiKey)
					.retrieve()
					.onStatus(status -> status == HttpStatus.NOT_FOUND,
							res -> Mono.error(new ApiTMDBException(
									"Erreur client : " + res.statusCode() + " for id= " + movieId)))
					.onStatus(status -> status == HttpStatus.INTERNAL_SERVER_ERROR,
							res -> Mono.error(new ApiTMDBException("Erreur serveur : " + res.statusCode())))
					.bodyToMono(MovieTMDB.class)
					.doOnError(error -> Mono.error(new ApiTMDBException("Erreur de l'API : " + error.getMessage())))
					.onErrorResume(error -> Mono.empty());

			return movieMono;

		} catch (WebClientResponseException e) {
			Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
			return null;
		} catch (InvalidDataAccessApiUsageException e) {
			Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
			return null;
		} catch (Exception e) {
			Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
			return null;
		}
	}

	public Mono<String> getAllTMDBGenres() {

		webClient = WebClient.create();

		Mono<String> genresMono = webClient.get()
				.uri("https://api.themoviedb.org/3/genre/movie/list?api_key={apiKey}&language=fr-FR", apiKey).retrieve()
				.onStatus(status -> status == HttpStatus.NOT_FOUND,
						res -> Mono.error(new ApiTMDBException("Erreur client : " + res.statusCode())))
				.onStatus(status -> status == HttpStatus.INTERNAL_SERVER_ERROR,
						res -> Mono.error(new ApiTMDBException("Erreur serveur : " + res.statusCode())))
				.bodyToMono(String.class)
				.doOnError(error -> Mono.error(new ApiTMDBException("Erreur de l'API : " + error.getMessage())))
				.onErrorResume(error -> Mono.empty());

		return genresMono;
	}

	public String getPoster(String uri, String titleFilm) {
		
		String filePath = posterDirectoryPath + titleFilm;
		String tmdbURLPoster = posterURL + uri;
		try {		
			File file = new File(filePath);
			
			if (!file.exists()) {
			    try {
			        file.createNewFile();
			        Logger.info("File created" + filePath);
			    } catch (IOException e) {
			    	Logger.error("IO_EXCETPION_FOR_CREATE_FILE "+ filePath + "STACK : " + e);			    }
			}
			
		    URL url = new URL(tmdbURLPoster);
		    InputStream in = url.openStream();
		    Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
		    
		} catch (IOException e) {
			Logger.error("ERREUR et " + e);
			return null;
		}
		
		return filePath;
	}

}
