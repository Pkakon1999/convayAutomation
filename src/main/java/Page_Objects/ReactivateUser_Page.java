package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ReactivateUser_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By StatusDropdown_loc = By.xpath("//div[@class='UnitType_userOptionPanel__3JDe3']//div[2]//div[1]//*[name()='svg']");
	By SelectActive_loc = By.xpath("//label[normalize-space()='Active']");
	By SelectSuspended_loc = By.xpath("//label[normalize-space()='Suspened']");
	By btn_threeDot = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[7]/div[1]/span[1]/span[1]");
	By btn_ReactivateUser_loc = By.xpath("//div[@class='cnv-content']//li[1]");
	By btn_confirmActivation_loc = By.xpath("//button[normalize-space()='Confirm Activation']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	

	// Constructor
	public ReactivateUser_Page(WebDriver driver) {
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

	// Method to Status dropdown
	public void roleDropdown() {
		driver.findElement(StatusDropdown_loc).click();
	}

	// Method to filter Active user
	public void selectActive() {
		driver.findElement(SelectActive_loc).click();
	}

	// Method to filter suspended User
	public void selectSuspended() {
		driver.findElement(SelectSuspended_loc).click();
	}

	// Method to Select 3 dot
	public void select3dot() {
		driver.findElement(btn_threeDot).click();
	}

	// Method to select Activate
	public void selectReactivate() {
		driver.findElement(btn_ReactivateUser_loc).click();
	}

	// Method to confirm activation
	public void selectConfirmActivation() {
		driver.findElement(btn_confirmActivation_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
