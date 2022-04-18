package pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
	
	WebDriver driver;
	
	public SignUp(WebDriver driver) {
		
		this.driver = driver;

	}

	@FindBy(css="input[name='fname']")
	public WebElement firstName;
	
	@FindBy(css="input[name='lname']")
	public WebElement lastName;
	
	@FindBy(css="button[id='rfs-btn']")
	public WebElement countryCodeDropDown;
	
	@FindBy(css="input[name='rfs-q']")
	public WebElement countryCodeSearchBox;
	
	@FindBy(css="li[id='rfs-IN']")
	public WebElement countryCode;
	
	@FindBy(css="input[name='phone']")
	public WebElement phone;
	
	@FindBy(css="input[name='email']")
	public List<WebElement> email;

	@FindBy(css="input[name='password']")
	public List<WebElement> password;
	
	@FindBy(xpath="//button[text()='Sign Up']")
	public WebElement signUp;
	
	@FindBy(xpath="//div[contains(text(), 'Email already exist')]")
	public WebElement userExists;
	
	public void registerNewUser(WebDriverWait wait, HashMap<String, String> data) throws InterruptedException {
		
		firstName.sendKeys(data.get("first_name"));
		lastName.sendKeys(data.get("last_name"));
		countryCodeDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(countryCodeSearchBox));
		countryCodeSearchBox.sendKeys("91");
		wait.until(ExpectedConditions.visibilityOf(countryCode));
		countryCode.click();
		phone.sendKeys(data.get("phone_number"));
		Thread.sleep(3000);
		try {
			email.get(0).sendKeys(data.get("email"));
		} catch (Exception e) {
			email.get(1).sendKeys(data.get("email"));
		}
		try {
			password.get(0).sendKeys(data.get("password"));
		} catch (Exception e) {
			password.get(1).sendKeys(data.get("password"));
		}
		signUp.click();
		
	}

}