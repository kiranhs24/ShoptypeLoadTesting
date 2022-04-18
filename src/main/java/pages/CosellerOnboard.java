package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CosellerOnboard extends BaseClass{
	
	WebDriver driver;

	public CosellerOnboard(WebDriver driver) {

		this.driver = driver;

	}
	
	@FindBy(xpath="//div[contains(text(), 'coseller')]")
	public WebElement coseller;
	
	@FindBy(xpath = "//span[text()='Coseller']")
	public WebElement cosellerProfile;
	
	@FindBy(xpath = "//li[contains(text(), 'My Profile')]")
	public WebElement myProfile;
	
	@FindBy(css = "input[name='isAdult']")
	public WebElement isAdult;
	
	@FindBy(css = "input[name='isAgeRestricted']")
	public WebElement isAgeRestricted;
	
	@FindBy(xpath = "//button[contains(text(), 'Save')]")
	public WebElement saveChanges;

}
