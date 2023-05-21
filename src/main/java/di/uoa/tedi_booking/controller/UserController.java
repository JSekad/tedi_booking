package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.config.security.AuthenticationRequest;
import di.uoa.tedi_booking.config.security.AuthenticationResponse;
import di.uoa.tedi_booking.config.security.RegisterRequest;
import di.uoa.tedi_booking.repositories.UserRepository;
import di.uoa.tedi_booking.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import di.uoa.tedi_booking.entities.User;

@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    @Autowired
    public UserController(UserRepository userRepository){super(userRepository);}
}
