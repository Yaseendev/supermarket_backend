package com.yaseenworks.supermarket.user.controllers;

import com.yaseenworks.supermarket.user.models.AuthenticationResponse;
import com.yaseenworks.supermarket.user.records.AuthenticationRequest;
import com.yaseenworks.supermarket.user.records.GoogleAuthRequest;
import com.yaseenworks.supermarket.user.records.RegistrationRequest;
import com.yaseenworks.supermarket.user.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) {
            var result = authService.register(request);
            return ResponseEntity.ok(result);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/google")
    public ResponseEntity<AuthenticationResponse> googleRegister(
            @RequestBody @Valid GoogleAuthRequest request
    ) {
        return ResponseEntity.ok(authService.googleAuthenticate(request));
    }

    @PostMapping("/token-check")
    public ResponseEntity<?> tokenCheck(@RequestHeader String Authorization) {
        System.out.println("Token Check Res: " + Authorization);
       final AuthenticationResponse res = authService.tokenCheck(Authorization);
        if(res.getUser() != null)
         return ResponseEntity.ok(res);
        return ResponseEntity.status(403).body("Token has been expired");
    }

}
