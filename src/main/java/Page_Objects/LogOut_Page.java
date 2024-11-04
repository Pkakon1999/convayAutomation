package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogOut_Page {

	WebDriver driver;

	// Constructors
	public LogOut_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By dropDownByName = By.xpath("//span[@class='user-name']");
	By dropDownByArrow = By.xpath("//span[@class='user-name']//*[name()='svg']");
	// By dropDownByLogo = By.className("cnv-profile-icon cnv-link");
	By btn_logout_loc = By.xpath("//a[normalize-space()='Logout']");

	public String getButtonText() {
		WebElement buttonLogout = driver.findElement(btn_logout_loc);
		return buttonLogout.getText();
	}

	// Action methods
	public void clickdropDown1() {
		driver.findElement(dropDownByName).click();
	}

	public void clickdropDown2() {
		driver.findElement(dropDownByArrow).click();
	}

	/*
	 * public void clickdropDown3() { driver.findElement(dropDownByLogo).click(); }
	 */

	public void clickLogout() {
		driver.findElement(btn_logout_loc).click();
	}

}
