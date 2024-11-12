package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StartInstantMeeting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By btn_startInstantMeeting = By.xpath("//button[normalize-space()='Start Now']");
	By btn_Start = By.xpath("//button[@id='startButton']");
	By btn_cross_loc = By.xpath("//div[@class='cnv-start-up-modal-left']");

	// Constructor
	public StartInstantMeeting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Start Instant Meeting
	public void clickInstantMeeting() {
		driver.findElement(btn_startInstantMeeting).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Start
	public void clickStart() {
		driver.findElement(btn_Start).click();
	}

	// Method to click on cross button
	public void clickCross() {
		driver.findElement(btn_cross_loc).click();
	}

}
