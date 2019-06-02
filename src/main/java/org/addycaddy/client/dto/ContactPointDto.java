package org.addycaddy.client.dto;

public class ContactPointDto {
    public static final String          TYPE_BILLING_ADDR      = "BillingAddress";
    public static final String          TYPE_BILLING_EMAIL     = "BillingEmail";
    public static final String          TYPE_BILLING_PHONE     = "BillingPhone";
    public static final String          TYPE_BUSINESS_FAX      = "Fax";
    //this is a business location, which may be different from billing and shipping address
    public static final String          TYPE_LOCATION          = "Location";
    public static final String          TYPE_SHIPPING_ADDR     = "ShippingAddress";
    public static final String          TYPE_BUSINESS_EMAIL    = "BusinessEmail";
    public static final String          TYPE_BUSINESS_PHONE    = "BusinessPhone";

    private String addressId;

    private String customerId;

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

    private String phoneNumber;

    private String latitude;

    private String longitude;

    public String getContactPointType() {
        return contactPointType;
    }

    public void setContactPointType(String contactPointType) {
        this.contactPointType = contactPointType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCustomerId() {
        return customerId;
    }

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
