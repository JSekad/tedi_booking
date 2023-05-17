package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/role")
public class RoleController extends GenericController<Role> {

    @Autowired
    public RoleController(RoleRepository roleRepository){super(roleRepository);}
}