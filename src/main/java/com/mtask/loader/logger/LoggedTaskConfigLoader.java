package com.mtask.loader.logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.mtask.loader.TaskConfigLoader;

public class LoggedTaskConfigLoader implements InvocationHandler {

	private TaskConfigLoader configLoader;
	
	public LoggedTaskConfigLoader(TaskConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
	
	public TaskConfigLoader getTaskExecutor() {
		return this.configLoader;
	}
	
	public static TaskConfigLoader newInstance(TaskConfigLoader configLoader) {
	    InvocationHandler handler = new LoggedTaskConfigLoader(configLoader);
	    ClassLoader loader = TaskConfigLoader.class.getClassLoader();
	    return (TaskConfigLoader) Proxy.newProxyInstance(
	    		loader, new Class[]{TaskConfigLoader.class}, handler);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		String methodName = method.getName();
		if ("readTasks".equals(methodName)) {
			
		} else if ("writeTask".equals(methodName)) {
			
		} else if ("writeTasks".equals(methodName)) {
			
		}
		return null;
	}

}
