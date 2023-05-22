package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.config.security.AuthenticationRequest;
import di.uoa.tedi_booking.config.security.AuthenticationResponse;
import di.uoa.tedi_booking.config.security.RegisterRequest;
import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.PersonRepository;
import di.uoa.tedi_booking.repositories.RoleRepository;
import di.uoa.tedi_booking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PersonService personService;

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(registerRequest.getBirthDate(), formatter);

        Optional<User> userTemp = userRepository.findAllByUserName(registerRequest.getUserName());
        if (userTemp.isPresent() && userTemp.get().getUsername().equals(registerRequest.getUserName())){
            return AuthenticationResponse.builder().token("User Exists").build();
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserName(registerRequest.getUserName());
        user.setRole(roleRepository.findById(3L).orElse(null));
        Person person = new Person();
        person.setBirthDate(dateOfBirth);
        person.setEmail(registerRequest.getEmail());
        person.setSurname(registerRequest.getSurame());
        person.setName(registerRequest.getName());
        person.setFathersName(registerRequest.getFathersName());
        person.setMothersName(registerRequest.getMothersName());
        person.setPhoneNumber(registerRequest.getPhoneNumber());
        person.setIdNumber(registerRequest.getIdNumber());
        user.setPerson(person);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        String jwtToken = "";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            User user = userRepository.findAllByUserName(authenticationRequest.getUsername()).orElseThrow();
            jwtToken = jwtService.generateToken(user);
        } catch (AuthenticationException ex) {

            String errorMessage = ex.getMessage();
            System.out.println("Error message: " + errorMessage);
            jwtToken = errorMessage;

        }catch (Throwable e){

            e.printStackTrace();

        }
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
