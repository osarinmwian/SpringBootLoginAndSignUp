package com.example.login_SignUp_Authentication.appuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;



@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity // Table representation in the database
public class AppUser implements UserDetails {
    // defining the userDetails class

    @Id
    @SequenceGenerator( // generating the student details by name, sequenceName and allocationSize
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue( // generating the student value by name
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    // Check if the account is secured
    private  Boolean locked;
    private Boolean enabled;

    // create an enum class for AppUserRole DECLARING A CONSTANT VARIABLES
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    public AppUser(String name,
                   String username,
                   String email, String password,
                   Boolean locked, Boolean enabled,
                   AppUserRole appUserRole) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority
                = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
