package de.bsi.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.bsi.bean.component.IdGenerator;
import de.bsi.bean.service.InMemoryItemStore;
import de.bsi.bean.service.ItemPersistenceService;
import de.bsi.bean.service.ItemService;
import de.bsi.bean.service.ItemServiceImpl;

// If you want to use @Configuration, uncomment the next line 
// and remove @Service & @Component annotations in other classes.
//@Configuration
public class ItemServiceConfig {
	
	@Bean(name = "itemService_2")
	public ItemService itemService() {
		return new ItemServiceImpl(itemPersistenceService());
	}
	
	@Bean(initMethod = "initStore", destroyMethod = "cleanStore",
			name = "itemPersistenceService_2")
	public ItemPersistenceService itemPersistenceService() {
		return new InMemoryItemStore();
	}
	
	@Bean(name = "idGenerator_2")
	public IdGenerator idGenerator() {
		return new IdGenerator();
	}

}
