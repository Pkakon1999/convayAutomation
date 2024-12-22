package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccessInvitationUX_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Define locators
	By meetingTitle_loc = By.xpath("//div[@class='InvitationCard_invitationsText__3GDSS']");
	By btn_accept = By.xpath("//div[@class='cnv-btn cnv-p-1 InvitationCard_acceptButton__3DmT0']");
	By btn_decline = By.xpath("//span[normalize-space()='Decline']");

	// Constructor
	public AccessInvitationUX_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// Method to get the text of Meeting Title from invitation card
    public String getMeetingTitleText() {
        WebElement meetingTitleElement = driver.findElement(meetingTitle_loc);
        return meetingTitleElement.getText();
    }

    // Method to validate Meeting Title text from invitation card
    public void validateMeetingTitleText(String expectedText) {
        String actualText = getMeetingTitleText();
        Assert.assertEquals(actualText, expectedText, "Meeting title text is incorrect!");
    }

	// Method to validate Accept button
    public String getAcceptButtonText() {
        WebElement acceptButtonElement = driver.findElement(btn_accept);
        return acceptButtonElement.getText();
	}

	// Method to validate Accept button text
	public void validateAcceptButtonText(String expectedText) {
		String actualText = getAcceptButtonText();
		Assert.assertEquals(actualText, expectedText, "Accept button text is incorrect!");
	}

	// Method to validate Decline button
	public String getDeclineButtonText() {
		WebElement declineButtonElement = driver.findElement(btn_decline);
		return declineButtonElement.getText();
	}

	// Method to validate Accept button text
	public void validateDeclineButtonText(String expectedText) {
		String actualText = getDeclineButtonText();
		Assert.assertEquals(actualText, expectedText, "Decline button text is incorrect!");
	}

}
