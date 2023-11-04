package com.insta.instagramapi.service;

import java.util.List;

import com.insta.instagramapi.exception.StoryException;
import com.insta.instagramapi.exception.UserException;
import com.insta.instagramapi.modal.Story;

public interface StoryService {
	
	public Story createStory(Story story , Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException,StoryException;

}
