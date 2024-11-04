package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class JoinWithMeetingLink_Page {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public JoinWithMeetingLink_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize the wait here
    }

    // Locators
    By txt_name_loc = By.xpath("//input[@placeholder='Enter your name']");
    By btn_continue_loc = By.xpath("//button[@id='startButton']");
    By tooltip_Message = By.xpath("//span[@class='StartJoinMeeting_tooltipTextRed__1YKoN']");

    // Action methods

    // Method to input name
    public void setUserName(String name) {
        driver.findElement(txt_name_loc).sendKeys(name);
    }

    // Method to click Continue
    public void clickContinue() {
        driver.findElement(btn_continue_loc).click();
    }

    public String getToltipValue() {
        // Wait for the tooltip message to be visible and return its text
        WebElement tooltipMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tooltip_Message));
        return tooltipMessageElement.getText();
    }
}
