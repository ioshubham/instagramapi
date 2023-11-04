package com.insta.instagramapi.response;

public class MessageResponse {
	
	private String message;
	
	public MessageResponse() {
		
	}

	public MessageResponse(String message) {
		super();
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
