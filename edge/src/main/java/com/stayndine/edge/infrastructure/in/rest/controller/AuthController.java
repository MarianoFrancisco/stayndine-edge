package com.stayndine.edge.infrastructure.in.rest.controller;

import com.stayndine.edge.application.port.in.command.LoginUseCase;
import com.stayndine.edge.application.port.in.command.RefreshUseCase;
import com.stayndine.edge.application.port.in.command.LogoutUseCase;
import com.stayndine.edge.infrastructure.in.rest.dto.auth.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RefreshUseCase refreshUseCase;
    private final LogoutUseCase logoutUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest body) {
        return ResponseEntity.ok(loginUseCase.handle(body));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshRequest body) {
        return ResponseEntity.ok(refreshUseCase.handle(body));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest body) {
        logoutUseCase.handle(body);
        return ResponseEntity.noContent().build();
    }
}
