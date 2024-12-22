package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class JoinScheduleMeeting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By joinScheduledMeeting_loc = By.xpath("//span[contains(text(),'Join')]");
	By btn_Continue = By.xpath("//button[@id='startButton']");
	By btn_cross_loc = By.xpath("//div[@class='cnv-start-up-modal-left']");
	
	
	// Constructor
	public JoinScheduleMeeting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to join a scheduled meeting
	public void clickJoinSchedule() {
		driver.findElement(joinScheduledMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Continue
	public void clickContinue() {
		driver.findElement(btn_Continue).click();
	}

	// Method to click on cross button
	public void clickCross() {
		driver.findElement(btn_cross_loc).click();
	}
}
