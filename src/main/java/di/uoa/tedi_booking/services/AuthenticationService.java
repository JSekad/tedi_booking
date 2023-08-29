package di.uoa.tedi_booking.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import di.uoa.tedi_booking.config.security.AuthenticationRequest;
import di.uoa.tedi_booking.config.security.AuthenticationResponse;
import di.uoa.tedi_booking.config.security.RegisterRequest;
import di.uoa.tedi_booking.entities.Person;
import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.PersonRepository;
import di.uoa.tedi_booking.repositories.RoleRepository;
import di.uoa.tedi_booking.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
            return AuthenticationResponse.builder().accessToken("UserModel Exists").build();
        }

        User user = new User();
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserName(registerRequest.getUserName());
        Set<Role> roles = new HashSet<Role>();
        for (String str: registerRequest.getRoles()){
            if (str.equals("owner")){
               roles.add(roleRepository.findById(3L).orElse(null));
            }
            if (str.equals("user")){
                roles.add(roleRepository.findById(2L).orElse(null));
            }
            if (str.equals("admin")){
                roles.add(roleRepository.findById(1L).orElse(null));
            }
        }
        user.setRoles(roles);
        Person person = new Person();
        person.setBirthDate(dateOfBirth);
        person.setEmail(registerRequest.getEmail());
        person.setSurname(registerRequest.getSurame());
        person.setName(registerRequest.getName());
        person.setPhoneNumber(registerRequest.getPhoneNumber());
        person.setIdNumber(registerRequest.getIdNumber());
        user.setPerson(person);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        String jwtToken = "";
        User user = null;
        try {

            if (authenticationRequest.getUsername().equals("admin") && authenticationRequest.getPassword().equals("admin")){
                user = new User();
                user.setUserName("admin");
                user.setId(0);

                Role adminRole = roleRepository.findById(1L).orElse(null);
                Set<Role> rolesSet = new HashSet<Role>();
                rolesSet.add(adminRole);
                user.setRoles(rolesSet);
                user.setPerson(null);
            }else{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
                user = userRepository.findAllByUserName(authenticationRequest.getUsername()).orElseThrow();
            }
            jwtToken = jwtService.generateToken(user);
        } catch (AuthenticationException ex) {

            String errorMessage = ex.getMessage();
            System.out.println("Error message: " + errorMessage);
            jwtToken = errorMessage;

        }catch (Throwable e){

            e.printStackTrace();

        }
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse refreshToken(
            HttpServletRequest request) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return AuthenticationResponse.builder()
                    .accessToken("No Bearer Auth")
                    .build();
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);


        if (userEmail != null) {
            User user = null;
            if (userEmail.equals("admin")){
                user = new User();
                user.setUserName("admin");
                user.setId(0);

                Role adminRole = roleRepository.findById(1L).orElse(null);
                Set<Role> rolesSet = new HashSet<Role>();
                rolesSet.add(adminRole);
                user.setRoles(rolesSet);
                user.setPerson(null);
            }else {
                user = userRepository.findAllByUserName(userEmail)
                        .orElseThrow();
            }
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }
        return AuthenticationResponse.builder()
                .accessToken("No username")
                .build();
    }

}
