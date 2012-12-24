package com.mtask.task;

import com.mtask.Task;

public class SimpleTask extends Task {

	private int repeatCount;
	private int repeatInterval;
	
	public SimpleTask(String id) {
		super(id);
	}
	
	public int getRepeatCount() {
		return repeatCount;
	}
	
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	
	public int getRepeatInterval() {
		return repeatInterval;
	}
	
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

}
