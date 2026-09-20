package com.transunion.automation.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Response payload model representing the API response for user operations.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {
    private String id;
    private String name;
    private String job;
    private String email;
    private String createdAt;
    private String updatedAt;
}
