package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import api_requests.APIRequest;
import api_requests.Payload;
import pages.BaseClass;
import pages.Checkout;
import utilities.Utilities;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLoad extends pages.BaseClass {

	private static final Logger logger = LogManager.getLogger(TestLoad.class);
	JSONParser jsonParser = new JSONParser();
	public static String vendorIdOne;
	public static String vendorIdTwo;

	public static String vendorOneProductIdOne;
	public static String vendorOneProductIdTwo;
	public static String vendorOneProductVariantIdOne;
	public static String vendorOneProductVariantIdTwo;
	public static String vendorOnePublishSlugOne;
	public static String vendorOnePublishSlugTwo;
	public static String vendorOneTrackerIdOne;
	public static String vendorOneTrackerIdTwo;
	public static String vendorOnePublishProductUrlOne;
	public static String vendorOnePublishProductUrlTwo;

	public static String vendorTwoProductIdOne;
	public static String vendorTwoProductIdTwo;
	public static String vendorTwoProductVariantIdOne;
	public static String vendorTwoProductVariantIdTwo;
	public static String vendorTwoPublishSlugOne;
	public static String vendorTwoPublishSlugTwo;
	public static String vendorTwoTrackerIdOne;
	public static String vendorTwoTrackerIdTwo;
	public static String vendorTwoPublishProductUrlOne;
	public static String vendorTwoPublishProductUrlTwo;

	@BeforeClass
	public static void setUp() throws IOException {

		prop = Utilities.readPropertiesFiles("betaConfig.properties");
		BaseClass.initChromeBrowser(prop.getProperty("frontend_beta_url").toString());
		je = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		checkout = PageFactory.initElements(driver, Checkout.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

	}

	@Test
	public void CreateVendors() throws IOException {

		// Create vendor sign up data

		vendorData = Utilities.createTestData("vendor");
		logger.info("Vendor Data - " + vendorData);
		payload = Payload.registerUser(vendorData.get("email"), vendorData.get("password"),
				vendorData.get("first_name") + " " + vendorData.get("last_name"), vendorData.get("phone_number"),
				prop.getProperty("email_bypass_id"));
		logger.info("Payload to register new vendor - " + payload);
		response = APIRequest.registerUser(prop.getProperty("backend_beta_url"), payload,
				prop.getProperty("email_bypass_id"));
		response.then().assertThat().statusCode(201);

		// Login vendor to get the login auth token

		automationVendorUserId = response.then().extract().body().jsonPath().get("data.id");
		logger.info("Vendor User ID - " + automationVendorUserId);
		payload = Payload.loginUser(vendorData.get("email"), vendorData.get("password"));
		logger.info("Payload to login new vendor - " + payload);
		response = APIRequest.loginUser(prop.getProperty("backend_beta_url"), payload,
				prop.getProperty("email_bypass_id"));
		response.then().assertThat().statusCode(200);
		loginAuthToken = response.then().extract().body().jsonPath().get("data.token");
		logger.info("Login auth token after vendor login - " + loginAuthToken);

		// Authenticate the vendor

		payload = Payload.authenticateUser("vendor");
		logger.info("Payload to authenticate vendor - " + payload);
		response = APIRequest.authenticateUser(prop.getProperty("backend_beta_url"), "Vendor", payload, loginAuthToken);
		response.then().assertThat().statusCode(200);
		loginAuthToken = response.then().extract().body().jsonPath().get("token");
		logger.info("Login auth token after authenticating vendor - " + loginAuthToken);

		// Enter vendor on boarding details

		payload = Payload.vendorDetails(vendorData.get("vendor_name"), vendorData.get("vendor_url"));
		logger.info("Payload for vendor onboarding details - " + payload);
		response = APIRequest.createVendorProfile(prop.getProperty("backend_beta_url"), payload, loginAuthToken);
		response.then().assertThat().statusCode(202);
		automationVendorToken = response.then().extract().body().jsonPath().get("token");
		logger.info("Vendor auth token - " + automationVendorToken);

		// Fetch Vendor ID

		response = APIRequest.getVendorProfileDetails(prop.getProperty("backend_beta_url"), automationVendorToken);
		response.then().assertThat().statusCode(200);
		automationVendorId = response.then().extract().body().jsonPath().get("vendors[0].id");
		logger.info("Vendor ID - " + automationVendorId);

		// Write the vendor auth token and vendor id to a file

		try {

			Object obj = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/vendor_details.json"));
			JSONArray jsonArray = (JSONArray) obj;
			logger.info("Opened json file");

			JSONObject vendor = new JSONObject();
			vendor.put("vendor_id", automationVendorId);
			vendor.put("vendor_user_id", automationVendorUserId);
			vendor.put("vendor_auth_token", automationVendorToken);

			logger.info("Json object to be written to file - " + vendor.toJSONString());
			jsonArray.add(vendor);

			FileWriter file = new FileWriter(System.getProperty("user.dir") + "/vendor_details.json");
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();
			logger.info("Json object written to file");

		} catch (ParseException | IOException e) {

			e.printStackTrace();

		}

	}

	@Test
	public void CreateCoseller() throws IOException {

		// Create coseller sign up data

		cosellerData = Utilities.createTestData("coseller");
		logger.info("Coseller Data - " + cosellerData);
		payload = Payload.registerUser(cosellerData.get("email"), cosellerData.get("password"), cosellerData.get("first_name") + " " + cosellerData.get("last_name"), cosellerData.get("phone_number"), prop.getProperty("email_bypass_id"));
		logger.info("Payload to register a new coseller - " + payload);
		response = APIRequest.registerUser(prop.getProperty("backend_beta_url"), payload, prop.getProperty("email_bypass_id"));
		response.then().assertThat().statusCode(201);
		
		// Login coseller to get the login auth token

		automationCosellerUserId = response.then().extract().body().jsonPath().get("data.id");
		logger.info("Coseller user id - " + automationCosellerUserId);

		payload = Payload.loginUser(cosellerData.get("email"), cosellerData.get("password"));
		logger.info("Login user data - " + payload);
		response = APIRequest.loginUser(prop.getProperty("backend_beta_url"), payload, prop.getProperty("email_bypass_id"));
		response.then().assertThat().statusCode(200);
		loginAuthToken = response.then().extract().body().jsonPath().get("data.token");
		
		// Create Coseller Profile
		
		response = APIRequest.createCosellerProfile(prop.getProperty("backend_beta_url"), loginAuthToken);
		response.then().assertThat().statusCode(200);
		loginAuthToken = response.then().extract().body().jsonPath().get("token");
		
		// Authenticate the coseller

		payload = Payload.authenticateUser("coseller");
		logger.info("Payload to create coseller profile - " + payload);
		response = APIRequest.authenticateUser(prop.getProperty("backend_beta_url"), "Coseller", payload, loginAuthToken);
		response.then().assertThat().statusCode(200);
		
		// Fetch the coseller auth token
		
		automationCosellerToken = response.then().extract().body().jsonPath().get("token");
		logger.info("Coseller auth token obtained - " + automationCosellerToken);

		// Write the coseller auth token and coseller user id to a file

		try {

			Object obj = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/coseller_details.json"));
			JSONArray jsonArray = (JSONArray) obj;
			logger.info("Opened json file");

			JSONObject coseller = new JSONObject();
			coseller.put("coseller_user_id", automationCosellerUserId);
			coseller.put("coseller_auth_token", automationCosellerToken);

			logger.info("Json object to be written to file - " + coseller.toJSONString());
			jsonArray.add(coseller);

			FileWriter file = new FileWriter(System.getProperty("user.dir") + "/coseller_details.json");
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();
			logger.info("Json object written to file");

		} catch (ParseException | IOException e) {

			e.printStackTrace();

		}

	}

	@Test
	public void ProductSync() {

		int randomNumber;
		JSONArray jsonArray = null;
		Random rand = new Random();
		JSONObject vendor = null;

		// pick vendor ids and vendor auth token for the file and sync the store

		try {

			Object obj = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/vendor_details.json"));
			jsonArray = (JSONArray) obj;
			logger.info("Opened json file");

			for (int i = 0; i < jsonArray.size(); i++) {

				logger.info("Syncing store for vendor - " + (i + 1));
				vendor = (JSONObject) jsonArray.get(i);
				payload = Payload.syncShopifyProducts("true", "true", prop.getProperty("shopifyAdminAccessToken"));
				logger.info("Payload to sync shopify products - " + payload);
				response = APIRequest.syncShopifyStore(prop.getProperty("backend_beta_url"),
						vendor.get("vendor_auth_token").toString(), payload);

				if (response.statusCode() == 400) {

					logger.info("Shopify store already linked");

				} else {

					response.then().assertThat().statusCode(202);
					logger.info("Synced Shopify Store");

				}

			}

			// pick 2 random ids

			randomNumber = rand.nextInt(jsonArray.size());
			vendor = (JSONObject) jsonArray.get(randomNumber);
			logger.info("Vendor Details - " + vendor);
			vendorIdOne = vendor.get("vendor_id").toString();
			logger.info("First vendor id - " + vendorIdOne);

			randomNumber = rand.nextInt(jsonArray.size());
			vendor = (JSONObject) jsonArray.get(randomNumber);
			vendorIdTwo = vendor.get("vendor_id").toString();
			logger.info("Second vendor id - " + vendorIdTwo);
			
			if(vendorIdTwo.equals(vendorIdOne)) {
				
				logger.info("Vendor one and vendor two are same, changing the vendor id");
				randomNumber = rand.nextInt(jsonArray.size());
				vendor = (JSONObject) jsonArray.get(randomNumber);
				vendorIdTwo = vendor.get("vendor_id").toString();
				logger.info("Changed Second vendor id - " + vendorIdTwo);
				
			}

		} catch (ParseException | IOException e) {

			e.printStackTrace();

		}

	}

	@Test
	public void PublishProducts() throws ParseException, FileNotFoundException, IOException, InterruptedException {
		
		JSONArray jsonArray = null;
		Random rand = new Random();
		JSONObject coseller = null;
		List<Map<String, String>> productDetails = new ArrayList<>();
		List<Map<String, String>> productDetails2 = new ArrayList<>();
		int randomNumber;
		
		Object obj = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/coseller_details.json"));
		jsonArray = (JSONArray) obj;
		logger.info("Opened json file");
		
		randomNumber = rand.nextInt(jsonArray.size());
		coseller = (JSONObject) jsonArray.get(randomNumber);
		automationCosellerToken = coseller.get("coseller_auth_token").toString();

		// Fetch 2 products for 1 vendor

		currency = prop.getProperty("currency");
		response = APIRequest.fetchProductWithVendorId(prop.getProperty("backend_beta_url"), vendorIdOne, currency);
		response.then().assertThat().statusCode(200);
		
		while(true) {
			
			if(response.then().extract().body().jsonPath().getInt("count") == 0) {
				
				response = APIRequest.fetchProductWithVendorId(prop.getProperty("backend_beta_url"), vendorIdOne, currency);
				response.then().assertThat().statusCode(200);
				
			} else {
				
				break;
				
			}
			
		}

		Object responseBody = new JSONParser().parse(response.getBody().asString());
		JSONObject jsonResponse = (JSONObject) responseBody;
		JSONArray productList = (JSONArray) jsonResponse.get("products");

		for (int i = 0; i < productList.size(); i++) {

			JSONObject productListJson = (JSONObject) productList.get(i);
			JSONArray variantList = (JSONArray) productListJson.get("variants");
			JSONObject variant = (JSONObject) variantList.get(0);

			if (Integer.parseInt(variant.get("quantity").toString()) > 0) {

				HashMap<String, String> productsVariants = new HashMap<String, String>();
				productsVariants.put("id", productListJson.get("id").toString());
				productsVariants.put("variant_id", variant.get("id").toString());
				productDetails.add(productsVariants);

			}

		}

		randomNumber = rand.nextInt(productDetails.size());
		vendorOneProductIdOne = productDetails.get(randomNumber).get("id");
		vendorOneProductVariantIdOne = productDetails.get(randomNumber).get("variant_id");

		randomNumber = rand.nextInt(productDetails.size());
		vendorOneProductIdTwo = productDetails.get(randomNumber).get("id");
		vendorOneProductVariantIdTwo = productDetails.get(randomNumber).get("variant_id");
		
		if(vendorOneProductIdTwo.equals(vendorOneProductIdOne)) {
			
			randomNumber = rand.nextInt(productDetails.size());
			vendorOneProductIdTwo = productDetails.get(randomNumber).get("id");
			vendorOneProductVariantIdTwo = productDetails.get(randomNumber).get("variant_id");
			
		}
		
		logger.info("Vendor One Product Id's - " + vendorOneProductIdOne + " , " + vendorOneProductIdTwo);
		logger.info("Vendor One Product Variant Id's - " + vendorOneProductVariantIdOne + " , " + vendorOneProductVariantIdTwo);
		
		// Fetch 2 products for other vendor
		
		response = APIRequest.fetchProductWithVendorId(prop.getProperty("backend_beta_url"), vendorIdTwo, currency);
		response.then().assertThat().statusCode(200);
		
		while(true) {
			
			if(response.then().extract().body().jsonPath().getInt("count") == 0) {
				
				response = APIRequest.fetchProductWithVendorId(prop.getProperty("backend_beta_url"), vendorIdTwo, currency);
				response.then().assertThat().statusCode(200);
				
			} else {
				
				break;
				
			}
			
		}
		
		Object responseBody2 = new JSONParser().parse(response.getBody().asString());
		JSONObject jsonResponse2 = (JSONObject) responseBody2;
		JSONArray productList2 = (JSONArray) jsonResponse2.get("products");

		for (int i = 0; i < productList2.size(); i++) {

			JSONObject productListJson = (JSONObject) productList2.get(i);
			JSONArray variantList = (JSONArray) productListJson.get("variants");
			JSONObject variant = (JSONObject) variantList.get(0);

			if (Integer.parseInt(variant.get("quantity").toString()) > 0) {

				HashMap<String, String> productsVariants = new HashMap<String, String>();
				productsVariants.put("id", productListJson.get("id").toString());
				productsVariants.put("variant_id", variant.get("id").toString());
				productDetails2.add(productsVariants);

			}

		}

		randomNumber = rand.nextInt(productDetails2.size());
		vendorTwoProductIdOne = productDetails2.get(randomNumber).get("id");
		vendorTwoProductVariantIdOne = productDetails2.get(randomNumber).get("variant_id");
		
		if(vendorTwoProductIdOne.equals(vendorOneProductIdTwo) || vendorTwoProductIdOne.equals(vendorOneProductIdOne)) {
			
			randomNumber = rand.nextInt(productDetails2.size());
			vendorTwoProductIdOne = productDetails2.get(randomNumber).get("id");
			vendorTwoProductVariantIdOne = productDetails2.get(randomNumber).get("variant_id");
			
		}
		
		randomNumber = rand.nextInt(productDetails2.size());
		vendorTwoProductIdTwo = productDetails2.get(randomNumber).get("id");
		vendorTwoProductVariantIdTwo = productDetails2.get(randomNumber).get("variant_id");
		
		if(vendorTwoProductIdTwo.equals(vendorTwoProductIdOne) || vendorTwoProductIdTwo.equals(vendorOneProductIdOne) || vendorTwoProductIdTwo.equals(vendorOneProductIdTwo)) {
			
			randomNumber = rand.nextInt(productDetails2.size());
			vendorTwoProductIdTwo = productDetails2.get(randomNumber).get("id");
			vendorTwoProductVariantIdTwo = productDetails2.get(randomNumber).get("variant_id");
			
		}

		logger.info("Vendor Two Product Id's - " + vendorTwoProductIdOne + " , " + vendorTwoProductIdTwo);
		logger.info("Vendor Two Product Variant Id's - " + vendorTwoProductVariantIdOne + " , " + vendorTwoProductVariantIdTwo);

		// Fetch Publish Slug Url

		response = APIRequest.getPublishSlug(prop.getProperty("backend_beta_url"), automationCosellerToken, vendorOneProductIdOne);
		response.then().assertThat().statusCode(200);
		vendorOnePublishSlugOne = response.then().extract().body().jsonPath().get("slug");
		vendorOneTrackerIdOne = response.then().extract().body().jsonPath().get("trackerId");
		
		response = APIRequest.getPublishSlug(prop.getProperty("backend_beta_url"), automationCosellerToken, vendorOneProductIdTwo);
		response.then().assertThat().statusCode(200);
		vendorOnePublishSlugTwo = response.then().extract().body().jsonPath().get("slug");
		vendorOneTrackerIdTwo = response.then().extract().body().jsonPath().get("trackerId");
		
		logger.info("Vendor One plublish slug urls - " + vendorOnePublishSlugOne + " , " + vendorOnePublishSlugTwo);
		logger.info("Vendor One tracker ids's - " + vendorOneTrackerIdOne + " , " + vendorOneTrackerIdTwo);
		
		response = APIRequest.getPublishSlug(prop.getProperty("backend_beta_url"), automationCosellerToken, vendorTwoProductIdOne);
		response.then().assertThat().statusCode(200);
		vendorTwoPublishSlugOne = response.then().extract().body().jsonPath().get("slug");
		vendorTwoTrackerIdOne = response.then().extract().body().jsonPath().get("trackerId");
		
		response = APIRequest.getPublishSlug(prop.getProperty("backend_beta_url"), automationCosellerToken, vendorTwoProductIdTwo);
		response.then().assertThat().statusCode(200);
		vendorTwoPublishSlugTwo = response.then().extract().body().jsonPath().get("slug");
		vendorTwoTrackerIdTwo = response.then().extract().body().jsonPath().get("trackerId");
		
		logger.info("Vendor Two plublish slug urls - " + vendorTwoPublishSlugOne + " , " + vendorTwoPublishSlugTwo);
		logger.info("Vendor Two tracker ids's - " + vendorTwoTrackerIdOne + " , " + vendorTwoTrackerIdTwo);

		// Publish the Products 
		
		vendorOnePublishProductUrlOne = "https://beta.shoptype.com" + vendorOnePublishSlugOne;
		payload = Payload.publishProduct(vendorIdOne, automationCosellerUserId.replace("\"", ""), vendorOnePublishProductUrlOne, vendorOneProductIdOne);
		APIRequest.publishProduct(prop.getProperty("backend_beta_url"), automationCosellerToken, payload);
		
		vendorOnePublishProductUrlTwo = "https://beta.shoptype.com" + vendorOnePublishSlugTwo;
		payload = Payload.publishProduct(vendorIdOne, automationCosellerUserId.replace("\"", ""), vendorOnePublishProductUrlTwo, vendorOneProductIdTwo);
		APIRequest.publishProduct(prop.getProperty("backend_beta_url"), automationCosellerToken, payload);
		
		vendorTwoPublishProductUrlOne = "https://beta.shoptype.com" + vendorTwoPublishSlugOne;
		payload = Payload.publishProduct(vendorIdTwo, automationCosellerUserId.replace("\"", ""), vendorTwoPublishProductUrlOne, vendorTwoProductIdOne);
		APIRequest.publishProduct(prop.getProperty("backend_beta_url"), automationCosellerToken, payload);
		
		vendorTwoPublishProductUrlTwo = "https://beta.shoptype.com" + vendorTwoPublishSlugTwo;
		payload = Payload.publishProduct(vendorIdTwo, automationCosellerUserId.replace("\"", ""), vendorTwoPublishProductUrlTwo, vendorTwoProductIdTwo);
		APIRequest.publishProduct(prop.getProperty("backend_beta_url"), automationCosellerToken, payload);
		
		logger.info("Vendor one publish product urls - " + vendorOnePublishProductUrlOne + " , " + vendorOnePublishProductUrlTwo);
		logger.info("Vendor two publish product urls - " + vendorTwoPublishProductUrlOne + " , " + vendorTwoPublishProductUrlTwo);
		
		// Track User Event
		
		payload = Payload.createUserEvent(deviceId, vendorOnePublishProductUrlOne, vendorOneTrackerIdOne);
		response = APIRequest.createUserEvent(prop.getProperty("backend_beta_url"), payload);
		response.then().assertThat().statusCode(200);
		
		payload = Payload.createUserEvent(deviceId, vendorOnePublishProductUrlTwo, vendorOneTrackerIdTwo);
		response = APIRequest.createUserEvent(prop.getProperty("backend_beta_url"), payload);
		response.then().assertThat().statusCode(200);
		
		payload = Payload.createUserEvent(deviceId, vendorTwoPublishProductUrlOne, vendorTwoTrackerIdOne);
		response = APIRequest.createUserEvent(prop.getProperty("backend_beta_url"), payload);
		response.then().assertThat().statusCode(200);
		
		payload = Payload.createUserEvent(deviceId, vendorTwoPublishProductUrlTwo, vendorTwoTrackerIdTwo);
		response = APIRequest.createUserEvent(prop.getProperty("backend_beta_url"), payload);
		response.then().assertThat().statusCode(200);
		
		logger.info("Created user events for all the 4 vendor products");
		
	}

	@Test
	public void UpdateCart() {
		
		// Create Empty Cart
		
		shoptypeApiKey = prop.getProperty("shoptype_api_key");
		response = APIRequest.createEmptyCart(prop.getProperty("backend_beta_url"), shoptypeApiKey);
		response.then().assertThat().statusCode(200);
		cartId = response.then().extract().body().jsonPath().get("id");
		
		logger.info("Cart Id - " + cartId);
		
		// Add items to cart
		
		response = APIRequest.addItemsToCart(prop.getProperty("backend_beta_url"), shoptypeApiKey, cartId, vendorOneProductIdOne, vendorOneProductVariantIdOne);
		response.then().assertThat().statusCode(200);
		
		response = APIRequest.addItemsToCart(prop.getProperty("backend_beta_url"), shoptypeApiKey, cartId, vendorOneProductIdTwo, vendorOneProductVariantIdTwo);
		response.then().assertThat().statusCode(200);
		
		response = APIRequest.addItemsToCart(prop.getProperty("backend_beta_url"), shoptypeApiKey, cartId, vendorTwoProductIdOne, vendorTwoProductVariantIdOne);
		response.then().assertThat().statusCode(200);
		
		response = APIRequest.addItemsToCart(prop.getProperty("backend_beta_url"), shoptypeApiKey, cartId, vendorTwoProductIdTwo, vendorTwoProductVariantIdTwo);
		response.then().assertThat().statusCode(200);
		
		logger.info("Added all 4 products to cart");
		
		// Create Checkout

		payload = Payload.createCheckout(deviceId,  cartId);
		response = APIRequest.createCheckout(prop.getProperty("backend_beta_url"), shoptypeApiKey, deviceId, cartId, null);
		response.then().assertThat().statusCode(200); 
		checkoutId = response.then().extract().body().jsonPath().get("checkout_id");
		redirectUri = "beta.shoptype.com" + response.then().extract().body().jsonPath().get("redirect_uri");
		
		logger.info("Checkout Url - " + redirectUri);

	}
	
	@Test
	public void UpdateCheckout() throws InterruptedException {
		
		// Open the Checkout URL on UI 
		
		driver.get("https://" + redirectUri.toString());
		logger.info("Opened checkout url on ui");
		
		// Enter shipping details
		
		logger.info("Adding shipping details");
		wait.until(ExpectedConditions.elementToBeClickable(checkout.name));
		checkout.name.sendKeys("Automation Checkout");
		logger.info("Name - Automation Checkout");
		checkout.phone.sendKeys("8888888888");
		logger.info("Phone - 8888888888");
		autoRegisteredEmail = Utilities.getNewEmailId();
		checkout.email.sendKeys(autoRegisteredEmail);
		logger.info("Email - shoptype@mailinator.com");
		checkout.location.sendKeys("4498 Woodford Pass");
		Thread.sleep(4000);
		checkout.location.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		logger.info("Selected location");

		Thread.sleep(5000);
		je.executeScript("arguments[0].scrollIntoView();", checkout.continueCheckout);
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(checkout.continueCheckout).click().perform();
		logger.info("Clicked on continue on checkout");

		try {
			
			wait.until(ExpectedConditions.elementToBeClickable(checkout.continuePayment));
			
		} catch (Exception e) {
			
			action.moveToElement(checkout.continueCheckout).click().perform();
			wait.until(ExpectedConditions.elementToBeClickable(checkout.continuePayment));
		}
		
		Thread.sleep(5000);
		checkout.continuePayment.click();
		logger.info("Clicked on continue to payment");
		
		try {
			
			wait.until(ExpectedConditions.visibilityOf(checkout.paymentModal));
			checkout.phoneNumber.sendKeys("1234567890");
			logger.info("Entered phone number 1234567890 on payment screen");
			
			Thread.sleep(5000);
			driver.switchTo().frame(checkout.paymentIframe);
			logger.info("Switched to payment iframe");
			
			wait.until(ExpectedConditions.elementToBeClickable(checkout.cardNumber));
			checkout.cardNumber.sendKeys("4242424242424242");
			logger.info("Entered card number - 4242424242424242");
			
			checkout.expiryDate.sendKeys("0228");
			logger.info("Entered expiry date - 02/28");
			checkout.cvc.sendKeys("4242");
			logger.info("Entered CVV - 4242");
			
			driver.switchTo().defaultContent();
			logger.info("Switched back to payment iframe");
			
			wait.until(ExpectedConditions.elementToBeClickable(checkout.pay));
			checkout.pay.click();
			Thread.sleep(3000);
			logger.info("Clicked on pay now");
		
		} catch (Exception e) {
			
			wait.until(ExpectedConditions.visibilityOf(checkout.authorizePyamentModel));
			checkout.authorizeCardNumber.sendKeys("4242 4242 4242 4242");
			logger.info("Entered card number - 4242 4242 4242 4242");
			
			checkout.authorizeCardMonth.sendKeys("06");
			logger.info("Entered card expiry month - 06");
	
			checkout.authorizeCardYear.sendKeys("29");
			logger.info("Entered card expiry year - 29");
			
			checkout.authorizeCVV.sendKeys("5454");
			logger.info("Entered card cvv - 5454");
			
			checkout.authorizePayNow.click();
			logger.info("Clicked on pay now");
			
		}
		
		// Wait till the order ID is received
		
		wait.until(ExpectedConditions.visibilityOf(checkout.orderConfirmed));
		Assert.assertTrue(checkout.orderConfirmed.isDisplayed());
		int refreshCount = 0;
				
		while(true) {
			
			try {
				
				if(refreshCount == refreshCountThreshold) {
					
					Assert.assertTrue("No Order ID Found", false);
					logger.info("No Order ID Found");
					
				}
				
				if(checkout.orderId.get(1).getText().split("#")[1] != null) {
					
					Assert.assertTrue("Order Id Found", Integer.parseInt(checkout.orderId.get(1).getText().split("#")[1]) > 0);
					logger.info("Order ID - " + Integer.parseInt(checkout.orderId.get(1).getText().split("#")[1]));
					break;
					
				}
				
			} catch (IndexOutOfBoundsException e) {
				
				System.out.println(e.getMessage());
				driver.navigate().refresh();
				Thread.sleep(3000);
				refreshCount++;
				logger.info("Trying to fetch order id after " + refreshCount + " refresh");
				
			}
			
		}
				
		logger.info("Order placed successfully");
		
	}
	
	@AfterClass
	public static void tearDown() {

		driver.quit();
		
		try {
			
			FileWriter vendorFile = new FileWriter(System.getProperty("user.dir") + "/vendor_details.json");
			vendorFile.write("[]");
			vendorFile.flush();
			vendorFile.close();
			logger.info("Cleared vendor json file");
			
			FileWriter cosellerFile = new FileWriter(System.getProperty("user.dir") + "/coseller_details.json");
			cosellerFile.write("[]");
			cosellerFile.flush();
			cosellerFile.close();
			logger.info("Cleared coseller json file");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
}
