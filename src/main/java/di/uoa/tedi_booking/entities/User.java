package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="user")
public class User implements UserDetails{

    @JsonIgnore
    @Id
    @Column(name = "idPerson")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "idPerson")
    private Person person;

    @Column(name = "username")
    @JsonIgnore
    private String userName;

    @Column(name = "password")
    @JsonIgnore
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rolesofusers",
            joinColumns = {
                    @JoinColumn(name = "iduser")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idrole")
            }
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authString = "";
        for (Role r: roles) {
            authString += r.getAlias();
            authString += ',';
        }
        if (authString.endsWith(",")) {
            authString = authString.substring(0, authString.length() - 1);
        }
        return List.of(new SimpleGrantedAuthority(authString));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
