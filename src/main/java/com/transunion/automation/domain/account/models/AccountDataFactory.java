package com.transunion.automation.domain.account.models;

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
     * Builds test account data with specified email, password and name, filling remaining required profile fields.
     *
     * @param email    Account email
     * @param password Account password
     * @param name     Full display name for the account
     * @return Populated AccountData with the given name
     */
    public static AccountData withEmailPasswordAndName(String email, String password, String name) {
        return AccountData.builder()
                .name(name)
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

    /**
     * Builds test account data missing mandatory registration fields (email and password omitted).
     *
     * @return AccountData with only name populated
     */
    public static AccountData withoutRequiredFields() {
        return AccountData.builder()
                .name("Missing Fields User")
                .build();
    }
}
