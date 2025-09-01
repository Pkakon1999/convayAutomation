package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BulkAction_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_Status = By.xpath("//div[@class='UnitType_userOptionPanel__3JDe3']//div[2]//div[1]//*[name()='svg']");
	By select_Active = By.xpath("//label[normalize-space()='Active']");
	By select_Suspened = By.xpath("//label[normalize-space()='Suspened']");
	By select_Invited = By.xpath("//label[normalize-space()='Invited']");
	
	By row1_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]");
	By row2_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]");
	By check_active1 = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]");
	By check_active2 = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]");
	By btn_close_loc = By.xpath("//li[@class='multi-action-close']//*[name()='svg']");
	By btn_bulkSuspend_loc = By.xpath("//li[normalize-space()='Suspend Users']");
	By btn_bulkSuspendNo_loc = By.xpath("//button[normalize-space()='No']");
	By btn_bulkSuspendYes_loc = By.xpath("//button[normalize-space()='Yes']");
	By btn_bulkActive_loc = By.xpath("//li[normalize-space()='Active Users']");
	By btn_bulkActiveYes_loc = By.xpath("//button[normalize-space()='Yes']");
	
	By row1_ResendUser1_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]");
	By row2_ResendUser2_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]");
	By check_resend1_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]");
	By check_resend2_loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/input[1]");
	By btn_bulkResend_loc = By.xpath("//li[normalize-space()='Resend Invitations']");
	By btn_bulkResendYes_loc = By.xpath("//button[normalize-space()='Yes']");
	
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Constructor
	public BulkAction_Page(WebDriver driver) {
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

	// Method to click on Status
	public void clickStatus() {
		driver.findElement(btn_Status).click();
	}

	// Method to Select Active status
	public void selectActive() {
		driver.findElement(select_Active).click();
	}

	// Method to Select Suspened status
	public void selectSuspened() {
		driver.findElement(select_Suspened).click();
	}

	// Method to Select Invited status
	public void selectInvited() {
		driver.findElement(select_Invited).click();
	}

	// Method to hover over the row and make the checkbox visible
	public void hoverOverRow1() {
		WebElement rowElement = driver.findElement(row1_loc);
		Actions actions = new Actions(driver);
		actions.moveToElement(rowElement).perform(); // Hover over the row
	}

	// Method to hover over the row and make the checkbox visible
	public void hoverOverRow2() {
		WebElement rowElement = driver.findElement(row2_loc);
		Actions actions = new Actions(driver);
		actions.moveToElement(rowElement).perform(); // Hover over the row
	}

	// Method to Select the checkbox of an user
	public void select1stUser() {
		driver.findElement(check_active1).click();
	}

	// Method to Select the checkbox of an user
	public void select2ndUser() {
		driver.findElement(check_active2).click();
	}

	// Method to click on close icon
	public void selectCloseIcon() {
		driver.findElement(btn_close_loc).click();
	}

	// Method to click on suspend
	public void clickBulkSuspend() {
		driver.findElement(btn_bulkSuspend_loc).click();
	}

	// Method to click on No
	public void clickSuspendNo() {
		driver.findElement(btn_bulkSuspendNo_loc).click();
	}

	// Method to click on Yes
	public void clickBulkSuspendYes() {
		driver.findElement(btn_bulkSuspendYes_loc).click();
	}

	// Method to click on Active
	public void clickActive() {
		driver.findElement(btn_bulkActive_loc).click();
	}

	// Method to click on Yes
	public void clickActiveYes() {
		driver.findElement(btn_bulkActiveYes_loc).click();
	}

	// Method to hover over the row and make the checkbox visible
	public void hoverOverInvitedRow1() {
		WebElement rowElement = driver.findElement(row1_ResendUser1_loc);
		Actions actions = new Actions(driver);
		actions.moveToElement(rowElement).perform(); // Hover over the row
	}

	// Method to hover over the row and make the checkbox visible
	public void hoverOverInvitedRow2() {
		WebElement rowElement = driver.findElement(row2_ResendUser2_loc);
		Actions actions = new Actions(driver);
		actions.moveToElement(rowElement).perform(); // Hover over the row
	}

	// Method to Select the checkbox of an user
	public void select1stUserResend() {
		driver.findElement(check_resend1_loc).click();
	}

	// Method to Select the checkbox of an user
	public void select2ndUserResend() {
		driver.findElement(check_resend2_loc).click();
	}

	// Method to click on Resend
	public void clickResend() {
		driver.findElement(btn_bulkResend_loc).click();
	}

	// Method to click on Yes
	public void clickBulkResendYes() {
		driver.findElement(btn_bulkResendYes_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
