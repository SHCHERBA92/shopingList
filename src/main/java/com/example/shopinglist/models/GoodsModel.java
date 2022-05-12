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
    private Long id;

    private String name;

    @Column(name = "status")
    private RoleOfStatus roleOfStatus = RoleOfStatus.READY_BUY;

    @ManyToOne
    @JoinColumn(name = "global_spisok_model_id")
    private GlobalSpisokModel globalSpisokModel;
}
