package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImportProductFromWooCommerce extends BaseClass{
	
	public ImportProductFromWooCommerce() {
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[contains(text(), 'WooCommerce')]")
	public WebElement importWooCommerce;
	
	@FindBy(css="input[id=domain]")
	public WebElement domain;
	
	@FindBy(css="input[id=apiKey]")
	public WebElement apiKey;
	
	@FindBy(css="input[id=password]")
	public WebElement apiPassword;
	
	@FindBy(css="input[id=dokan-vendor-id]")
	public WebElement dokanVendorId;
	
	@FindBy(css="input[id=dokan-vendor-name]")
	public WebElement dokanVendorName;
	
	@FindBy(xpath="//div[contains(text(), 'checkout on Shoptype')]")
	public WebElement enableShoptypeCheckout;
	
	@FindBy(xpath="//button[contains(text(), 'Link')]")
	public WebElement link;

}
