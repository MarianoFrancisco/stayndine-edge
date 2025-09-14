package com.stayndine.edge.infrastructure.in.rest.controller;

import com.stayndine.edge.application.port.in.command.SignupUseCase;
import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupRequest;
import com.stayndine.edge.infrastructure.in.rest.dto.signup.SignupResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/signup")
@RequiredArgsConstructor
public class SignupController {

    private final SignupUseCase signup;

    @PostMapping
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest req) {
        return ResponseEntity.ok(signup.handle(req));
    }
}