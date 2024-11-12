package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogUXValidation_Page {

	WebDriver driver;

	// Constructors
	public BlogUXValidation_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By btn_blog_loc = By.xpath("//a[contains(text(),'Blog')]");
	By btn_acceptCookies_loc = By.xpath("//button[@data-cky-tag='accept-button']");

	// Action methods

	// Method to click on Blog button
	public void clickBlog() {
		driver.findElement(btn_blog_loc).click();
	}

	// Method to click on Accept Cookie button
	public void clickAccept() {
		driver.findElement(btn_acceptCookies_loc).click();
	}

}
