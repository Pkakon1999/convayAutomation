package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationLogo_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Constructors
	public OrganizationLogo_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageOrganization_loc = By.xpath("//span[normalize-space()='Manage Organization']");
	By btn_change_loc = By.xpath("//div[normalize-space()='Change']");
	By file_input_loc = By.xpath("//input[@type='file']");
	By btn_remove_loc = By.xpath("//div[normalize-space()='Remove']");
	By btn_cancel_loc = By.xpath("//button[normalize-space()='Cancel']");
	By btn_add_loc = By.xpath("//button[normalize-space()='Add']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	By toaster_Message2 = By.xpath("//div[@class='cnv-toast-body']");

	// Action methods

	public void clickdropDown() {
		driver.findElement(adminDropdown).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Manage Organization
	public void clickManageOrganization() {
		driver.findElement(btn_ManageOrganization_loc).click();
	}

	public void clickChange() {
		driver.findElement(btn_change_loc).click();
	}

	public void clickRemove() {
		driver.findElement(btn_remove_loc).click();
	}

	public void clickCancel() {
		driver.findElement(btn_cancel_loc).click();
	}

	public void clickAdd() {
		driver.findElement(btn_add_loc).click();
	}

	public void uploadProfilePicture(String profilePicturePath) {
		WebElement fileInput = driver.findElement(file_input_loc);
		fileInput.sendKeys(profilePicturePath); // Send the file path to the input element
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}
	
	public String getToasterValue2() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message2));
		return toasterMessageElement.getText();
	}
}
