package api_requests;

import utilities.Utilities;

public class Payload {
	
	public static String registerUser(String email, String password, String name, String phoneNumber, String platformId) {
		
		return "{\r\n"
				+ "  \"name\": \"" + name + "\",\r\n"
				+ "  \"phone\": \"" + phoneNumber + "\",\r\n"
				+ "  \"email\": \"" + email + "\",\r\n"
				+ "  \"password\": \"" + password + "\"\r\n"
				+ "}";
		
	}
	
	public static String loginUser(String email, String password) {
		
		return "{\r\n"
				+ "  \"email\": \"" + email + "\",\r\n"
				+ "  \"password\": \"" + password + "\"\r\n"
				+ "}";
		
	}
	
	public static String authenticateUser(String userType) {
		
		return "{\r\n"
				+ "  \"userType\": \"" + userType + "\"\r\n"
				+ "}";
		
	}
	
	public static String vendorDetails(String vendorName, String vendorUrl) {
		
		return "{\r\n"
				+ "  \"name\": \"" + vendorName + "\",\r\n"
				+ "  \"productCategories\": [\r\n"
				+ "    \"Apparel\"\r\n"
				+ "  ],\r\n"
				+ "  \"url\": \"" + vendorUrl + "\",\r\n"
				+ "  \"timestamp\": " + Utilities.getUnixEpochTime() + "\r\n"
				+ "}";
		
	}
	
	public static String networkDetails(String networkName, String networkUrl) {
		
		return "{\r\n"
				+ "  \"name\": \"" + networkName + "\",\r\n"
				+ "  \"urls\": [\r\n"
				+ "    \"" + networkUrl + "\"\r\n"
				+ "  ]\r\n"
				+ "}";
		
	}
	
	public static String acceptNetworkInviteFromVendor(String networkId) {
		
		return "{\r\n"
				+ "    \"network_id\": \"" + networkId + "\"\r\n"
				+ "}";
		
	}
	
	public static String sendInviteToVendorFromNetwork(String vendorId) {
		
		return "{\r\n"
				+ "    \"vendor_id\": \"" + vendorId + "\"\r\n"
				+ "}";
		
	}
		
	public static String updateAttributionDetails(String vendorId, String attributionId) {
		
		return "{\r\n"
				+ "  \"id\": \"" + attributionId + "\",\r\n"
				+ "  \"vendorId\": \"" + vendorId + "\",\r\n"
				+ "  \"attributionConfig\": {\r\n"
				+ "    \"configs\": {\r\n"
				+ "      \"l1\": {\r\n"
				+ "        \"percentage\": 20,\r\n"
				+ "        \"timeLimit\": 259200000000000,\r\n"
				+ "        \"timeLimitUnit\": \"hours\"\r\n"
				+ "      },\r\n"
				+ "      \"l2\": {\r\n"
				+ "        \"percentage\": 20,\r\n"
				+ "        \"timeLimit\": 129600000000000,\r\n"
				+ "        \"timeLimitUnit\": \"hours\"\r\n"
				+ "      },\r\n"
				+ "      \"lIntro\": {\r\n"
				+ "        \"percentage\": 20,\r\n"
				+ "        \"timeLimit\": null,\r\n"
				+ "        \"timeLimitUnit\": \"infinite\"\r\n"
				+ "      },\r\n"
				+ "      \"lX\": {\r\n"
				+ "        \"percentage\": 40,\r\n"
				+ "        \"timeLimit\": 3600000000000,\r\n"
				+ "        \"timeLimitUnit\": \"hours\"\r\n"
				+ "      },\r\n"
				+ "      \"networkCommission\": {\r\n"
				+ "        \"percentage\": 30,\r\n"
				+ "        \"timeLimit\": null,\r\n"
				+ "        \"timeLimitUnit\": \"\"\r\n"
				+ "      }\r\n"
				+ "    },\r\n"
				+ "    \"adjustmentConfigs\": null\r\n"
				+ "  }\r\n"
				+ "}";
		
	}
	
	public static String syncShopifyProducts(String isAdult, String isAgeRestricted, String shopifyToken) {
		
		return "{\r\n"
				+ "  \"storeName\": \"shop-types\",\r\n"
				+ "  \"accessToken\": \"" + shopifyToken + "\",\r\n"
				+ "  \"timestamp\": " + Utilities.getUnixEpochTime() + ",\r\n"
				+ "  \"enableCheckoutShoptype\": true,\r\n"
				+ "  \"restrictions\": {\r\n"
				+ "    \"isAdult\": \"" + isAdult +"\",\r\n"
				+ "    \"isAgeRestricted\": \"" + isAgeRestricted + "\"\r\n"
				+ "  }\r\n"
				+ "}";
		
	}
	
	public static String syncWoocommerceProducts() {
		
		return "{\r\n"
				+ "  \"storeName\": \"https://shoptypewoo.wpcomstaging.com\",\r\n"
				+ "  \"storeHostUrl\": \"https://shoptypewoo.wpcomstaging.com\",\r\n"
				+ "  \"consumerKey\": \"ck_b22be12d33b3bee1365fb2776aaff11d6c9d7c9a\",\r\n"
				+ "  \"consumerSecret\": \"cs_11d03e4028aaec811ef45dd1b246250e030fb517\",\r\n"
				+ "  \"dokan_vendor_id\": \"176134914\",\r\n"
				+ "  \"dokan_vendor_name\": \"Gada electronics\",\r\n"
				+ "  \"enableCheckoutShoptype\": true,\r\n"
				+ "  \"shippingServiceId\": \"001\"\r\n"
				+ "}";
		
	}
	
	public static String publishProduct(String vendorId, String cosellerId, String slug, String productId) {
		
		return "{"
				+ "\"productId\":\"" + productId + "\","
				+ "\"vendorId\":\"" + vendorId + "\","
				+ "\"referrerLink\":\"https://beta.shoptype.com/cosellerproducts\","
				+ "\"link\":\"" + slug + "\","
				+ "\"medium\":\"copy\","
				+ "\"timestamp\":1629109316231,"
				+ "\"cosellerId\":\"" + cosellerId + "\"}";
		
	}
	
	public static String addItemstoCart(String productId, String variant_id) {
		
		return "{\r\n"
				+ "    \"product_id\": \"" + productId + "\",\r\n"
				+ "    \"product_variant_id\": \"" + variant_id + "\",\r\n"
				+ "    \"quantity\": 1\r\n"
				+ "}";
		
	}
	
	public static String createCheckout(String device_id, String cart_id) {
		
		return "{\r\n"
				+ "  \"deviceId\": \"" + device_id + "\",\r\n"
				+ "  \"cartId\": \"" + cart_id + "\"\r\n"
				+ "}";
		
	}
	
	public static String placeOrder(String checkout_id, String variant_id) {
		
		return "{\r\n"
				+ "    \"meta_data\": [\r\n"
				+ "        {\r\n"
				+ "            \"key\": \"checkout_id\",\r\n"
				+ "            \"value\": \"" + checkout_id + "\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"line_items\": [\r\n"
				+ "        {\r\n"
				+ "            \"product_id\": " + variant_id + ",\r\n"
				+ "            \"quantity\": 1\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
	}
	
	public static String createUserEvent(String device_id, String url, String tracker_id) {
		
		return "{"
				+ "\"device_id\":\"" + device_id + "\","
				+ "\"url\":\"" + url + "\","
				+ "\"tracker_id\":\"" + tracker_id + "\","
				+ "\"referrer\":\"\""
				+ "}";
		
	}
	
	public static String createUserEventWithPlatformUrl(String device_id, String url, String tracker_id, String platformUrl) {
		
		return "{"
				+ "\"device_id\":\"" + device_id + "\","
				+ "\"url\":\"" + url + "\","
				+ "\"tracker_id\":\"" + tracker_id + "\","
				+ "\"referrer\":\"" + platformUrl + "\""
				+ "}";
		
	}
	
	public static String addStripePaymentPayoutConfig(String accountId, String key, String secret, String clientId) {
		
		return "{"
				+ "\"payment_method\":\"stripe\","
				+ "\"currency\":\"USD\","
				+ "\"account_id\":\"" + accountId + "\","
				+ "\"key\":\"" + key + "\","
				+ "\"secret\":\"" + secret + "\","
				+ "\"is_payout_same\":true,"
				+ "\"client_id\":\"" + clientId + "\"" 
				+ "}";
		
	}
	
}
