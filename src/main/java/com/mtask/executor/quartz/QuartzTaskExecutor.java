package com.mtask.executor.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.mtask.Task;
import com.mtask.TaskException;
import com.mtask.executor.TaskExecutor;
import com.mtask.task.CronTask;
import com.mtask.task.SimpleTask;

public class QuartzTaskExecutor implements TaskExecutor {

	private Scheduler scheduler;
	
	public static final String QUARTZ_PROPERTIES = "quartz.properties";
	
	public static final String DEF_JOB_GROUP = Scheduler.DEFAULT_GROUP;
	public static final String DEF_TRIGGER_GROUP = Scheduler.DEFAULT_GROUP;
	
	// ---- constructors
	public QuartzTaskExecutor() {
		try {
			StdSchedulerFactory factory = new StdSchedulerFactory();
			factory.initialize(QUARTZ_PROPERTIES);

			scheduler = factory.getScheduler();

		} catch (SchedulerException e) {
			throw new TaskException(e);
		}
	}
	
	// ---- implement methods
	@Override
	public void startup() {
		// Scheduler will not execute jobs until it has been started 
		// (though they can be scheduled before start())
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			throw new TaskException(e);
		}
	}
	
	@Override
	public void startupDelayed(int seconds) {
	    try {
            scheduler.startDelayed(seconds);
        } catch (SchedulerException e) {
            throw new TaskException(e);
        }
	}
	
	@Override
	public void add(Task task) throws Exception {
		// TODO Auto-generated method stub
		final String taskId = task.getId();
		
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger(); 
		
		JobDetail jobDetail = JobBuilder
				.newJob(QuartzJobAdapter.class)
				.withIdentity(buildJobKey(taskId))
				.withDescription(task.getDescription())
				.build();
		
		Trigger trigger = null;
		
		if (task instanceof CronTask) {
			CronTask cronTask = (CronTask) task;
			
			trigger = triggerBuilder
					.withIdentity(buildTriggerKey(taskId))
					.startAt(task.getStartTime())
					.endAt(task.getEndTime())
					.withDescription(buildTriggerDesc(task.getDescription()))
					.withSchedule(
							CronScheduleBuilder
							.cronSchedule(cronTask.getCronExpression()))
					.build();
		} else {
			SimpleTask simpleTask = (SimpleTask) task;
			
			trigger = triggerBuilder
					.withIdentity(buildTriggerKey(taskId))
					.startAt(task.getStartTime())
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(simpleTask.getRepeatInterval())
							.withRepeatCount(simpleTask.getRepeatCount()))
					.build();
			
		}
		
		scheduler.addJob(jobDetail, true);
	}

	@Override
	public void activity(String taskId) throws Exception {
		// scheduler.scheduleJob(jobDetail, trigger);
	}

	@Override
	public void pause(String taskId) throws Exception {
		scheduler.pauseJob(JobKey.jobKey("job1", "group1"));
		scheduler.pauseTrigger(TriggerKey.triggerKey("trigger1", "group1"));
	}

	@Override
	public void resume(String taskId) throws Exception {
		// TODO Auto-generated method stub
		scheduler.resumeJob(JobKey.jobKey("job1", "group1"));
		scheduler.resumeTrigger(TriggerKey.triggerKey("trigger1", "group1"));
	}

	@Override
	public void execute(String taskId) throws Exception {
		// TODO Auto-generated method stub
		scheduler.triggerJob(buildJobKey(taskId));
	}

	@Override
	public void remove(String taskId) throws Exception {
		// TODO Auto-generated method stub
		scheduler.deleteJob(JobKey.jobKey("job1", "group1"));
		scheduler.unscheduleJob(TriggerKey.triggerKey("trigger1", "group1"));
	}

    @Override
    public void shutdown() {
        try {
            // does not return until executing Jobs complete execution
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            throw new TaskException(e);
        }
    }
    
    @Override
    public void shutdownNow() {
        try {
            scheduler.shutdown(false);
        } catch (SchedulerException e) {
            throw new TaskException(e);
        }
    }
    
	// ---- private methods
	private JobKey buildJobKey(String taskId) {
		return new JobKey(taskId, DEF_JOB_GROUP);
	}
	
	private TriggerKey buildTriggerKey(String taskId) {
		return TriggerKey.triggerKey(buildTriggerName(taskId), DEF_TRIGGER_GROUP);
	}
	
	private String buildTriggerName(String taskId) {
		return "trigger." + taskId;
	}
	
	private String buildTriggerDesc(String taskDesc) {
		return "trigger." + taskDesc;
	}
	
}
