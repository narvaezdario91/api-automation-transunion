package com.transunion.automation.models.account;

/**
 * Factory providing predefined and dynamic AccountData instances for testing.
 */
public final class AccountDataFactory {

    public static final String DEFAULT_EMAIL = "transunion.automation.test@gmail.com";
    public static final String DEFAULT_PASSWORD = "Test@Automation2024";

    private AccountDataFactory() {
        // Utility factory
    }

    /**
     * Builds standard default test user account data.
     *
     * @return AccountData populated with default values
     */
    public static AccountData defaultUser() {
        return withEmailAndPassword(DEFAULT_EMAIL, DEFAULT_PASSWORD);
    }

    /**
     * Builds test account data with specified email and password, filling remaining required profile fields.
     *
     * @param email    Account email
     * @param password Account password
     * @return Populated AccountData
     */
    public static AccountData withEmailAndPassword(String email, String password) {
        return AccountData.builder()
                .name("Automation Test User")
                .email(email)
                .password(password)
                .title("Mr")
                .birthDate("15")
                .birthMonth("08")
                .birthYear("1990")
                .firstName("Automation")
                .lastName("Tester")
                .company("TransUnion")
                .address1("Av Calle 100 # 15-20")
                .address2("Piso 5")
                .country("United States")
                .zipcode("110111")
                .state("California")
                .city("Los Angeles")
                .mobileNumber("3001234567")
                .build();
    }

    /**
     * Builds a uniquely generated dynamic account for isolated lifecycle tests (e.g. create & delete).
     *
     * @return AccountData with timestamped unique email
     */
    public static AccountData dynamicUser() {
        long timestamp = System.currentTimeMillis();
        String uniqueEmail = "auto_user_" + timestamp + "@testautomation.com";
        return withEmailAndPassword(uniqueEmail, "Pass@" + timestamp);
    }
}
