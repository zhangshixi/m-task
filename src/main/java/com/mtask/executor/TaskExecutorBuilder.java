package com.mtask.executor;

import com.mtask.Task;
import com.mtask.error.handler.LoggedExecuteErrorHandler;
import com.mtask.executor.decroctor.AsyncTaskExecutor;
import com.mtask.executor.decroctor.HandledTaskExecutor;
import com.mtask.executor.decroctor.ListenedTaskExecutor;
import com.mtask.executor.decroctor.LoggedTaskExecutor;
import com.mtask.executor.decroctor.MonitoredTaskExecutor;
import com.mtask.executor.quartz.QuartzTaskExecutor;

public class TaskExecutorBuilder {

    private boolean jmxMonitoring = false;
    private String taskConfigLocation = null;
	
	private TaskExecutor taskExecutor;
	
	// ---- public methods
	public TaskExecutor build() {
	    taskExecutor = doBuildExecutor();
	    Task[] tasks = doLoadTasks();
	    
	    doAddTasksToExecutor(tasks, taskExecutor);
	    
	    return taskExecutor;
	}
	
	public boolean isJmxMonitoring() {
	    return jmxMonitoring;
	}
	
	public void setJmxMonitoring(boolean jmxMonitoring) {
	    this.jmxMonitoring = jmxMonitoring;
	}
	
	// ---- private methods
	private TaskExecutor doBuildExecutor() {
	    taskExecutor = new QuartzTaskExecutor();
        taskExecutor = new LoggedTaskExecutor(taskExecutor);
        taskExecutor = new ListenedTaskExecutor(taskExecutor);
        taskExecutor = new HandledTaskExecutor(taskExecutor, new LoggedExecuteErrorHandler());
        if (jmxMonitoring) {
            taskExecutor = new MonitoredTaskExecutor(taskExecutor);
        }
        taskExecutor = new AsyncTaskExecutor(taskExecutor);
        
        return taskExecutor;
	}
	
	private Task[] doLoadTasks() {
	    return null;
	}
	
	private void doAddTasksToExecutor(Task[] tasks, TaskExecutor taskExecutor) {
	    
	}
	
}
