package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JoinInstantMeeting_Page {

	WebDriver driver;

	// Constructors
	public JoinInstantMeeting_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By btn_JoinMeeting_loc = By.xpath("//div[contains(@class,'cnv-btn-jon Join_joinButton__1eAPJ')]");
	By txt_meetingID_loc = By.xpath("//input[@placeholder='Enter meeting id or personal meeting link...']");
	By txt_meetingLink_loc = By.xpath("//input[@placeholder='Enter meeting id or personal meeting link...']");
	By btn_continue_loc = By.xpath("//button[@id='startButton']");

	// Action methods

	// Method to Join A Meeting from landing page
	public void clickJoinMeeting() {
		driver.findElement(btn_JoinMeeting_loc).click();
	}

	// Method to input meeting ID
	public void setMeetingID(String ID) {
		driver.findElement(txt_meetingID_loc).sendKeys(ID);
	}

	// Method to input meeting Link
	public void setMeetingLink(String Link) {
		driver.findElement(txt_meetingLink_loc).sendKeys(Link);
	}

	// Method to click Continue
	public void clickContinue() {
		driver.findElement(btn_continue_loc).click();
	}

}