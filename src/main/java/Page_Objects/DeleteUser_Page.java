package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DeleteUser_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_threeDot = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[7]/div[1]/span[1]/span[1]");
	By btn_deleteUser_loc = By.xpath("//a[normalize-space()='Delete']");
	By btn_confirmDeletion_loc = By.xpath("//button[normalize-space()='Confirm Deletion']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	By errorMessage_loc = By.xpath("//span[@class='errorNoteBoth']");
	

	// Constructor
	public DeleteUser_Page(WebDriver driver) {
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

	// Method to select delete
	public void selectDelete() {
		driver.findElement(btn_deleteUser_loc).click();
	}

	// Method to select confirm deletion
	public void selectConfirmDeletion() {
		driver.findElement(btn_confirmDeletion_loc).click();
	}

	// Method to validate error message text
	public String getMessageText(String expectedText) {
		WebElement messageElement = driver.findElement(errorMessage_loc);
		return messageElement.getText();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
