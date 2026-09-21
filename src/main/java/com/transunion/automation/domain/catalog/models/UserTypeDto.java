package com.transunion.automation.domain.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO representing the user type within a category.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTypeDto {
    private String usertype;
}
