package com.mtask.monitor;

import java.util.Date;

public interface TaskExecCycleMBean {
	
	public int getExecuteNumbers(String taskId);
	
	public long getAverageExecuteMillis(String taskId);
	
	public Date getLastExecuteTime(String taskId);
	
	public long getLastExecuteMillis(String taskId);
	
}
