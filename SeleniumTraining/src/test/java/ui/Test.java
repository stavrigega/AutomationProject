package ui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;





public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		//setting the driver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stavri\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		//Initiating your chromedriver
		WebDriver driver=new ChromeDriver();
		
		
		//Applied wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//maximize window
		driver.manage().window().maximize();
		
		//open browser with desired URL
		driver.get(URL.qa);
		Thread.sleep(3000);
						
		// Open a new tab using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");

        // Get window handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                 
        // Switch to the new tab
        driver.switchTo().window(tabs.get(1));

        Actions actions = new Actions(driver);
        // Open a URL in the new tab
        driver.get(URL.qaBO);

        driver.findElement(By.xpath(Variables.BOusernameField) ).sendKeys(Variables.BOusername);        
        driver.findElement(By.xpath(Variables.BOpasswordField) ).sendKeys(Variables.password); 
        driver.findElement(By.xpath(Buttons.BOloginButton)).click();
        WebElement elementToHover = driver.findElement(By.xpath(Variables.BOSideMenu));
        actions.moveToElement(elementToHover).perform();
        driver.findElement(By.xpath(Variables.NotificationsMenu)).click();
        driver.findElement(By.xpath(Variables.NotificationsSubMenu)).click();
        
        
        
        
        // Waiting time
        Thread.sleep(3000);
        
        
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);

        // Closing the browser
        driver.close();
        
        
		
		//Waiting time
		Thread.sleep(3000);
		
		//closing the browser
		driver.quit();
		
		
		
		
	}

}
