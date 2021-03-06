package org.addycaddy.client.dto;

public class ContactPointDto {
    public static final String          TYPE_BILLING_ADDR      = "BillingAddress";
    public static final String          TYPE_BILLING_EMAIL     = "BillingEmail";
    public static final String          TYPE_BILLING_FAX       = "BillingFax";
    public static final String          TYPE_BILLING_PHONE     = "BillingPhone";
    public static final String          TYPE_BUSINESS_ADDR     = "BusinessAddr";
    public static final String          TYPE_BUSINESS_EMAIL    = "BusinessEmail";
    public static final String          TYPE_BUSINESS_FAX      = "BusinessFax";
    public static final String          TYPE_BUSINESS_PHONE    = "BusinessPhone";
    public static final String          TYPE_HOME_ADDR         = "HomeAddr";
    public static final String          TYPE_HOME_EMAIL        = "HomeEmail";
    public static final String          TYPE_HOME_FAX          = "HomeFax";
    public static final String          TYPE_HOME_PHONE        = "HomePhone";
    // this is a business location, which may be different from billing and shipping address
    public static final String          TYPE_LOCATION_ADDR     = "LocationAddress";
    public static final String          TYPE_LOCATION_EMAIL    = "LocationEmail";
    public static final String          TYPE_LOCATION_FAX      = "LocationFax";
    public static final String          TYPE_LOCATION_PHONE    = "LocationPhone";
    public static final String          TYPE_SHIPPING_ADDR     = "ShippingAddress";
    public static final String          TYPE_WORK_ADDR         = "WorkAddr";
    public static final String          TYPE_WORK_EMAIL        = "WorkEmail";
    public static final String          TYPE_WORK_FAX          = "WorkFax";
    public static final String          TYPE_WORK_PHONE        = "WorkPhone";

    private String addressId;

    private String customerId;

    private String additionalId;

    private String contactPointType;

    private String attention;

    private String name;

    private String street1;

    private String street2;

    private String city;

    private String state;

    private String postalCode;

    private String countryCode;

    private String email;

    private String phoneExtension;

    private String phoneNumber;

    private String latitude;

    private String longitude;

    /**
     * An optional additional owner ID that can be used along with customer ID to define ownership of a group of addresses.
     * @return the additional ID
     */
    public String getAdditionalId() {
        return additionalId;
    }

    /**
     * An optional additional owner ID that can be used along with customer ID to define ownership of a group of addresses.
     * @param additionalId the additional ID
     */
    public void setAdditionalId(String additionalId) {
        this.additionalId = additionalId;
    }

    /**
     * The type of contact point. A given customer ID/additional ID can only have one contact point of a type at a time.
     * If a new one is created, the old one will be ended.
     * @return the contact point type
     */
    public String getContactPointType() {
        return contactPointType;
    }

    /**
     * The type of contact point. A given customer ID/additional ID can only have one contact point of a type at a time.
     * If a new one is created, the old one will be ended.
     * @param contactPointType the contact point type
     */
    public void setContactPointType(String contactPointType) {
        this.contactPointType = contactPointType;
    }

    /**
     * Unique identifier for this address. Will be set by AddyCaddy if not provided.
     * @return the address ID
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * Unique identifier for this address. Will be set by AddyCaddy if not provided.
     * @param addressId the address ID
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * The primary ID for the owner of this contact point. It is expected for several contact points to have the same
     * customer ID.
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * The primary ID for the owner of this contact point. It is expected for several contact points to have the same
     * customer ID.
     * @param customerId the customer ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "ContactPointDto{" +
                "addressId='" + addressId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", additionalId='" + additionalId + '\'' +
                ", contactPointType='" + contactPointType + '\'' +
                ", attention='" + attention + '\'' +
                ", name='" + name + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
