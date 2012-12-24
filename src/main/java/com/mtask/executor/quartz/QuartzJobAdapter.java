package com.mtask.executor.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mtask.TaskContext;
import com.mtask.TaskHandler;

public class QuartzJobAdapter implements Job {

	private TaskHandler _taskHandler;
	
	public QuartzJobAdapter(TaskHandler taskHandler) {
		_taskHandler = taskHandler;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.equals(context);
		try {
			_taskHandler.execute(convertContext(context));
		} catch (Exception cause) {
			throw new JobExecutionException(cause);
		}
	}
	
	private TaskContext convertContext(JobExecutionContext context) {
		return null; // TODO:
	}

}
