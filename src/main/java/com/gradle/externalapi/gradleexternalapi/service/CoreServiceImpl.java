package com.gradle.externalapi.gradleexternalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.restclient.RestApiClientBlocking;
import com.gradle.externalapi.gradleexternalapi.restclient.RestApiClientNonBlocking;

import reactor.core.publisher.Flux;

@Service
public class CoreServiceImpl implements CoreService {
	
	@Autowired
	private RestApiClientNonBlocking restApiClientNonBlocking;
	
	@Autowired
	private RestApiClientBlocking restApiClientBlocking;

	@Override
	public Flux<Post> getAllPostsAsync() {
		Flux<ResponseEntity<Post>> result = restApiClientNonBlocking.getAllPosts();
	
		Flux<Post> postList = result
		        .flatMap(responseEntity -> Flux.just(responseEntity.getBody()));		        
		
		result.doOnNext(response -> {
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody());
        })
        .blockLast(); 
		
		return postList;	
	}	
	
	@Override
	public ResponseEntity<List<Post>> getAllPosts() {
		ResponseEntity<List<Post>> result = this.restApiClientBlocking.getPosts();
		return result;
	}
	
	@Override
	public ResponseEntity<List<Comment>> getCommentsForPost(final Integer postId) {
		ResponseEntity<List<Comment>> result = this.restApiClientBlocking.getCommentsForPost(postId);
		return result;
	}

}
