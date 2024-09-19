package asw.intro;

import asw.intro.service.Service;
import asw.intro.server.ServiceImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUpperCaseService {
	
    @Test 
	public void testCiaoMondoHaUnSaluto() {
		Service service = new ServiceImpl();
        assertEquals("CIAO", service.alpha("cIaO"), "alpha deve convertire una stringa a lettere maiuscole");
    }
	
}
