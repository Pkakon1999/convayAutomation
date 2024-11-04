package Page_Objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgetPassword_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Constructors
	public ForgetPassword_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators

	By btn_forgetPassword_loc = By.xpath("//span[@class='forgetPassword']");
	By txt_email_loc = By.xpath("//input[@placeholder='name@work-email.com']");
	By btn_sendVerificationRequest_loc = By.xpath("//input[@name='sign_up_submit']");
	By txt_required_loc = By.xpath("//span[@class='cnv-element-left']");
	By txt_verify_loc = By.xpath("//h1[normalize-space()='Verify Email']");
	By btn_resendEmail_loc = By.xpath("//button[normalize-space()='Resend Link']");
	By txt_newPassword_loc = By.xpath("//input[@placeholder='Enter new password']");
	By txt_confirmPassword_loc = By.xpath("//input[@placeholder='Confirm password']");
	By btn_completeReset_loc = By.xpath("//input[@name='sign_up_submit']");

	// Action methods
	public void clickForgetPassword() {
		driver.findElement(btn_forgetPassword_loc).click();
	}

	public void setEmail(String mail) {
		driver.findElement(txt_email_loc).sendKeys(mail);
	}

	public void clickSend() {
		driver.findElement(btn_sendVerificationRequest_loc).click();
	}

	public String getRequiredMessage() {
		WebElement requiredMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(txt_required_loc));
		return requiredMessageElement.getText();
	}
	
	public String getVerifyEmail() {
		WebElement verifyEmailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(txt_verify_loc));
		return verifyEmailElement.getText();
	}
	
	public void clickResend() {
		driver.findElement(btn_resendEmail_loc).click();
	}
	
	public void setNewPassword(String newPass) {
		driver.findElement(txt_newPassword_loc).sendKeys(newPass);
	}
	
	public void setConfirmPassword(String confirmPass) {
		driver.findElement(txt_confirmPassword_loc).sendKeys(confirmPass);
	}
	
	public void clickCompleteReset() {
		driver.findElement(btn_completeReset_loc).click();
	}
}
