package de.bsi.bean.schedule;

import static de.bsi.bean.schedule.ScheduledJobs.END_MSG;
import static de.bsi.bean.schedule.ScheduledJobs.SCHEDULED_MSG;
import static de.bsi.bean.schedule.ScheduledJobs.START_MSG;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import de.bsi.bean.component.IdGenerator;

// TODO Set annotations to enable scheduling executed in async threads.
public class AsyncScheduledJobs {
	
	private Logger log = Logger.getLogger(AsyncScheduledJobs.class.getName());
	
	@Autowired 
	private IdGenerator idGenerator;

	// TODO async execution with cron expression in application.properties scheduled.special_seconds
	void logAtSpecialSeconds() {
		log.log(Level.INFO, SCHEDULED_MSG, "at special seconds");
	}
	
	// TODO async execution with fixed period between invocations and initial delay. Both read in application.properties
	void startJobOften() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, START_MSG, new String[]{"FIXED-RATE", jobId});
		Thread.sleep(7500);
		log.log(Level.INFO, END_MSG, new String[]{"FIXED-RATE", jobId});
	}
	
}
