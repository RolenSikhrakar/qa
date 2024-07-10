package Automation;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PIM extends SetUp{
	
	public static String recentlyAddedEmployeeId;
	public static String recentlyAddedEmployeeFullName;

	// Positive Testing
	// 1. Verify if users can view the list of employees and navigate through different pages using pagination.
	@Test(priority = 1)
	public static void viewEmployees() throws InterruptedException {
		
		// 1. Login using administrator credentials into System (Orange HRM)  
		loginInWithAdminCredentials();
		
		// 2. Click on "PIM" section from left menu & then "Employee List" button on upper side
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[2]")).click();
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		
		// 3. Scroll at bottom of pagination & click on 1 no. of pagination
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement nextElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[4]/nav[1]/ul[1]/li[2]/button[1]"));
		js.executeScript("arguments[0].scrollIntoView();", nextElement);
		nextElement.click();
		Thread.sleep(3000);
		
		// 4. click on previous of pagination
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[4]/nav[1]/ul[1]/li[1]/button[1]")));
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[4]/nav[1]/ul[1]/li[1]/button[1]")).click();
		Thread.sleep(3000);
			
		// 5. Scroll at the top where there is Employee Information label
		WebElement employeeInformationLabelElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/h5[1]"));
		js.executeScript("arguments[0].scrollIntoView();", employeeInformationLabelElement);
		
	}
	
	// Positive Testing
	// 2. Check if users can successfully add a new employee to the system with all required information provided.
	@Test(priority = 2)
	public static void addEmployeeWithValidData() throws InterruptedException {
		
		// 1. Click on "Add Employee" on upper section
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[2]/nav[1]/ul[1]/li[3]")).click();
		
		// 2. Initialize required fields to add new employee
		String firstName = "Dave";
		String middleName = "Scott";
		String lastName = "Mustaine";
		WebElement employeeIdElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/input[1]"));
		String employeeId = employeeIdElement.getAttribute("value");
		
		// 3. Set required fields to add new employee
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(firstName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/input[1]")).sendKeys(middleName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]")).sendKeys(lastName);
		
		// 4. Add profile picture for new employee
		WebElement photoInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
    	Thread.sleep(1000);
    	File uploadFile = new File("ProfilePicforOrangeHRM/DaveMustaine.JPG");
    	photoInput.sendKeys(uploadFile.getAbsolutePath());
		
    	// 5. Click on "Save" button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/button[2]")).click();
		
		
		// 6. Wait until employee is added to the server
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/h6[1]")));
		Thread.sleep(3000);
		
		recentlyAddedEmployeeId = employeeId;
		recentlyAddedEmployeeFullName = firstName + " " + middleName + " " + lastName;
		
	}
	
	// Positive Testing
	// 3. Check if users can search for specific employees by entering their name or employee ID in the search bar and receive relevant search results.
	@Test(priority = 3)
	public static void searchEmployeeWithNameAndEmployeeId() throws InterruptedException {
		
		// 1. Click on "Employee List"
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		
		// 2. Enter employee name & id on respective fields, then click "Search" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys(recentlyAddedEmployeeFullName);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(recentlyAddedEmployeeId);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]")).click();
		Thread.sleep(3000);	
		
		// 3. Scroll at bottom to see the required employee
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement bottomLabelElement = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[3]/p[1]"));
		js.executeScript("arguments[0].scrollIntoView();", bottomLabelElement);
		Thread.sleep(3000);
		
	}
	
	// Positive Testing
	// 4. Verify that users can successfully update an existing employee's information with valid and complete data.
	@Test(priority = 4)
	public static void updateEmployeeByEmployeeId() throws InterruptedException {
		
		// 1. Set required fields to update employee
		String updateFirstName = "William";
		String updateMiddleName = "Axl";
		String updateLastName = "Rose";
		
		// 2. Click on edit icon
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[9]/div[1]/button[2]")).click();
		
		// 3. Update First Name
		WebElement firstNameInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]"));
		firstNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));  // Clear existing text
		firstNameInput.sendKeys(updateFirstName);

		// 4. Update Middle Name 
		WebElement middleNameInput = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/input[1]"));
		middleNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));  // Clear existing text
		middleNameInput.sendKeys(updateMiddleName);

		// 5. Update Last Name 
		WebElement lastNameInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]"));
		lastNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));  // Clear existing text
		lastNameInput.sendKeys(updateLastName);
		
		Thread.sleep(2000);
		
		// 6. Click on "Save" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[4]/button[1]")).click();
		recentlyAddedEmployeeFullName = updateFirstName + " " + updateMiddleName + " " + updateLastName;
		
		// 7. Wait until employee is updated to the server
		Thread.sleep(5000);
	}
	
	// Positive Testing
	// 5. Verify that users can successfully delete an existing employee from the system.
	@Test(priority = 5)
	public static void deleteEmployeeByEmployeeId() throws InterruptedException {
		
		// 1. Click on "Employee List" on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		
		// 2. Search for recently added employee by employee Id
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(recentlyAddedEmployeeId);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]")).click();
		Thread.sleep(1000);
		
		// 3. Click on delete icon
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[9]/div[1]/button[1]")).click();
		Thread.sleep(1000);
		
		// 4. Click on "Yes,Delete" confirmation pop up
		driver.findElement(By.xpath("//body/div[@id='app']/div[3]/div[1]/div[1]/div[1]/div[3]/button[2]")).click();
		
	}
	
	// Positive Testing
	// 6. Check if users can successfully update personal settings with valid data.
	@Test(priority = 6)
	public static void configureOptionalFieldPersonalSettings() throws InterruptedException {
		
		// 1. Add a new Employee
		addEmployeeWithValidData();
		
		// 2. Click on "Configuration" drop down list & select "Optional Fields" form the list
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[1]")).click();
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[2]/nav[1]/ul[1]/li[1]/ul[1]/li[1]")).click();
		
		// 3. Click on toggle button for Show Nick Name, Smoker and Military Service in Personal Details
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")).click();
		Thread.sleep(2000);
		
		// 4. Click on toggle button for Show SSN field in Personal Details
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")).click();
		Thread.sleep(2000);
		
		// 5. Click on toggle button for Show SIN field in Personal Details
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]")).click();
		Thread.sleep(2000);
		
		// 6. Click on "Save" button
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/button[1]")).click();
		
		// 7. Click on "Employee List" button & search for newly added employee with employee name & employeeId field
		searchEmployeeWithNameAndEmployeeId();
		
		// 8. Click on "Edit" icon to view recently added employee
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[9]/div[1]/button[2]")).click();
		Thread.sleep(2000);
		
		// 9. Verify that Nick Name, Smoker, Military Service, SSN & SIN are displayed using scroll function
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		WebElement ssnLabelElement = driver.findElement(By.xpath("//label[contains(text(),'SSN Number')]"));
		js.executeScript("arguments[0].scrollIntoView();", ssnLabelElement);
		Thread.sleep(3000);
		
	}
	
	// Negative Testing
	// 7. Check if users enter an invalid search query in the search bar (e.g., non-existent employee name or ID) and receive no search results.
	@Test(priority = 7)
	public static void searchUsingInvalidOrNonExistentEmployeeId() throws InterruptedException {
		
		// 1. Set invalid search EmployeeId
		String searchEmployeeId = ":;:;";
		
		// 2. Click on "Employee List" on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[2]")).click();
		
		// 3. Enter invalid EmployeeId on respective field
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(searchEmployeeId);
		
		// 4. Click on "Search" button
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]")).click();
		Thread.sleep(3000);

	}
	
	// Negative Testing
	// 8. Verify that users attempt to add an employee with missing required information (e.g., name, employee Id) and receive an error message indicating the missing fields.
	@Test(priority = 8)
	public static void addEmployeeWithMissingRequiredFields() {
		
		// 1. Click on "Add Employee" on top
		driver.findElement(By.xpath("//header/div[2]/nav[1]/ul[1]/li[3]")).click();
		
		// 2. Initialize required fields to blank
		String requiredFieldFirstName = "";
		String requiredFieldLastName = "";
		
		// 3. Set empty string to required fields
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(requiredFieldFirstName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(requiredFieldLastName);
		
		// 4. Click on "Save" button
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/button[2]")).click();
	}
	
	// Negative Testing
	// 9. Check if users can try to add an employee with a duplicate employee ID, and the system rejects the submission, displaying an error message indicating the ID is already in use.
	@Test(priority = 9)
	public static void addEmployeeWithDuplicateEmployeeId() throws InterruptedException {
		
		addEmployeeWithValidData();

		// 1. Click on "Add Employee" on upper section
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/header[1]/div[2]/nav[1]/ul[1]/li[3]")).click();
		
		// 2. Initialize required fields to add new employee
		String firstName = "Paul";
		String middleName = "";
		String lastName = "Stanly";
		
		// 3. Set required fields to add new employee
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")).sendKeys(firstName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/input[1]")).sendKeys(middleName);
		driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]")).sendKeys(lastName);
		WebElement employeeIdInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/input[1]"));
		employeeIdInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));  // Clear existing text
		employeeIdInput.sendKeys(recentlyAddedEmployeeId);
		
		// 4. Add profile picture for new employee
		WebElement photoInput = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
    	Thread.sleep(1000);
    	File uploadFile = new File("ProfilePicforOrangeHRM/PaulStanley.JPG");
    	photoInput.sendKeys(uploadFile.getAbsolutePath());
		
    	// 5. Click on "Save" button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/button[2]")).click();
		
	}
}
