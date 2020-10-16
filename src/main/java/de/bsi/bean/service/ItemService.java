package de.bsi.bean.service;

import model.Item;

public interface ItemService {
	
	Item createAndPersistItem(String name);
	
	void removeItem(Item item);
	
	Item findItemById(String id);
}
