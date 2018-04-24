
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;

import static org.junit.Assert.*;
import calendar.Appt;
//import calendar.CalDay;
//import calendar.DataHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.GregorianCalendar;
//import java.util.LinkedList;


public class DataHandlerTest{

 @Test(timeout = 4000)
 public void test00()  throws Throwable  {
	  DataHandler dh0 = new DataHandler();
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




}
