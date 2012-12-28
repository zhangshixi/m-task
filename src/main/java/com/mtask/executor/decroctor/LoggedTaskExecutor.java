package com.mtask.executor.decroctor;

import com.mlogger.Loggers;
import com.mtask.Task;
import com.mtask.executor.TaskExecutor;

public class LoggedTaskExecutor extends DecrocteTaskExecutor {

	private final Loggers logger = Loggers.getLoggers(getClass());
	
	public LoggedTaskExecutor(TaskExecutor taskExecutor) {
		super(taskExecutor);
	}

	@Override
	public void add(Task task) throws Exception {
		getDelagateTaskExecutor().add(task);
		logger.debug("Added task [{0}] succeed.", task.getId());
	}

	@Override
	public void activity(String taskId) throws Exception {
		getDelagateTaskExecutor().activity(taskId);
		logger.debug("Activitied task [{0}] succeed.", taskId);
	}

	@Override
	public void pause(String taskId) throws Exception {
		getDelagateTaskExecutor().pause(taskId);
		logger.debug("Paused task [{0}] succeed.", taskId);
	}

	@Override
	public void resume(String taskId) throws Exception {
		getDelagateTaskExecutor().resume(taskId);
		logger.debug("Resumed task [{0}] succeed.", taskId);
	}

	@Override
	public void execute(String taskId) throws Exception {
		logger.debug("Execute task [{0}] start.", taskId);
		long startTime = System.currentTimeMillis();
		getDelagateTaskExecutor().execute(taskId);
		long executedTime = System.currentTimeMillis() - startTime;
		logger.debug("Executed task [{0}] succeed, executed time is [{1}] ms.", taskId, executedTime);
	}

	@Override
	public void remove(String taskId) throws Exception {
		getDelagateTaskExecutor().remove(taskId);
		logger.debug("Removed task [{0}] succeed.", taskId);
	}

}
