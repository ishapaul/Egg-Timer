package Isha.EggTimerTest.config;

import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;


public class BrowserManager {
	
	static WebDriver driver;
	@SuppressWarnings("deprecation")
	public static WebDriver getDriver(String browser) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Isha.Paul\\OTH_Test_Master\\chromedriver.exe");
	 	DesiredCapabilities dc = new DesiredCapabilities();
	 	dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        // @SuppressWarnings("deprecation")
        if (browser.equalsIgnoreCase("chrome")) {
        	driver=new ChromeDriver(dc);
        } else if (browser.equalsIgnoreCase("firefox")) {
        	driver= new FirefoxDriver(dc);
        } else {
        	Assert.assertTrue(false,"No browser type sent");
        }
		        
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	        
        driver.manage().window().maximize();
        System.out.println("Launched Browser:" + browser);
        return driver;
	}

}
