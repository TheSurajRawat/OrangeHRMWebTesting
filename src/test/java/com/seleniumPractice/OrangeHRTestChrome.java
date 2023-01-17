package com.seleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;

public class OrangeHRTestChrome {
	
	private static WebDriver driver;
	private static String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@BeforeClass
	public static void setup() {
		String driverPath = "D:\\JavaProject\\driver\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", driverPath); 
		
		driver = new ChromeDriver();
	}
		
	@AfterClass
	public static void windup() {
			driver.close();	
	}
	
	@Test
	public void verifyLoginPage() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedTitle = "OrangeHRM";		
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals("OrangeHRM page is not available",expectedTitle,actualTitle);
		
	}
		
	@Test
	public void checkPresenceOfInputField() {
		driver.get(url);	
		driver.manage().window().maximize();
		
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		
		if(username.isDisplayed() && password.isDisplayed() && username.isEnabled() && password.isEnabled()){
			Assert.assertEquals("Username & Password fields are not available or disable",true,true);
		}
		
	}
	
	@Test
	public void verifyErrorMessage() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedErrorMessage = "Required";
		
		WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
		loginButton.click();
		
		WebElement errorForUsername = driver.findElement(By.xpath("(//span[text() = 'Required'])[1]"));
		String actualUsernameError = errorForUsername.getText();
		
		WebElement errorForPassword = driver.findElement(By.xpath("(//span[text() = 'Required'])[2]"));
		String actualPasswordError = errorForPassword.getText();
		
		if(actualUsernameError.equals(expectedErrorMessage) && actualPasswordError.equals(expectedErrorMessage)) {
			Assert.assertEquals("Please Enter usename & password",actualUsernameError,actualUsernameError);
		}
		
	}
	
	@Test
	public void verifyForgotPasswordLink() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedForgotPasswordLinkText = "Forgot your password?";
		
		WebElement forgotPasswordLink = driver.findElement(By.xpath("//p[text() = 'Forgot your password? ']"));
		String actualForgotPasswordLinkText = forgotPasswordLink.getText();
		
		Assert.assertEquals("Forgot Passowrd link is not available",expectedForgotPasswordLinkText,actualForgotPasswordLinkText);
		
		}
	
	@Test
	public void validateLoginWithInvalidCredential() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
	
		username.sendKeys("Admin");
		password.sendKeys("Admin12");
		loginButton.click();
		
		String expectedErrorMessage = "Invalid credentials";
		
		WebElement errorMessageforInvalidCrdential = driver.findElement(By.xpath("//p[text() = 'Invalid credentials']"));
		String actualErrorMessage = errorMessageforInvalidCrdential.getText();
		
		Assert.assertEquals("Error Message is not available",expectedErrorMessage,actualErrorMessage);
	
		}
	
	@Test
	public void verifyForgotPasswordLinkIsWorking() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement forgotPasswordLink = driver.findElement(By.xpath("//p[text() = 'Forgot your password? ']"));
		forgotPasswordLink.click();
		
		String expectedHeading = "Reset Password";
		
		WebElement resetPageHeading = driver.findElement(By.xpath("//*[text() = 'Reset Password']"));
		String actualHeading = resetPageHeading.getText();
		
		Assert.assertEquals("Reset Password page is not available",expectedHeading,actualHeading);
		
		}
	
	@Test
	public void verifySuccessfulLogin() {
		driver.get(url);
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
	
		username.sendKeys("Admin");
		password.sendKeys("admin123");
		loginButton.click();
		
		String expectedLabel = "Dashboard";
		
		WebElement homePageLabel = driver.findElement(By.xpath("(//*[text() = 'Dashboard'])[2]"));
		String actualLabel = homePageLabel.getText();
		
		Assert.assertEquals("HomePage is not available",expectedLabel,actualLabel);
		
		WebElement signoutDropdown = driver.findElement(By.xpath("//p[text() = 'Paul Collings']"));
		signoutDropdown.click();
		
		WebElement signout = driver.findElement(By.xpath("//a[text() = 'Logout']"));
		signout.click();
	
		}

}
