package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String firstName, @RequestParam String alias, @RequestParam String title) {

        Role user = new Role();
        user.setAlias(alias);
        user.setTitle(title);
        roleRepository.save(user);
        return "Role Created";
    }

    @PostMapping(path = "/remove/{Id}")
    public @ResponseBody String deleteUser(@PathVariable String Id) {
        List<Role> r = roleRepository.findById(Long.parseLong(Id)).stream().toList();
        roleRepository.deleteAll(r);
        return "Deleted";
    }

    @GetMapping(path = "/all")
    public @ResponseBody List< Role > getAllUsers() {
        return roleRepository.findAll();
    }

}