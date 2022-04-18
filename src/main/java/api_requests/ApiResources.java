package api_requests;

public enum ApiResources {
	
	Register("/register"), 
	LoginAPI("/login"), 
	AuthAPI("/authenticate"), 
	VendorProfileCreation("/vendors"),
	NetworkProfileCreation("/networks"),
	GetCosellerProfileDetails("/cosellers"),
	SyncShopifyStoreAPI("/store/shopify"),
	SyncWoocommerceStoreAPI("/store/woo-commerce"), 
	VendorDetailsAPI("/me"),
	PublishSlug("/track/publish-slug"), 
	PublishProduct("/publish-product"),
	ProductSearch("/products"),
	CreateVendorAttribution("/vendor-attribution"),
	GetVendorAttributionDetails("/vendor-attribution/{vendorId}"),
	CosellerDashboard("/coseller-dashboard"),
	CreateCart("/cart"),
	CreateUserEvent("/track/user-event"),
	CreateCheckout("/checkout"),
	WooCommercePlaceOrder("/wp-json/wc/v3/orders"),
	AwakeMoneyAccount("/awake-money/accounts"),
	NetworkDetails("/networks"),
	InviteVendorToNetwork("/network-vendor-connections"),
	ManageVendorsForNetwork("/networks/manage-vendors"),
	DeleteVendorFromNetwork("/network-vendor-connections/{vendorId}"),
	AcceptNetworkInviteFromVendor("/vendor-network-connections/accept"),
	DeleteNetworkFromVendor("/vendor-network-connections/{networkId}"),
	GetNetworkConnectionsForVendor("/vendor-network-connections"),
	AddPaymentConfig("/payment-config"),
	AddPayoutConfig("/payout-config"),
	GetVendorsFromCoseller("/vendors"),
	GetProductsForVendorFromCoseller("/products"),
	CheckoutDetails("/checkout/{checkoutId}"),
	DeleteUser("/user/");

	private String resource;

	ApiResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;

	}

}