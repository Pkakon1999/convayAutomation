package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserInfo_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Constructors
	public EditUserInfo_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By dropDownByName = By.xpath("//span[@class='user-name']");
	By btn_profile_loc = By.xpath("//a[normalize-space()='Profile']");
	By btn_editProfileInfo_loc = By.xpath("//div[@class='cnv-col-12']//a[contains(text(),'Edit')]");
	By txt_firstName_loc1 = By.xpath("//input[@placeholder='Enter First Name']");
	By txt_lastName_loc1 = By.xpath("//input[@placeholder='Enter Last Name']");
	By txt_phoneNumber_loc1 = By.xpath("//input[@placeholder='1 (702) 123-4567']");
	By txt_firstName_loc2 = By.xpath("//div[@class='cnv-col-12']//span[contains(text(),'Kakon Paul Avi')]");
	By txt_lastName_loc2 = By.xpath("//input[@placeholder='Enter Last Name']");
	By txt_phoneNumber_loc2 = By.xpath("//input[@placeholder='1 (702) 123-4567']");
	By btn_cancel_loc = By.xpath("//a[normalize-space()='Cancel']");
	By btn_saveChange_loc = By.xpath("//input[@id='submit_email_pass']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Action methods

	//Method to click on dropdown
	public void clickdropDown() {
		driver.findElement(dropDownByName).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	//Method to click on user profile
	public void clickProfile() {
		driver.findElement(btn_profile_loc).click();
	}

	//Method to click on edit profile
	public void clickEditProfileInfo() {
		driver.findElement(btn_editProfileInfo_loc).click();
	}

	//Method to clear first name
	public void clearFirstName() {
		WebElement firstName = driver.findElement(txt_firstName_loc1);

		// Use JavaScript to clear the field
		JavascriptExecutor fn = (JavascriptExecutor) driver;
		fn.executeScript("arguments[0].value = '';", firstName);
	}

	
	//Method to clear last name
	public void clearLastName() {
		WebElement lastName = driver.findElement(txt_lastName_loc1);

		// Use JavaScript to clear the field
		JavascriptExecutor ln = (JavascriptExecutor) driver;
		ln.executeScript("arguments[0].value = '';", lastName);
	}

	//Method to clear phone number
	public void clearPhoneNumber() {
		WebElement phoneNumber = driver.findElement(txt_phoneNumber_loc1);

		// Use JavaScript to clear the field
		JavascriptExecutor pn = (JavascriptExecutor) driver;
		pn.executeScript("arguments[0].value = '';", phoneNumber);
	}

	//Method to set first name
	public void setFirstName(String fn) {
		driver.findElement(txt_firstName_loc1).sendKeys(fn);
	}

	//Method to set last name
	public void setLastName(String ln) {
		driver.findElement(txt_lastName_loc1).sendKeys(ln);
	}

	//Method to set phone number
	public void setPhoneNumber(String pn) {
		driver.findElement(txt_phoneNumber_loc1).sendKeys(pn);
	}

	//Method to click cancel
	public void clickCancel() {
		driver.findElement(btn_cancel_loc).click();
	}

	//Method to click save change
	public void clickSaveChange() {
		driver.findElement(btn_saveChange_loc).click();
	}

	// Method to get the value of the first name field
	public String getFirstNameValue() {
		return driver.findElement(txt_firstName_loc2).getAttribute("value");
	}

	// Method to get the value of the last name field
	public String getLastNameValue() {
		return driver.findElement(txt_lastName_loc2).getAttribute("value");
	}

	// Method to get the value of the phone number field
	public String getPhoneNumberValue() {
		return driver.findElement(txt_phoneNumber_loc2).getAttribute("value");
	}

	public String getToasterValue(String expectedText) {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}
}
