package com.insta.instagramapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insta.instagramapi.modal.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	

}
