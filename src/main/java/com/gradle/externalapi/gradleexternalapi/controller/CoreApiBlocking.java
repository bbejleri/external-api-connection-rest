package com.gradle.externalapi.gradleexternalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.gradle.externalapi.gradleexternalapi.logging.ControllerLogging;
import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.service.CoreService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Component
public class CoreApiBlocking {
	
	@Autowired
	private CoreService coreService; 
	
	@Autowired
	private ControllerLogging controllerLogging;
	
	public ResponseEntity<List<Post>> getAllPosts() {
		return this.coreService.getAllPosts();
	}
	
	public ResponseEntity<List<Comment>> getCommentsForPost(@NotNull @Positive final Integer postId) {
		ResponseEntity<List<Comment>> response = coreService.getCommentsForPost(postId);
		if (!response.getBody().isEmpty()) {
			controllerLogging.log00001IS("Success");
		}
		return response;
	}

}
