package com.example.shopinglist.models;

import com.example.shopinglist.auth_model.AuthUserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalSpisokModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "GlobalSpisokModel", sequenceName = "GlobalSpisokModel_seq", initialValue = 50)
    private Long id;

    @Column(name = "create_date")
    private LocalDate createAt = LocalDate.now();

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(unique = false, name = "name_store")
    private String storeName;

    @Column(unique = false, name = "name_shop_list")
    private String nameOfShopList;

    @OneToMany(mappedBy = "globalSpisokModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GoodsModel> goodsModels;

    @ManyToOne
    @JoinColumn(name = "user_id")
    AuthUserModel userModel;
}
