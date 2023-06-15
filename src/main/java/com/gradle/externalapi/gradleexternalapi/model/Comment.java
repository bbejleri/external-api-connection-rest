package com.gradle.externalapi.gradleexternalapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
	
    private Integer postId;
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String body;

}
