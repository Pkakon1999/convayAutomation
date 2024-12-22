package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StartScheduleMeeting_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By sceduler_loc = By.xpath("//div[contains(@class,'cnv-open-box')]");
	By startTime_loc = By.xpath("//div[@class='cnv-form-group-col-2-select']//div[1]//div[1]//div[1]//div[1]//*[name()='svg']");
	By selectStartTime_loc = By.xpath("//div[@class='cnv-col-xl-1 cnv-col-lg-1 cnv-col-md-1 cnv-mt-1 ScheduleCreateTimezone_marginAlignment__2XguW']");
	By save_loc = By.xpath("//button[@id='save-schedule']");
	By cancel_loc = By.xpath("//button[@class='cnv-btn cnv-btn-cancel-new cnv-mr-3']");
	By ok_loc = By.xpath("//button[normalize-space()='Ok']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By startScheduledMeeting_loc = By.xpath("//span[contains(@class,'UpcomingPrevious_buttonHover__tbVyE')]");
	By btn_Start = By.xpath("//button[@id='startButton']");
	By btn_cross_loc = By.xpath("//div[@class='cnv-start-up-modal-left']");
	
	
	// Constructor
	public StartScheduleMeeting_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Open Sceduler
	public void clickScheduler() {
		driver.findElement(sceduler_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public WebElement getCardContainer() {
		// Locate the scrollable container element within the card
		return driver.findElement(By.xpath("//body/div[@id='root']/div[@class='cnv-wrapper cnv-theme-light']/div[@class='cnv-content-area']/div[@class='cnv-content']/div[@id='portalForModal']/div[@class='cnv-container-fluid Scheduler_main__F23x0']/div[@class='Scheduler_scheduleCreate__2kizM']/div[@class='cnv-container']/div[@class='cnv-card schedule-create-custom']/div[@class='cnv-card-body']/div[@id='taber1']/div[@id='event-details']/div/div[1]"));
	}

	// Method to click on start time to schedule a meeting
	public void clickStartTime() {
		driver.findElement(startTime_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to select a start time
	public void selectStart() {
		driver.findElement(selectStartTime_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to save the schedule meeting
	public void selectSave() {
		driver.findElement(save_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on cancel button
	public void selectCancel() {
		driver.findElement(cancel_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on OK button
	public void selectOK() {
		driver.findElement(ok_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Meeting Management
	public void clickMeetingManagement() {
		driver.findElement(meetingManagement_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to start a scheduled meeting
	public void clickStartSchedule() {
		driver.findElement(startScheduledMeeting_loc).click();
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

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}
}
