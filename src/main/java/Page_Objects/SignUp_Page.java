package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUp_Page {

	WebDriver driver;

	// Constructors
	public SignUp_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By txt_signupmail_loc = By.name("signUpMail");
	By btn_signup_loc = By.name("sign_up_submit");

	// Action methods

	// Method to input username
	public void setUserName(String user) {
		driver.findElement(txt_signupmail_loc).sendKeys(user);
	}

	// Method to click login
	public void clickLogin() {
		driver.findElement(btn_signup_loc).click();
	}

}