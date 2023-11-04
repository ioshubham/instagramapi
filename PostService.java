package com.insta.instagramapi.service;

import java.util.List;


import com.insta.instagramapi.exception.PostException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Post;


public interface PostService {
	
	public Post createdPost(Post post,Integer userId) throws UserException;
	
	public String deletePost(Integer postId, Integer userId) throws UserException,PostException;
	
	public List<Post> findPostByUserId(Integer userId) throws UserException;
	
	public Post findPostById(Integer PostId) throws PostException;
	
	public List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException, UserException;
	
	public String savePost(Integer postId, Integer userId) throws PostException,UserException;
	
	public String unSavedPost(Integer postId, Integer userId) throws PostException,UserException;
	
	public Post likePost(Integer postId,Integer userId) throws UserException,PostException;
	
	public Post unlikePost (Integer postId,Integer userId ) throws UserException,PostException;
	
}
