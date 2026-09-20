package com.transunion.automation.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Request payload model implementing the Builder pattern for creating users.
 */
@Getter
@Builder(toBuilder = true)
@Jacksonized
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto {
    private String name;
    private String job;
    private String email;
}
