package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResetPassword_Page {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public ResetPassword_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait here
    }

    // Locators
    By dropDownByName = By.xpath("//span[@class='user-name']");
    By btn_profile_loc = By.xpath("//a[normalize-space()='Profile']");
    By btn_editPassword_loc = By.xpath("//form//div[@class='cnv-col-xl-10 cnv-col-8']//a[1]");
    By txt_oldPassword_loc = By.xpath("//input[@placeholder='Old Password']");
    By txt_newPassword_loc = By.xpath("//input[@placeholder='New Password']");
    By txt_confirmPassword_loc = By.xpath("//input[@placeholder='Confirm New Password']");
    By btn_cancel_loc = By.xpath("//a[normalize-space()='Cancel']");
    By btn_saveChange_loc = By.xpath("//input[@id='submit_email_pass']");
    By toaster_PasswordNotChanged = By.xpath("//div[@class='cnv-toast-body']");
    By toaster_PasswordChanged = By.xpath("//div[@class='cnv-toast-body']");
    By errorMessage = By.xpath("//div[@class='cnv-form-group']//div[1]");

    // Action methods
    public void clickdropDown() {
        driver.findElement(dropDownByName).click();
    }

    public void clickProfile() {
        driver.findElement(btn_profile_loc).click();
    }

    public void clickEditPassword() {
        driver.findElement(btn_editPassword_loc).click();
    }

    public void setOldPassword(String old) {
        driver.findElement(txt_oldPassword_loc).sendKeys(old);
    }

    public void setNewPassword(String New) {
        driver.findElement(txt_newPassword_loc).sendKeys(New);
    }

    public void setConfirmPassword(String confirm) {
        driver.findElement(txt_confirmPassword_loc).sendKeys(confirm);
    }

    public void clickCancel() {
        driver.findElement(btn_cancel_loc).click();
    }

    public void clickSaveChange() {
        driver.findElement(btn_saveChange_loc).click();
    }

    // Method to get the value of the toaster
    public String getToasterValue() {
        // Wait for the toaster message to be visible and return its text
        WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_PasswordNotChanged));
        return toasterMessageElement.getText();
    }
    
    public String getToasterValue2() {
        // Wait for the toaster message to be visible and return its text
        WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_PasswordChanged));
        return toasterMessageElement.getText();
    }
    
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
