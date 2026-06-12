package com.seek_with_sight.email.infrastructure.in.rest;

import com.seek_with_sight.email.application.port.in.ResendVerificationUseCase;
import com.seek_with_sight.email.application.port.in.VerifyEmailUseCase;
import com.seek_with_sight.email.infrastructure.in.rest.dto.ResendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final VerifyEmailUseCase verifyEmailUseCase;
    private final ResendVerificationUseCase resendVerificationUseCase;

    @GetMapping("/verify")
    public ResponseEntity<Void> verify(@RequestParam String token) {
        verifyEmailUseCase.verify(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-verification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resend(@RequestBody ResendRequest request) {
        resendVerificationUseCase.resend(request.email());
    }
}
