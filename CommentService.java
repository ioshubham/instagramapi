package com.insta.instagramapi.service;

import com.insta.instagramapi.exception.CommentExeption;
import com.insta.instagramapi.exception.PostException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Comment;

public interface CommentService {
	
	public Comment createComment(Comment comment ,Integer postId, Integer userId) throws UserException, PostException;
	
	public Comment findCommentById(Integer commentId) throws CommentExeption;
	
	public Comment likeComment(Integer commentId, Integer userId) throws CommentExeption,UserException;
	
	public Comment unlikeComment(Integer commentId, Integer userId) throws CommentExeption,UserException;


}
