package com.insta.instagramapi.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagramapi.exception.PostException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Post;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.response.MessageResponse;
import com.insta.instagramapi.service.PostService;
import com.insta.instagramapi.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<Post> createPostHandeler(@RequestBody Post post, @RequestHeader("Authorization") String token)
			throws UserException {

		User user = userService.findUserProfile(token);
		Post createdPost = postService.createdPost(post, user.getId());

		return new ResponseEntity<Post>(createdPost, HttpStatus.OK);
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<List<Post>> findPostByUserIdHandeler(@PathVariable("id") Integer userId)
			throws UserException {
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/following/{ids}")
	public ResponseEntity<List<Post>> findAllPostByUserIdHandeler(@PathVariable("ids") List<Integer> userIds)
			throws UserException, PostException {
		List<Post> posts = postService.findAllPostByUserId(userIds);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/{PostId}")
	public ResponseEntity<Post> findPostByIdHandeler(@PathVariable Integer PostId) throws PostException {

		Post post = postService.findPostById(PostId);

		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@PutMapping("/liked/{postId}")
	public ResponseEntity<Post> likedPostHandeler(@PathVariable Integer postId,
			@RequestHeader("Authorization") String token) throws UserException, PostException {

		User user = userService.findUserProfile(token);
		Post likedPost = postService.likePost(postId, user.getId());

		return new ResponseEntity<Post>(likedPost, HttpStatus.OK);
	}
	
	@PutMapping("/unliked/{postId}")
	public ResponseEntity<Post> unlikedPostHandeler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {

		User user = userService.findUserProfile(token);
		Post unlikedPost = postService.unlikePost(postId, user.getId());

		return new ResponseEntity<Post>(unlikedPost, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<MessageResponse> deletePostHandeler(@PathVariable Integer postId,
			@RequestHeader("Authorization") String token) throws UserException, PostException {

		User user = userService.findUserProfile(token);

		String message = postService.deletePost(postId, user.getId());

		MessageResponse res = new MessageResponse(message);

		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);

	}
	
	@PutMapping("/save_post/{postId}")
	public ResponseEntity<MessageResponse> savePostHandeler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws PostException, UserException {

		User user = userService.findUserProfile(token);

		String message = postService.savePost(postId, user.getId());

		MessageResponse res = new MessageResponse(message);

		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);

	}
	
	@PutMapping("/unsave_post/{postId}")
	public ResponseEntity<MessageResponse> unsavePostHandeler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws PostException, UserException {

		User user = userService.findUserProfile(token);

		String message = postService.unSavedPost(postId, user.getId());

		MessageResponse res = new MessageResponse(message);

		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);

	}
	
	
	
}
