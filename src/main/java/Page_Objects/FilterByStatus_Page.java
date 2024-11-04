package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterByStatus_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By StatusDropdown_loc = By.xpath("//div[@class='UnitType_userOptionPanel__3JDe3']//div[2]//div[1]//*[name()='svg']");
	By SelectActive_loc = By.xpath("//label[normalize-space()='Active']");
	By SelectInvited_loc = By.xpath("//label[normalize-space()='Invited']");
	By SelectSuspended_loc = By.xpath("//label[normalize-space()='Suspended']");

	// Constructor
	public FilterByStatus_Page(WebDriver driver) {
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

	// Method to Select Owner
	public void selectActive() {
		driver.findElement(SelectActive_loc).click();
	}

	// Method to select Admin
	public void selectInvited() {
		driver.findElement(SelectInvited_loc).click();
	}

	// Method to select User
	public void selectSuspended() {
		driver.findElement(SelectSuspended_loc).click();
	}

}
