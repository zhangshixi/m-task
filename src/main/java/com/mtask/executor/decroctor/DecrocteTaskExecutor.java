package com.mtask.executor.decroctor;

import com.mtask.Task;
import com.mtask.executor.TaskExecutor;

public abstract class DecrocteTaskExecutor implements TaskExecutor {
    
    private TaskExecutor _taskExecutor;

    public DecrocteTaskExecutor(TaskExecutor taskExecutor) {
        _taskExecutor = taskExecutor;
    }
    
    protected TaskExecutor getDelagateTaskExecutor() {
        return _taskExecutor;
    }
    
    @Override
    public void startup() {
        _taskExecutor.startup();
    }

    @Override
    public void startupDelayed(int seconds) {
        _taskExecutor.startupDelayed(seconds);
    }

    @Override
    public void add(Task task) throws Exception {
        _taskExecutor.add(task);
    }

    @Override
    public void activity(String taskId) throws Exception {
        _taskExecutor.activity(taskId);
    }

    @Override
    public void pause(String taskId) throws Exception {
        _taskExecutor.pause(taskId);
    }

    @Override
    public void resume(String taskId) throws Exception {
        _taskExecutor.resume(taskId);
    }

    @Override
    public void execute(String taskId) throws Exception {
        _taskExecutor.execute(taskId);
    }

    @Override
    public void remove(String taskId) throws Exception {
        _taskExecutor.remove(taskId);
    }

    @Override
    public void shutdown() {
        _taskExecutor.shutdown();
    }

    @Override
    public void shutdownNow() {
        _taskExecutor.shutdownNow();
    }

}
