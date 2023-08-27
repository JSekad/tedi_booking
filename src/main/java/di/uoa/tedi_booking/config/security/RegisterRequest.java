package di.uoa.tedi_booking.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String surame;
    private String fathersName;
    private String mothersName;
    private String birthDate;
    private String idNumber;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private ArrayList<String> roles;
}
