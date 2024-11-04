package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationInfo_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Constructors
	public OrganizationInfo_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageOrganization_loc = By.xpath("//span[normalize-space()='Manage Organization']");
	By btn_edit_loc = By.xpath("//div[@class='cnv-row ']//div[@class='cnv-col-sm-2 cnv-col-md-2 cnv-col-xl-2 cnv-col-lg-2 OrganizationSetUp_buttonText__U064h'][normalize-space()='Edit']");
	By txt_organizationName_loc = By.xpath("//input[contains(@value,'Synesis')]");
	By btn_cancel_loc = By.xpath("//button[normalize-space()='Cancel']");
	By btn_save_loc = By.xpath("//button[normalize-space()='Save']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");
	
	// Action methods

	public void clickdropDown() {
		driver.findElement(adminDropdown).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to click Manage Organization
	public void clickManageOrganization() {
		driver.findElement(btn_ManageOrganization_loc).click();
	}

	public void clickEdit() {
		driver.findElement(btn_edit_loc).click();
	}

	public void setName(String name) {
        driver.findElement(txt_organizationName_loc).sendKeys(name);
    }

	public void clickCancel() {
		driver.findElement(btn_cancel_loc).click();
	}

	public void clickSave() {
		driver.findElement(btn_save_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

	public void clearName() {
		driver.findElement(txt_organizationName_loc).clear();
		
	}
}
