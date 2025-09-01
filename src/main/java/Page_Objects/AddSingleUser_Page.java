package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddSingleUser_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_AddSingleUser = By.xpath("//button[normalize-space()='Add Single User']");
	By txt_FirstName_loc = By.name("firstName");
	By txt_LastName_loc = By.name("lastName");
	By txt_Email_loc = By.name("email");
	By txt_JobTitle_loc = By.name("jobTitle");
	By roleDropdown1 = By.xpath("//div[@class='cnv-select-with-label']//*[name()='svg']");
	By roleDropdown2 = By.xpath("//li[normalize-space()='Admin']");
	By roleDropdown3 = By.xpath("//li[normalize-space()='Owner']");
	By btn_AddUser = By.xpath("//span[normalize-space()='Add User']");
	By errorMessage = By.xpath("//p[normalize-space()='User already exists']");
	By successMessage = By.xpath("//h2[normalize-space()='Invitations Sent']");

	// Constructor
	public AddSingleUser_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
	}

	// Method to click Admin dropdown
	public void clickAdminDropdown() {
		driver.findElement(adminDropdown).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Manage Users
	public void clickManageUsers() {
		driver.findElement(btn_ManageUsers).click();
	}

	// Method to click Add Single User
	public void clickAddSingleUser() {
		driver.findElement(btn_AddSingleUser).click();
	}

	// Method to enter First Name
	public void enterFirstName(String firstName) {
		driver.findElement(txt_FirstName_loc).sendKeys(firstName);
	}

	// Method to enter Last Name
	public void enterLastName(String lastName) {
		driver.findElement(txt_LastName_loc).sendKeys(lastName);
	}

	// Method to enter Email
	public void enterEmail(String email) {
		driver.findElement(txt_Email_loc).sendKeys(email);
	}

	// Method to enter Job Title
	public void enterJobTitle(String jobTitle) {
		driver.findElement(txt_JobTitle_loc).sendKeys(jobTitle);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void selectUserRole() {
		driver.findElement(roleDropdown1).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void selectUserRole2() {
		driver.findElement(roleDropdown2).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void selectUserRole3() {
		driver.findElement(roleDropdown3).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Add User button
	public void clickAddUser() {
		driver.findElement(btn_AddUser).click();
	}

	// Method to get the error message text
	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

	// Method to get the success message text
	public String getSuccessMessage() {
		return driver.findElement(successMessage).getText();
	}

	// Utility method to wait until element is clickable
	public void waitUntilClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Utility method to wait until element is visible
	public void waitUntilVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
