package com.transunion.automation.models.account;

import java.util.HashMap;
import java.util.Map;

/**
 * Data model representing user account profile and registration details.
 */
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

    public AccountData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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
