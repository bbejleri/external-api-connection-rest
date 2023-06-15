package com.gradle.externalapi.gradleexternalapi.restclient;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.gradle.externalapi.gradleexternalapi.logging.RestApiLogging;
import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.model.Todo;

@Component
public class RestApiClientBlocking {
	
	private static final String BASE_PATH = "https://jsonplaceholder.typicode.com";
	
	   @Autowired	
	   private RestTemplate restTemplate;
	   
	   @Autowired
	   private RestApiLogging restApiLogging;
	   
	   public ResponseEntity<Todo> getToDos(final Integer todoId){
			String apiUrl = BASE_PATH + "/todos/" + todoId;
			ResponseEntity<Todo> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Todo.class);
			return responseEntity;
		}
	   
	   public ResponseEntity<List<Post>> getPosts(){
			String apiUrl = BASE_PATH + "/posts";
			try {
				ResponseEntity<List<Post>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
				if (response.getStatusCode().is2xxSuccessful()) {
	                response = ResponseEntity.ok(response.getBody());
	            }
				return response;
			} catch (HttpClientErrorException exception) {
				
				restApiLogging.log00001ES("Client Side Error Occurred: ", exception);
				if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
					ResponseEntity<List<Post>> response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
					return response;
				}
				if (exception.getStatusCode() == HttpStatus.BAD_REQUEST) {
					ResponseEntity<List<Post>> response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
					return response;
				} else {
					return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
	            
			} catch (Exception exception) {
				
				restApiLogging.log00001ES("Server Side Error Occurred: ", exception);
	            ResponseEntity<List<Post>> errorResponseServer = new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
			    return errorResponseServer;
			}
		}

	@Cacheable(value = "comments", key = "#postId")
	public ResponseEntity<List<Comment>> getCommentsForPost(final Integer postId) {
		final String apiUrl = BASE_PATH + "/comments?postId=" + postId;
		try {
			ResponseEntity<List<Comment>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>(){});
			if (response.getStatusCode().is2xxSuccessful()) {
				response = ResponseEntity.ok(response.getBody());
			}
			return response;
		} catch (HttpClientErrorException exception){
			restApiLogging.log00001ES("Client Side Error Occurred: ", exception);
			if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
				ResponseEntity<List<Comment>> response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
				return response;
			}
			if (exception.getStatusCode() == HttpStatus.BAD_REQUEST) {
				ResponseEntity<List<Comment>> response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
				return response;
			} else {
				return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
				
		} catch (Exception exception) {
			restApiLogging.log00001ES("Server Side Error Occurred: ", exception);
			ResponseEntity<List<Comment>> response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
}
