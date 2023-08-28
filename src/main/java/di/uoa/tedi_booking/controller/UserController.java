package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import di.uoa.tedi_booking.entities.User;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    private final UserService userService;

    @Autowired
    public UserController(UserService service){
        super(service);
        this.userService = service;
    }

    @GetMapping(path = "/usersMeAitimaEggrafis")
    public @ResponseBody List<User> usersMeAitimaEggrafis(){
        return userService.usersMeAitimaEggrafis();
    }
}
