package com.example.shopinglist.auth_model;

import com.example.shopinglist.models.GlobalSpisokModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

    private final String _NAME = "auth_user";
    private final String SEQUENCE_NAME = "sequence_auth_user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = _NAME, sequenceName = SEQUENCE_NAME, initialValue = 50)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_nickName")
    private String nickName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password_;

    @Column(name = "code_activation")
    private String codeActivation;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private RoleOfUser roleOfUser;

    @Column(name = "user_active")
    private Boolean active;

    @Column(name = "user_localCreated")
    private LocalDate localDateCreated;

    @JsonIgnore
    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private List<GlobalSpisokModel> globalSpisokModels;

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
        return this.active;
    }
}
