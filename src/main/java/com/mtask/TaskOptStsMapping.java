package com.mtask;

import java.util.HashMap;
import java.util.Map;

public final class TaskOptStsMapping {
	
	private TaskOptStsMapping() {
	}
	
	private static final Map<TaskOption, TaskStatus> OPT_STS_MAPPING;
	static {
		OPT_STS_MAPPING = new HashMap<TaskOption, TaskStatus>(6, 1F);
		
		OPT_STS_MAPPING.put(TaskOption.ADD,  	 TaskStatus.PAUSED  );
		OPT_STS_MAPPING.put(TaskOption.ACTIVITY, TaskStatus.ACTIVITY);
		OPT_STS_MAPPING.put(TaskOption.PAUSE,    TaskStatus.PAUSED  );
		OPT_STS_MAPPING.put(TaskOption.RESUME,   TaskStatus.ACTIVITY);
		OPT_STS_MAPPING.put(TaskOption.EXECUTE,  TaskStatus.ACTIVITY);
		OPT_STS_MAPPING.put(TaskOption.REMOVE,   TaskStatus.REMOVED );
	}
	
	public static TaskStatus getTaskStatus(TaskOption option) {
		return OPT_STS_MAPPING.get(option);
	}
	
}
