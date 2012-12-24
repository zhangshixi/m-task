package com.mtask.monitor;

import java.io.IOException;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;

public class TaskMBeanServer {

	private MBeanServer mbserver;
	private Thread shutdownHookThread;
	private JMXConnectorServer connectorServer;
	private volatile boolean isHutdownHookCalled = false;

	private static final TaskMBeanServer INSTANCE = new TaskMBeanServer();
	
	private TaskMBeanServer() {
		initialize();
	}

	public static TaskMBeanServer getInstance() {
		return INSTANCE;
	}
	
	public boolean isActive() {
		return mbserver != null && connectorServer != null
				&& connectorServer.isActive();
	}

	public int getMBeanCount() {
		if (mbserver != null) {
			return mbserver.getMBeanCount();
		} else {
			return 0;
		}
	}
	
	public boolean isRegistered(String name) {
		try {
			return mbserver != null
					&& mbserver.isRegistered(new ObjectName(name));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void registerMBean(Object target, String name) {
		if (isRegistered(name)) {
			return;
		}
		
		if (mbserver != null) {
			try {
				mbserver.registerMBean(target, new ObjectName(name));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public final void shutdown() {
		try {
			if (connectorServer != null && connectorServer.isActive()) {
				connectorServer.stop();
				//log.warn("JMXConnector stop");
				if (!isHutdownHookCalled) {
					Runtime.getRuntime().removeShutdownHook(shutdownHookThread);
				}
			}
		} catch (IOException e) {
			//log.error("Shutdown Xmemcached MBean server error", e);
		}
	}
	
	// ---- private methods ---------------------------------------------------
	private void initialize() {
		// TODO Auto-generated method stub
		
		this.shutdownHookThread = new Thread(new ShutdownHookThread());
		Runtime.getRuntime().addShutdownHook(this.shutdownHookThread);
	}
	
	// ---- private methods ---------------------------------------------------
	private class ShutdownHookThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				isHutdownHookCalled = true;
				if (connectorServer.isActive()) {
					connectorServer.stop();
					// log.warn("JMXConnector stop");
				}
			} catch (IOException e) {
				// log.error("Shutdown m-task MBean server error", e);
			}
		}
		
	}
	
}
