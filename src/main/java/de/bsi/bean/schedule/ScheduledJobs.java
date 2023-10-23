package de.bsi.bean.schedule;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import de.bsi.bean.component.IdGenerator;

@EnableScheduling
@Configuration
public class ScheduledJobs {

	static final String SCHEDULED_MSG = "CronJob executed {0}";
	static final String START_MSG = "START of {0} job {1}";
	static final String END_MSG = "END of {0} job {1}";
	
	private Logger log = Logger.getLogger(ScheduledJobs.class.getName());
	
	// <second> <minute> <hour> <day of month> <month> <day of week>
	@Scheduled(cron = "1 * * * * *")
	void logEvery1stSecond() {
		log.log(Level.INFO, SCHEDULED_MSG, "every 1st second of any minute at any day");
	}
	
	@Scheduled(cron = "1-10 */2 * * * Mon-Fri")
	void logAtWorkingDays() {
		log.log(Level.INFO, SCHEDULED_MSG, "often at working days");
	}
	
	@Scheduled(cron = "0 0 0 25 12 ?", zone = "Europe/Berlin")
	void logOnceAtXmas() {
		log.log(Level.INFO, SCHEDULED_MSG, "once at Christmas day");
	}
	
	@Autowired private IdGenerator idGenerator;
	
	@Scheduled(fixedDelay = 1000, initialDelay = 5000)
	void startJobAfterCompletionOfPreviousSchedule() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, START_MSG, new String[]{"FIXED-DELAY", jobId});
		Thread.sleep(3000);
		log.log(Level.INFO, END_MSG, new String[]{"FIXED-DELAY", jobId});
	}
}
