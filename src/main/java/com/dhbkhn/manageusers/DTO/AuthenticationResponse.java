package com.dhbkhn.manageusers.DTO;

import com.dhbkhn.manageusers.enums.Role;

public class AuthenticationResponse {
    private String name;
    private String token;
    private String message;
    private Role role;
    private int id;
    private String avatar;
    private String email;
    private String phone;

    public AuthenticationResponse(String token, String message, Role role, String name, int id,
            String avatar, String email, String phone) {
        this.token = token;
        this.message = message;
        this.role = role;
        this.name = name;
        this.id = id;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
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

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
