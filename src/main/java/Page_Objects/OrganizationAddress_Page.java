package Page_Objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationAddress_Page {

	WebDriver driver;
	WebDriverWait wait;

	// Constructors
	public OrganizationAddress_Page(WebDriver driver) {
		this.driver = driver;
	}

	// Locators

	By adminDropdown = By.xpath("//span[@class='submenu-arrow']");
	By btn_ManageOrganization_loc = By.xpath("//span[normalize-space()='Manage Organization']");
	By btn_edit_loc = By.xpath("//div[contains(@class,'cnv-col-sm-2 cnv-col-md-2 cnv-col-xl-2 cnv-col-lg-2 cnv-mt-3 OrganizationSetUp_buttonText__U064h')]");
	By txt_location_loc = By.xpath("//input[contains(@name,'address')]");
	By txt_city_loc = By.xpath("//input[@name='city']");
	By txt_state_loc = By.xpath("//input[@name='zip']");
	By dropdown_country_loc = By.xpath("//div[@class='cnv-select-with-label']//*[name()='svg']");
	By countryName = By.xpath("//li[normalize-space()='Denmark']");
	By btn_cancel_loc = By.xpath("//button[normalize-space()='Cancel']");
	By btn_save_loc = By.xpath("//button[normalize-space()='Save']");
	By toaster_Message = By.xpath("//div[@class='cnv-toast-body']");

	// Action methods

	// Click dropdown
	public void clickdropDown() {
		driver.findElement(adminDropdown).click();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// To click Manage Organization
	public void clickManageOrganization() {
		driver.findElement(btn_ManageOrganization_loc).click();
	}

	//To edit address
	public void clickEdit() {
		driver.findElement(btn_edit_loc).click();
	}

	// To edit Location
	public void setLocation(String location) {
		driver.findElement(txt_location_loc).sendKeys(location);
	}

	// To edit city
	public void setCity(String city) {
		driver.findElement(txt_city_loc).sendKeys(city);
	}

	//To edit state/zip code
	public void setState(String state) {
		driver.findElement(txt_state_loc).sendKeys(state);
	}

	//To open the dropdown field
	public void clickDropdown() {
		driver.findElement(dropdown_country_loc).click();
	}

	//To select a desired country
	public void clickCountry() {
		driver.findElement(countryName).click();
	}

	//To cancel the edit functionalities
	public void clickCancel() {
		driver.findElement(btn_cancel_loc).click();
	}

	//To save the changes
	public void clickSave() {
		driver.findElement(btn_save_loc).click();
	}

	public String getToasterValue() {
		// Wait for the toaster message to be visible and return its text
		WebElement toasterMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toaster_Message));
		return toasterMessageElement.getText();
	}

	//To clear the text of the Location field
	public void clearLocation() {
		driver.findElement(txt_location_loc).clear();

	}

	//To clear the text of the city field
	public void clearCity() {
		driver.findElement(txt_city_loc).clear();

	}

	//To clear the text of the state field
	public void clearState() {
		driver.findElement(txt_state_loc).clear();

	}

}
