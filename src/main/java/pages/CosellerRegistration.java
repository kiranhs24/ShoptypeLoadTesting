package pages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CosellerRegistration {
	
	WebDriver driver;
	
	public CosellerRegistration(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	@FindBy(xpath = "//button[contains(text(), 'Cosell')]")
	public WebElement cosell;
	
	@FindBy(id = "st-login-handler-cosell-steps-form-proceed-to-register")
	public WebElement cosellRegister;
	
	@FindBy(id = "link-to-sign-up")
	public WebElement signUpCosell;
	
	@FindBy(css = "input[name='firstName']")
	public WebElement firstName;
	
	@FindBy(css = "input[name='lastName']")
	public WebElement lastName;
	
	@FindBy(css = "input[name='email']")
	public WebElement email;
	
	@FindBy(css = "input[name='password']")
	public WebElement password;
	
	@FindBy(css = "input[name='confirmPassword']")
	public WebElement confirmPassword;
	
	@FindBy(css = "input[name='mobile']")
	public WebElement phone;
	
	@FindBy(css = "#st-login-handler-sign-up-form-submit")
	public WebElement submit;
	
	public void registerCoseller(HashMap<String, String> data) throws InterruptedException {
		
		firstName.sendKeys(data.get("first_name"));
		lastName.sendKeys(data.get("last_name"));
		phone.sendKeys(data.get("phone_number"));
		Thread.sleep(3000);
		email.sendKeys(data.get("email"));
		password.sendKeys(data.get("password"));
		confirmPassword.sendKeys(data.get("password"));
		submit.click();
		
	}

}
