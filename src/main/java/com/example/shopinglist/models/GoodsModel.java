package com.example.shopinglist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private GlobalSpisokModel globalSpisokModel;
}
