package com.transunion.automation.models.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO representing user account profile details returned by the API.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String title;

    @JsonProperty("birth_day")
    private String birthDay;

    @JsonProperty("birth_month")
    private String birthMonth;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
}
