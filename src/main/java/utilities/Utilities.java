package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	static WebDriver driver;
	static Properties prop;

	public static Properties readPropertiesFiles(String fileName) throws IOException {

		FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/props/" + fileName);
		prop = new Properties();
		prop.load(reader);
		reader.close();
		return prop;

	}
	
	public static String getNewEmailId() {
		
		return "Automation_" + UUID.randomUUID().toString().replace("-", "") + "@mailinator.com";
		
	}
	
	public static String getDeviceId() {
		
		return UUID.randomUUID().toString().replace("-", "");
		
	}
	
	public static HashMap<String, String> createTestData(String userType) throws IOException {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		if(userType.equalsIgnoreCase("vendor")) {
			
			data.put("first_name", "Automation");
			data.put("last_name", "Vendor");
			data.put("phone_number", "8888888888");
			data.put("email", getNewEmailId());
			data.put("password", "#Kiran1234");
			data.put("vendor_url", "https://www.quora.com");
			data.put("vendor_name", getNewEmailId().split("@")[0]);
			
		} else if(userType.equalsIgnoreCase("coseller")) {
			
			data.put("first_name", "Automation");
			data.put("last_name", "Coseller");
			data.put("phone_number", "8888888888");
			data.put("email", getNewEmailId());
			data.put("password", "#Kiran1234");
			
		} else if(userType.equalsIgnoreCase("network")) {
			
			data.put("first_name", "Automation");
			data.put("last_name", "Network");
			data.put("phone_number", "8888888888");
			data.put("email", getNewEmailId());
			data.put("password", "#Kiran1234");
			
			data.put("network_name", getNewEmailId());
			data.put("newtork_url", "https://www." + getNewEmailId().split("@")[0] + ".com/");
			
		}
		
		return data;
		
	}

	public static boolean isElementPresent(WebDriver driver, WebDriverWait wait, String locator) {

		boolean isFound = false;
		
		try {
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				isFound = true;

		} catch (Exception e) {

			isFound = false;

		}
		
		return isFound;

	}
	
	public static boolean verifyNotifications(WebDriver driver, WebDriverWait wait, String email, String emailType) throws InterruptedException {

		boolean isEmailVerified = false;
		
		Thread.sleep(3000);
		driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email);
		Thread.sleep(3000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'GO') and contains(@class, 'primary')]"))).click();
		Thread.sleep(3000);
		if(emailType.contains("verify") || emailType.contains("otp") || emailType.contains("auto registered")) {
			
			isEmailVerified = Utilities.isElementPresent(driver, wait, 
									"//table[@class='table-striped jambo_table']//td[contains(text(), 'Verify Email')]");
		
		} else if(emailType.contains("reset password")) {
			
			isEmailVerified = Utilities.isElementPresent(driver, wait, 
									"//table[@class='table-striped jambo_table']//td[contains(text(),'Reset Password')]");
			
		} else if(emailType.contains("order") || emailType.contains("vendor")) {
			
			isEmailVerified = Utilities.isElementPresent(driver, wait, 
									"//table[@class='table-striped jambo_table']//td[contains(text(), 'confirmed')]");
			
		} else if(emailType.contains("referral")) {
			
			isEmailVerified = Utilities.isElementPresent(driver, wait, 
					"//table[@class='table-striped jambo_table']//td[contains(text(), 'Referral')]");
			
		}

		return isEmailVerified;
		
	}

	public static boolean verifyEmail(WebDriver driver, WebDriverWait wait, String email) throws InterruptedException {

		boolean isEmailVerified = false;
		
		Thread.sleep(5000);
		driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email);
		Thread.sleep(5000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'GO') and contains(@class, 'primary')]"))).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table[@class='table-striped jambo_table']//td[contains(.,'noreply@awake.me')]"))).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='html_msg_body']")));
		WebElement messageBody = driver.findElement(By.xpath("//iframe[@id='html_msg_body']"));
		driver.switchTo().frame(messageBody);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Verify Email']"))).click();
		Thread.sleep(5000);
		String parentWindow = driver.getWindowHandle();
		Set<String> tabsOpened = driver.getWindowHandles();

		for (String window : tabsOpened) {

			if (!window.equalsIgnoreCase(parentWindow)) {

				driver.switchTo().window(window);
				if (driver.getTitle().equalsIgnoreCase("Shoptype")) {

					isEmailVerified = true;

				}
			}
		}
		
		return isEmailVerified;

	}

	public static String getItemFromLocalStorage(WebDriver driver, String key) {

		JavascriptExecutor je = ((JavascriptExecutor) driver);
		return (String) je.executeScript(String.format("return window.localStorage.getItem('%s');", key));

	}
	
	public static long getUnixEpochTime() {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}

}
