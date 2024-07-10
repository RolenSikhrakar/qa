package Automation;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUp {
	
	public static WebDriver driver;
	public static String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@BeforeSuite
	public static void setUp() throws InterruptedException {

		// 1. Setup driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		// 2. Maximize the browser
		driver.manage().window().maximize();
		driver.get(url);
		
		// 3. Set implicit wait of 60 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	}
	
	@AfterSuite
	 public void tearDown() throws InterruptedException {
	    	
		// 1. Quit driver
	    Thread.sleep(3000);
	    driver.quit();

	 }
	
	public static void loginInWithAdminCredentials() throws InterruptedException {
		
		// 1. Enter credentials for login
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys("Admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys("admin123");
		Thread.sleep(2000);
		
		// 2. Scroll at bottom & click "Login" button
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]")).click();
		
	}
	
	public static void logOut() throws InterruptedException {
		
		// 1. Click on upper right profile picture
		driver.findElement(By.xpath("//header/div[1]/div[2]/ul[1]/li[1]/span[1]")).click();
		Thread.sleep(2000);
		// 2. Click on "Logout"
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[2]/ul[1]/li[1]/ul[1]/li[4]")).click();
				
		
	}

	public static void addEmployeeForTesting() throws InterruptedException {
		
		// 1. Navigate to PIM module by clicking on "PIM" on left menu
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[2]/a[1]")).click();

		// 2. Click on "Add Employee" on upper section
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[2]/nav[1]/ul[1]/li[3]")).click();
		
		// 3. Initialize required fields to add new employee
		String firstName = "Dave";
		String middleName = "Scott";
		String lastName = "Mustaine";
		WebElement employeeIdElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/input[1]"));
		String employeeId = employeeIdElement.getAttribute("value");
		
		// 4. Set required fields to add new employee
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(firstName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/input[1]")).sendKeys(middleName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]")).sendKeys(lastName);
		
		// 5. Add profile picture for new employee
		WebElement photoInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
    	Thread.sleep(1000);
    	File uploadFile = new File("ProfilePicforOrangeHRM/DaveMustaine.JPG");
    	photoInput.sendKeys(uploadFile.getAbsolutePath());
		
    	// 6. Click on "Save" button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/button[2]")).click();
		
		
		// 7. Wait until employee is added to the server
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/h6[1]")));
		Thread.sleep(5000);
		
	}
	
}
