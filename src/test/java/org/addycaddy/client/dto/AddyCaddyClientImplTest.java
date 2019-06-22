package org.addycaddy.client.dto;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is not a true unit test since it runs against a live server and the server will maintain state. Therefore,
 * these tests must be run in the specified order.
 *
 * It is recommended that you start the server using an in memory database and then run these tests against it.
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class AddyCaddyClientImplTest {
    public static final String          COUNTRY_CODE_US = "US";
    public static final String          CUSTOMER_ID = "zbeeblebrox";
    private static final String         TEST_HOSTNAME = "localhost";

    private static AddyCaddyClientImpl  addyCaddyClient;
    private static ContactPointDto      dtoAddress;
    private static ContactPointDto      dtoEmail;
    private static ContactPointDto      dtoPhone;

    public static ContactPointDto getBillingEmail() {
        ContactPointDto result = new ContactPointDto();

        result.setContactPointType(ContactPointDto.TYPE_BILLING_EMAIL);
        result.setCustomerId(CUSTOMER_ID);
        result.setEmail("zbeeblebrox@galaxy.gov");

        return result;
    }


    public static ContactPointDto getBillingPhone() {
        ContactPointDto result = new ContactPointDto();

        result.setContactPointType(ContactPointDto.TYPE_BILLING_PHONE);
        result.setCustomerId(CUSTOMER_ID);
        result.setPhoneNumber("6145551212");
        result.setCountryCode(COUNTRY_CODE_US);

        return result;
    }

    public static ContactPointDto getLocation() {
        ContactPointDto result = new ContactPointDto();

        result.setCountryCode(COUNTRY_CODE_US);
        result.setContactPointType(ContactPointDto.TYPE_LOCATION_ADDR);
        result.setCustomerId(CUSTOMER_ID);
        result.setAttention("Billing Dept.");
        result.setName("Zaphod Beeblebrox");
        result.setStreet1("100 N. High St.");
        result.setStreet2("Suite 405");
        result.setCity("Columbus");
        result.setState("OH");
        result.setPostalCode("42315");

        return result;
    }

    @BeforeAll
    static void initialize() throws Exception {
        addyCaddyClient = new AddyCaddyClientImpl(TEST_HOSTNAME);

        dtoEmail = getBillingEmail();
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.create(dtoEmail));

        dtoAddress = getLocation();
        dtoPhone = getBillingPhone();
        ContactPointDto[] dtos = new ContactPointDto[] {dtoPhone, dtoAddress};
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.create(dtos));
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFind() throws Exception {
        ContactPointDto[] dtos = addyCaddyClient.findByCustomerId(CUSTOMER_ID);
        assertEquals(3, dtos.length);

        for (ContactPointDto dto : dtos) {
            assertEquals(CUSTOMER_ID, dto.getCustomerId());
            assertNotNull(dto.getAddressId());

            switch (dto.getContactPointType()) {
                case ContactPointDto.TYPE_LOCATION_ADDR:
                    assertEquals(dtoAddress.getStreet1(), dto.getStreet1());
                    break;

                case ContactPointDto.TYPE_BILLING_EMAIL:
                    assertEquals(dtoEmail.getEmail(), dto.getEmail());
                    break;

                case ContactPointDto.TYPE_BILLING_PHONE:
                    assertEquals(dtoPhone.getPhoneNumber(), dto.getPhoneNumber());
                    break;

                default:
                    fail("Invalid type:" + dto.getContactPointType());
            }
        }
    }

    @Test
    void testSearch() throws Exception {
        //create contacts for other customer
        String otherCustomer = "adent";

        ContactPointDto dto4 = getBillingEmail();
        dto4.setCustomerId(otherCustomer);
        final String otherEmail = "adent@earth.com";
        dto4.setEmail(otherEmail);
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.create(dto4));

        ContactPointDto dto5 = getBillingPhone();
        dto5.setCustomerId(otherCustomer);
        final String otherPhone = "7405551212";
        dto5.setPhoneNumber(otherPhone);
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.create(dto5));

        ContactPointDto dto6 = getLocation();
        dto6.setCustomerId(otherCustomer);
        final String otherZip = "01234";
        dto6.setPostalCode(otherZip);
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.create(dto6));

        //search by email
        ContactPointDto[] result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_EMAIL, dtoEmail.getEmail());
        assertEquals(1, result.length);
        ContactPointDto resultDto = result[0];
        assertEquals(CUSTOMER_ID, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_BILLING_EMAIL, resultDto.getContactPointType());
        assertEquals(dtoEmail.getEmail(), resultDto.getEmail());

        result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_EMAIL, otherEmail);
        assertEquals(1, result.length);
        resultDto = result[0];
        assertEquals(otherCustomer, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_BILLING_EMAIL, resultDto.getContactPointType());
        assertEquals(otherEmail, resultDto.getEmail());

        //search by phone
        result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_PHONE, dtoPhone.getPhoneNumber());
        assertEquals(1, result.length);
        resultDto = result[0];
        assertEquals(CUSTOMER_ID, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_BILLING_PHONE, resultDto.getContactPointType());
        assertEquals(dtoPhone.getPhoneNumber(), resultDto.getPhoneNumber());

        result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_PHONE, otherPhone);
        assertEquals(1, result.length);
        resultDto = result[0];
        assertEquals(otherCustomer, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_BILLING_PHONE, resultDto.getContactPointType());
        assertEquals(otherPhone, resultDto.getPhoneNumber());

        //search by postal code
        result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_POSTAL_CODE, dtoAddress.getPostalCode());
        assertEquals(1, result.length);
        resultDto = result[0];
        assertEquals(CUSTOMER_ID, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_LOCATION_ADDR, resultDto.getContactPointType());
        assertEquals(dtoAddress.getPostalCode(), resultDto.getPostalCode());

        result = addyCaddyClient.search(AddyCaddyConstants.SEARCH_BY_POSTAL_CODE, otherZip);
        assertEquals(1, result.length);
        resultDto = result[0];
        assertEquals(otherCustomer, resultDto.getCustomerId());
        assertEquals(ContactPointDto.TYPE_LOCATION_ADDR, resultDto.getContactPointType());
        assertEquals(otherZip, resultDto.getPostalCode());
    }

    @Test
    void testUpdate() throws Exception {
        ContactPointDto[] dtos = addyCaddyClient.findByCustomerId(CUSTOMER_ID);
        ContactPointDto myDto = null;

        for (ContactPointDto dto : dtos) {
            if (ContactPointDto.TYPE_LOCATION_ADDR.equals(dto.getContactPointType())) {
                myDto = dto;
                break;
            }
        }

        final String newStreet = "100 S. High St.";
        myDto.setStreet1(newStreet);
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.update(myDto));

        dtos = addyCaddyClient.findByCustomerId(CUSTOMER_ID);

        for (ContactPointDto dto : dtos) {
            if (ContactPointDto.TYPE_BILLING_ADDR.equals(dto.getContactPointType())) {
                myDto = dto;
                break;
            }
        }

        assertEquals(newStreet, myDto.getStreet1());
    }

    @Test
    void testUpdate_array() throws Exception {
        ContactPointDto[] dtos = addyCaddyClient.findByCustomerId(CUSTOMER_ID);
        ContactPointDto phoneDto = null;
        ContactPointDto emailDto = null;

        for (ContactPointDto dto : dtos) {
            if (ContactPointDto.TYPE_BILLING_EMAIL.equals(dto.getContactPointType())) {
                emailDto = dto;
            }
            else if (ContactPointDto.TYPE_BILLING_PHONE.equals(dto.getContactPointType())) {
                phoneDto = dto;
            }
        }
        assertNotNull(phoneDto);
        assertNotNull(emailDto);

        final String newPhone = "123456789";
        phoneDto.setPhoneNumber(newPhone);

        final String newEmail = "slartibartfast@magrathea.com";
        emailDto.setEmail(newEmail);

        dtos = new ContactPointDto[] {phoneDto, emailDto};
        assertEquals(AddyCaddyConstants.RESPONSE_SUCCESS, addyCaddyClient.update(dtos));

        dtos = addyCaddyClient.findByCustomerId(CUSTOMER_ID);

        boolean foundEmail = false;
        boolean foundPhone = false;

        for (ContactPointDto dto : dtos) {
            if (ContactPointDto.TYPE_BILLING_EMAIL.equals(dto.getContactPointType())) {
                assertEquals(newEmail, dto.getEmail());
                foundEmail = true;
            }
            else if (ContactPointDto.TYPE_BILLING_PHONE.equals(dto.getContactPointType())) {
                assertEquals(newPhone, dto.getPhoneNumber());
                foundPhone = true;
            }
        }

        assertTrue(foundEmail);
        assertTrue(foundPhone);
    }
}
