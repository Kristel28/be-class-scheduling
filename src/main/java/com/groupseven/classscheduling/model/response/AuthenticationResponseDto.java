package com.groupseven.classscheduling.model.response;

import lombok.Data;

@Data
public class AuthenticationResponseDto {

    private String token;
    private String tokenType = "Bearer ";

    public AuthenticationResponseDto(String token) {
        this.token = token;
    }
}