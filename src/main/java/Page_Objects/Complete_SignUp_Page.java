package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Complete_SignUp_Page {

	WebDriver driver;

	// Constructors
	public Complete_SignUp_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By txt_FirstName_loc = By.name("firstName");
	By txt_LastName_loc = By.name("lastName");
	By txt_password_loc = By.name("newPassword");
	By btn_Complete_SignUp = By.name("sign_up_submit");

	
	public String getButtonText() {
		WebElement buttonCmptlSignup = driver.findElement(btn_Complete_SignUp);
		return buttonCmptlSignup.getText();
	}
	
	// Action methods
	public void setFirstName(String user) {
		driver.findElement(txt_FirstName_loc).sendKeys(user);
	}

	public void setLastName(String user) {
		driver.findElement(txt_LastName_loc).sendKeys(user);
	}

	public void setPassword(String pwd) {
		driver.findElement(txt_password_loc).sendKeys(pwd);
	}

	public void clickLogin() {
		driver.findElement(btn_Complete_SignUp).click();
	}

}
