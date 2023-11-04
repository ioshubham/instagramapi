package com.insta.instagramapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.response.MessageResponse;
import com.insta.instagramapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException {
		
		User user=userService.findUserById(id);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@GetMapping("username/{username}")
	public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException {
		
		User user= userService.findUserByUsername(username);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PutMapping("/follow/{followUserId}")
	public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer followUserId, @RequestHeader("Authorization") String token) throws UserException {
		
		User user = userService.findUserProfile(token);
		
		String message = userService.followUser(user.getId(), followUserId);
		
		
	    MessageResponse res=new MessageResponse(message);
	    System.out.println("message is "+res.toString());
		
		return new ResponseEntity<MessageResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/unfollow/{userId}")
	public ResponseEntity<MessageResponse> unFollowUserHandler(@PathVariable Integer userId,@RequestHeader("Authorization") String token) throws UserException {
		
		User user = userService.findUserProfile(token);
		
		String message = userService.unFollowUser(user.getId(), userId);
		
	    MessageResponse res=new MessageResponse(message);
		
		return new ResponseEntity<MessageResponse>(res,HttpStatus.OK);

	}
	
	@GetMapping("/req")
	public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
		
		User user = userService.findUserProfile(token);
	
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/m/{usersIds}")
	public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userId) throws UserException {
		List<User> users = userService.findUserByIds(userId);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	// api/users/search?q="query"
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException {
		List<User> users=userService.searchUser(query);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PutMapping("/account/edit")
	public ResponseEntity<User> updateUserHandler(@RequestHeader("Authorization") String token, @RequestBody User user) throws UserException {

		User requser = userService.findUserProfile(token);
		
	    User updatedUser=userService.updateUserDetails(user, requser);
		
		return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
	}

}
