package com.core.auth.interfaces.rest;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.core.auth.application.usecase.*;
import com.core.auth.dto.*;

@RestController
@RequestMapping("/auth")
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
    public void register(@RequestBody AuthRequest req) {
        register.execute(req.email, req.password);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return login.execute(req.email, req.password);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody Map<String,String> body) {
        return refresh.execute(body.get("refreshToken"));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Map<String,String> body) {
        logout.execute(body.get("refreshToken"));
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}
