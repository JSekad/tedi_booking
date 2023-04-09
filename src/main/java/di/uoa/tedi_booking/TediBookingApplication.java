package di.uoa.tedi_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/*
    exclude={SecurityAutoConfiguration.class}
    gia na mh se phgainei sthn login page toy
    spring security
 */
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TediBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TediBookingApplication.class, args);
    }

}
