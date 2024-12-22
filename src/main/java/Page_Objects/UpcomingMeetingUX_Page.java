package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class UpcomingMeetingUX_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By upcomingMeetingField_loc = By.xpath("//div[@class='upcoming-meeting-title']");
	By upcomingMeetingDate_loc = By.xpath("//p[@class='CardManagement_date__1e9Y2']");
	By btn_upcomingMeetingStart = By.xpath("//div[@class='cnv-jon-btn-small UpcomingMeetingCard_textAlign__3eQQQ']");

	// Constructor
	public UpcomingMeetingUX_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the Upcoming meeting field
	public String getUpcomingMeetingField() {
		return driver.findElement(upcomingMeetingField_loc).getAttribute("value");
	}

	// Method to get the Upcoming meeting date
	public String getUpcomingMeetingDate() {
		return driver.findElement(upcomingMeetingDate_loc).getAttribute("value");
	}

	// Method to get the text of "Start" button
	public String getButtonText() {
		WebElement buttonElement = driver.findElement(btn_upcomingMeetingStart);
		return buttonElement.getText();
	}

	// Method to validate "Start" button text
	public void validateButtonText(String expectedText) {
		String actualText = getButtonText();
		Assert.assertEquals(actualText, expectedText, "Start");
	}
}
