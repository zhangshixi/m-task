package com.mtask.executor.decroctor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mtask.Task;
import com.mtask.executor.TaskExecutor;

public class AsyncTaskExecutor implements TaskExecutor {

	private TaskExecutor taskExecutor;
	private ExecutorService threadPool = Executors.newCachedThreadPool();
	
	public AsyncTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	@Override
	public void add(Task task) throws Exception {
		this.taskExecutor.add(task);
	}

	@Override
	public void activity(String taskId) throws Exception {
		this.taskExecutor.activity(taskId);
	}

	@Override
	public void pause(String taskId) throws Exception {
		this.taskExecutor.pause(taskId);
	}

	@Override
	public void resume(String taskId) throws Exception {
		this.taskExecutor.resume(taskId);
	}

	@Override
	public void execute(String taskId) throws Exception {
		this.threadPool.submit(new ExecuteThread(taskId, this.taskExecutor));
	}

	@Override
	public void remove(String taskId) throws Exception {
		this.taskExecutor.remove(taskId);
	}

	// ---- inner classes ---------------------------------------------------
	private static class ExecuteThread implements Runnable {

		private String taskId;
		private TaskExecutor executor;
		
		public ExecuteThread(String taskId, TaskExecutor executor) {
			this.taskId = taskId;
			this.executor = executor;
		}
		
		@Override
		public void run() {
			long startTimeMillis = System.currentTimeMillis();
			
			try {
				this.executor.execute(taskId);
			} catch (Exception ex) {
				// TODO:
				ex.printStackTrace();
			}
			
			long endTimeMillis = System.currentTimeMillis();
			long executedTime = endTimeMillis - startTimeMillis;
			
			System.out.println("Execute time: " + executedTime);
		}
		
	}
	
}
