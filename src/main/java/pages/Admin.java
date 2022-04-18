package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Admin {
	
	WebDriver driver;
	
	public Admin(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	@FindBy(xpath = "//span[contains(text(), 'approvals')]/parent::div")
	public WebElement approval;
	
	@FindBy(xpath = "//span[contains(text(), 'Network')]/parent::button")
	public WebElement network; 
	
	@FindBy(xpath = "//span[contains(text(), 'Confirm')]/parent::button")
	public WebElement confirm;
	
	@FindBy(css = "#country")
	public WebElement country;
	
	@FindBy(css = "#radio2")
	public WebElement individual;
	
	@FindBy(xpath = "//span[contains(text(), 'test phone number')]")
	public WebElement testPhoneNumber;
	
	@FindBy(css = "#email")
	public WebElement email;
	
	@FindBy(css = "button[type='submit']")
	public WebElement continueKyc;
	
	@FindBy(xpath = "//span[contains(text(), 'test code')]/parent::span/parent::div/parent::div/parent::button")
	public WebElement testCode;
	
	@FindBy(css = "#first_name")
	public WebElement firstName;
	
	@FindBy(css = "#last_name")
	public WebElement lastName;
	
	@FindBy(css = "input[name='dob-day']")
	public WebElement day;
	
	@FindBy(css = "input[name='dob-month']")
	public WebElement month;
	
	@FindBy(css = "input[name='dob-year']")
	public WebElement year;
	
	@FindBy(css = "input[name='address']")
	public WebElement addressLine1;
	
	@FindBy(css = "input[name='address-line2']")
	public WebElement addressLine2;
	
	@FindBy(css = "input[name='zip']")
	public WebElement postalCode;
	
	@FindBy(css = "input[name='locality']")
	public WebElement city;
	
	@FindBy(xpath = "//span[contains(text(), 'industry')]/parent::div/parent::div/parent::button")
	public WebElement industry;
	
	@FindBy(css = "li[id$='item-1']")
	public WebElement software;
	
	@FindBy(css = "input[name='business_profile[url]']")
	public WebElement businessUrl;
	
	@FindBy(xpath = "//span[contains(text(), 'test account')]/parent::span/parent::div/parent::div/parent::button")
	public WebElement testAccount;
	
	public final String updateLocator = "//span[contains(text(), 'Update')]/parent::span/parent::div/parent::div/parent::button"; 
	@FindBy(xpath = updateLocator)
	public WebElement update;
	
	@FindBy(css = "#subregion")
	public WebElement state;
	
	@FindBy(css = "#id_number")
	public WebElement socialNumber;
	
	@FindBy(xpath = "//span[contains(text(), 'test document')]/parent::span/parent::div/parent::div/parent::button")
	public WebElement testDocument;
	
	public final String verified = "//span[contains(text(), 'Verified')]";
	@FindBy(xpath = verified)
	public WebElement verifiedDocuments;
	
	@FindBy(xpath = "//span[contains(text(), 'Done')]/parent::span/parent::div")
	public List<WebElement> done;
	
	@FindBy(xpath = "//span[contains(text(), 'Submit')]/parent::span/parent::div")
	public List<WebElement> submit;
	
	@FindBy(xpath = "//li[contains(text(), 'Logout')]")
	public WebElement logout;
	
	public void clickOnAccept(String networkName) {
		
		By acceptKyc = By.xpath(""
				+ "//p[contains(text(), '" + networkName + "')]/parent::div/parent::div//span[contains(text(), 'Accept')]/parent::button"
						+ "");
		driver.findElement(acceptKyc).click();
		
	}
	
	public void clickOnDecline(String networkName) {
		
		By declineKyc = By.xpath(""
				+ "//p[contains(text(), '" + networkName + "')]/parent::div/parent::div//span[contains(text(), 'Decline')]/parent::button"
						+ "");
		driver.findElement(declineKyc).click();
		
	}

}
