package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CookieConsentPage {
	   private WebDriver driver;

	    // Locator for the "Accept" button
	    private By acceptButtonLocator = By.xpath("//button[@data-cky-tag='accept-button']"); // Use the actual locator for the accept button

	    // Constructor
	    public CookieConsentPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Method to click the "Accept" button
	    public void acceptCookies() {
	        WebElement acceptButton = driver.findElement(acceptButtonLocator);
	        acceptButton.click();
	    }

	    // Optional: Method to check if the cookie banner is displayed
	    public boolean isCookieBannerDisplayed() {
	        try {
	            WebElement acceptButton = driver.findElement(acceptButtonLocator);
	            return acceptButton.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
}
