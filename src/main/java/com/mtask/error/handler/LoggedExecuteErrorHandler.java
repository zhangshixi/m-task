package com.mtask.error.handler;

import com.mlogger.Loggers;
import com.mtask.error.ExecuteErrorHandler;

public class LoggedExecuteErrorHandler implements ExecuteErrorHandler {
	
	private final Loggers logger = Loggers.getLoggers(getClass());
	
	@Override
	public void handleError(String taskId, Throwable cause) {
		logger.error("Task [id={0}] executing error!", cause, taskId);
	}

}
