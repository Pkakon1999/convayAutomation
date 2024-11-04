package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterByRole_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By RoleDropdown_loc = By.xpath("//div[@class='UnitType_filterDropdown__26sIS']//div[1]//div[1]//*[name()='svg']");
	By SelectOwner_loc = By.xpath("//label[normalize-space()='Owner']");
	By SelectAdmin_loc = By.xpath("//label[normalize-space()='Admin']");
	By SelectUser_loc = By.xpath("//label[normalize-space()='User']");

	// Constructor
	public FilterByRole_Page(WebDriver driver) {
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

	// Method to Role dropdown
	public void roleDropdown() {
		driver.findElement(RoleDropdown_loc).click();
	}

	// Method to Select Owner
	public void selectOwner() {
		driver.findElement(SelectOwner_loc).click();
	}

	// Method to select Admin
	public void selectAdmin() {
		driver.findElement(SelectAdmin_loc).click();
	}

	// Method to select User
	public void selectUser() {
		driver.findElement(SelectUser_loc).click();
	}

}
