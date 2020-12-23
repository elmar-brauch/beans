package de.bsi.bean.service;

import java.util.*;

import javax.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bsi.bean.component.IdGenerator;
import model.Item;

@Service
public class InMemoryItemStore implements ItemPersistenceService {

	@Autowired IdGenerator idGenerator;
	
	private Map<String, Item> store;
	
	@PostConstruct
	void initStore() {
		store = new HashMap<>();
	}
	
	@PreDestroy
	private void cleanStore() {
		store.clear();
	}
	
	@Override
	public String saveItem(Item newItem) {
		String generatedId = idGenerator.generateId();
		newItem.setId(generatedId);
		store.put(generatedId, newItem);
		return generatedId;
	}

	@Override
	public Item findItem(String id) {
		if (id == null)
			return null;
		return store.get(id);
	}

	@Override
	public void deleteItem(String id) {
		if (id == null || store.remove(id) == null)
			throw new NoSuchElementException("Element with given id not found in store.");
	}
}
