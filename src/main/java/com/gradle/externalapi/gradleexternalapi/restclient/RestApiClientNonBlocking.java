package com.gradle.externalapi.gradleexternalapi.restclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.model.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RestApiClientNonBlocking {
	
	private static final String BASE_PATH = "https://jsonplaceholder.typicode.com";

	private WebClient webClient;
	
	public RestApiClientNonBlocking() {
        this.webClient = WebClient.create();
    }
	
    public Mono<ResponseEntity<Todo>> getToDos() {
        String apiUrl = BASE_PATH + "/todos/1";

        Mono<ResponseEntity<Todo>> result = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Todo.class)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        return result;
	}
    
    public Flux<ResponseEntity<Post>> getAllPosts(){
    	String apiUrl = BASE_PATH + "/posts";
    	
    	Flux<ResponseEntity<Post>> result = webClient.get()
    			.uri(apiUrl)
    			.retrieve()   		
    			.bodyToFlux(Post.class)
    			.map(response -> ResponseEntity.ok(response))
    			.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    	return result;
    }
	
}
