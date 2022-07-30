package com.example.shopinglist.models.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String description;

    private String country;

    private String city;
}
