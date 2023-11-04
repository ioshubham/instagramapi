package com.insta.instagramapi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagramapi.dto.UserDto;
import com.insta.instagramapi.exception.CommentExeption;
import com.insta.instagramapi.exception.PostException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Comment;
import com.insta.instagramapi.modal.Post;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.repository.CommentRepository;
import com.insta.instagramapi.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException {
		
		User user = userService.findUserById(userId);
		
		Post post = postService.findPostById(postId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.setUser(userDto);
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment createdComment = commentRepository.save(comment);
		
		post.getComments().add(createdComment);
		
		postRepository.save(post);
		
		return createdComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws CommentExeption {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new CommentExeption("comment  is not exists with comment id "+commentId);
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws CommentExeption, UserException {

		User user = userService.findUserById(userId);
		
		Comment comment = findCommentById(commentId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.getLikedByUsers().add(userDto);
		
		return commentRepository.save(comment);
	}

	@Override
	public Comment unlikeComment(Integer commentId, Integer userId) throws CommentExeption, UserException {
		
User user = userService.findUserById(userId);
		
		Comment comment = findCommentById(commentId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		comment.getLikedByUsers().remove(userDto);
		
		return commentRepository.save(comment);
	}

}
