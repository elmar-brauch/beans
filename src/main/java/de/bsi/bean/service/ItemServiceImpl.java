package de.bsi.bean.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import model.Item;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemPersistenceService persistenceService;
	
	// Spring injects ItemPersistenceService automatically.
	public ItemServiceImpl(ItemPersistenceService persistenceService) {
		this.persistenceService = persistenceService;
	}

	@Override
	public Item createAndPersistItem(String name) {
		if (!StringUtils.hasText(name))
			throw new IllegalArgumentException();
		var item = new Item();
		item.setName(name);
		item.setDate(new Date());
		String itemId = persistenceService.saveItem(item);
		item.setId(itemId);
		return item;
	}

	@Override
	public void removeItem(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		persistenceService.deleteItem(item.getId());
	}

	@Override
	public Item findItemById(String id) {
		if (!StringUtils.hasText(id))
			throw new IllegalArgumentException();
		return persistenceService.findItem(id);
	}

}
