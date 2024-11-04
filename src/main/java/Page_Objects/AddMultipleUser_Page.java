package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddMultipleUser_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_MultipleUser = By.xpath("//button[normalize-space()='Add Multiple User']");
	By btn_Download = By.xpath("//button[normalize-space()='Download']");
	By btn_Upload = By.xpath("//button[normalize-space()='Upload']");
	By btn_BrowseFile = By.xpath("//span[@class='multipleUserFileUpload']");
	By btn_Confirm = By.xpath("//button[normalize-space()='Confirm']");
	By btn_UploadAgain = By.xpath("//button[normalize-space()='Upload Again']");
	By btn_SendInvitation = By.xpath("//button[normalize-space()='Send Invitations']");
	By failedMessage = By.xpath("//p[normalize-space()='File upload failed']");
	By successMessage = By.xpath("//h3[normalize-space()='Successfully Uploaded']");

	// Constructor
	public AddMultipleUser_Page(WebDriver driver) {
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

	// Method to click Manage Users
	public void clickAddMultipleUser1() {
		driver.findElement(btn_MultipleUser).click();
	}

	// Method to click Download button
	public void clickDownload() {
		driver.findElement(btn_Download).click();
	}

	// Method to click Upload button
	public void clickUpload() {
		driver.findElement(btn_Upload).click();
	}

	// Method to upload file by providing the file path
	public void uploadFile(String filePath) {
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(filePath);
	}

	// Method to click Browse File button
	public void clickBrowseFile() {
		driver.findElement(btn_BrowseFile).click();
	}

	// Method to click Confirm button
	public void clickConfirm() {
		driver.findElement(btn_Confirm).click();
	}

	// Method to click Upload Again button
	public void clickUploadAgain() {
		driver.findElement(btn_UploadAgain).click();
	}

	// Method to click Send Invitation button
	public void clickSendInvitation() {
		driver.findElement(btn_SendInvitation).click();
	}

	// Method to get the failed message text
	public String getFailedMessage() {
		return driver.findElement(failedMessage).getText();
	}

	// Method to get the failed message text
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
