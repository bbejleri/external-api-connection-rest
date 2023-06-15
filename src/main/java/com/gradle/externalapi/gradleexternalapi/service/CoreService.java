package com.gradle.externalapi.gradleexternalapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;

import reactor.core.publisher.Flux;

public interface CoreService {
	
	Flux<Post> getAllPostsAsync();
	
	ResponseEntity<List<Post>> getAllPosts();
	
	ResponseEntity<List<Comment>> getCommentsForPost(final Integer postId);
}
