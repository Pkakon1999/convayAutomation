package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ValidateWeekView_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By sceduler_loc = By.xpath("//div[contains(@class,'cnv-open-box')]");
	By cancel_loc = By.xpath("//button[@id='cancel-schedule']");
	By week_loc = By.xpath("//button[normalize-space()='Week']");
	By weekView_loc = By.xpath("//button[normalize-space()='Week']");
	
	
	// Constructor
	public ValidateWeekView_Page(WebDriver driver) {
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

	// Method to click on cancel button
	public void clickCancel() {
		driver.findElement(cancel_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Week button
	public void selectWeek() {
		driver.findElement(week_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	 // Method to get the text of Week button
    public String getWeekButtonText() {
        WebElement weekElement = driver.findElement(weekView_loc);
        return weekElement.getText();
    }

    // Method to validate Week button text
    public void validateWeekButtonText(String expectedText) {
        String actualText = getWeekButtonText();
        Assert.assertEquals(actualText, expectedText, "Button text is incorrect!");
    }
}
