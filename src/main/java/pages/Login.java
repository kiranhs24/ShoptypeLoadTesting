package pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

	WebDriver driver;

	public Login(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(css = "input[name='email']")
	public WebElement email;

	@FindBy(css = "input[name='password']")
	public WebElement password;

	@FindBy(xpath = "//button[text()='Sign In']")
	public WebElement signIn;

	public boolean signInUser(WebDriverWait wait, HashMap<String, String> data) {
		
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(data.get("email"));
		password.sendKeys(data.get("password"));
		signIn.click();
		
		return true;

	}

}
