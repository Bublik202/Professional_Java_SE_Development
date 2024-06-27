package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUnionImpl implements ThreadUnion{
	private final String name;	
	private final AtomicInteger threadCount;	
	private final List<FinishedThreadResult> finishThreadList;
	private boolean isShutdown;
	private final List<Thread> threadList;
	

	public ThreadUnionImpl(String name) {
		super();
		this.name = name;
		this.threadCount = new AtomicInteger(0);
		this.finishThreadList = Collections.synchronizedList(new ArrayList<>());
		this.isShutdown = false;
		this.threadList = Collections.synchronizedList(new ArrayList<>());
	}

	@Override
	public Thread newThread(Runnable r) {			
		if(isShutdown) {
			throw new IllegalStateException();
		}
		Thread thread = new Thread(r) {
			@Override
			public void run() {
				super.run();
				finishThreadList.add(new FinishedThreadResult(this.getName()));
				
			}
		};	
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
			FinishedThreadResult result = new FinishedThreadResult(t.getName(), e);
			finishThreadList.add(result);
		});
		String nameThread = name + "-worker-" + threadCount.getAndIncrement();
		thread.setName(nameThread);
		threadList.add(thread);		
		
		return thread;
	}

	@Override
	public int totalSize() {	
		return threadCount.get();
	}

	@Override
	public int activeSize() {		
		return (int) threadList.stream().filter(Thread::isAlive).count();
	}

	@Override
	public void shutdown() {
		threadList.forEach(t -> {
			t.interrupt();
		});
		isShutdown = true;
	}

	@Override
	public boolean isShutdown() {
		return isShutdown;
	}

	@Override
	public void awaitTermination() {
		threadList.stream().filter(Thread::isAlive).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		});
	}

	@Override
	public boolean isFinished() {
		return isShutdown && activeSize() == 0;
	}

	@Override
	public List<FinishedThreadResult> results() {
		return new ArrayList<>(finishThreadList);
	}

}
