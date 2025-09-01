package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UpcomingMeetingDetails_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By btn_UpcomingMeeting = By.xpath("//a[normalize-space()='Upcoming Meetings']");
	By meetingHostField_loc = By.xpath("//div[@class='cnv-text-host cnv-mt-1']");
	By meetingIDField_loc = By.xpath("//div[@class='cnv-text-host cnv-mt-2']");
	By meetingURLField_loc = By.xpath("//div[@class='cnv-scroll-400-agenda cnv-scroll-400-small']//div[3]");

	// Constructor
	public UpcomingMeetingDetails_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on upcoming meeting
	public void clickUpcomingMeetings() {
		driver.findElement(btn_UpcomingMeeting).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to get the meeting Host name of the Upcoming meeting field
	public String getHost() {
		return driver.findElement(meetingIDField_loc).getAttribute("value");
	}

	// Method to get the meeting ID of the Upcoming meeting field
	public String getID() {
		return driver.findElement(meetingIDField_loc).getAttribute("value");
	}

	// Method to get the meeting URL of the Upcoming meeting field
	public String getURL() {
		return driver.findElement(meetingURLField_loc).getAttribute("value");
	}
}
