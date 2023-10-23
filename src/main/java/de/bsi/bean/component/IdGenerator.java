package de.bsi.bean.component;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Scope("prototype")
public class IdGenerator {
	
	private String prefix;
	
	/** This methods show difference between Prototype and Singleton scope. */ 
	@PostConstruct
	private void initPrefixAfterConstruction() {
		this.prefix = UUID.randomUUID().getMostSignificantBits() + "___";
	}
	
	public String generateId() {
		return this.prefix + UUID.randomUUID().getMostSignificantBits();
	}

}
