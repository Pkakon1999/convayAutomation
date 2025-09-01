package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddParticipants_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By sceduler_loc = By.xpath("//div[contains(@class,'cnv-open-box')]");
	By eventDetails_loc = By.xpath("//a[normalize-space()='Event Details']");
	By cancel_loc = By.xpath("//button[@id='cancel-schedule']");

	By addParticipant_loc = By.xpath("//a[normalize-space()='Add Participants']");
	By add_loc = By.xpath("//input[@placeholder='Add participant or team']");
	By Select_loc = By.xpath("//div[@class='ParticipantSuggestionItem_main__3U6GB']//span//span[contains(text(),'Abdulllah Al')]");

	// Constructor
	public AddParticipants_Page(WebDriver driver) {
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

	// Method to click Event Details
	public void clickEventDetails() {
		driver.findElement(eventDetails_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Add Participant
	public void clickAddParticipant() {
		driver.findElement(addParticipant_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on add
	public void clickAdd() {
		driver.findElement(add_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to Select participant
	public void clickSelect() {
		driver.findElement(Select_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Cancel
	public void clickCancel() {
		driver.findElement(cancel_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

}
