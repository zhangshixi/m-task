package com.mtask;

public class TaskException extends RuntimeException {

	private static final long serialVersionUID = 3854552969950433477L;

	public TaskException() {
		super();
	}

	public TaskException(String message) {
		super(message);
	}

	public TaskException(Throwable cause) {
		super(cause);
	}

	public TaskException(String message, Throwable cause) {
		super(message, cause);
	}

}
