package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.DTOS.PasswordDTO;
import di.uoa.tedi_booking.DTOS.security.AuthenticationRequest;
import di.uoa.tedi_booking.services.AuthenticationService;
import di.uoa.tedi_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import di.uoa.tedi_booking.entities.User;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    private final UserService userService;
    private final AuthenticationService authService;

    @Autowired
    public UserController(UserService service,AuthenticationService authService){
        super(service);
        this.userService = service;
        this.authService = authService;
    }



    @GetMapping(path = "/usersMeAitimaEggrafis")
    public  ResponseEntity<List<User>> usersMeAitimaEggrafis(){
        List<User> users = userService.usersMeAitimaEggrafis();
        System.out.println(users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) throws IOException {
        try{
            return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/changePassWord")
    public ResponseEntity changePassWord(@RequestBody PasswordDTO pass) throws IOException {
        try{
            if(authService.changePassWord(pass).equals("ok"))
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity("PassWord Invalid", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity("PassWord Invalid", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateDetails")
    public ResponseEntity changePassWord(@RequestBody User user) throws IOException {
        try{
            if(userService.updateDetails(user).equals("ok"))
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity("PassWord Invalid", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity("PassWord Invalid", HttpStatus.NOT_FOUND);
        }
    }

}
