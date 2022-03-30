package com.example.shopboard.entity;

import javax.persistence.*;

@Table(name = "community_user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    //다수의 유저는 지역을 선택할 수 있다.
    //단방향 - 지역(Area)을 참조
    @ManyToOne(
        fetch = FetchType.LAZY,
        targetEntity = AreaEntity.class
    )
    @JoinColumn(name = "area_id")
    private AreaEntity residence; //fk

    private Boolean isShopOwner;  //특정 유저 판별


    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, AreaEntity residence, Boolean isShopOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.residence = residence;
        this.isShopOwner = isShopOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AreaEntity getResidence() {
        return residence;
    }

    public void setResidence(AreaEntity residence) {
        this.residence = residence;
    }

    public Boolean getIsShopOwner() {
        return isShopOwner;
    }

    public void setIsShopOwner(Boolean shopOwner) {
        isShopOwner = shopOwner;
    }
}
