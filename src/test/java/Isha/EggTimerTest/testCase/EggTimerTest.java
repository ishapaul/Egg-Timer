package Isha.EggTimerTest.testCase;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;  
import org.openqa.selenium.WebDriver;
import Isha.EggTimerTest.config.BrowserManager;
import Isha.EggTimerTest.pageObjects.HomePage;
import Isha.EggTimerTest.pageObjects.TimerPage;


public class EggTimerTest {
	
	//Global variables
	public static Integer timeInSeconds;
	public static long startTime;
	public static Integer secondsCounter = 0;
	public static Integer timeElapsed = 0;
	public static String before = null;
	public static String after = null; 	
	public WebDriver driver = null;
		
	@BeforeTest
	@Parameters({ "browser","url" })
	public void setUp(String browser, String url) {
		
		//launch Browser
		driver= BrowserManager.getDriver(browser); 	    
        
		//Open the eggtimer home page
		driver.get(url);     		
	}
	
	@Test
	@Parameters({ "time" })
	public void verifyTimerCountdown(String time) {	  
		
		// Instantiate the Pages
		HomePage homePage = new HomePage(driver);
		TimerPage timerPage = new TimerPage(driver);        
		    
		// Validate the EggTimer Title Page
		if(homePage.validateHomePageTitle().indexOf("e.ggtimer - a simple countdown timer") != -1) {
		System.out.println("Successfully launched eggtimer");	
		        	
			//Wait for Page Load to complete
			homePage.waitForPageToLoad(driver); 
		  
			//Fetch the Input Time In Seconds
			timeInSeconds = homePage.getTimeInSeconds(time);
		        
			//Start the Timer in Home Page
			homePage.startTimer(time, timeInSeconds);
		        
			//Fetch the Start Time of Timer
			startTime = homePage.getStartTime();  
		        
			//Fetch the Timer Countdown values
			int[] timer = timerPage.validateTimerCountDown(startTime);
			timeElapsed = timer[0];
			secondsCounter = timer[1];			    
		      	
			// Print Timer Countdown calculated in seconds
			System.out.println("Timer stopped in: " + timeElapsed + " seconds");	
			System.out.println("Countdown happened: " + secondsCounter + " number of times");	
			
		} else {
		    System.out.println("Unable to launch eggtimer");	
		}
		       	
		//Validate the Countdown Timer
		if(timeElapsed == timeInSeconds) {
			  System.out.println("Countdown actually happens.Validation successful");	
		} else {
		      System.out.println("Countdown Validation failed");	
		}
		       	
		if(secondsCounter == timeInSeconds + 1) {
		       	System.out.println("Countdown happens in Seconds.Validation successful");	
		} else {
		       	System.out.println("Countdown Validation In Seconds failed");	
		}   
		
		Assert.assertEquals(timeElapsed, timeInSeconds, "Countdown Validation failed.Time Elapsed does not equal input time by user");
		// Assert.assertEquals(secondsCounter, timeInSeconds+1, "Countdown Validation failed.Time Remaining does not decrease in 1 second decrements");
		
 			 
} 
	
@AfterTest
public void closeApplication() {
		//close application
		driver.quit();
	}
}

