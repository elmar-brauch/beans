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

@EnableAsync
@EnableScheduling
@Configuration
public class AsyncScheduledJobs {
	
	private Logger log = Logger.getLogger(AsyncScheduledJobs.class.getName());
	
	@Autowired 
	private IdGenerator idGenerator;

	@Scheduled(cron = "${scheduled.special_seconds}")
	@Async
	void logAtSpecialSeconds() {
		log.log(Level.INFO, SCHEDULED_MSG, "at special seconds");
	}
	
	@Scheduled(fixedRateString = "${scheduled.fixed-rate}", initialDelayString = "${scheduled.initial-delay}")
	@Async
	void startJobOften() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, START_MSG, new String[]{"FIXED-RATE", jobId});
		Thread.sleep(7500);
		log.log(Level.INFO, END_MSG, new String[]{"FIXED-RATE", jobId});
	}
	
}
