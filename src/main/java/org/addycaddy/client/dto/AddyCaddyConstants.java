package org.addycaddy.client.dto;

public interface AddyCaddyConstants {
    public static final String          SEARCH_BY_EMAIL = "email";
    public static final String          SEARCH_BY_PHONE = "phone";
    public static final String          SEARCH_BY_POSTAL_CODE = "postalCode";

    public static final String          KEY_CONTACT_POINT = "contactPoint";
    public static final String          KEY_CONTACT_POINTS = "contactPoints";
    public static final String          KEY_CUSTOMER_ID = "customerId";
    public static final String          KEY_SEARCH_BY = "searchBy";
    public static final String          KEY_SEARCH_KEY = "searchKey";

    public static final String          PATH_CREATE = "/create";
    public static final String          PATH_CREATE_MANY = "/createMany";
    public static final String          PATH_FIND_BY_CUST_ID = "/findByCustomerId";
    public static final String          PATH_SEARCH = "/search";
    public static final String          PATH_UPDATE = "/update";
    public static final String          PATH_UPDATE_MANY = "/updateMany";

    public static final String          RESPONSE_FAILURE = "failure";
    public static final String          RESPONSE_SUCCESS = "success";
}
