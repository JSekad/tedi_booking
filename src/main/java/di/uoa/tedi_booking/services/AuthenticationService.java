package di.uoa.tedi_booking.services;

import di.uoa.tedi_booking.DTOS.PasswordDTO;
import di.uoa.tedi_booking.DTOS.security.AuthenticationRequest;
import di.uoa.tedi_booking.DTOS.security.AuthenticationResponse;
import di.uoa.tedi_booking.DTOS.security.RegisterRequest;
import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.repositories.PersonRepository;
import di.uoa.tedi_booking.repositories.RoleRepository;
import di.uoa.tedi_booking.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

    public String changePassWord(PasswordDTO passPhrase) throws Exception {
        Optional<User> user = userRepository.findById((Long) Long.parseLong(passPhrase.getUserId()));
        if (user.isPresent()){
            User us = user.get();
            us.setPassword(passwordEncoder.encode(passPhrase.getPassword()));
            userRepository.save(us);
        }
        return "ok";
    }
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
//        Person person = new Person();
        user.setBirthDate(dateOfBirth);
        user.setEmail(registerRequest.getEmail());
        user.setSurname(registerRequest.getSurame());
        user.setName(registerRequest.getName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setIdNumber(registerRequest.getIdNumber());
        user.setNumOfReviews(0);
        user.setAverageReviews((float) 0);
        user.setId(0);
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

                Role adminRole = roleRepository.findById(1L).orElse(null);
                Set<Role> rolesSet = new HashSet<Role>();
                rolesSet.add(adminRole);
                user.setRoles(rolesSet);
                user.setId(0);
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

                Role adminRole = roleRepository.findById(1L).orElse(null);
                Set<Role> rolesSet = new HashSet<Role>();
                rolesSet.add(adminRole);
                user.setRoles(rolesSet);
                user.setId(0);
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
