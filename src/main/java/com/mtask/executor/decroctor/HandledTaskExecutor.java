package com.mtask.executor.decroctor;

import com.mtask.Task;
import com.mtask.error.ExecuteErrorHandler;
import com.mtask.executor.TaskExecutor;

public class HandledTaskExecutor implements TaskExecutor {
	
	private TaskExecutor taskExecutor;
	private ExecuteErrorHandler[] errorHandlers;
	
	public HandledTaskExecutor(TaskExecutor taskExecutor, ExecuteErrorHandler... errorHandlers) {
		this.taskExecutor = taskExecutor;
		this.errorHandlers = errorHandlers;
	}

	@Override
	public void add(Task task) throws Exception {
		try {
			this.taskExecutor.add(task);
		} catch (Exception ex) {
			callbackErrorHandlers(task.getId(), ex);
			throw ex;
		}
	}

	@Override
	public void activity(String taskId) throws Exception {
		try {
			this.taskExecutor.activity(taskId);
		} catch (Exception ex) {
			callbackErrorHandlers(taskId, ex);
			throw ex;
		}
	}

	@Override
	public void pause(String taskId) throws Exception {
		try {
			this.taskExecutor.pause(taskId);
		} catch (Exception ex) {
			callbackErrorHandlers(taskId, ex);
			throw ex;
		}
	}

	@Override
	public void resume(String taskId) throws Exception {
		try {
			this.taskExecutor.resume(taskId);
		} catch (Exception ex) {
			callbackErrorHandlers(taskId, ex);
			throw ex;
		}
	}

	@Override
	public void execute(String taskId) throws Exception {
		try {
			this.taskExecutor.execute(taskId);
		} catch (Exception ex) {
			callbackErrorHandlers(taskId, ex);
			throw ex;
		}
	}

	@Override
	public void remove(String taskId) throws Exception {
		try {
			this.taskExecutor.remove(taskId);
		} catch (Exception ex) {
			callbackErrorHandlers(taskId, ex);
			throw ex;
		}
	}
	
	// ---- private methods ---------------------------------
	private void callbackErrorHandlers(String taskId, Exception error) {
		for (ExecuteErrorHandler handler : errorHandlers) {
			handler.handleError(taskId, error);
		}
	}
	
}
