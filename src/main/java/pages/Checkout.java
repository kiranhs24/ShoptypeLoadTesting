package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout {
	
	WebDriver driver;

	public Checkout(WebDriver driver) {

		this.driver = driver;

	}
	
	@FindBy(xpath = "//button[contains(text(), 'Buy Now')]")
	public WebElement buyNow;
	
	@FindBy(xpath = "//button[contains(text(), 'Checkout')]")
	public WebElement checkout;
	
	@FindBy(css = "#stripe-payment-modal")
	public WebElement paymentModal;
	
	@FindBy(css = "#card-element")
	public WebElement cardElement;
	
	@FindBy(xpath = "//iframe[contains(@name, 'privateStripeFrame')]")
	public WebElement paymentIframe;
	
	@FindBy(css = "input[name='name']")
	public WebElement name;
	
	@FindBy(css = "input[name='phone']")
	public WebElement phone;
	
	@FindBy(xpath = "//label[contains(text(), 'Email')]/following-sibling::div/input")
	public WebElement email;
	
	@FindBy(id = "shipping-google-autocomplete")
	public WebElement location;
	
	@FindBy(xpath = "//button[contains(text(), 'Continue')]")
	public WebElement continueCheckout;
	
	@FindBy(xpath = "//button[contains(text(), 'Continue to Payment')]")
	public WebElement continuePayment;
	
	@FindBy(css = "input[type='number']")
	public WebElement phoneNumber;
	
	@FindBy(css = "input[name='cardnumber']")
	public WebElement cardNumber;

	@FindBy(css = "input[name='exp-date']")
	public WebElement expiryDate;
	
	@FindBy(css = "input[name='cvc']")
	public WebElement cvc;
	
	@FindBy(xpath = "//button[contains(text(), 'USD')]")
	public WebElement pay;
	
	@FindBy(xpath = "//div[contains(text(), 'order has been processed')]")
	public WebElement orderConfirmed;
	
	@FindBy(xpath = "//div[contains(text(), 'Order')]")
	public List<WebElement> orderId;
	
	@FindBy(css = "form.authnet-payment-form")
	public WebElement authorizePyamentModel;
	
	@FindBy(css = "#cardNumber")
	public WebElement authorizeCardNumber;
	
	@FindBy(css = "#expMonth")
	public WebElement authorizeCardMonth;
	
	@FindBy(css = "#expYear")
	public WebElement authorizeCardYear;
	
	@FindBy(css = "#cardCode")
	public WebElement authorizeCVV;
	
	@FindBy(css = "button.authorize-payement-btn")
	public WebElement authorizePayNow;
	
}
