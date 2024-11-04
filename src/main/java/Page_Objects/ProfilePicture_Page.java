package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePicture_Page {

    WebDriver driver;
    WebDriverWait wait;

    // Constructors
    public ProfilePicture_Page(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By dropDownByName = By.xpath("//span[@class='user-name']");
    By btn_profile_loc = By.xpath("//a[normalize-space()='Profile']");
    By btn_change_loc = By.xpath("//a[normalize-space()='Change']");
    By file_input_loc = By.xpath("//input[@type='file']"); // Locator for file input
    By btn_remove_loc = By.xpath("//a[normalize-space()='Remove']");
    By btn_cancel_loc = By.xpath("//button[normalize-space()='Cancel']");
    By btn_add_loc = By.xpath("//button[normalize-space()='Add']");

    // Action methods

    public void clickdropDown() {
        driver.findElement(dropDownByName).click();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickProfile() {
        driver.findElement(btn_profile_loc).click();
    }

    public void clickChange() {
        driver.findElement(btn_change_loc).click();
    }

    public void clickRemove() {
        driver.findElement(btn_remove_loc).click();
    }

    public void clickCancel() {
        driver.findElement(btn_cancel_loc).click();
    }

    public void clickAdd() {
        driver.findElement(btn_add_loc).click();
    }

    public void uploadProfilePicture(String profilePicturePath) {
        WebElement fileInput = driver.findElement(file_input_loc);
        fileInput.sendKeys(profilePicturePath); // Send the file path to the input element
    }
}
