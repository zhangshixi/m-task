package com.mtask.executor.decroctor;

import java.util.HashMap;
import java.util.Map;

import com.mtask.Task;
import com.mtask.event.EventListener;
import com.mtask.executor.TaskExecutor;

public class ListenedTaskExecutor implements TaskExecutor {

	private TaskExecutor taskExecutor;
	private EventListener[] globalListeners;
	private Map<String, EventListener[]> taskListenersMap = new HashMap<>();
	
	public ListenedTaskExecutor(TaskExecutor taskExecutor, EventListener... globalListeners) {
		this.taskExecutor = taskExecutor;
		this.globalListeners = globalListeners;
	}
	
	@Override
	public void add(Task task) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(task.getId());
		
		for (EventListener listener : globalListeners) {
			listener.beforeAdd(task);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforeAdd(task);
			}
		}
		
		this.taskExecutor.add(task);
		
		for (EventListener listener : globalListeners) {
			listener.afterAdded(task);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterAdded(task);
			}
		}
	}

	@Override
	public void activity(String taskId) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.beforeActivity(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforeActivity(taskId);
			}
		}
		
		this.taskExecutor.activity(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.afterActivitied(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterActivitied(taskId);
			}
		}
	}

	@Override
	public void pause(String taskId) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.beforePause(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforePause(taskId);
			}
		}
		
		this.taskExecutor.pause(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.afterPaused(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterPaused(taskId);
			}
		}
	}

	@Override
	public void resume(String taskId) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.beforeResume(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforeResume(taskId);
			}
		}
		
		this.taskExecutor.resume(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.afterResumed(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterResumed(taskId);
			}
		}
	}

	@Override
	public void execute(String taskId) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.beforeExecute(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforeExecute(taskId);
			}
		}
		
		this.taskExecutor.execute(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.afterExecuted(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterExecuted(taskId);
			}
		}
	}

	@Override
	public void remove(String taskId) throws Exception {
		EventListener[] taskListeners = taskListenersMap.get(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.beforeRemove(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.beforeRemove(taskId);
			}
		}
		
		this.taskExecutor.remove(taskId);
		
		for (EventListener listener : globalListeners) {
			listener.afterRemoved(taskId);
		}
		if (taskListeners != null) {
			for (EventListener listener : taskListeners) {
				listener.afterRemoved(taskId);
			}
		}
	}
	
}
