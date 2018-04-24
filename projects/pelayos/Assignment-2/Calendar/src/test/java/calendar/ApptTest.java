/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
@Test(timeout = 4000)
public void test01()  throws Throwable  {

}

@Test(timeout = 4000)
public void test02()  throws Throwable  {
    Appt appt2 = new Appt(15, 30, 31, 2, 2018, null, null, null);
    appt2.setValid();
    
   //testing an correct start minute
    appt2.setStartMinute(30);
    appt2.setValid();
    assertTrue(appt2.getValid());
    //testing an incorrect start minute
    appt2.setStartMinute(45);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
    //testing a correct start hour       
    appt2.setStartHour(15);
    appt2.setValid();
    assertTrue(appt2.getValid());
   //testing an incorrect start hour   
    appt2.setStartHour(66);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
    //testing a correct start day 
    appt2.setStartDay(28);
    appt2.setValid();
    assertTrue(appt2.getValid());
    //testing an incorrect start day
    appt2.setStartDay(35);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
    //testing the correct start month
    appt2.setStartMonth(2);
    appt2.setValid();
    assertTrue(appt2.getValid());
    //testing incorrect start month
    appt2.setStartMonth(13);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
    //testing a correct start year
    appt2.setStartYear(2018);
    appt2.setValid();
    assertTrue(appt2.getValid());
    //testing an incorrect start year
    appt2.setStartYear(-5);
    appt2.setValid();
    assertFalse(appt2.getValid());
}

}
