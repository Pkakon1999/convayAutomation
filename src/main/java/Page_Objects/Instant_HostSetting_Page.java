package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Instant_HostSetting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By startInstant_loc = By.xpath("//button[normalize-space()='Start Now']");
	By hostControl_loc = By.xpath("//a[@id='change-host-controls']//*[name()='svg']");
	By start_loc = By.xpath("//button[@id='startButton']");

	// Constructor
	public Instant_HostSetting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Start Now button
	public void clickSTartInstant() {
		driver.findElement(startInstant_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getCardContainer() {
		// Locate the scrollable container element within the card
		return driver.findElement(By.xpath("//div[@class='HostControl_tab3__1DzJs']"));
	}

	// Method to click Host Control
	public void clickHostControl() {
		driver.findElement(hostControl_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Start
	public void clickStart() {
		driver.findElement(start_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

}
