package com.gradle.externalapi.gradleexternalapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
	
    private Integer userId;
	
	private Integer id;
	
	private String title;
	
	private String body;
	
}
