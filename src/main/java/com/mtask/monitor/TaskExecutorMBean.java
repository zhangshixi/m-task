package com.mtask.monitor;

import com.mtask.Task;

public interface TaskExecutorMBean {
	
	public void add(Task task) throws Exception;
	
	public void activity(String taskId) throws Exception;

	public void pause(String taskId) throws Exception;
	
	public void resume(String taskId) throws Exception;

	public void execute(String taskId) throws Exception;
	
	public void remove(String taskId) throws Exception;
	
}
