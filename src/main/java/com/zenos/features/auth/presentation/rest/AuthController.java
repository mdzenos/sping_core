package com.core.auth.interfaces.rest;

import org.springframework.web.bind.annotation.*;

import com.core.auth.application.usecase.*;
import com.core.auth.dto.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase login;
    private final RegisterUseCase register;
    private final RefreshUseCase refresh;
    private final LogoutUseCase logout;

    public AuthController(
            LoginUseCase l,
            RegisterUseCase r,
            RefreshUseCase rf,
            LogoutUseCase lo
    ) {
        this.login = l;
        this.register = r;
        this.refresh = rf;
        this.logout = lo;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        return register.execute(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return login.execute(req);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody Map<String, String> body) {
        return refresh.execute(body.get("refreshToken"));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Map<String, String> body) {
        logout.execute(body.get("refreshToken"));
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}
