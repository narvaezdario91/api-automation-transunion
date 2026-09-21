package com.transunion.automation.domain.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Response DTO representing the list of products returned by the catalog API.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsListResponseDto {
    private Integer responseCode;
    private List<ProductDto> products;
}
