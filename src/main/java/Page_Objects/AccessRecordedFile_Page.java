package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccessRecordedFile_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By selectPreviousMeeting_loc = By.xpath("//a[normalize-space()='Previous Meeting']");
	By selectMeeting_loc = By.xpath("//div[@class='cnv-meeting-right-area cnv-ml-3']//div//div[@class='cnv-tab-container']");
	By recordings_loc = By.xpath("//a[normalize-space()='Recordings']");
	By copy_loc = By.xpath("//div[@class='Recording_recordLowerAction__20n1E']//div[1]");
	By download_loc = By.xpath("//div[@class='Recording_recordLowerAction__20n1E']//div[2]");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	
	// Constructor
	public AccessRecordedFile_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Previous Meeting
	public void clickPreviousMeeting() {
		driver.findElement(selectPreviousMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to select a scheduled meeting to edit
	public void selectMeeting() {
		driver.findElement(selectMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Recordings tab
	public void clickRecordings() {
		driver.findElement(recordings_loc).click();
	}

	// Method to click on copy button
	public void clickCopy() {
		driver.findElement(copy_loc).click();
	}

	// Method to click on Download button
	public void clickDownload() {
		driver.findElement(download_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

}
