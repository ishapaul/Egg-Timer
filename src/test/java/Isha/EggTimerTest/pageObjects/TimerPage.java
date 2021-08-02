package Isha.EggTimerTest.pageObjects;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class TimerPage {
	WebDriver driver;
	public static Integer secondsCounter = 0;
	public static String before = null;
	public static String after = null;
	By countDownText = By.xpath("//p[@class='ClassicTimer-time']/span");
	
	//Timer Page Constructor
	public TimerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//Return Timer Page WebElements Countdown Text	
	public List<WebElement> countDownText()
	{
		return driver.findElements(countDownText);
	}
	
	// Function to check if Alert is Present
	public boolean isAlertPresent(WebDriver driver) 
	{ 
	    try 
	    { 
	        driver.switchTo().alert();
	        Reporter.log("Alert Presence checked", true);
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	    	Reporter.log("Alert Presence checked",false);
	        return false; 
	    }   // catch 
	    
	    
	}   // isAlertPresent()
	
	
	//Function to validate the Timer Countdown
	public int[] validateTimerCountDown(long startTime) {
		
	int[] timer = new int[2];
    		
	String pageSource = driver.getPageSource();
	// parse the page source through jsoup
	Document document = Jsoup.parse(pageSource);
	        
	  while(true){
       	  try {	            		  
         	 if(countDownText().size()>1) {	          		  
         		after = countDownText().get(0).getText() + countDownText().get(1).getText();         		       			
         	 } else {	               		
         	    after = countDownText().get(0).getText(); 
         	 }        
         	       
             if(after.equalsIgnoreCase("Time Expired!")) {
         		long endTime = System.currentTimeMillis() / 1000;
         		System.out.println("End Time in Seconds:" + endTime);  
         		int timeElapsed = (int) (endTime - startTime);
         		timer[0] = timeElapsed;
         		System.out.println("Time Elapsed:" + timeElapsed);
             	break;
         	 }
  			 
         	 if(!after.equals(before)){
  				System.out.println(after);
  				before=after;	  
  				secondsCounter++;	
         	 } // End of If Loop      	 
    		  
         	 } catch(StaleElementReferenceException s) {
         		  try {
         			System.out.println("Stale Element Encounter.Please Proceed with Countdown");  
         		  } catch (UnhandledAlertException f) {
         			  try {
         				  Alert alert = driver.switchTo().alert();
         				  String alertText = alert.getText();
         				  System.out.println("Alert Text: " + alertText);
         				  alert.accept();
         			  } catch (NoAlertPresentException e) {
         				  e.printStackTrace();
         			  }
         		  }  
  			} catch (UnhandledAlertException f) {
  			    handleAlert();
  			} catch(IndexOutOfBoundsException i) {
  				 i.printStackTrace();
  			}         	  
      } // End of While Loop  
         	
      if(isAlertPresent(driver)) {
         	handleAlert();
      }   
      
      timer[1] = secondsCounter;
         	
      return timer;     
       
	}
	
	public void handleAlert() {
		if(isAlertPresent(driver)) {
     		try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        System.out.println("Alert Text: " + alertText);
		        alert.accept();
		    } catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }
     	}    
	}


}
