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
	By meetingTitle_loc = By.xpath("//body/div[@id='root']/div[@class='cnv-wrapper cnv-theme-light']/div[@class='cnv-content-area']/div[@class='cnv-content']/div[@id='portalForModal']/div[1]/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
	By btn_accept = By.xpath("//body/div[@id='root']/div[@class='cnv-wrapper cnv-theme-light']/div[@class='cnv-content-area']/div[@class='cnv-content']/div[@id='portalForModal']/div[1]/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]");
	By btn_decline = By.xpath("//body/div[@id='root']/div[contains(@class,'cnv-wrapper cnv-theme-light')]/div[contains(@class,'cnv-content-area')]/div[contains(@class,'cnv-content')]/div[@id='portalForModal']/div[contains(@class,'undefined')]/div[contains(@class,'cnv-row')]/div[contains(@class,'cnv-col-4 cnv-col-md-12')]/div[contains(@class,'cnv-dashboard-column-3-box-2')]/div[contains(@class,'invitation-taber-custom InvitationRequests_invitationMain__2gctc')]/div[contains(@class,'InvitationRequests_scrollArea__3LuMF')]/div[contains(@class,'InvitationRequests_requestMain__1CNlj')]/div/div[contains(@class,'cnv-container')]/div/div[contains(@class,'cnv-dashboard-card-content')]/div/div[contains(@class,'cnv-btn cnv-p-1 cnv-ml-3 InvitationCard_denyButton__YAETS')]/span[1]");

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
