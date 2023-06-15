package com.gradle.externalapi.gradleexternalapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gradle.externalapi.gradleexternalapi.model.Comment;
import com.gradle.externalapi.gradleexternalapi.model.Post;

@ExtendWith(MockitoExtension.class)
public class CoreControllerBlockingTest {
	
	@Mock
    private CoreApiBlocking coreApiBlocking;

    @InjectMocks
    private CoreControllerBlocking coreControllerBlocking;
    
    @Test
    public void testGetAllPosts_Success() {
        List<Post> expectedPosts = Arrays.asList(
                new Post(1, 1, "Title 1", "Content 1"),
                new Post(2, 2, "Title 2", "Content 2")
        );
        ResponseEntity<List<Post>> expectedResponse = new ResponseEntity<>(expectedPosts, HttpStatus.OK);
        when(coreApiBlocking.getAllPosts()).thenReturn(expectedResponse);
        ResponseEntity<List<Post>> response = coreControllerBlocking.getAllPosts();
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
    
    @Test
    public void testGetAllPosts_ResourceNotFound() {
        ResponseEntity<List<Post>> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(coreApiBlocking.getAllPosts()).thenReturn(expectedResponse);
        ResponseEntity<List<Post>> response = coreControllerBlocking.getAllPosts();
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
    
    @Test
    public void testGetCommentsForPost_Success() {
    	final int postId = 1;
        List<Comment> expectedComments = Arrays.asList(
                new Comment(postId, 1, "Bora", "test@test.com", "Comment 1"),
                new Comment(postId, 2, "Lisa", "test@test.com", "Comment 2")
        );
        ResponseEntity<List<Comment>> expectedResponse = new ResponseEntity<>(expectedComments, HttpStatus.OK);
        when(coreApiBlocking.getCommentsForPost(postId)).thenReturn(expectedResponse);
        ResponseEntity<List<Comment>> response = coreControllerBlocking.getCommentsForPost(postId);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
    
    @Test
    public void testGetCommentsForPost_ResourceNotFound() {
        Integer postId = 1;
        ResponseEntity<List<Comment>> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(coreApiBlocking.getCommentsForPost(postId)).thenReturn(expectedResponse);
        ResponseEntity<List<Comment>> response = coreControllerBlocking.getCommentsForPost(postId);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

}
