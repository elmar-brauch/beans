package de.bsi.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.bsi.bean.service.ItemService;

@SpringBootApplication
public class BeanDemoApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(BeanDemoApplication.class, args);
		var itemService = context.getBean(ItemService.class);
		itemService.createAndPersistItem("Test");
		Thread.sleep(10000);
	}

}
