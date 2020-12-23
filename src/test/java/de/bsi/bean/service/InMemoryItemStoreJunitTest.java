package de.bsi.bean.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

import de.bsi.bean.component.IdGenerator;
import model.Item;

class InMemoryItemStoreJunitTest {
	
	InMemoryItemStore itemStore;
	String existingItemId;
	
	@BeforeEach
	void setup() {
		itemStore = new InMemoryItemStore();
		itemStore.initStore();
		itemStore.idGenerator = new IdGenerator();
		
		Item testData = new Item();
		testData.setName("Book");
		existingItemId = itemStore.saveItem(testData);
		assertThat(existingItemId).isNotBlank();
	}
	
	@Test
	void findItemPositiveTest() {
		Item item = itemStore.findItem(existingItemId);
		assertEquals("Book", item.getName());
	}
	
	@Test
	void findItemNegativeTest() {
		assertNull(itemStore.findItem("unknown"));
	}
	
	@Test
	void deleteItemPositiveTest() {
		itemStore.deleteItem(existingItemId);
		assertNull(itemStore.findItem(existingItemId));
	}
	
	@Test
	void deleteItemExceptionTest() {
		try {
			itemStore.deleteItem("123");
		} catch(NoSuchElementException ex) {
			// Better solution to test Exceptions is shown in SpringTest.java.
			return;
		}
		fail("NoSuchElementException expected");
	}
}