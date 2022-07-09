package com.example.shopinglist.auth_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "auth_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserModel implements UserDetails {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_nickName")
    private String nickName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password_;

    @Column(name = "user_role")
    private RoleOfUser roleOfUser;

    @Column(name = "user_active")
    private Boolean active;

    @Column(name = "user_localCreated")
    private LocalDate localDateCreated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(RoleOfUser.USER);
    }

    @Override
    public String getPassword() {
        return this.getPassword_();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
