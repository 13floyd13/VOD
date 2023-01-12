package com.forrest.cinema.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.forrest.cinema.entities.MovieTMDB;

import reactor.core.publisher.Mono;

@RestController
public class RestTMDBController {
	
	@Value("${tmdb-api.key}")
	private String apiKey;

	private static final Logger Logger = LoggerFactory.getLogger(RestTMDBController.class);
	private WebClient webClient;
		
	public RestTMDBController(WebClient webClient) {
		this.webClient = webClient;
	}

    public Mono<MovieTMDB> getInfosFromApiTMDB(String movieId) {
    	
    	System.out.println(movieId);
    	webClient = WebClient.create();

    	try {   	
	    	Mono<MovieTMDB> movieMono = webClient.get()
	    			   .uri("https://api.themoviedb.org/3/movie/{movieId}?api_key={apiKey}&language=fr-FR", movieId, apiKey)
	    			   .retrieve()
	    			   .onStatus(status -> status == HttpStatus.NOT_FOUND,
	    			   res -> Mono.error(new Exception("Erreur client : " + res.statusCode() + " for id= " + movieId)))
	    			   .onStatus(status -> status == HttpStatus.INTERNAL_SERVER_ERROR, res -> Mono.error(new Exception("Erreur serveur : " + res.statusCode())))
	    			   .bodyToMono(MovieTMDB.class)
	    			   .doOnError(error -> Logger.error("Erreur de l'API : {}", error.getMessage()))
	    			   .onErrorResume(error -> Mono.empty());
    	
	    	System.out.println(movieId);
    	  return movieMono;
    	
    	} catch (WebClientResponseException e){
    		Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
    		return null;
    	} catch(InvalidDataAccessApiUsageException e) {
    		Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
    		return null;
    	} catch (Exception e) {
    		Logger.error("WEB_CLIENT_MOVIE_EXCEPTION id = " + movieId, e);
    		return null;
    	}
    }
    
    public Mono<String> getAllTMDBGenres() {
    	
    	webClient = WebClient.create();
    	
    	try {
    		Mono<String> genresMono = webClient.get()
    				.uri("https://api.themoviedb.org/3/genre/movie/list?api_key={apiKey}&language=fr-FR", apiKey)
    				.retrieve()
    				//.onStatus(HttpStatusCode::isError, response -> Mono.error(new Exception("Erreur de l'API : " + response.statusCode())))
    				//.onStatus(HttpStatusCode::is2xxSuccessful, response -> Mono.error(new Exception("Erreur de l'API : " + response.statusCode())))
	    			.bodyToMono(String.class);
//	    			.doOnNext(body -> Logger.error("Corps de la réponse : {}", body))
//	    		    .doOnError(error -> Logger.error("Erreur lors de l'appel de l'API : {}", error))
//	    		    .doOnSuccess(success -> Logger.error("Super"));
	    		    //.map(response -> objectMapper.readValue(response, GenresTMDB.class));
	    		    
    		//System.out.println(genresMono.block().getGenresTMDB());
    		

//	    		    genresMono.doOnSuccess(genresTMDB -> {
//	    		        System.out.println("GenresTMDB: " + genresTMDB);
//	    		        //System.out.println("HTTP response body: " + genresTMDB.getBody());
//	    		    }).subscribe();
//    		List<GenreTMDB> genresList = genresMono
//    			    .map(genres -> genres.getGenresTMDB())
//    			    .block();
    		
//    		Mono<List<GenreTMDB>> genresListMono = genresMono.flatMap(genres -> {
//    			  List<GenreTMDB> genresList = genres.getGenresTMDB();
//    			  return Mono.just(genresList);
//    			});

    		//List<GenreTMDB> genresList = genresListMono.block();
    		
    		//System.out.println(genresList.size());

    		return genresMono;
    		
    	} catch (WebClientResponseException e) {
    		Logger.error("WEB_CLIENT_GENRE_EXCEPTION", e);
    		return null;
    	}
    }
    
}
