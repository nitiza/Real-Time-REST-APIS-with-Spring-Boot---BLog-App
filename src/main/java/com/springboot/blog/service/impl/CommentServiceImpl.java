package com.springboot.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) 
	{
		Comment comment = mapToEntity(commentDto);
		
		// retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		// set post to comment entity
		comment.setPost(post);
		
		// save comment entity to DB
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}
	
	private CommentDto mapToDto(Comment comment)
	{
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setBody(comment.getBody());
		commentDto.setEmail(comment.getEmail());
		return commentDto;
		
	}
	
	private Comment mapToEntity(CommentDto commentDto)
	{
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setBody(commentDto.getBody());
		comment.setEmail(comment.getEmail());
		comment.setName(commentDto.getName());
		return comment;
		
	}
	
	

}
