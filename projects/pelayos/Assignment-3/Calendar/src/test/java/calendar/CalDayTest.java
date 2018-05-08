/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;


public class CalDayTest{
	
	// init an object
	  GregorianCalendar today;
		
	  @Before public void initialize() {
		  	// getting info on today's date
			Calendar rightnow = Calendar.getInstance();
			int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
			int thisMonth = rightnow.get(Calendar.MONTH);
			int thisYear = rightnow.get(Calendar.YEAR);

			// Set the gregorian cal object with todays date info
			today = new GregorianCalendar(thisYear, thisMonth, thisDay);
		}
	

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  //testing an empty day
	  CalDay calday0 = new CalDay();
	  assertFalse(calday0.isValid());
	  //everything should be empty
	  assertEquals(null, calday0.iterator());
	  String daystr = calday0.toString();
	  assertEquals(daystr, "");

  }
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  CalDay calday = new CalDay(today);
	  assertFalse(calday.getAppts() == null);
	  assertTrue(calday.isValid());
	  assertEquals(calday.getDay(), today.get(today.DAY_OF_MONTH));
	  assertEquals(calday.getMonth(), today.get(today.MONTH) + 1);
	  assertEquals(calday.getYear(), today.get(today.YEAR));
	  //making appointments
	  Appt appt0 = new Appt(today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(1, 15, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "1", "D", "phony.com");
	  Appt appt2 = new Appt(10, 45, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "2", "D", "phony.com");
	  Appt appt3 = new Appt(12, 30, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "3", "D", "phony.com");
	  Appt appt4 = new Appt(13, 10, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "4", "D", "phony.com");
	  Appt appt5 = new Appt(17, 30, today.get(today.DAY_OF_MONTH), today.get(today.MONTH) + 1, today.get(today.YEAR), "5", "D", "phony.com");
	  String daystr = calday.getFullInfomrationApp(calday);
	  assertFalse(daystr == null);
	  calday.toString();
	  
	  //addign the apptiontments to the greg cal day obj
	  calday.addAppt(appt0);
	  calday.addAppt(appt1);
	  calday.addAppt(appt2); 
	  calday.addAppt(appt4);
	  calday.addAppt(appt3);
	  calday.addAppt(appt5);
	  //checking they got added
	  assertEquals(calday.getSizeAppts(), 6);
	  
	  Iterator i = calday.iterator();
	  //fixed a bug for these would be true I had reversed the appt order
	  assertEquals((Appt)i.next(), appt0);
	  assertEquals((Appt)i.next(), appt1);
	  assertEquals((Appt)i.next(), appt2);
	  assertEquals((Appt)i.next(), appt3);
	  assertEquals((Appt)i.next(), appt4);
	  assertEquals((Appt)i.next(), appt5);

	  daystr = calday.toString();
	  daystr = calday.getFullInfomrationApp(calday);
	  
	  assertFalse(daystr.contains("-5:-5AM"));
	  //assertTrue(daystr.contains("1:20AM"));
	  assertTrue(daystr.contains("10:45AM"));
	  //assertTrue(daystr.contains("6:30PM"));
	  assertTrue(daystr.contains("1:10PM"));
	  assertTrue(daystr.contains("5:30PM"));
	
	  
	 

  }

}
