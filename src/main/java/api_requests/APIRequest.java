package api_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIRequest {

	static RequestSpecification request;
	static Response response;
	static ApiResources apiresource;

	public static RequestSpecification getSpecswithoutToken(String baseUri) {

		RestAssured.baseURI = baseUri;
		request = RestAssured.given().header("Content-Type", "application/json");
		return request;

	}

	public static RequestSpecification getSpecswithToken(String baseUri, String authToken) {

		RestAssured.baseURI = baseUri;
		request = RestAssured.given()
					.header("Content-Type", "application/json")
					.header("Authorization", authToken.replace("\"", ""));
		return request;

	}
	
	public static Response registerUser(String baseUri, String payload, String platformId) {
		
		apiresource = ApiResources.valueOf("Register");
		request = getSpecswithoutToken(baseUri)
					.body(payload)
					.header("X-Shoptype-PlatformId", platformId.replace("\"", ""));
		response = request.post(apiresource.getResource());
		return response;
		
	} 
	
	public static Response authenticateUser(String baseUri, String userType, String payload, String authToken) {
		
		apiresource = ApiResources.valueOf("AuthAPI");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response loginUser(String baseUri, String payload, String platformId) {
		
		apiresource = ApiResources.valueOf("LoginAPI");
		
		if(platformId != null) {
			
			request = getSpecswithoutToken(baseUri)
					.body(payload)
					.header("X-Shoptype-PlatformId", platformId);
			response = request.post(apiresource.getResource());
			
		} else {
			
			request = getSpecswithoutToken(baseUri)
					.body(payload);
			response = request.post(apiresource.getResource());
			
		}
		
		return response;
		
	}
	
	public static Response createVendorProfile(String baseUri, String payload, String authToken) {
		
		apiresource = ApiResources.valueOf("VendorProfileCreation");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	} 
	
	public static Response createCosellerProfile(String baseUri, String authToken) {
		
		apiresource = ApiResources.valueOf("GetCosellerProfileDetails");
		request = getSpecswithToken(baseUri, authToken)
					.body("{}");
		response = request.post(apiresource.getResource());
		return response;
		
	} 
	
	public static Response createNetworkProfile(String baseUri, String payload, String authToken) {
		
		apiresource = ApiResources.valueOf("NetworkProfileCreation");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response sendInvitetoVendorFromNetwork(String baseUri, String payload, String networkAuthToken) {
		
		apiresource = ApiResources.valueOf("InviteVendorToNetwork");
		request = getSpecswithToken(baseUri, networkAuthToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response acceptNetworkInviteFromVendor(String baseUri, String payload, String vendorAuthToken) {
		
		apiresource = ApiResources.valueOf("AcceptNetworkInviteFromVendor");
		request = getSpecswithToken(baseUri, vendorAuthToken)
					.body(payload);
		response = request.put(apiresource.getResource());
		return response;
		
	}
	
	public static Response manageVendorsFromNetwork(String baseUri, String networkAuthToken) {
		
		apiresource = ApiResources.valueOf("ManageVendorsForNetwork");
		request = getSpecswithToken(baseUri, networkAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getNetworkConnectionsForVendor(String baseUri, String vendorAuthToken) {
		
		apiresource = ApiResources.valueOf("GetNetworkConnectionsForVendor");
		request = getSpecswithToken(baseUri, vendorAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getVendorProfileDetails(String baseUri, String vendorAuthToken) {
		
		apiresource = ApiResources.valueOf("VendorDetailsAPI");
		request = getSpecswithToken(baseUri, vendorAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getNetworkProfileDetails(String baseUri, String vendorAuthToken) {
		
		apiresource = ApiResources.valueOf("NetworkProfileCreation");
		request = getSpecswithToken(baseUri, vendorAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
		
	public static Response deleteNetworkFromVendor(String baseUri, String networkId, String vendorAuthToken) {
		
		apiresource = ApiResources.valueOf("DeleteNetworkFromVendor");
		request = getSpecswithToken(baseUri, vendorAuthToken)
					.pathParam("networkId", networkId);
		response = request.delete(apiresource.getResource());
		return response;
		
	}
	
	public static Response deleteVendorFromNetwork(String baseUri, String vendorId, String networkAuthToken) {
		
		apiresource = ApiResources.valueOf("DeleteNetworkFromVendor");
		request = getSpecswithToken(baseUri, networkAuthToken)
					.pathParam("networkId", vendorId);
		response = request.delete(apiresource.getResource());
		return response;
		
	}

	public static void deleteUser(String baseUri, String userId, String authToken) {

		apiresource = ApiResources.valueOf("DeleteUser");
		request = getSpecswithToken(baseUri, authToken);
		response = request.delete(apiresource.getResource() + userId.replace("\"", ""));
		response.then().log().headers().log().status().log().body().assertThat().statusCode(200);

	}
	
	public static Response syncShopifyStore(String baseUri, String authToken, String payload) {

		apiresource = ApiResources.valueOf("SyncShopifyStoreAPI");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;

	}

	public static Response syncWooStore(String baseUri, String authToken, String payload) {

		apiresource = ApiResources.valueOf("SyncWoocommerceStoreAPI");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;

	}
	
	public static Response getAttributionDetails(String baseUri, String vendorAuthToken, String vendorId) {
		
		apiresource = ApiResources.valueOf("GetVendorAttributionDetails");
		request = getSpecswithToken(baseUri, vendorAuthToken)
					.pathParam("vendorId", vendorId);
		response = request.get(apiresource.getResource());
		return response;
		
	}

	public static Response updateAttribution(String baseUri, String authToken, String payload, String attributionId) {

		apiresource = ApiResources.valueOf("CreateVendorAttribution");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.put(apiresource.getResource() + "/" + attributionId);
		return response;

	}

	public static Response fetchProductWithVendorId(String baseUri, String vendorId, String currency) {

		apiresource = ApiResources.valueOf("ProductSearch");
		request = getSpecswithoutToken(baseUri)
					.queryParam("vendorId", vendorId.replace("\"", ""));
		response = request.get(apiresource.getResource());
		return response;

	}

	public static Response getPublishSlug(String baseUri, String authToken, String productId) {

		apiresource = ApiResources.valueOf("PublishSlug");
		request = getSpecswithToken(baseUri, authToken)
					.queryParam("productId", productId.replace("\"", ""));
		response = request.get(apiresource.getResource());
		return response;

	}

	public static void publishProduct(String baseUri, String authToken, String payload) {

		apiresource = ApiResources.valueOf("PublishProduct");
		request = getSpecswithToken(baseUri, authToken)
					.body(payload);
		response = request.post(apiresource.getResource());

	}

	public static Response getCosellerView(String baseUri, String authToken, String viewType, String currency) {

		apiresource = ApiResources.valueOf("CosellerDashboard");
		request = getSpecswithToken(baseUri, authToken)
					.queryParam("viewType", viewType)
					.queryParam("currency", currency);
		response = request.get(apiresource.getResource());
		return response;

	}

	public static Response createEmptyCart(String baseUri, String apiKey) {

		apiresource = ApiResources.valueOf("CreateCart");
		request = getSpecswithoutToken(baseUri)
					.header("X-Shoptype-Api-Key", apiKey.replace("\"", ""))
					.header("Referer", "https://beta.shoptype.com/").header("Origin", "https://beta.shoptype.com/")
					.body("{}");
		response = request.post(apiresource.getResource());
		return response;

	}

	public static Response addItemsToCart(String baseUri, String apiKey, String cart_id, String productId, String variant_id) {

		apiresource = ApiResources.valueOf("CreateCart");
		request = getSpecswithoutToken(baseUri)
					.header("X-Shoptype-Api-Key", apiKey.replace("\"", ""))
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/")
					.body(Payload.addItemstoCart(productId, variant_id));
		response = request.log().all().post(apiresource.getResource() + "/" + cart_id + "/add");
		return response;

	}
	
	public static Response createCheckout(String baseUri, String apiKey, String device_id, String cart_id, String platform_id) {
		
		apiresource = ApiResources.valueOf("CreateCheckout");
		
		if(platform_id != null) {
			
			request = getSpecswithoutToken(baseUri)
					.header("X-Shoptype-Api-Key", apiKey.replace("\"", ""))
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/")
					.header("X-Shoptype-PlatformId", platform_id.replace("\"", ""))
					.body(Payload.createCheckout(device_id, cart_id));
			
		} else {
			
			request = getSpecswithoutToken(baseUri)
					.header("X-Shoptype-Api-Key", apiKey.replace("\"", ""))
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/")
					.body(Payload.createCheckout(device_id, cart_id));
			
		}
		
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response placeOrder(String baseUri, String checkout_id, String variant_id, String username, String password) {
		
		apiresource = ApiResources.valueOf("WooCommercePlaceOrder");
		request = getSpecswithoutToken(baseUri)
				.auth().preemptive().basic(username.replace("\"", ""), password.replace("\"", ""))
				.body(Payload.placeOrder(checkout_id, variant_id));
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response createUserEvent(String baseUri, String payload) {
		
		apiresource = ApiResources.valueOf("CreateUserEvent");
		request = getSpecswithoutToken(baseUri)
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/")
					.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response getVendorAttributionDetails(String baseUri, String vendorId, String authToken) {
		
		apiresource = ApiResources.valueOf("CreateVendorAttribution");
		request = getSpecswithToken(baseUri, authToken)
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/");
		response = request.get(apiresource.getResource() + "/" + vendorId);
		return response;
		
	}
	
	public static Response getWalletDetails(String baseUri, String authToken) {
		
		apiresource = ApiResources.valueOf("AwakeMoneyAccount");
		request = getSpecswithToken(baseUri, authToken)
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/");
		response = request.get(apiresource.getResource());
		return response;
		
	} 
	
	public static Response getVendorOrderDetails(String baseUri, String authToken, String vendorId) {
		
		apiresource = ApiResources.valueOf("VendorProfileCreation");
		request = getSpecswithToken(baseUri, authToken)
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/");
		response = request.get(apiresource.getResource() + "/" + vendorId + "/orders");
		return response;
		
	}
	
	public static Response getNetworkDetails(String baseUri, String authToken) {
		
		apiresource = ApiResources.valueOf("NetworkDetails");
		request = getSpecswithToken(baseUri, authToken)
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/");
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response addPaymentsAndPayouts(String baseUri, String networkAuthToken, String payload) {
		
		apiresource = ApiResources.valueOf("AddPaymentConfig");
		request = getSpecswithToken(baseUri, networkAuthToken)
				.body(payload);
		response = request.post(apiresource.getResource());
		return response;
		
	}
	
	public static Response getPaymentDetails(String baseUri, String networkAuthToken) {
		
		apiresource = ApiResources.valueOf("AddPaymentConfig");
		request = getSpecswithToken(baseUri, networkAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getPayoutDetails(String baseUri, String networkAuthToken) {
		
		apiresource = ApiResources.valueOf("AddPayoutConfig");
		request = getSpecswithToken(baseUri, networkAuthToken);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getVendorsFromCoseller(String baseUri, String restrictionValue, String restrictionKey) {
		
		apiresource = ApiResources.valueOf("GetVendorsFromCoseller");
		request = getSpecswithoutToken(baseUri)
					.queryParam("currency", "USD")
					.queryParam("restrictionsKey", restrictionKey)
					.queryParam("restrictionsValue", restrictionValue);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getProductsForVendorFromCoseller(String baseUri, String cosellerAuthToken, String restrictionValue, String restrictionKey, String vendorId) {
		
		apiresource = ApiResources.valueOf("GetVendorsFromCoseller");
		request = getSpecswithToken(baseUri, cosellerAuthToken)
					.queryParam("vendorId", vendorId)
					.queryParam("currency", "USD")
					.queryParam("includeFields", "currency,catalogId,description,id")
					.queryParam("restrictionsKey", restrictionKey)
					.queryParam("restrictionsValue", restrictionValue);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
	public static Response getCosellerProfileDetails(String baseUri, String cosellerAuthToken) {
		
		apiresource = ApiResources.valueOf("GetCosellerProfileDetails");
		request = getSpecswithToken(baseUri, cosellerAuthToken);
		response = request.get(apiresource.getResource());
		return response;
	}
	
	public static Response getCheckoutDetails(String baseUri, String checkoutId, String apiKey) {
		
		apiresource = ApiResources.valueOf("CheckoutDetails");
		request = getSpecswithoutToken(baseUri)
					.header("x-shoptype-api-key", apiKey.replace("\"", ""))
					.header("Referer", "https://beta.shoptype.com/")
					.header("Origin", "https://beta.shoptype.com/")
					.pathParam("checkoutId", checkoutId);
		response = request.get(apiresource.getResource());
		return response;
		
	}
	
}
