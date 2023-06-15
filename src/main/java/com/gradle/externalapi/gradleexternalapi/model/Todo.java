package com.gradle.externalapi.gradleexternalapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
	
	private Integer userId;
	
	private Integer id;
	
	private String title;
	
	private Boolean completed;

}
