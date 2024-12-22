package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UpcomingMeetingJoin_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By joinUpcomingMeeting_loc = By.xpath("//div[@class='cnv-jon-btn-small UpcomingMeetingCard_textAlign__3eQQQ']");
	By btn_Join = By.xpath("//button[@id='startButton']");
	By btn_cross_loc = By.xpath("//div[@class='cnv-start-up-modal-left']");
	
	
	// Constructor
	public UpcomingMeetingJoin_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to join in an upcoming meeting
	public void clickJoinUpcoming() {
		driver.findElement(joinUpcomingMeeting_loc).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click on Continue
	public void clickStart() {
		driver.findElement(btn_Join).click();
	}

	// Method to click on cross button
	public void clickCross() {
		driver.findElement(btn_cross_loc).click();
	}

}
