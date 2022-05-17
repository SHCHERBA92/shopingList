package com.example.shopinglist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.aop.IntroductionAwareMethodMatcher;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RoleOfStatus roleOfStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "global_spisok_model_id")
    private GlobalSpisokModel globalSpisokModel;
}
