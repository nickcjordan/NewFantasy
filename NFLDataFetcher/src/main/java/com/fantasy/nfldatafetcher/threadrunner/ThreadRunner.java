package com.fantasy.nfldatafetcher.threadrunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ThreadRunner {
	
	protected static Logger log = Logger.getLogger(ThreadRunner.class);
	
	protected void waitForThreads(ExecutorService executor) {
		executor.shutdown();
		try {
			executor.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.error(e);
		}
	}

}
