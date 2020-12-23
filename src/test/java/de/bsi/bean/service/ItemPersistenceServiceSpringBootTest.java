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

//SpringBootTest has simpler setup, because itemStore instantiation is done by Spring.
@SpringBootTest(classes = {InMemoryItemStore.class, IdGenerator.class})
class ItemPersistenceServiceSpringBootTest {
	
	@Autowired ItemPersistenceService itemStore;
	
	// All tests and following code could be exactly the same as in JunitTest.java,
	// but here is a demo of parameterized tests.
	
	static Stream<Item> createTestData() {
		Item testData = new Item();
		testData.setName("Book");
		return Stream.of(testData);
	}
	
	@ParameterizedTest
	@MethodSource("createTestData")
	void saveAndFindItemTest(Item item) {
		String id = itemStore.saveItem(item);
		Item searchedItem = itemStore.findItem(id);
		assertEquals("Book", searchedItem.getName());
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