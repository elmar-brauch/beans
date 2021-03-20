package de.bsi.bean.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.bsi.bean.component.IdGenerator;

@SpringBootTest(classes = {ScheduledJobs.class, IdGenerator.class})
class ScheduledJobsTest {

	@Test
	void justWaitForSomeLogs() throws InterruptedException {
		Thread.sleep(120000);
	}

}
