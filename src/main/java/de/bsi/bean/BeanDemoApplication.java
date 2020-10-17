package de.bsi.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.bsi.bean.service.ItemService;

@SpringBootApplication
public class BeanDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BeanDemoApplication.class, args);
		var itemService = context.getBean(ItemService.class);
		var item = itemService.createAndPersistItem("Test");
		System.out.println("Item created with id: " + item.getId());
	}

}
