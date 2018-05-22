package calendar;

import java.util.Calendar;
import java.util.Random;
import calendar.CalDay;
import calendar.DataHandler;
import java.util.GregorianCalendar;
import org.junit.Test;
import java.util.LinkedList;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"deleteAppt", "getApptRange"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
    /**
     * Generate Random Tests that tests DataHandler Class.
     */
 
    
	 @Test
	  public void randomtest()  throws Throwable  {
		 
			GregorianCalendar today = new GregorianCalendar(3,10,2015);	//invalid
			CalDay calday = new CalDay(today); //making a new day
			
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 
	
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				try {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed); //randomseed
				
				    boolean value = ValuesGenerator.getBoolean(0.5f,random);
				    String str = ValuesGenerator.getString(random);
				    DataHandler datahandler = new DataHandler();
				    DataHandler datahandler1 = new DataHandler(str,value);
				
				
				 int startHour=ValuesGenerator.getRandomIntBetween(random, 0, 23);
				 int startMinute=ValuesGenerator.getRandomIntBetween(random, 0, 59);
				 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 31);
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
				 int startYear=ValuesGenerator.getRandomIntBetween(random, 1, 2018);
				 String title="Birthday Party";
				 String description="This is my birthday party.";
				 String emailAddress="xyz@gmail.com";

				 //Construct a new Appointment object with the initial data	 
				 //Construct a new Appointment object with the initial data	 
		         Appt appt = new Appt(startHour,
		                  startMinute ,
		                  startDay ,
		                  startMonth ,
		                  startYear ,
		                  title,
		                 description,
		                 emailAddress);
		         //invalid appt
		         Appt appt1 = new Appt(2,30,2,2,2002,title,description,emailAddress);
		         Appt invalid_appt = new Appt(-2,-30,2,2,2002,title,description,emailAddress);
		         
			 if(!appt.getValid())continue;
			for (int i = 0; i < NUM_TESTS; i++) {
				try {
					String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("deleteAppt")){
						   appt1.setValid();
						   appt1.getValid();
						   invalid_appt.setValid();
						   invalid_appt.getValid();
						   
						   datahandler1.saveAppt(appt1);
						   datahandler1.deleteAppt(appt1);
						   datahandler1.deleteAppt(appt1);
						   datahandler1.saveAppt(invalid_appt);
						   datahandler1.deleteAppt(invalid_appt);
						}
					   else if (methodName.equals("getApptRange")){
						   int sizeArray = ValuesGenerator.getRandomIntBetween(random,0,8);
						   int[] recurDays = ValuesGenerator.generateRandomArray(random,sizeArray);
						   int recur=ApptRandomTest.RandomSelectRecur(random);
						   int recurIncrement = ValuesGenerator.RandInt(random);
						   int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						   appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						   
						   int thisMonth = ValuesGenerator.getRandomIntBetween(random,1,12);
						   int thisDay = ValuesGenerator.getRandomIntBetween(random,1,31);
						   int thisYear = ValuesGenerator.getRandomIntBetween(random,1,2018);
						   // add month day and year for tomorrow random generated
						   int thisMonth_t = ValuesGenerator.getRandomIntBetween(random,1,12);
						   int thisDay_t = ValuesGenerator.getRandomIntBetween(random,1,31);
						   int thisYear_t = ValuesGenerator.getRandomIntBetween(random,1,2018);
						   GregorianCalendar today1 = new GregorianCalendar(thisYear,thisMonth, thisDay);
						   GregorianCalendar tomorrow = new GregorianCalendar(thisYear_t,thisMonth_t,thisDay_t);	//removing +1
						   LinkedList<CalDay> calDays = new LinkedList<CalDay>();
						   calDays = (LinkedList<CalDay>) datahandler1.getApptRange(today1,tomorrow);
						   
						   //calDays = (LinkedList<CalDay>) datahandler1.getApptRange(tomorrow,today1);
						   
						   
						   
						}
						//catch causing this part to be skipped resulting in an error 
					
				}catch(DateOutOfRangeException e){
							
				            System.out.println("DateOutofRangeException "+ e);
							
						}
					   
				}

			}catch(NullPointerException e){
				
	            System.out.println("NullPointerException "+ e);
				
			}
		   
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
	 
		 System.out.println("Done testing...");
	 } 
		 
		 
	 }


	
