package asw.ciaomondo;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCiaoMondo {
	
    @Test 
	public void testCiaoMondoHaUnSaluto() {
        CiaoMondo ciaoMondo = new CiaoMondo();
        assertNotNull("ciaoMondo deve restituire un saluto", ciaoMondo.getSaluto());
    }
	
}
