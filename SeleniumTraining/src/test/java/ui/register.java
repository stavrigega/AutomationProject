package ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class register {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		int set1, set2, set3, set4;
		Random generator = new Random();
		set1 = generator.nextInt(1000);
		set2 = generator.nextInt(643) + 100;
		set3 = generator.nextInt(8999) + 1000;
		set4 = generator.nextInt(2000) + 1;
		
		String registerPhone = Variables.prefix + set2 + set3;
		String identityCard = String.valueOf(set1) + String.valueOf(set4);
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stavri\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
		
		// Making the driver wait 10 seconds until the element is displayed on the page
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Maximize the window
		driver.manage().window().maximize();
		
		// Register form
		driver.get(URL.qa);
		Thread.sleep(2000);
		driver.findElement(By.xpath(Buttons.MenuRegisterButton)).click();
		driver.findElement(By.xpath(Variables.phoneInput)).sendKeys(registerPhone); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.firstNameField)).sendKeys(Variables.firstName); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.lastNameField)).sendKeys(Variables.lastName + set1 ); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.identityCardField)).sendKeys(identityCard); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.passwordField)).sendKeys(Variables.password); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.confirmPasswordField)).sendKeys(Variables.password); Thread.sleep(1000);
		driver.findElement(By.xpath(Variables.currencyDropdown)).click(); Thread.sleep(1000);      
        driver.findElement(By.xpath(Currencie.UGX)).click(); Thread.sleep(3000);		    		
        driver.findElement(By.xpath(Buttons.registerButton)).click();
		
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
        
        // Logging in to Back Office
        driver.findElement(By.xpath(Variables.BOusernameField) ).sendKeys(Variables.BOusername); Thread.sleep(1000);        
        driver.findElement(By.xpath(Variables.BOpasswordField) ).sendKeys(Variables.password); Thread.sleep(1000); 
        driver.findElement(By.xpath(Buttons.BOloginButton)).click();
       
        // Hover to the menu bar
        WebElement elementToHover = driver.findElement(By.xpath(Variables.BOSideMenu));
        actions.moveToElement(elementToHover).perform();
        
        // Navigating to the notifications page
        driver.findElement(By.xpath(Variables.NotificationsMenu)).click(); Thread.sleep(1000);
        driver.findElement(By.xpath(Variables.NotificationsSubMenu)).click(); Thread.sleep(1000);
        
        // Filter by phone number
		driver.findElement(By.xpath(Variables.Recipients)).sendKeys(registerPhone, Keys.ENTER); Thread.sleep(1000);
		
		// Getting the notification and cropping it to the last 5 digits
		WebElement element = driver.findElement(By.xpath(Variables.OTPsms));
		String OTPnotification = element.getText();
		String OTPcode = OTPnotification.substring(OTPnotification.length() - 5);
		Thread.sleep(2000);
		
		// Switching back to the previous tab
		driver.switchTo().window(tabs.get(0));
	    Thread.sleep(2000);
		
	    // Pasting the OTP code and clicking submit
		driver.findElement(By.xpath(Variables.OTPfield)).sendKeys(OTPcode); Thread.sleep(1000);
		driver.findElement(By.xpath(Buttons.OTPsubmit)).click();
	    
	    
		Thread.sleep(5000);
		driver.quit();
		
		

		
		
		
		
		
	}

}
