package com.core.modules.auth.controller;

import com.core.modules.auth.service.AuthService;
import com.core.shared.dto.*;
import com.core.shared.util.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        return ResponseHelper.ok(service.register(req), "Register success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        return ResponseHelper.ok(service.login(req), "Login success");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest req) {
        return ResponseHelper.ok(service.refresh(req.refreshToken), "Refresh success");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshRequest req) {
        service.logout(req.refreshToken);
        return ResponseHelper.ok(null, "Logout success");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String token) {
        return ResponseHelper.ok(
                service.profile(token.replace("Bearer ", "")),
                "Profile fetched"
        );
    }
}
