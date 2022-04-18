package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VendorOnboard extends BaseClass {

	WebDriver driver;

	public VendorOnboard(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(xpath = "//div[contains(text(), 'vendor')]")
	public WebElement vendor;
	
	@FindBy(xpath = "//span[contains(text(), 'Vendor')]")
	public WebElement vendorProfile;

	@FindBy(xpath = "//button[text()='Next']")
	public WebElement next;

	@FindBy(css = "input[name='vendorName']")
	public WebElement vendorName;

	@FindBy(css = "input[name='textBar']")
	public WebElement sellingCategory;
	
	@FindBy(xpath = "//div[text()='Flower/Gifts']")
	public WebElement category;

	@FindBy(css = "input[name='websiteUrl']")
	public WebElement websiteUrl;
	
	@FindBy(xpath = "//li[contains(text(), 'Refer')]")
	public WebElement refer;
	
	@FindBy(xpath = "//span[contains(text(), 'products')]/parent::div/parent::li")
	public WebElement products;
	
	public final String addProduct = "//button[contains(text(), 'new product')]";
	@FindBy(xpath = addProduct)
	public WebElement addNewProduct;
	
	@FindBy(xpath = "//button[contains(text(), 'Shopify')]")
	public WebElement importShopify;
	
	@FindBy(css = "input[name='domain']")
	public WebElement storeName;
	
	@FindBy(css = "input[name='apiKey']")
	public WebElement key;
	
	@FindBy(css = "input[name='password']")
	public WebElement secret;
	
	@FindBy(xpath = "//div[contains(text(), 'checkout on Shoptype')]")
	public WebElement enableShoptypeCheckout;
	
	@FindBy(xpath = "//div[contains(text(), 'Adult')]")
	public WebElement isAdult;
	
	@FindBy(xpath = "//div[contains(text(), 'Age Restricted')]")
	public WebElement isAgeRestricited;
	
	@FindBy(xpath = "//button[contains(text(), 'Link')]")
	public WebElement link;

	@FindBy(xpath = "//button[contains(text(), 'View')]")
	public List<WebElement> view;
	
	@FindBy(xpath = "//button[contains(text(), 'Publish')]")
	public List<WebElement> publish;
	
	@FindBy(xpath = "//button[contains(text(), 'Attribution')]")
	public List<WebElement> attribution;
	
	public final String acceptLocator = "//span[contains(text(), 'Accept')]/parent::button";
	@FindBy(xpath = acceptLocator)
	public WebElement accept;
	
	public final String withdrawLocator = "//span[contains(text(), 'Withdraw')]/parent::button"; 
	@FindBy(xpath = withdrawLocator)
	public WebElement withdraw;
	
	public final String removeLocator = "//span[contains(text(), 'Remove')]/parent::button";
	@FindBy(xpath = removeLocator)
	public WebElement remove;
	
	@FindBy(xpath = "//span[contains(text(), 'networks')]/parent::div")
	public WebElement networks;
	
}
