package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HostSetting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By setting_Loc = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/*[name()='svg'][3]");
	By save_loc = By.xpath("//button[normalize-space()='Save']");

	// Constructor
	public HostSetting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Host Setting
	public void clickSetting() {
		driver.findElement(setting_Loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Save
	public void clickSave() {
		driver.findElement(save_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

}
