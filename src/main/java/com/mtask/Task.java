package com.mtask;

import java.util.Date;

public abstract class Task {
	
	private String _id;
	// private String _group; // TODO: unsupported
	private Date startTime;
	private Date endTime;
	private String description;
	private TaskHandler _callbackHandler;
	
	
	public Task(String id) {
		if (id == null) {
			throw new NullPointerException("id");
		}
		_id = id;
	}
	
	public String getId() {
		return _id;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public TaskHandler getCallbackHandler() {
		return _callbackHandler;
	}
	
	public void setCallbackHandler(TaskHandler callbackHandler) {
		_callbackHandler = callbackHandler;
	}
	
	public void setCallbackHandler(String callbackHandler) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		_callbackHandler = (TaskHandler) Class.forName(callbackHandler).newInstance();
	}
	
}
