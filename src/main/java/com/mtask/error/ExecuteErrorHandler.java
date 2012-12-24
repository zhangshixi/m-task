package com.mtask.error;

public interface ExecuteErrorHandler {
	
	public void handleError(String taskId, Throwable cause);
	
}
