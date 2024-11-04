package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ResetPasswordByAdmin_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageUsers = By.xpath("//span[normalize-space()='Manage Users']");
	By btn_threeDot = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[10]/div[7]/div[1]/span[1]/span[1]");
	By btn_resetPassword_loc = By.xpath("//a[normalize-space()='Reset Password']");
	By txt_newPass_loc = By.xpath("//input[@placeholder='New Password']");
	By txt_confirmPass_loc = By.xpath("//input[@placeholder='Confirm Password']");
	By btn_update_loc = By.xpath("//button[normalize-space()='Update']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	

	// Constructor
	public ResetPasswordByAdmin_Page(WebDriver driver) {
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

	// Method to select reset password
	public void selectResetPassword() {
		driver.findElement(btn_resetPassword_loc).click();
	}
	
	//Method to take new password
	public void setNewPassword(String New) {
        driver.findElement(txt_newPass_loc).sendKeys(New);
    }

	//Method to take confirm password
    public void setConfirmPassword(String confirm) {
        driver.findElement(txt_confirmPass_loc).sendKeys(confirm);
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

}
