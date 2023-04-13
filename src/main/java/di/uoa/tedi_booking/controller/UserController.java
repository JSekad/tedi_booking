package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import di.uoa.tedi_booking.entities.User;

@Controller
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    @Autowired
    public UserController(UserRepository userRepository){super(userRepository);}
}
