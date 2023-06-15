package com.gradle.externalapi.gradleexternalapi.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gradle.externalapi.gradleexternalapi.model.Post;
import com.gradle.externalapi.gradleexternalapi.service.CoreService;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/base/nonblocking")
@Tag(name = "An API to connect to a fake external API for testing purposes - Async/Non-Blocking")
public class CoreControllerNonBlocking {
	
	@Autowired
	private CoreService coreService;
	
	@GetMapping(path = "/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Post> getAllPosts() {		
		Flux<Post> result = coreService.getAllPostsAsync();		
		return result.delayElements(Duration.ofSeconds(1));
	}
}
