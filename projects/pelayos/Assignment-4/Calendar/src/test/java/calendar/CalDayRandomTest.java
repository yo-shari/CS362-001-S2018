package calendar;


import org.junit.Test;
import java.util.Calendar;
import java.util.Random;
import calendar.CalDay;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	

	
    /**
     * Generate Random Tests that tests CalDay Class.
     */		 
		 private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
			private static final int NUM_TESTS=100;

			/**
			 * Return a randomly selected method to be tests !.
			 */
		    public static String RandomSelectMethod(Random random){
		        String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class

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
		     * Generate Random Tests that tests Appt Class.
		     */
			 @Test
			  public void randomtest()  throws Throwable  {
				//init an object 

				
				
				GregorianCalendar today = new GregorianCalendar(3,10,2015);
				CalDay calday = new CalDay(today); //making a new day
				
				
				 long startTime = Calendar.getInstance().getTimeInMillis();
				 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

				 
				 System.out.println("Start testing...");
				 
				try{ 
					//changed the < to >
					for (int iteration = 0; elapsed < TestTimeout; iteration++) {
						long randomseed =System.currentTimeMillis(); //10
			//			System.out.println(" Seed:"+randomseed );
						Random random = new Random(randomseed);
						
						 int startHour=ValuesGenerator.getRandomIntBetween(random, 0, 23);
						 int startMinute=ValuesGenerator.getRandomIntBetween(random, 0, 59);
						 int startDay=ValuesGenerator.getRandomIntBetween(random, 1, 31);
						 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 12);
						 int startYear=ValuesGenerator.getRandomIntBetween(random, -2, 2018);
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
				         // make new app
				         Appt appt1 = new Appt(2,30,28,6,1990,title,description,emailAddress);

					 if(!appt.getValid())continue;
					for (int i = 0; i < NUM_TESTS; i++) {
							String methodName = CalDayRandomTest.RandomSelectMethod(random);
							   if (methodName.equals("addAppt")){
								   appt.setValid();
								   calday.addAppt(appt);
								   calday.addAppt(appt1);
								} 
						}
						
						 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
					        if((iteration%10000)==0 && iteration!=0 )
					              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
					 
					}
				}catch(NullPointerException e){
					
				}
			 
				 System.out.println("Done testing...");
			 }


			


			
		}
