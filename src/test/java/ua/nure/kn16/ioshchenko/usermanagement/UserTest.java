package ua.nure.kn16.ioshchenko.usermanagement;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private static final int YEAR_OF_BIRTH = 1999;
	private static final long ID = 1L;
	  
	private User user;
	
	//Tests are relevant for October 30
	// Test (getting the full name) 
	private static final String NAME = "Ivan";
	private static final String SURNAME = "Ivanov";
	
	// Test (1) When is the birthday on this day (October 26)
	private static final int DAY_OF_BIRTH_1 = 26;
	private static final int MONTH_OF_BIRTH_1 = Calendar.OCTOBER;
	
	// Test (2) When a month has already passed, the day has passed 
	private static final int DAY_OF_BIRTH_2 = 10;
	private static final int MONTH_OF_BIRTH_2 = Calendar.APRIL;
	
	// Test (3) When the month has not yet come, the day has not come 
	private static final int DAY_OF_BIRTH_3 = 28;
	private static final int MONTH_OF_BIRTH_3 = Calendar.NOVEMBER;		
	
	// Test (4) When the month come, the day passed
	private static final int DAY_OF_BIRTH_4 = 24;
	private static final int MONTH_OF_BIRTH_4 = Calendar.OCTOBER;	
	
	// Test (5) When the month came, and the day did not come 
	private static final int DAY_OF_BIRTH_5 = 30;
	private static final int MONTH_OF_BIRTH_5 = Calendar.OCTOBER;

	@Before
	public void setUp() throws Exception {
		user = new User(ID, NAME, SURNAME, new Date());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFullName()  {
		assertEquals("Ivanov, Ivan", user.getFullName());
	}
	
	 // Test (1) When is the birthday on this day 
    @Test
    public void Test_age1(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
        Date dateofBirth=calendar.getTime();
        user.setDateofBirth(dateofBirth);
        assertEquals(19, user.getAge());
    }
	
	// Test (2) When a month has already passed, the day has passed 
	@Test 
    public void Test_age2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
        Date dateofBirth=calendar.getTime();
        user.setDateofBirth(dateofBirth);
        assertEquals(19, user.getAge());
    }
	
	// Test (3) When the month has not yet come, the day has not come
    @Test
    public void Test_age3(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
        Date dateofBirth=calendar.getTime();
        user.setDateofBirth(dateofBirth);
        assertEquals(18, user.getAge());
    }
	
	 // Test (4) When the month come, the day passed
    @Test 
    public void Test_age4(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,  MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
        Date dateofBirth = calendar.getTime();
        user.setDateofBirth(dateofBirth);
        assertEquals(19, user.getAge());
    }

	// Test (5) When the month came, and the day did not come  
    @Test
    public void Test_age5(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
        Date dateofBirth=calendar.getTime();
        user.setDateofBirth(dateofBirth);
        assertEquals(18, user.getAge());
    }
}
