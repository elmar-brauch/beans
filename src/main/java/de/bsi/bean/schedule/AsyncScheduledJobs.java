package de.bsi.bean.schedule;

import java.text.MessageFormat;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;

import de.bsi.bean.component.IdGenerator;

import static de.bsi.bean.schedule.ScheduledJobs.*;

@EnableAsync
@EnableScheduling
@Configuration
public class AsyncScheduledJobs {
	
	Logger log = Logger.getLogger(AsyncScheduledJobs.class.getName());
	@Autowired IdGenerator idGenerator;

	@Scheduled(cron = "${scheduled.special_seconds}")
	@Async
	void logAtSpecialSeconds() {
		log.log(Level.INFO, SCHEDULED_MSG, "at special seconds");
	}
	
	@Scheduled(fixedRateString = "${scheduled.fixed-rate}", initialDelayString = "${scheduled.initial-delay}")
	@Async
	void startJobOften() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, MessageFormat.format(START_MSG, "FIXED-RATE", jobId));
		Thread.sleep(7500);
		log.log(Level.INFO, MessageFormat.format(END_MSG, "FIXED-RATE", jobId));
	}
	
}
