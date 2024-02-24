package asw.spring.show;

import asw.spring.show.config.ShowConfig; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={ ShowConfig.class })
public class HendrixTest {

	@Autowired
	private Artist hendrix; 
	
	@Test 
	public void hendrixTest() {
    	assertEquals( "I'm Jimi: Ua ua uaa", hendrix.perform() );
	}

}
