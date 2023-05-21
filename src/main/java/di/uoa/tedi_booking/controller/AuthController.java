package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.config.security.AuthenticationRequest;
import di.uoa.tedi_booking.config.security.AuthenticationResponse;
import di.uoa.tedi_booking.config.security.RegisterRequest;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    public AuthenticationService auth;


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

    @RequestMapping("/login")
    public String login() {
        return "login.jsp";
    }
}
