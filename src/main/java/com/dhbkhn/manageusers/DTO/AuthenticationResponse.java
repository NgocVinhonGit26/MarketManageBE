package com.dhbkhn.manageusers.DTO;

import com.dhbkhn.manageusers.enums.Role;

public class AuthenticationResponse {
    private String name;
    private String token;
    private String message;
    private Role role;

    public AuthenticationResponse(String token, String message, Role role, String name) {
        this.token = token;
        this.message = message;
        this.role = role;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
