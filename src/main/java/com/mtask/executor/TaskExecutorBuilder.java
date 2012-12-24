package com.mtask.executor;

import com.mtask.error.handler.LoggedExecuteErrorHandler;
import com.mtask.executor.decroctor.AsyncTaskExecutor;
import com.mtask.executor.decroctor.HandledTaskExecutor;
import com.mtask.executor.decroctor.ListenedTaskExecutor;
import com.mtask.executor.decroctor.LoggedTaskExecutor;
import com.mtask.executor.decroctor.MonitoredTaskExecutor;
import com.mtask.executor.quartz.QuartzTaskExecutor;

public class TaskExecutorBuilder {
	
	private boolean jmxMonitoring = false;
	
	private TaskExecutor taskExecutor;
	
	public TaskExecutor build() {
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
	
}
