package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.DTOS.security.AuthenticationRequest;
import di.uoa.tedi_booking.DTOS.security.AuthenticationResponse;
import di.uoa.tedi_booking.DTOS.security.RegisterRequest;
import di.uoa.tedi_booking.services.AuthenticationService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthenticationService auth;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(auth.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(auth.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request) throws IOException, java.io.IOException {
        return ResponseEntity.ok(auth.refreshToken(request));
    }
}
