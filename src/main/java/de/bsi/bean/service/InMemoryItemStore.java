package de.bsi.bean.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bsi.bean.component.IdGenerator;
import model.Item;

@Service
public class InMemoryItemStore implements ItemPersistenceService {

	@Autowired private IdGenerator idGenerator;
	
	private Map<String, Item> store;
	
	@PostConstruct
	private void initStore() {
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
		return store.get(id);
	}

	@Override
	public void deleteItem(String id) {
		store.remove(id);
	}
}
