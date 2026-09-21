package com.transunion.automation.models.account;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data model representing user account profile and registration details.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountData {

    private String name;
    private String email;
    private String password;
    private String title;
    private String birthDate;
    private String birthMonth;
    private String birthYear;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobileNumber;

    /**
     * Converts the account data into a form parameter map matching Automation Exercise API field names.
     *
     * @return Map of form field key-value pairs
     */
    public Map<String, String> toFormParamMap() {
        Map<String, String> params = new HashMap<>();
        putIfNotNull(params, "name", name);
        putIfNotNull(params, "email", email);
        putIfNotNull(params, "password", password);
        putIfNotNull(params, "title", title);
        putIfNotNull(params, "birth_date", birthDate);
        putIfNotNull(params, "birth_month", birthMonth);
        putIfNotNull(params, "birth_year", birthYear);
        putIfNotNull(params, "firstname", firstName);
        putIfNotNull(params, "lastname", lastName);
        putIfNotNull(params, "company", company);
        putIfNotNull(params, "address1", address1);
        putIfNotNull(params, "address2", address2);
        putIfNotNull(params, "country", country);
        putIfNotNull(params, "zipcode", zipcode);
        putIfNotNull(params, "state", state);
        putIfNotNull(params, "city", city);
        putIfNotNull(params, "mobile_number", mobileNumber);
        return params;
    }

    private void putIfNotNull(Map<String, String> map, String key, String value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}
