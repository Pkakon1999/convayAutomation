package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ScheduleMeetingUXValidation_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By sceduler_loc = By.xpath("//div[contains(@class,'cnv-open-box')]");
	By startTime_loc = By.xpath("//div[@class='cnv-form-group-col-2-select']//div[1]//div[1]//div[1]//div[1]//*[name()='svg']");
	By selectStartTime_loc = By.xpath("//div[@class='rbc-events-container']");
	By save_loc = By.xpath("//button[@id='save-schedule']");
	By ok_loc = By.xpath("//button[normalize-space()='Ok']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	By meetingManagement_loc = By.xpath("//span[normalize-space()='Meeting Management']");
	By meetingTitle_loc = By.xpath("//div[@class='cnv-text-intro cnv-txt-16 cnv-mt-2']");
	By startButton_loc = By.xpath("//span[contains(@class,'UpcomingPrevious_buttonHover__tbVyE')]");
	
	
	// Constructor
	public ScheduleMeetingUXValidation_Page(WebDriver driver) {
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
		return driver.findElement(By.xpath("//nav[@class='cnv-top-nav Header_main__2JQMc']"));
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

	 // Method to get the text of Meeting Title
    public String getMeetingTitleText() {
        WebElement meetingTitleElement = driver.findElement(meetingTitle_loc);
        return meetingTitleElement.getText();
    }

    // Method to validate Meeting Title text
    public void validateMeetingTitleText(String expectedText) {
        String actualText = getMeetingTitleText();
        Assert.assertEquals(actualText, expectedText, "Meeting title text does not match!");
    }

    // Method to get the text of Start button
    public String getStartButtonText() {
        WebElement meetingTimeElement = driver.findElement(startButton_loc);
        return meetingTimeElement.getText();
    }

    // Method to validate Start button text
    public void validateStartButtonText(String expectedText) {
        String actualText = getStartButtonText();
        Assert.assertEquals(actualText, expectedText, "Meeting time text does not match!");
    }

    // Example usage of validation methods
    public void validateMeetingDetails(String expectedTitle, String expectedText) {
        validateMeetingTitleText(expectedTitle);
        validateStartButtonText(expectedText);
    }

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}
}
