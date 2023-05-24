package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(schema="booking_app", name="user")
public class User implements UserDetails{

    @Id
    @JsonIgnore
    @Column(name = "idPerson")
    private Integer id;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "idPerson")
    private Person person;

    @Column(name = "username")
    private String userName;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getAlias()));
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
