package com.core.modules.auth.controller;

import com.core.modules.auth.service.AuthService;
import com.core.shared.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return service.login(req);
    }

    // REGISTER
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        return service.register(req);
    }

    // REFRESH
    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest req) {
        return service.refresh(req.refreshToken);
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(@RequestBody RefreshRequest req) {
        service.logout(req.refreshToken);
        return "logged out";
    }

    // PROFILE
    @GetMapping("/profile")
    public ProfileResponse profile(@RequestHeader("Authorization") String token) {
        String accessToken = token.replace("Bearer ", "");
        return service.profile(accessToken);
    }
}
