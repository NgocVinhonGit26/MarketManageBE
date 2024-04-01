package com.dhbkhn.manageusers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhbkhn.manageusers.DTO.AuthenticationResponse;
import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Trả về tên của file index.html (không cần đuôi .html nếu đã cấu hình
                        // thymeleaf hoặc template engine tương tự)
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/signout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successfully");
    }
}
