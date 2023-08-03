package ui;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class login {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stavri\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(URL.qa);
		driver.findElement(By.xpath(Buttons.MenuLoginButton)).click();
		driver.findElement(By.xpath(Variables.phoneInput)).sendKeys(Variables.prefix + "7777777"); 
		driver.findElement(By.xpath(Variables.passwordField)).sendKeys(Variables.password); 
		Thread.sleep(3000);
		driver.findElement(By.xpath(Buttons.loginButton)).click(); 
		Thread.sleep(3000);
		driver.close();
		
	}

}
