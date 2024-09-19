package asw.spring.show;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class HendrixTest {

	private Artist hendrix; 
	
	@BeforeEach
	public void setupHendrix() {
		ApplicationContext context = new ClassPathXmlApplicationContext("show-beans.xml");
    	this.hendrix = (Artist) context.getBean("hendrix");
	}

	@Test 
	public void hendrixTest() {
    	assertEquals( "I'm Jimi: Ua ua uaa", hendrix.perform() );
	}

}
