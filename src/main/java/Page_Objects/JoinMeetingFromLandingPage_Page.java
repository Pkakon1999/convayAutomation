package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinMeetingFromLandingPage_Page {

	WebDriver driver;

	// Constructors
	public JoinMeetingFromLandingPage_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By btn_JoinMeeting_loc = By.xpath("//a[normalize-space()='Join a Meeting']");
	By txt_meetingID_loc = By.xpath("//input[@placeholder='Enter meeting id or personal meeting link...']");
	By txt_name_loc = By.xpath("//input[@placeholder='Enter your name']");
	By btn_continue_loc = By.xpath("//button[@id='startButton']");

	// Action methods

	// Method to Join A Meeting from landing page
	public void clickJoinMeeting() {
		driver.findElement(btn_JoinMeeting_loc).click();
	}

	// Method to input meeting ID or link
	public void setMeetingLink(String link) {
		driver.findElement(txt_meetingID_loc).sendKeys(link);
	}

	// Method to input name
	public void setUserName(String name) {
		driver.findElement(txt_name_loc).sendKeys(name);
	}

	// Method to click Continue
	public void clickContinue() {
		driver.findElement(btn_continue_loc).click();
	}

}