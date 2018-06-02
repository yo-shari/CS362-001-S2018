
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;

import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;


public class DataHandlerTest{

	ByteArrayOutputStream output;
	GregorianCalendar today, tomorrow;
	
	  public boolean hasAppt(List<Appt> l, Appt a) {
		  Iterator<Appt> i = l.iterator();
		  while (i.hasNext()) {
			  Appt b = i.next();
			  if (a.getTitle().equals(b.getTitle()))
				  	return true;
		  }
		  return false;
	  }
	
	@Before public void initialize() {		
		// Get a calendar which is set to a specified date.
		today = new GregorianCalendar(2018, 5, 7);
		tomorrow = new GregorianCalendar(2018, 5, 7);
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);

		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
	
	
 @Test(timeout = 4000)
 public void test00()  throws Throwable  {
	  DataHandler dh0 = new DataHandler("calendar3.xml", false);
	  	 //A simple appointment that should work 
	     Appt appt0 = new Appt(15, 30, 14, 9, 2018, null, null, null);
	     appt0.setValid();
	     //Testing invalid appt with bad start hour
	     Appt appt1 = new Appt(66, 23, 14, 9, 2018, null, null, null);
	     appt1.setValid();		
	     
	     //testing save & delete for appt 1 the bad one
	     assertFalse(dh0.saveAppt(appt1));
	     assertFalse(dh0.deleteAppt(appt1));
	     //testing save & delete for appt 0 should work
	     assertTrue(dh0.saveAppt(appt0));
	     assertTrue(dh0.deleteAppt(appt0));
	     
	      Field field = DataHandler.class.getDeclaredField("valid");
	      field.setAccessible(true);
	      field.setBoolean(dh0, false);
		  assertEquals(dh0.getApptRange(today, tomorrow), null);	   
	     
	     
  }
 @Test(timeout = 4000)  		//testing an appt with more fields
 public void test01()  throws Throwable  {
		 DataHandler dh0 = new DataHandler("test_1", false);
		 DataHandler dh1 = new DataHandler("test_1", true);
		     
		  Appt appt00 = new Appt(15, 30, 14, 9, 2018, "Test Bday", "fake event", "phonyperson@zmail.com");
		     appt00.setValid();
		     
		     assertTrue(dh0.saveAppt(appt00));
		     assertTrue(dh0.deleteAppt(appt00)); //return true since not autosave

		     assertTrue(dh1.saveAppt(appt00));
		     assertTrue(dh1.deleteAppt(appt00)); //autosave should return true

		     assertTrue(dh1.saveAppt(appt00));
		     appt00.setXmlElement(null);		//try setting to null
		     assertFalse(dh1.deleteAppt(appt00)); //should return false as Xml location missing
	  
}
  
  
  
@Test(timeout = 4000)
public void test02()  throws Throwable  {

  String name = "testName3";
  File file = new File(name);
  FileWriter fileWriter = new FileWriter(file);
  fileWriter.write("This is a test line");
  fileWriter.flush();
  fileWriter.close();

  try {
     DataHandler dh0 = new DataHandler(name);
     fail("Should have IOException");

  } catch (IOException e) {
     System.err.println(e.toString());
  }
}


@Test(timeout = 4000)
public void test03()  throws Throwable  {
	 DataHandler dh0 = new DataHandler("test_3", true);
	 
     String desc = "Test event description";
     String emailAddress = "phonyperson@zmail.com";
	 String title = "Test Event";
     String title2 = "Test Event 2";
     String title3 = "Test Event 3";

     
     Appt appt0 = new Appt(15, 30, 14, 9, 2018, title, desc, emailAddress);
     int[] recurDays = {3};
     appt0.setRecurrence(recurDays, Appt.RECUR_BY_WEEKLY, 1, 2);
     appt0.setValid();
     assertTrue(dh0.saveAppt(appt0));
     
     Appt appt1 = new Appt(15, 35, 14, 9, 2018, title2, desc, emailAddress);
     int[] recurDays2 = {7};
     appt1.setRecurrence(recurDays2, Appt.RECUR_BY_WEEKLY, 1, 2);
     appt1.setValid();
     assertTrue(dh0.saveAppt(appt1));
     
     Appt appt2 = new Appt(8, 35, 14, 10, 2018, title3, desc, emailAddress);
     int[] recurDays3 = {4};
     appt2.setRecurrence(recurDays2, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
     appt2.setValid();
     assertTrue(dh0.saveAppt(appt2));
}

@Test(timeout = 4000)
public void test04()  throws Throwable  {
	  DataHandler dh = new DataHandler("calendar.xml");
	  List<CalDay> daylist;
	  try {
		  daylist = dh.getApptRange(tomorrow, today);
		  fail();
	  }
	  catch(DateOutOfRangeException e) {}
	  daylist = dh.getApptRange(today, tomorrow);
	  tomorrow.add(Calendar.MONTH, 1);
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals("", output.toString());
}

@Test(timeout = 4000)
public void test05()  throws Throwable  {
	DataHandler dh = new DataHandler("calendar.xml", false);
	  File f = new File("calendar.xml");
	  long modTimeOld = f.lastModified();
	  Appt appt0 = new Appt(-1, -1, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "0", "D", "a.com");
	  Appt appt1 = new Appt(18, 61, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "1", "D", "a.com");
	  Appt appt2 = new Appt(9, 30, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "2", "D", "a.com");
	  Appt appt3 = new Appt(12, 8, today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH) + 1, today.get(Calendar.YEAR), "3", "D", "a.com");
	  //appt0.setValid(); // appointments without start times are never valid (bug)
	  appt1.setValid();	  // correctly invalid
	  appt2.setValid();
	  appt3.setValid();
	  List<CalDay> daylist = dh.getApptRange(today, tomorrow);
	  int start_num = daylist.get(0).getSizeAppts();
	  
	  assertTrue(dh.saveAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  long modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.saveAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.saveAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.saveAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 3 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.deleteAppt(appt3));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 2 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt2));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertFalse(dh.deleteAppt(appt1));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), 1 + start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
	  
	  assertTrue(dh.deleteAppt(appt0));
	  daylist = dh.getApptRange(today, tomorrow);
	  assertEquals(daylist.get(0).getSizeAppts(), start_num);
	  modTimeNew = f.lastModified();
	  assertEquals(modTimeOld, modTimeNew);
}

}
