package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditMeetingInfo_Page {

    WebDriver driver;
    WebDriverWait wait;

    // Constructors
    public EditMeetingInfo_Page(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By dropDownByName = By.xpath("//span[@class='user-name']");
    By btn_profile_loc = By.xpath("//a[normalize-space()='Profile']");
    By btn_editPMU_loc = By.xpath("//div[contains(@class,'cnv-col-sm-2')]//a[contains(text(),'Edit')]");
    By txt_PMU_loc = By.xpath("//input[contains(@name,'pmuString')]");
    By txt_PMU_loc2 = By.xpath("//input[contains(@name,'pmuString')]");
    By pmuField = By.xpath("//div[@class='cnv-row']//div[1]//form[1]//hr[1]");
    By btn_cancel_loc = By.xpath("//a[normalize-space()='Cancel']");
    By btn_saveChange_loc = By.xpath("//input[@id='submit_pmu']");

    // Action methods

    public void clickdropDown() {
        driver.findElement(dropDownByName).click();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickProfile() {
        driver.findElement(btn_profile_loc).click();
    }

    public void clickEditPMU() {
        driver.findElement(btn_editPMU_loc).click();
    }
    

    public void clearPMU() {
        WebElement pmuElement = driver.findElement(txt_PMU_loc2);

        // Use JavaScript to clear the field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", pmuElement);
    }

    public void setPMU(String pmu) {
        driver.findElement(txt_PMU_loc).sendKeys(pmu);
    }

    public void clickCancel() {
        driver.findElement(btn_cancel_loc).click();
    }

    public void clickSaveChange() {
        driver.findElement(btn_saveChange_loc).click();
    }

    // Method to get the value of the PMU field
    public String getPMUFieldValue() {
        return driver.findElement(pmuField).getAttribute("value");
    }
}
