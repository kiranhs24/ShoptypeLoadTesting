package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Notifications {
	
	WebDriver driver;
	
	public Notifications(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	@FindBy(xpath = "//span[contains(text(), 'confirmation email has been sent')]")
	public WebElement emailSent;
	
	@FindBy(xpath = "//div[contains(text(), 'Forgot')]")
	public WebElement forgotPassword;
	
	@FindBy(xpath = "//button[contains(text(), 'Send')]")
	public WebElement sendForgotPasswordEmail;
	
	@FindBy(css = "input[name='to']")
	public WebElement toEmail;
	
	@FindBy(css = "input[name='subject']")
	public WebElement subject;
		
}
