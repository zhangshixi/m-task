package com.mtask.event;

import com.mtask.Task;

public interface EventListener {
	
	public void beforeAdd(Task task);
	public void afterAdded(Task task);
	
	public void beforeActivity(String taskId);
	public void afterActivitied(String taskId);

	public void beforePause(String taskId);
	public void afterPaused(String taskId);
	
	public void beforeResume(String taskId);
	public void afterResumed(String taskId);

	public void beforeRemove(String taskId);
	public void afterRemoved(String taskId);
	
	public void beforeExecute(String taskId);
	public void afterExecuted(String taskId);
	
}
