package com.insta.instagramapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagramapi.exception.StoryException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Story;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.service.StoryService;
import com.insta.instagramapi.service.UserService;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StoryService storyService;
	
	@PostMapping("/create")
	public ResponseEntity<Story> createStryHandeler(@RequestBody Story story,@RequestHeader("Authorization") String  token) throws UserException{

		User user = userService.findUserProfile(token);
		
		Story createdStory = storyService.createStory(story, user.getId());
		
		return new ResponseEntity<Story>(createdStory, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Story>> findAllUserStoryByUserHandeller(@PathVariable Integer userId) throws UserException, StoryException {
		
		User user = userService.findUserById(userId);
		
		List<Story> stories=storyService.findStoryByUserId(user.getId());
		
		return new ResponseEntity<List<Story>>(stories,HttpStatus.OK);
	}

}
