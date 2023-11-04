package com.insta.instagramapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagramapi.dto.UserDto;
import com.insta.instagramapi.exception.StoryException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Story;
import com.insta.instagramapi.modal.User;
import com.insta.instagramapi.repository.StoryRepository;
import com.insta.instagramapi.repository.UserRepository;

@Service
public class StoryServiceImplementation implements StoryService {
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	public Story createStory(Story story, Integer userId) throws UserException {
		
		User user = userService.findUserById(userId);
		
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		
		story.setUser(userDto);
		story.setTimestamp(LocalDateTime.now());
		
		user.getStories().add(story);
		
		return storyRepository.save(story);
		
	}
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
		
		User user = userService.findUserById(userId);
		List<Story> stories = user.getStories();
		
		if(stories.size()==0) {
			throw new StoryException("User not have nay story");
		}
		
		return stories;
	}


}
