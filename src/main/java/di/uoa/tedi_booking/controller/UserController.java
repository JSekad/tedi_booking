package di.uoa.tedi_booking.controller;

import di.uoa.tedi_booking.DTOS.PasswordDTO;
import di.uoa.tedi_booking.DTOS.security.AuthenticationRequest;
import di.uoa.tedi_booking.entities.HostPhoto;
import di.uoa.tedi_booking.entities.RoomImageDefault;
import di.uoa.tedi_booking.services.AuthenticationService;
import di.uoa.tedi_booking.services.HostPhotoService;
import di.uoa.tedi_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import di.uoa.tedi_booking.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController extends GenericController<User>{

    private final UserService userService;
    private final AuthenticationService authService;

    private final HostPhotoService hostPhotoService;

    @Autowired
    public UserController(UserService service,AuthenticationService authService,HostPhotoService hostPhotoService){
        super(service);
        this.userService = service;
        this.authService = authService;
        this.hostPhotoService = hostPhotoService;
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

    @PostMapping("/approveUser")
    public ResponseEntity<?> approveUser(@RequestParam(value = "idUser") String idUser){
        int rowsUpdated = userService.approveUser(Integer.parseInt(idUser));
        if(rowsUpdated != 1)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User approval failed");
        else
            return ResponseEntity.status(HttpStatus.OK).body("User approval succeed");
    }

    @PostMapping(path = "/updateHostImage")
    public ResponseEntity<?> updateDefaultImage(@RequestParam(value = "idPerson") String id, @RequestParam(value ="image") MultipartFile img) throws IOException {
        HostPhoto newHostPhoto = new HostPhoto();
        newHostPhoto.setId(Long.parseLong(id));
        newHostPhoto.setImageFromMulitpart(img.getBytes());
        return this.hostPhotoService.update(newHostPhoto);
    }

}
