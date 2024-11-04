package Page_Objects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Page {

	WebDriver driver;

	// Constructors
	public Login_Page(WebDriver driver) 
	{
		this.driver = driver;
	}

	// Locators
	
	By txt_username_loc = By.name("signInMail");
	By txt_password_loc = By.name("password");
	By btn_login_loc = By.name("sign_up_submit");


	// Action methods
	public void setUserName(String user) {
		driver.findElement(txt_username_loc).sendKeys(user);
	}
	
	

	public void setPassword(String pwd) {
		driver.findElement(txt_password_loc).sendKeys(pwd);
	}

	public void clickLogin() {
		driver.findElement(btn_login_loc).click();
	}


}
