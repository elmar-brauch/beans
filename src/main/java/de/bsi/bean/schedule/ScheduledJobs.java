package de.bsi.bean.schedule;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import de.bsi.bean.component.IdGenerator;

// TODO Set annotation to enable scheduling
public class ScheduledJobs {

	static final String SCHEDULED_MSG = "CronJob executed {0}";
	static final String START_MSG = "START of {0} job {1}";
	static final String END_MSG = "END of {0} job {1}";
	
	private Logger log = Logger.getLogger(ScheduledJobs.class.getName());
	
	// <second> <minute> <hour> <day of month> <month> <day of week>
	// TODO see log
	void logEvery1stSecond() {
		log.log(Level.INFO, SCHEDULED_MSG, "every 1st second of any minute at any day");
	}
	
	// TODO see log
	void logAtWorkingDays() {
		log.log(Level.INFO, SCHEDULED_MSG, "often at weekend days");
	}
	
	// TODO see log
	void logOnceAtXmas() {
		log.log(Level.INFO, SCHEDULED_MSG, "once at Christmas day");
	}
	
	@Autowired private IdGenerator idGenerator;
	
	// TODO fixed delay between end of last and start of next invocation. Use initial delay. 
	void startJobAfterCompletionOfPreviousSchedule() throws InterruptedException {
		String jobId = idGenerator.generateId();
		log.log(Level.INFO, START_MSG, new String[]{"FIXED-DELAY", jobId});
		Thread.sleep(3000);
		log.log(Level.INFO, END_MSG, new String[]{"FIXED-DELAY", jobId});
	}
}
