package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    RoleController(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    @GetMapping(path = "/all")
    public @ResponseBody List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping(path="{id}")
   public @ResponseBody Role findRole(@PathVariable Integer id){
        return roleRepository.findById(id);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addRole(@RequestBody Role role){
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.OK).body("New role saved successfully");
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateRole(@RequestBody Role role){
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.OK).body("Role updated successfully");
    }

    @DeleteMapping(path = "/remove/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleRepository.findById(id).ifPresent(roleRepository::delete);
        return ResponseEntity.status(HttpStatus.OK).body("Role deleted successfully");
    }

}