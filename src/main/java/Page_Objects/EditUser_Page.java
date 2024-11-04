package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EditUser_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_threeDot = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[7]/div[1]/span[1]/span[1]");
	By btn_editUser_loc = By.xpath("//a[normalize-space()='Edit User']");
	By txt_FirstName_loc = By.name("firstName");
	By txt_LastName_loc = By.name("lastName");
	By txt_JobTitle_loc = By.name("jobTitle");
	By roleDropdown1 = By.xpath("//div[@class='cnv-select-with-label']//*[name()='svg']");
	By roleDropdown2 = By.xpath("//li[normalize-space()='Admin']");
	By roleDropdown3 = By.xpath("//li[normalize-space()='Owner']");
	By btn_update_loc = By.xpath("//input[@value='Update']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Constructor
	public EditUser_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

	// Method to Select 3 dot
	public void select3dot() {
		driver.findElement(btn_threeDot).click();
	}

	// Method to select edit user
	public void selectEditUser() {
		driver.findElement(btn_editUser_loc).click();
	}

	// Method to enter First Name
	public void enterFirstName(String firstName) {
		driver.findElement(txt_FirstName_loc).sendKeys(firstName);
	}

	// Method to enter Last Name
	public void enterLastName(String lastName) {
		driver.findElement(txt_LastName_loc).sendKeys(lastName);
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

	// Method to click on update button
	public void clickUpdate() {
		driver.findElement(btn_update_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

	// Method to clear the first name textbox
	public void clearFirstName() {
		WebElement firstNameField = driver.findElement(txt_FirstName_loc);
		firstNameField.clear(); // Clear the text in the textbox
	}

	// Method to clear the last name textbox
	public void clearLastName() {
		WebElement lastNameField = driver.findElement(txt_LastName_loc);
		lastNameField.clear(); // Clear the text in the textbox
	}

	// Method to clear the job title textbox
	public void clearJobTitle() {
		WebElement jobTitle = driver.findElement(txt_JobTitle_loc);
		jobTitle.clear(); // Clear the text in the textbox
	}

}
