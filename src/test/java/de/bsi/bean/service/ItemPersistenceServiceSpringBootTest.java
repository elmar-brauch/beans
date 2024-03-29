package de.bsi.bean.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.bsi.bean.component.IdGenerator;
import model.Item;

@SpringBootTest(classes = {InMemoryItemStore.class, IdGenerator.class})
class ItemPersistenceServiceSpringBootTest {
	
	@Autowired ItemPersistenceService itemStore;
	
	static Stream<Item> createTestData() {
		Item testData1 = new Item();
		testData1.setName("Book");
		Item testData2 = new Item();
		testData2.setName("Ball");
		return Stream.of(testData1, testData2);
	}
	
	@ParameterizedTest
	@MethodSource("createTestData")
	void saveAndFindItemTest(Item item) {
		String id = itemStore.saveItem(item);
		Item searchedItem = itemStore.findItem(id);
		assertEquals(item.getName(), searchedItem.getName());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"unknown", "123"})
	@NullAndEmptySource
	void findItemNegativeTest(String itemId) {
		assertNull(itemStore.findItem(itemId));
	}
	
	@ParameterizedTest
	@MethodSource("createTestData")
	void deleteItemPositiveTest(Item item) {
		String id = itemStore.saveItem(item);
		itemStore.deleteItem(id);
		assertNull(itemStore.findItem(id));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"unknown", "123"})
	@NullAndEmptySource
	void deleteItemExceptionTest(String itemId) {
		assertThrows(NoSuchElementException.class, () -> itemStore.deleteItem(itemId));
	}
}