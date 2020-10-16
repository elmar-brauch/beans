package de.bsi.bean.service;

import model.Item;

public interface ItemPersistenceService {
	
	/** Stores new item and returns its Id. */
	String saveItem(Item newItem);
	
	/** Searches for item by given Id. */
	Item findItem(String id);
	
	/** Deletes item with given Id. */
	void deleteItem(String id);
}
