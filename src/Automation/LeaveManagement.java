package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LeaveManagement extends SetUp{
	
	// Positive Testing
	// 1. Check if users can successfully apply for leave with valid details.
	@Test(priority = 1)
	public static void applyLeaveWithValidDetails() throws InterruptedException {
		
		// 1. Login with administrator credentials
		loginInWithAdminCredentials();
		
		// 2. Click on "Leave" sub menu on left
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(1000);
		
		// 3. Click on "Apply" button on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[1]")).click();
		Thread.sleep(1000);
		
		// 4. Select leave type from "Leave Type" drop down list which uses <div> instead of <select> html element
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		
		// 5. Select appropriate "From Date" & "To Date" from respective date picker
		
		WebElement dateBoxFromDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxFromDate.sendKeys("2024-09-25"); //Fill date as yyyy-mm-dd as 2024-09-25
		Thread.sleep(1000);
		
		WebElement dateBoxToDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxToDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Clear default date
		dateBoxToDate.sendKeys("2024-09-27"); //Fill date as yyyy-mm-dd as 2024-09-27
		Thread.sleep(1000);
		
		
		// 6. Select appropriate Partial Days from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 7. Select appropriate Duration from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 8. Scroll to "Apply" button
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement applyButtonElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]"));
		js.executeScript("arguments[0].scrollIntoView();", applyButtonElement);
		Thread.sleep(2000);
		
		// 9. Click on "Apply" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]")).click();
		Thread.sleep(1000);
		
	}
	
	// Positive testing
	// 2. Check if users can successfully view their leave history.
	@Test(priority = 2)
	public static void viewLeaveHistory() throws InterruptedException {
		
		// 1. Click on "Leave List" on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[6]")).click();
		Thread.sleep(2000);
		
		// 2. Scroll to bottom to view leave history
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[3]/p[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomElement);
		Thread.sleep(3000);
		
		// 3. After viewing leave history scroll to top
		WebElement element = driver.findElement(By.tagName("header"));
		js.executeScript("arguments[0].scrollIntoView();", element); 
		Thread.sleep(2000);
		
	}
	
	// Positive testing
	// 3. Check if users can successfully add new leave entitlements for employees.
	@Test(priority = 3)
	public static void addNewLeaveEntitlement() throws InterruptedException {
		
		// 1. First add employee to set entitlements for it
		addEmployeeForTesting();
		
		// 2. Navigate to Leave module by clicking on "Leave" on left menu
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[3]/a[1]/span[1]")).click();
		
		// 3. Click on "Entitlements" at top & then "Add Entitlements"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Add Entitlements')]")).click();
		Thread.sleep(1000);
		
		// 4. Enter employee name to set entitlements
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("Dave Scott Mustaine");
		Thread.sleep(1000);
		
		// 5. Select Leave Type from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 6. Set entitlements to 14 days
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[3]/div[1]/div[2]/input[1]")).sendKeys("14");

		// 7. Click on "Save" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[4]/button[2]")).click();
		Thread.sleep(1000);
		
		// 8. Click on "Confirm" button for confirmation pop up
		driver.findElement(By.xpath("//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[2]")).click();
		
	}
	
	// Positive Testing
	// 4. Check if users can filter and search for specific leave records.
	@Test(priority = 4)
	public static void filterAndSearchForSpecificLeaveRecords() throws InterruptedException {
		
		// 1. Click on "My Leave" at top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		Thread.sleep(1000);
		
		// 2. Select Leave Type from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 3. Scroll to bottom to view leave list
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[3]/p[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomElement);
		Thread.sleep(3000);
		
		// 4. After viewing leave list, scroll to top
		WebElement myleaveListLabelElement = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/h5[1]"));
		js.executeScript("arguments[0].scrollIntoView();", myleaveListLabelElement); 
		Thread.sleep(2000);
		
	}
	
	// Positive Testing
	// 5. Check if users can edit existing leave entitlements.
	@Test(priority = 5)
	public static void editEntitlements() throws InterruptedException {
		
		// 1. Click on "Entitlements" then "My Entitlements"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]/span[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'My Entitlements')]")).click();
		Thread.sleep(1000);
		
		// 2. Click on "Search" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[1]")).click();
		Thread.sleep(1000);
		
		// 3. Scroll to leave list
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomEditIconElement = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/button[2]/i[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomEditIconElement);
		Thread.sleep(2000);
		
		// 4. Click on edit icon
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/button[2]/i[1]")).click();
		Thread.sleep(1000);
		
		// 5. Clear existing value
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[3]/div[1]/div[2]/input[1]")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		Thread.sleep(1000);
		// 6. Enter entitlements
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[3]/div[1]/div[2]/input[1]")).sendKeys("15");
		Thread.sleep(1000);
		
		// 7. Click "Save" button
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[2]")).click();
		
	}
	
	// Negative Testing
	// 6. Verify that users attempt to apply for leave with invalid or incomplete details, and the system handles the error appropriately.
	@Test(priority = 6)
	public static void applyLeaveWithInvalidOrIncompleteDetails() throws InterruptedException {
		
		// 1. Click on "Apply" button on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[1]")).click();
		Thread.sleep(1000);
		
		// 2. Select leave type from "Leave Type" drop down list which uses <div> instead of <select> html element
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		
		// 3. Select inappropriate "From Date" & "To Date" from respective date picker
		WebElement dateBoxFromDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxFromDate.sendKeys("2024-09-2524"); //Fill date as yyyy-mm-dd 
		Thread.sleep(1000);
		
		WebElement dateBoxToDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxToDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Clear default date
		dateBoxToDate.sendKeys("2024-09-2724"); //Fill date as yyyy-mm-dd 
		Thread.sleep(1000);
		
		
		// 4. Select appropriate Partial Days from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 5. Select appropriate Duration from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 6. Scroll to "Apply" button
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement applyButtonElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]"));
		js.executeScript("arguments[0].scrollIntoView();", applyButtonElement);
		Thread.sleep(2000);
		
		// 7. Click on "Apply" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]")).click();
		Thread.sleep(1000);		
		
	}

	// Negative Testing
	// 7. Verify that users try to apply for leave that exceeds their available leave balance.
	@Test(priority = 7)
	public static void applyLeaveWhichExceedsLeaveBalance() throws InterruptedException {
		

		// 1. Click on "Apply" button on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[1]")).click();
		Thread.sleep(1000);
		
		// 2. Select leave type from "Leave Type" drop down list which uses <div> instead of <select> html element
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		
		// 3. Select "From Date" & "To Date" from respective date picker which exceeds leave balance
		WebElement dateBoxFromDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxFromDate.sendKeys("2024-04-25"); //Fill date as yyyy-mm-dd 
		Thread.sleep(1000);
		
		WebElement dateBoxToDate = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxToDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Clear default date
		dateBoxToDate.sendKeys("2024-08-27"); //Fill date as yyyy-mm-dd 
		Thread.sleep(1000);
		
		
		// 4. Select appropriate Partial Days from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 5. Select appropriate Duration from drop down list which uses <div> instead of <select>
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]")).click();
		Thread.sleep(1000);
		
		// 6. Scroll to "Apply" button
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement applyButtonElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]"));
		js.executeScript("arguments[0].scrollIntoView();", applyButtonElement);
		Thread.sleep(2000);
		
		// 7. Click on "Apply" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[5]/button[1]")).click();
		Thread.sleep(1000);		
				
	}
	
	// Negative Testing
	// 8. Verify that user enters incorrect data and search for specific leave giving appropriate validation message.
	@Test(priority = 8)
	public static void searchLeaveWithInvalidData() throws InterruptedException {
		
		// 1. Click on "Leave List" at top
		driver.findElement(By.xpath("//a[contains(text(),'Leave List')]")).click();
		Thread.sleep(1000);		
		
		// 2. Set invalid date to "From Date" & "To Date"
		WebElement dateBoxFromDate = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxFromDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Clear default date
		dateBoxFromDate.sendKeys("2024-04-2524");  
		Thread.sleep(1000);
		
		WebElement dateBoxToDate = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxToDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Clear default date
		dateBoxToDate.sendKeys("2024-08-2724"); 
		Thread.sleep(1000);
		
		// 3. Click on "Search" button
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[3]/button[2]")).click();
		
	}
	
	// Negative Testing
	// 9. Verify that user enters blank data and search for specific leave giving appropriate validation message.
	@Test(priority = 9)
	public static void searchLeaveWithBlankData() throws InterruptedException {
		

		// 1. Click on "Leave List" at top
		driver.findElement(By.xpath("//a[contains(text(),'Leave List')]")).click();
		Thread.sleep(3000);		
		
		// 2. Set blank date to "From Date" & "To Date"
		WebElement dateBoxFromDate = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxFromDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Set blank date 
		Thread.sleep(1000);
		
		WebElement dateBoxToDate = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/input[1]"));
		dateBoxToDate.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); // Set blank date
		Thread.sleep(1000);
		
		// 3. Click on "Search" button
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[3]/button[2]")).click();	
		
	}
	
}
