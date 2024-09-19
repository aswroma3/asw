package asw.ciaomondo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCiaoMondo {
	
    @Test 
	public void testCiaoMondoHaUnSaluto() {
        CiaoMondo ciaoMondo = new CiaoMondo();
        assertNotNull("ciaoMondo deve restituire un saluto", ciaoMondo.getSaluto());
    }
	
}
