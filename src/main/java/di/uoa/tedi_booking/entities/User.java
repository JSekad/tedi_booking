package di.uoa.tedi_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@Table(schema="tedi", name="user")
@NamedNativeQueries({
        @NamedNativeQuery(name="usersMeAitimaEggrafis", query="select p.*,u.*,r.id,r.name as rolename,r.alias from user u inner join person p on u.id = p.id inner join rolesofusers rou on u.id = rou.iduser inner join role r on rou.idrole = r.id where rou.idRole = 3 and u.approved is null",resultClass = User.class)
})
public class User extends Person implements UserDetails{

    @Column(name = "username")
    @JsonIgnore
    private String userName;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    private Boolean approved;
    private OffsetDateTime dateApproved;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rolesofusers",
            joinColumns = {
                    @JoinColumn(name = "iduser",referencedColumnName = "id")
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
