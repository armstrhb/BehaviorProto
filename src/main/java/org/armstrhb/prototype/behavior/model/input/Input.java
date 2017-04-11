package org.armstrhb.prototype.behavior.model.input;

public class Input {
	public static final String CHANGE_STATE = "FLIP_STATE";
	public static final String SHUTDOWN_STATE = "SHUTDOWN";
	private String message;
	
	public Input(String inMessage) {
		message = inMessage;
	}
	
	public void setMessage(String inMessage) {
		message = inMessage;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isStateChangeRequest() {
		return message.equalsIgnoreCase(CHANGE_STATE);
	}
	
	public boolean isShutdown() {
		return message.equalsIgnoreCase(SHUTDOWN_STATE);
	}
}
