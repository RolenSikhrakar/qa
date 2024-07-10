package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class InsuranceClaimManagement extends SetUp{
	
	// Positive Testing
	// 1. Verify that users can successfully view a list of all their submitted insurance claims with complete details.
	@Test(priority = 1)
	public static void viewListOfSubmittedInsuranceClaims() throws InterruptedException {
		
		// 1. Login with administrator credentials
		loginInWithAdminCredentials();
		
		// 2. Click on "Claim"
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[11]/a[1]")).click();
		Thread.sleep(1000);
		
		// 3. Click on "My Claims"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]")).click();
		Thread.sleep(2000);
		
		// 4. Scroll to bottom to view submitted insurance claims
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomLabelElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[3]/p[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomLabelElement);
		Thread.sleep(3000);
		
		// 5. Scroll to top		
		WebElement topLabelElement = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h5[1]"));
		js.executeScript("arguments[0].scrollIntoView();", topLabelElement);
		Thread.sleep(2000);
		
	}
	
	// Positive Testing
	// 2. Verify that users can sort their claims based on different criteria.
	@Test(priority = 2)
	public static void sortClaimBasedOnReferenceId() throws InterruptedException {
		
		// Click on "Claim"
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[11]/a[1]")).click();
		Thread.sleep(1000);
		
		// Click on "My Claims"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]")).click();
		Thread.sleep(2000);
		
		// Scroll to claim list
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomLabelElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[3]/p[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomLabelElement);
		Thread.sleep(2000);
		
		// Click on Reference Id column header
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/i[1]")).click();
		Thread.sleep(1000);
		
		// Select "Ascending"
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/span[1]")).click();
		Thread.sleep(3000);
		
		// Scroll to top 
		WebElement topLabelElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h5[1]"));
		js.executeScript("arguments[0].scrollIntoView();", topLabelElement);
		Thread.sleep(2000);
		
	}
	
	// Positive Testing
	// 3. Test if insurance claim can be submitted.
	@Test(priority = 3)
	public static void submitInsuranceClaim() throws InterruptedException {
		
		// 1. Click on "Claim"
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[11]/a[1]")).click();
		Thread.sleep(1000);
		
		// 2. Click on "Submit Claim"
		driver.findElement(By.xpath("//a[contains(text(),'Submit Claim')]")).click();
		Thread.sleep(1000);
		
		// 3. Select Event
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 4. Select Currency
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 5. Click "Create" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[2]")).click();
		
		// 6. Scroll down to "Submit" button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement submitButtonElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[9]/button[3]"));
		js.executeScript("arguments[0].scrollIntoView();", submitButtonElement);
		Thread.sleep(2000);
		
		// 7. Click on "Submit" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[9]/button[3]")).click();
		
	}
	
	// Negative Testing
	// 4. Check if users attempt to submit a claim with incomplete or invalid information, and the system rejects the submission.
	@Test(priority = 4)
	public static void submitClaimWithIncompleteOrInvalidData() throws InterruptedException {
		
		// 1. Click on "Claim"
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[11]/a[1]")).click();
		Thread.sleep(1000);
		
		// 2. Click on "Submit Claim"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]")).click();
		Thread.sleep(2000);
		
		// 3. Click on "Create" button without selecting Event & Currency
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
	} 
	
	// Negative Testing
	// 5. Check if users can search claims by entering invalid data.
	@Test(priority = 5)
	public static void searchClaimsByEnteringInvalidData() throws InterruptedException {
		
		// 1. Click on "Claim"
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[9]/a[1]")).click();
		Thread.sleep(1000);
		
		// 2. Click on "My Claims"
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[2]/nav[1]/ul[1]/li[3]")).click();
		Thread.sleep(1000);
		
		// 3. Set invalid Reference Id
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("XXXXXX");
		Thread.sleep(1000);
		
		// 4. Click "Search"
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[3]/button[2]")).click();
		Thread.sleep(1000);
		
	}

}
