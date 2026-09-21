package com.transunion.automation.domain.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO representing an individual product in the catalog.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private Integer id;
    private String name;
    private String price;
    private String brand;
    private CategoryDto category;
}
