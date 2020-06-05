package com.sid.thermostat.app.task.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TaskManagerImpl implements TaskManager {

	private ExecutorService executorService;

	@Autowired
	public TaskManagerImpl(Environment env) {

		executorService = Executors
				.newFixedThreadPool(Integer.parseInt(env.getProperty("task.concurrency", DEFAULT_NO_OF_THREADS)));
	}

	public void execute(AsyncTask task) {
		executorService.submit(task);
	}

	public <V> void execute(SyncTask<V> task, ResponseCallback<V> callback) {
		Future<V> future = executorService.submit(task);
		addCallback(future, callback, Executors.newCachedThreadPool());
	}

	private <V> void addCallback(Future<V> future, ResponseCallback<V> callback, ExecutorService newCachedThreadPool) {
		newCachedThreadPool.execute(() -> {
			try {
				callback.onSucess(future.get());
			} catch (InterruptedException | ExecutionException e) {
				callback.onFailure(e);
			}
		});
	}

}
