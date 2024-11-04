package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Landing_Page {

	WebDriver driver;

	// Constructors
	public Landing_Page(WebDriver driver) 
	{
		this.driver = driver;
	}

	// Locators Landing Page
	
	By btn_signIn = By.xpath("//a[normalize-space()='Sign In']");
	



	public void clickLogin() {
		driver.findElement(btn_signIn).click();
	}

}
