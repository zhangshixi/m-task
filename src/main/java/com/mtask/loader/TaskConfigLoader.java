package com.mtask.loader;

import java.util.List;

import com.mtask.Task;

public interface TaskConfigLoader {
	
	public List<Task> readTasks();
	
	public void writeTask(Task task);
	
	public void writeTasks(List<Task> tasks);
	
}
