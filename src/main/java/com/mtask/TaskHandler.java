package com.mtask;

public interface TaskHandler {
	
	public void execute(TaskContext context) throws Exception;
	
}