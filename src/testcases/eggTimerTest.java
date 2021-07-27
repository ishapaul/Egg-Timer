package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import objectRepository.HomePage;
import objectRepository.TimerPage;

public class eggTimerTest {
	
	public static String time = "15 seconds"; ;
	public static Integer timeInSeconds;
	public static long startTime;
	public static Integer secondsCounter = 0;
	public static Integer timeElapsed = 0;
	public static String before = null;
	public static String after = null;  
	
	
		
	public static void main(String[] args) throws InterruptedException {
		
		// Set Up the Browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Isha.Paul\\OTH_Test_Master\\chromedriver.exe");
	 	DesiredCapabilities dc = new DesiredCapabilities();
	 	dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        @SuppressWarnings("deprecation")
		WebDriver driver=new ChromeDriver(dc);
        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);	        
        driver.manage().window().maximize();
        
        //Open the eggtimer home page
        driver.get("https://e.ggtimer.com/");
        
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
	    secondsCounter = timer[0];
	    
      	
       	// Print Timer Countdown calculated in seconds
       	System.out.println("Timer stopped in: " + secondsCounter + "seconds");	
       	
       	//Validate the Countdown Timer
       	if(timeElapsed == timeInSeconds) {
       		System.out.println("Countdown actually happens. Validation successful");	
       	} else {
       		System.out.println("Countdown Validation failed");	
       	}
       	
       	if(secondsCounter == timeInSeconds) {
       		System.out.println("Countdown happens in Seconds.Validation successful");	
       	} else {
       		System.out.println("Countdown Validation In Seconds failed");	
       	}
       	
        } else {
        	System.out.println("Unable to launch eggtimer");	
        }
       	
       	//close application
       	driver.quit();
 
	}     

}
