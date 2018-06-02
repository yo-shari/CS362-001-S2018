/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt0.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
  	  String string0 = appt0.toString();
  	  assertTrue(appt0.getRecurDays().length == 0);
      assertEquals(Appt.RECUR_BY_WEEKLY, appt0.getRecurBy());
      assertEquals(Appt.RECUR_NUMBER_FOREVER, appt0.getRecurNumber());
      assertTrue(appt0.isRecurring());
      assertEquals(1, appt0.getRecurIncrement());
     
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertTrue(appt0.hasTimeSet());
   
      
      appt0.setValid();
//      assertTrue(appt0.getValid());

      assertEquals(null, appt0.getXmlElement());
      
      //assertTrue(appt0.isOn(15, 30, 2018));
     // assertFalse(appt0.isOn(14, 30, 2018));
     // assertFalse(appt0.isOn(14,  30, 2018));
      
      
      //testing the appointment variables
      assertFalse(appt0.getStartYear() < 0);
      //assertTrue(appt0.getStartMonth() < 13);
      assertTrue(appt0.getStartDay() < 32);
      assertTrue(appt0.getStartHour() < 24);
      assertTrue(appt0.getStartMinute() < 60);
      

  }

@Test(timeout = 4000)
public void test01()  throws Throwable  {
    Appt appt2 = new Appt(15, 30, 31, 2, 2018, "title", "test", null);

    assertFalse(appt2.isRecurring());
    assertEquals(0, appt2.getRecurIncrement());
    
   //testing an correct start minute
    appt2.setStartMinute(30);
    appt2.setValid();
   // assertTrue(appt2.getValid());
    //testing an incorrect start minute
    appt2.setStartMinute(45);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
//    assertTrue(appt2.getStartMinute() >= 0);
//    //assertEquals(appt2.getStartMinute(),30);
    
    //testing a correct start hour       
    appt2.setStartHour(15);
   // appt2.setValid();
    //assertTrue(appt2.getValid());
   //testing an incorrect start hour   
    appt2.setStartHour(66);
    appt2.setValid();
    assertFalse(appt2.getValid());
//    
//    assertTrue(appt2.getStartHour() >= 0);
//    assertEquals(appt2.getStartHour(),15);
//    
    //testing a correct start day 
    appt2.setStartDay(28);
    appt2.setValid();
    //assertTrue(appt2.getValid());
    //testing an incorrect start day
    appt2.setStartDay(35);
    appt2.setValid();
    assertFalse(appt2.getValid());
//    
//    assertTrue(appt2.getStartDay() > 0);
//    assertEquals(appt2.getStartDay(),31);
    
    //testing the correct start month
    appt2.setStartMonth(2);
    appt2.setValid();
   // assertTrue(appt2.getValid());
    //testing incorrect start month
    appt2.setStartMonth(13);
    appt2.setValid();
    assertFalse(appt2.getValid());
//    
//    assertEquals(appt2.getStartMonth(),2);
//    assertTrue(appt2.getStartMonth() > 0);
//    
    //testing a correct start year
    appt2.setStartYear(2018);
    appt2.setValid();
    //assertTrue(appt2.getValid());
    //testing an incorrect start year
    appt2.setStartYear(-5);
    appt2.setValid();
    assertFalse(appt2.getValid());
    
//    assertTrue(appt2.getStartYear() >= 0);
//    assertEquals(appt2.getStartYear(),2018);
    
    //checking that title and are correct
    assertEquals(appt2.getDescription(), "test");
    assertEquals(appt2.getTitle(), "title");
    
    //changing the title and desc back to null and testing again
    appt2.setTitle(null);
    assertEquals(appt2.getTitle(), "");
    appt2.setDescription(null);
    assertEquals(appt2.getDescription(), "");
    //email wasn't changed and should be null
    assertEquals(appt2.getDescription(), "");
}

@Test(timeout = 4000)
public void test02()  throws Throwable  {
	Appt appt0 = new Appt(4, 15, 23, 6, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
    String string0 = appt0.toString();

    assertEquals("\t6/23/2018 at 4:15am ,Birthday Party, This is my birthday party\n", string0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    // testing good boundries
    
    appt0.setStartMinute(0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMinute(59);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartHour(0);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartHour(23);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartDay(1);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartDay(31);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMonth(1);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartMonth(6);
    appt0.setStartDay(1);
       
    appt0.setStartMonth(12);
    appt0.setValid();
    assertTrue(appt0.getValid());
    
    appt0.setStartYear(1);
    appt0.setValid();
    assertTrue(appt0.getValid());

    //testing bad bounderies

    appt0.setStartMinute(-1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMinute(60);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartHour(-1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartHour(24);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    
    appt0.setStartDay(0);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartDay(31);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMonth(0);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartMonth(13);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
    appt0.setStartYear(0);
    appt0.setValid();
    assertFalse(appt0.getValid());

    appt0.setStartYear(1);
    appt0.setValid();
    assertFalse(appt0.getValid());
    
   


}

}
