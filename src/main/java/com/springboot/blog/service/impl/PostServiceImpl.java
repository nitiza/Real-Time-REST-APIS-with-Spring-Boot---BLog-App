package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;


@Service
public class PostServiceImpl  implements PostService{
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		
		// convert DTO to entity
		Post post = mapToEntity(postDto);
		//Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
//		
		Post newPost = postRepository.save(post);
		
		// convert entity to DTO
		PostDto postResponse = mapToDTO(newPost);
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
		}
	
	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		PostDto postDto = mapToDTO(post);
		return postDto;
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		//get post by id
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		
		Post updatedPost = postRepository.save(post);
		
		return mapToDTO(updatedPost);
		
		
		
	}
	
	@Override
	public void deletePostById(long id) {
		
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
		
	}
	
	
	//convert entity to DTO
	private PostDto mapToDTO(Post post)
	{
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		
		return postDto;
		
	}
	
	private Post mapToEntity(PostDto postDto)
	{
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
		
	}

		

}
