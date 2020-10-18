package de.bsi.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import de.bsi.bean.service.ItemService;

@SpringBootApplication
// Uncomment the next 2 lines, if you want to use bean definition in xml.
//@ComponentScan(useDefaultFilters = false)
//@ImportResource(locations = {"classpath:spring.xml"})
public class BeanDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BeanDemoApplication.class, args);
		var itemService = context.getBean(ItemService.class);
		var item = itemService.createAndPersistItem("Test");
		System.out.println("Item created with id: " + item.getId());
	}

}
