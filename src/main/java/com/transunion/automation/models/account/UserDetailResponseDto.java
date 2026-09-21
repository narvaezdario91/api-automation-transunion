package com.transunion.automation.models.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

/**
 * Response DTO representing the response from GET /api/getUserDetailByEmail.
 */
@Getter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailResponseDto {

    private Integer responseCode;
    private UserDto user;
}
