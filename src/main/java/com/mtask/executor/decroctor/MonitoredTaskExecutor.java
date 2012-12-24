package com.mtask.executor.decroctor;

import com.mtask.Task;
import com.mtask.TaskOption;
import com.mtask.executor.TaskExecutor;
import com.mtask.monitor.TaskExecCycle;
import com.mtask.monitor.TaskLifeCycle;

public class MonitoredTaskExecutor implements TaskExecutor {

	private final TaskExecutor  taskExecutor;
	private final TaskLifeCycle taskLifeCycle;
	private final TaskExecCycle taskExecCycle;
	
	public MonitoredTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor   = taskExecutor;
		this.taskLifeCycle  = new TaskLifeCycle();
		this.taskExecCycle  = new TaskExecCycle();
	}
	
	@Override
	public void add(Task task) throws Exception {
		this.taskExecutor.add(task);
		this.taskLifeCycle.callTask(task, TaskOption.ADD);
	}

	@Override
	public void activity(String taskId) throws Exception {
		this.taskExecutor.activity(taskId);
		this.taskLifeCycle.callTask(taskId, TaskOption.ACTIVITY);
	}

	@Override
	public void pause(String taskId) throws Exception {
		this.taskExecutor.pause(taskId);
		this.taskLifeCycle.callTask(taskId, TaskOption.PAUSE);
	}

	@Override
	public void resume(String taskId) throws Exception {
		this.taskExecutor.resume(taskId);
		this.taskLifeCycle.callTask(taskId, TaskOption.RESUME);
	}

	@Override
	public void execute(String taskId) throws Exception {
		this.taskLifeCycle.beforeExecTask(taskId);
		this.taskExecCycle.beforeExecTask(taskId);
		this.taskExecutor.execute(taskId);
		this.taskExecCycle.afterExecTask(taskId);
		this.taskLifeCycle.afterExecTask(taskId);
	}

	@Override
	public void remove(String taskId) throws Exception {
		this.taskExecutor.remove(taskId);
		this.taskLifeCycle.callTask(taskId, TaskOption.REMOVE);
	}

}
