package com.insta.instagramapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req) {
		
		ErrorDetails err  = new ErrorDetails(ue.getMessage(), req.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetails> postExceptionHandler(PostException ue, WebRequest req) {
		
		ErrorDetails err  = new ErrorDetails(ue.getMessage(), req.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(CommentExeption.class)
	public ResponseEntity<ErrorDetails> commentExceptionHandler(CommentExeption ue, WebRequest req) {
		
		ErrorDetails err  = new ErrorDetails(ue.getMessage(), req.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(StoryException.class)
	public ResponseEntity<ErrorDetails> storyExceptionHandler(StoryException ue, WebRequest req) {
		
		ErrorDetails err  = new ErrorDetails(ue.getMessage(), req.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me) {
		
		ErrorDetails err  = new ErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(),"Validation Error",LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue, WebRequest req) {
		
		ErrorDetails err  = new ErrorDetails(ue.getMessage(), req.getDescription(true),LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST); 
	}
	
}
