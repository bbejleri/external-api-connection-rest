package com.gradle.externalapi.gradleexternalapi.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.externalapi.gradleexternalapi.error.ResourceNotFoundException;
import com.gradle.externalapi.gradleexternalapi.logging.ControllerLogging;
import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.service.CoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/base")
@Tag(name = "An API to connect to a fake external API for testing purposes - Synchronious")
public class CoreControllerBlocking {
	
	@Autowired
	private CoreApiBlocking coreApiBlocking;
	
	@GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Post>> getAllPosts(){		
		return coreApiBlocking.getAllPosts();
	}
	
	@GetMapping(path = "/comments", produces = MediaType.APPLICATION_JSON)
	@Operation(
    summary = "Rest call to get all commments for a given post", 
	responses = { 
			@ApiResponse(responseCode = "200", description = "Operation was successful."),
			@ApiResponse(responseCode = "500", description = "Internal Server Error."),
			@ApiResponse(responseCode = "400", description = "Bad Request. Please verify the given parameters.")
	},
	parameters = { @Parameter(name = "postId", required = true, description = "Id of the post from which the comments will be collected.")})
	public ResponseEntity<List<Comment>> getCommentsForPost(@RequestParam final Integer postId) {
		return this.coreApiBlocking.getCommentsForPost(postId);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Resource not found: " + ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }
}
