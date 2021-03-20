package de.bsi.bean.schedule;

import java.text.MessageFormat;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;

import de.bsi.bean.component.IdGenerator;

@EnableScheduling
@Configuration
public class ScheduledJobs {

	static final String SCHEDULED_MSG = "CronJob executed {0}";
	static final String START_MSG = "START of {0} job {1}";
	static final String END_MSG = "END of {0} job {1}";
	
	Logger log = Logger.getLogger(ScheduledJobs.class.getName());
	
	// <second> <minute> <hour> <day of month> <month> <day of week>
	@Scheduled(cron = "1 * * * * *")
	private void logEvery1stSecond() {
		log.log(Level.INFO, SCHEDULED_MSG, "every 1st second of any minute at any day");
	}
	
	@Scheduled(cron = "1-10 */2 * * * Mon-Fri")
	private void logAtWorkingDays() {
		log.log(Level.INFO, SCHEDULED_MSG, "often at working days");
	}
	
	@Scheduled(cron = "0 0 0 25 12 ?", zone = "Europe/Berlin")
	private void logOnceAtXmas() {
		log.log(Level.INFO, SCHEDULED_MSG, "once at Christmas day");
	}
	
	@Autowired IdGenerator idGenerator;
	
	@Scheduled(fixedDelay = 1000, initialDelay = 5000)
	private void startJobAfterCompletionOfPreviousSchedule() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, MessageFormat.format(START_MSG, "FIXED-DELAY", jobId));
		Thread.sleep(3000);
		log.log(Level.INFO, MessageFormat.format(END_MSG, "FIXED-DELAY", jobId));
	}
}
