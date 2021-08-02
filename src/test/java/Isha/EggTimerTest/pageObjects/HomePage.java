package Isha.EggTimerTest.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	public static String time;
	public static Integer timeInSeconds;
	By timeInput = By.xpath("//input[@id='EggTimer-start-time-input-text']");
	By startBtn = By.xpath("//button[contains(.,'Start')]");
	
	
	// Home Page Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Return Home Page Element Timer Input Box
	public WebElement timeInput()
	{
		return driver.findElement(timeInput);
	}
	
	//Return Home Page Element Start Button
	public WebElement startBtn()
	{
		return driver.findElement(startBtn);
	}
	
	//Function to validate Home Page Title
	public String validateHomePageTitle() {
		System.out.println(driver.getTitle()); 
		return driver.getTitle();
	}    
	
	//Function to get the input time in seconds
	public Integer getTimeInSeconds(String time) {
		
		if(time.indexOf("Hours") != -1 || time.indexOf("hours") != -1 || time.indexOf("Hour") != -1 || time.indexOf("hour") != -1 || time.indexOf("Hrs") != -1 || time.indexOf("hrs") != -1 || time.indexOf("h") != -1) {
    		timeInSeconds = Integer.parseInt(time.split(" ")[0])*3600;        		
    	} else if(time.indexOf("Minutes") != -1 || time.indexOf("minutes") != -1 || time.indexOf("Minute") != -1 || time.indexOf("minute") != -1 || time.indexOf("Mins") != -1 || time.indexOf("mins") != -1) {	        	
			timeInSeconds = Integer.parseInt(time.split(" ")[0]) * 60;
      	} else {
      		timeInSeconds = Integer.parseInt(time.split(" ")[0]); 
      	}
		// End of Else If Loop		
		return timeInSeconds;
	}
	
	//Function to Enter Time and start Timer
	public void startTimer(String time, Integer timeInSeconds) {
		timeInput().sendKeys(time);
		startBtn().click(); 
		String currentURL = driver.getCurrentUrl();        
        if(currentURL.indexOf((time.split(" ")[0])) != -1) {
        	System.out.println(currentURL); 
        	System.out.println("Coundown in Seconds:" + timeInSeconds);
        }        
        
	}
	
	//Function to fetch the Start Time of Timer in Seconds
	public long getStartTime() {		
        long startTime = System.currentTimeMillis() / 1000;
        System.out.println("Start Time in Seconds:" + startTime);     	
    	return startTime;
	}
	
	//Function to wait for Page to Load
	public void waitForPageToLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver d) {
	            try {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            } catch (Exception e) {
	                return Boolean.FALSE;
	            }
	        }
	    };	   
		WebDriverWait wait = new WebDriverWait(driver, 1000);
	    wait.until(javascriptDone);
	}    


}
