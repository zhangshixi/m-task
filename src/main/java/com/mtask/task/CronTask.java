package com.mtask.task;

import com.mtask.Task;

public class CronTask extends Task {
	
	private String _cronExpression;

	public CronTask(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getCronExpression() {
		return _cronExpression;
	}
	
	public void setCronExpression(String cronExpression) {
		_cronExpression = cronExpression;
	}
	
}
