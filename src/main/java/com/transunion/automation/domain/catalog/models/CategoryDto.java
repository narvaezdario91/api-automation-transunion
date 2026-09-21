package com.transunion.automation.domain.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO representing the category of a product.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {
    private UserTypeDto usertype;
    private String category;
}
