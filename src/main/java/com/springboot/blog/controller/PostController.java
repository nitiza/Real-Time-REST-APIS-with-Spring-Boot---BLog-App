package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	// create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
	{
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	// get all posts rest api
	@GetMapping
	public List<PostDto> getAllPosts()
	{
		return postService.getAllPosts();
	}
	
	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostId(@PathVariable(name = "id") long id)
	{
		return ResponseEntity.ok(postService.getPostById(id));
		
	}
	
	//update post by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id)
	{
		
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
			
	}
	
	//delete post rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id)
	{
			postService.deletePostById(id);
			
			return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
	}
	
	

}
