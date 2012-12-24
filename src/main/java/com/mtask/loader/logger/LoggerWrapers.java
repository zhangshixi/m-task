package com.mtask.loader.logger;

import com.mtask.loader.TaskConfigLoader;

public final class LoggerWrapers {
	
	public static TaskConfigLoader warpConfigLoader(TaskConfigLoader configLoader) {
		return LoggedTaskConfigLoader.newInstance(configLoader);
	}
	
}
