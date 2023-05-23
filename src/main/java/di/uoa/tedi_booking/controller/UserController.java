package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import di.uoa.tedi_booking.entities.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    private final UserService userService;

    @Autowired
    public UserController(UserService service){
        super(service);
        this.userService = service;
    }
}
