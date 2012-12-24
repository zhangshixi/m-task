package com.mtask.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mtask.util.DateUtil;

public class TaskExecCycle implements TaskExecCycleMBean {
	
	private final ExecTaskRepository execRepository;
	
	public TaskExecCycle() {
		registerMBean();
		this.execRepository = new ExecTaskRepository();
	}
	
	public void beforeExecTask(String taskId) {
		this.execRepository.addTaskExec(taskId, new TaskExec(DateUtil.getCurrentTime()));
	}

	public void afterExecTask(String taskId) {
		this.execRepository.getLastTaskExec(taskId).setEndTime(DateUtil.getCurrentTime());
	}
	
	@Override
	public int getExecuteNumbers(String taskId) {
		List<TaskExec> taskExecList = this.execRepository.getTaskExecList(taskId);
		return taskExecList == null ? 0 : taskExecList.size();
	}
	
	@Override
	public long getAverageExecuteMillis(String taskId) {
		List<TaskExec> taskExecList = this.execRepository.getTaskExecList(taskId);
		if (taskExecList == null) {
			return 0L;
		} else {
			long sumExecuteMillis = 0L;
			for (TaskExec execute : taskExecList) {
				sumExecuteMillis += execute.getExecuteMillis();
			}
			return sumExecuteMillis / getExecuteNumbers(taskId);
		}
	}

	@Override
	public Date getLastExecuteTime(String taskId) {
		TaskExec lastTaskExec = this.execRepository.getLastTaskExec(taskId);
		return lastTaskExec.getStartTime();
	}

	@Override
	public long getLastExecuteMillis(String taskId) {
		TaskExec lastTaskExec = this.execRepository.getLastTaskExec(taskId);
		return lastTaskExec.getExecuteMillis();
	}
	
	private void registerMBean() {
		StringBuilder name = new StringBuilder();
		name.append(TaskExecCycle.class.getPackage().getName());
		name.append(":type=");
		name.append(TaskExecCycle.class.getSimpleName());
		
		TaskMBeanServer.getInstance().registerMBean(this, name.toString());
	}

	// ---- inner classes ------------------------------------------------------------
	private static class TaskExec {
		
		private Date startTime;
		private Date endTime;
		
		public TaskExec(Date startTime) {
			this.startTime = startTime;
		}
		
		public Date getStartTime() {
			return startTime;
		}
		
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
		public long getExecuteMillis() {
			return endTime.getTime() - startTime.getTime(); 
		}
		
	}
	
	private static class ExecTaskRepository {
		private Map<String, List<TaskExec>> taskExecMap = 
				new ConcurrentHashMap<String, List<TaskExec>>();
		
		public List<TaskExec> getTaskExecList(String taskId) {
			return taskExecMap.get(taskId);
		}
		
		public TaskExec getLastTaskExec(String taskId) {
			List<TaskExec> taskExecList = taskExecMap.get(taskId);
			return taskExecList.get(taskExecList.size() - 1);
		}
		
		public void addTaskExec(String taskId, TaskExec taskExec) {
			List<TaskExec> taskExecList = taskExecMap.get(taskId);
			if (taskExecList == null) {
				taskExecList = new ArrayList<TaskExec>();
			}
			taskExecList.add(taskExec);
			taskExecMap.put(taskId, taskExecList);
		}
		
	}
	
}
