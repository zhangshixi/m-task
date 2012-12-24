package com.mtask.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mtask.Task;
import com.mtask.TaskOptStsMapping;
import com.mtask.TaskOption;
import com.mtask.TaskStatus;
import com.mtask.util.DateUtil;

public class TaskLifeCycle implements TaskLifeCycleMBean {

	private final StatusTaskRepository taskRepository;
	
	public TaskLifeCycle() {
		registerMBean();
		this.taskRepository = new StatusTaskRepository();
	}

	public void callTask(Task task, TaskOption option) {
		String taskId = task.getId();
		StatusTask statusTask = taskRepository.getStatusTask(taskId);
		
		if (statusTask == null) {
			statusTask = new StatusTask(task);
			taskRepository.addStatusTask(statusTask);
		}
	}
	
	public void callTask(String taskId, TaskOption option) {
		StatusTask statusTask = taskRepository.getStatusTask(taskId);
		
		TaskStatus lastStatus = statusTask.getLastStatus().getTaskStatus();
		TaskStatus callStatus = TaskOptStsMapping.getTaskStatus(option);
		
		if (lastStatus != callStatus) {
			statusTask.addStatus(new Status(callStatus, DateUtil.getCurrentTime()));
		}
	}
	
	public void beforeExecTask(String taskId) {
		StatusTask statusTask = taskRepository.getStatusTask(taskId);
		statusTask.getLastStatus().setTaskStatus(TaskStatus.ECECUTING);
	}

	public void afterExecTask(String taskId) {
		StatusTask statusTask = taskRepository.getStatusTask(taskId);
		statusTask.getLastStatus().setTaskStatus(TaskStatus.ACTIVITY);
	}
	
	
	@Override
	public int getTaskNumbers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTaskStatus(String taskId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void registerMBean() {
		StringBuilder name = new StringBuilder();
		name.append(TaskLifeCycle.class.getPackage().getName());
		name.append(":type=");
		name.append(TaskLifeCycle.class.getSimpleName());
		
		TaskMBeanServer.getInstance().registerMBean(this, name.toString());
	}

	// ---- inner classes -------------------------------------------------------------
	private static class Status {
		private TaskStatus taskStatus;
		private Date startTime;
		
		public Status(TaskStatus tashStatus, Date startTime) {
			this.taskStatus = tashStatus;
			this.startTime = startTime;
		}
		
		public TaskStatus getTaskStatus() {
			return taskStatus;
		}
		
		public void setTaskStatus(TaskStatus taskStatus) {
			this.taskStatus = taskStatus;
		}
		
		public Date getStartTime() {
			return startTime;
		}
		
	}
	
	private static class StatusTask {
		
		private Task task;
		private List<Status> statusList = new ArrayList<Status>();
		
		public StatusTask(Task task) {
			this.task = task;
		}
		
		public Task getTask() {
			return task;
		}
		
		public List<Status> getStatusList() {
			return statusList;
		}
		
		public Status getLastStatus() {
			return statusList.get(statusList.size() - 1);
		}
		
		public void addStatus(Status status) {
			statusList.add(status);
		}
		
	}
	
	private static class StatusTaskRepository {
		
		private Map<String, StatusTask> allTaskMap = 
				new ConcurrentHashMap<String, StatusTask>();
		
		public StatusTask getStatusTask(String taskId) {
			return allTaskMap.get(taskId);
		}
		
		public void addStatusTask(StatusTask statusTask) {
			allTaskMap.put(statusTask.getTask().getId(), statusTask);
		}
		
	}

}
