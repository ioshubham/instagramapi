package com.insta.instagramapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagramapi.dto.UserDto;
import com.insta.instagramapi.exception.PostException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Post;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.repository.PostRepository;
import com.insta.instagramapi.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Post createdPost(Post post, Integer userId) throws UserException {

		User user = userService.findUserById(userId);

		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());

		post.setUser(userDto);

		Post createdPost = postRepository.save(post);

		return createdPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {

		Post post = findPostById(postId);

		User user = userService.findUserById(userId);

		if (post.getUser().getId().equals(user.getId())) {
			postRepository.deleteById(post.getId());
			return "Post Deleted Successfully....!";
		}

		throw new PostException("You can't delete other user's posts ");
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws UserException {

		List<Post> posts = postRepository.findByUserId(userId);

		if (posts.size() == 0) {
			throw new UserException("This User does not have any post");
		}

		return posts;
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {

		Optional<Post> opt = postRepository.findById(postId);

		if (opt.isPresent()) {
			return opt.get();
		}

		throw new PostException("Post not found with id : " + postId);
	}

	@Override
	public List<Post> findAllPostByUserId(List<Integer> userId) throws PostException, UserException {

		List<Post> posts = postRepository.findAllPostByUserIds(userId);

		if (posts.size() == 0) {
			throw new PostException("No Post Available");
		}

		return posts;
	}

	@Override
	public String savePost(Integer postId, Integer userId) throws PostException, UserException {

		Post post = findPostById(postId);

		User user = userService.findUserById(userId);

		if (!user.getSavedPost().contains(post)) {
			user.getSavedPost().add(post);
			userRepository.save(user);
		}

		return "Your Post Saved Succesfully";
	}

	@Override
	public String unSavedPost(Integer postId, Integer userId) throws PostException, UserException {

		Post post = findPostById(postId);

		User user = userService.findUserById(userId);

		if (!user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
			userRepository.save(user);
		}

		return "Your Post remoed Succesfully";
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws UserException, PostException {

		Post post = findPostById(postId);

		User user = userService.findUserById(userId);

		UserDto userDto = new UserDto();

		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());

		post.getLikedByUser().add(userDto);

		return postRepository.save(post);
	}

	@Override
	public Post unlikePost(Integer postId, Integer userId) throws UserException, PostException {

		Post post = findPostById(postId);

		User user = userService.findUserById(userId);

		UserDto userDto = new UserDto();

		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());

		post.getLikedByUser().remove(userDto);

		return postRepository.save(post);

	}

}
